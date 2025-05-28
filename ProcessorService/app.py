import time
from rabbitmq.consumer import consumir_mensagens
from services.processar_service import processar_mensagens
import os
from dotenv import load_dotenv

load_dotenv()

queue_name = os.getenv('RABBITMQ_QUEUE')

def main():
    while True:
        print("🔍 Buscando mensagens...")
        mensagens = consumir_mensagens(queue_name)
        if mensagens:
            print(f"📦 {len(mensagens)} mensagens recebidas.")
            processar_mensagens(mensagens)
        else:
            print("📭 Nenhuma mensagem encontrada.")

        print("🕑 Aguardando 60 segundos...\n")
        time.sleep(60)

if __name__ == '__main__':
    main()