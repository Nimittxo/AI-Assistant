import os
import tensorflow as tf

os.environ['TF_CPP_MIN_LOG_LEVEL'] = '3' #Setting the @LOG_LEVEL to 3 for extreme suppressions!

#setting up the TensorFlow warnings to redirect and write up to a file: 
TF_log_file = 'tensorflow_logs.txt'

print("Chnaging the configuration of TF!")