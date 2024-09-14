import os
import tensorflow as tf

os.environ['TF_CPP_MIN_LOG_LEVEL'] = '0' #Setting the @LOG_LEVEL to 3 for extreme suppressions!

#setting up the TensorFlow warnings to redirect and write up to a file: 
TF_log_file = 'tensorflow_logs.txt'
tf.get_logger().setLevel('ERROR') #lOGGING DOWN THE ERROR AND WARNING MESSAGES: 

import logging
logging.basicConfig(filename=TF_log_file, level=logging.ERROR, format='%(asctime)s - %(levelname)s - %(message)s')
logger = tf.get_logger()
logger.setLevel(logging.ERROR)
print("TensorFlow logging configured.")
print(tf.__version__)