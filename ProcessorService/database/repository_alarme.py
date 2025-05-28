from database.connection import get_connection

def inserir_alarme(silo_id, tipo, descricao, data_hora):
    connection = get_connection()
    cursor = connection.cursor()

    sql="""
    INSERT INTO alarme (silo_id, tipo, descricao, data_hora)
    VALUES (%s, %s, %s, %s)
    """

    try:
        cursor.execute(sql, (silo_id, tipo, descricao, data_hora))
        connection.commit()
        print(f"🚨 Alarme registrado: {tipo} - {descricao}")
    except Exception as ex:
        print(f"❌ Erro ao inserir alarme: {e}")
        connection.rollback()
    finally:
        cursor.close()
        connection.close()