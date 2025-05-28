import os
import pika
from dotenv import load_dotenv

load_dotenv()

def get_connection():
    credentials = pika.PlainCredentials(
        os.getenv('RABBITMQ_USER'),
        os.getenv('RABBITMQ_PASS')
    )
    parameters = pika.ConnectionParameters(
        host=os.getenv('RABBITMQ_HOST'),
        port=int(os.getenv('RABBITMQ_PORT')),
        credentials=credentials,
    )
    return pika.BlockingConnection(parameters)