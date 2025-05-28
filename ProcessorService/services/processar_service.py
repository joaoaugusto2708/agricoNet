from rabbitmq.consumer import consumir_mensagens
from database.repository_temp import inserir_temperatura
from database.repository_alarme import inserir_alarme
from database.repository_silo import buscar_silo_por_id
from datetime import datetime

def processar_mensagens(mensagens):
    for msg in mensagens:
        silo_id = msg.get("siloId")
        data = msg.get("data")
        hora = msg.get("hora")
        temperatura = msg.get("temperatura")
        status_sensor = msg.get("statusSensor")
        data_hora = construir_data_hora(data, hora)

        silo = buscar_silo_por_id(silo_id)
        if not silo:
            print(f"❌ Silo {silo_id} não encontrado. Ignorando mensagem.")
            continue

        if status_sensor != "N":
            inserir_alarme(silo[0], "SENSOR_NAO_FUNCIONAL", "Sensor com falha.", data_hora)
            continue

        if temperatura is None:
            inserir_alarme(silo[0], "SENSOR_NAO_FUNCIONAL", "Sem leitura de temperatura.", data_hora)
            continue

        inserir_temperatura(silo_id, temperatura, data_hora)

        if temperatura < 10:
            inserir_alarme(silo[0], "TEMPERATURA_BAIXA", f"Temperatura {temperatura}°C abaixo do limite.", data_hora)
        elif temperatura > 50:
            inserir_alarme(silo[0], "TEMPERATURA_ALTA", f"Temperatura {temperatura}°C acima do limite.", data_hora)

        print(f"✅ Dados do silo {silo_id} processados com sucesso.")

def construir_data_hora(data_list, hora_list):
    """
    Constrói um objeto datetime a partir dos arrays de data e hora.
    """
    try:
        ano, mes, dia = data_list
        hora, minuto = hora_list
        return datetime(ano, mes, dia, hora, minuto)
    except Exception as e:
        print(f"Erro ao construir data/hora: {e}")
        return None