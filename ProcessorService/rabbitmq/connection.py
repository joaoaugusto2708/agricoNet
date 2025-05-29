import os
import pika
import time
from dotenv import load_dotenv

load_dotenv()

def get_connection(max_retries=10, delay=5):
    print("RABBITMQ_HOST:", os.getenv('RABBITMQ_HOST'))
    print("RABBITMQ_PORT:", os.getenv('RABBITMQ_PORT'))
    print("RABBITMQ_USER:", os.getenv('RABBITMQ_USER'))
    print("RABBITMQ_PASS:", os.getenv('RABBITMQ_PASS'))

    credentials = pika.PlainCredentials(
        os.getenv('RABBITMQ_USER'),
        os.getenv('RABBITMQ_PASS')
    )
    parameters = pika.ConnectionParameters(
        host=os.getenv('RABBITMQ_HOST'),
        port=int(os.getenv('RABBITMQ_PORT', 5672)),
        credentials=credentials,
    )

    for attempt in range(max_retries):
        try:
            print(f"🔌 Tentando conectar ao RabbitMQ (tentativa {attempt+1})...")
            return pika.BlockingConnection(parameters)
        except pika.exceptions.AMQPConnectionError as e:
            print(f"⏳ Conexão falhou: {e}. Tentando novamente em {delay} segundos...")
            time.sleep(delay)

    raise Exception("❌ Não foi possível conectar ao RabbitMQ após várias tentativas.")
