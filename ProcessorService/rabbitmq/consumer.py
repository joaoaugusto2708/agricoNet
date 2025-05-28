from rabbitmq.connection import get_connection
import json

def consumir_mensagens(queue_name):
    connection = get_connection()
    channel = connection.channel()

    channel.queue_declare(queue=queue_name, durable=True)

    mensagens = []

    def callback(ch, method, properties, body):
        mensagem = json.loads(body)
        mensagens.append(mensagem)
        ch.basic_ack(delivery_tag=method.delivery_tag)

    for method_frame, properties, body in channel.consume(queue=queue_name, inactivity_timeout=5):
        if method_frame:
            callback(channel, method_frame, properties, body)
        else:
            break  # Sai após timeout

    channel.cancel()
    connection.close()

    return mensagens
