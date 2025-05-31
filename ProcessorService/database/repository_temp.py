from database.connection import get_connection

def inserir_temperatura(silo_id, temperatura, data_hora):
    conn = get_connection()
    cursor = conn.cursor()
    sql = """
    INSERT INTO temperatura (siloId, temperaturaSilo, dataMedicao)
    VALUES (%s, %s, %s)
    """
    try:
        cursor.execute(sql, (silo_id, temperatura, data_hora))
        conn.commit()
    except Exception as e:
        print(f"❌ Erro ao inserir temperatura: {e}")
        conn.rollback()
    finally:
        cursor.close()
        conn.close()