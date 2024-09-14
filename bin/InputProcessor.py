import os
import random
import json
import pickle
import numpy as np
import sys

os.environ['TF_CPP_MIN_LOG_LEVEL'] = '3'

import nltk
from nltk.stem import WordNetLemmatizer
from tensorflow.keras.models import load_model

import configure_tf_logging

lemmatizer = WordNetLemmatizer()

script_dir = os.path.dirname(os.path.abspath(__file__))
intents_path = os.path.join(script_dir, 'intents.json')
words_path = os.path.join(script_dir, 'words.pkl')
classes_path = os.path.join(script_dir, 'classes.pkl')
model_path = os.path.join(script_dir, 'chatbot_model.keras')

# Check if files exist and handle errors
if not os.path.exists(intents_path):
    raise FileNotFoundError(f"{intents_path} file not found!")
if not os.path.exists(words_path):
    raise FileNotFoundError(f"{words_path} file not found!")
if not os.path.exists(classes_path):
    raise FileNotFoundError(f"{classes_path} file not found!")
if not os.path.exists(model_path):
    raise FileNotFoundError(f"{model_path} file not found!")

# Load data
with open(intents_path, 'r', encoding='utf-8') as file:
    intents = json.load(file)

words = pickle.load(open(words_path, 'rb'))
classes = pickle.load(open(classes_path, 'rb'))
model = load_model(model_path)

def clean_up_sentence(sentence):
    sentence_words = nltk.word_tokenize(sentence)
    sentence_words = [lemmatizer.lemmatize(word) for word in sentence_words]
    return sentence_words

def bag_of_words(sentence):
    sentence_words = clean_up_sentence(sentence)
    bag = [0] * len(words)
    for w in sentence_words:
        for i, word in enumerate(words):
            if word == w:
                bag[i] = 1
    return np.array(bag)

def predict_class(sentence):
    bow = bag_of_words(sentence)
    res = model.predict(np.array([bow]))[0]
    ERROR_THRESHOLD = 0.25
    results = [[i, r] for i, r in enumerate(res) if r > ERROR_THRESHOLD]
    results.sort(key=lambda x: x[1], reverse=True)
    return_list = []
    for r in results:
        return_list.append({'intent': classes[r[0]], 'probability': str(r[1])})
    return return_list

def get_response(intents_list, intents_json):
    tag = intents_list[0]['intent']
    list_of_intents = intents_json['intents']
    for i in list_of_intents:
        if i['tag'] == tag:
            result = random.choice(i['responses'])
            break
    return result

def main():
    sys.stdout.reconfigure(encoding='utf-8')
    sys.stderr.reconfigure(encoding='utf-8')
    if len(sys.argv) > 1:
        message = ' '.join(sys.argv[1:])
        ints = predict_class(message)
        res = get_response(ints, intents)
        print(res)
    else:
        print("No input provided")

if __name__ == "__main__":
    main()
