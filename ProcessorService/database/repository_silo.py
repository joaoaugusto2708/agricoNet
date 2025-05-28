from database.connection import get_connection

def buscar_silo_por_id(id):
    connection = get_connection()
    cursor = connection.cursor()

    sql= "SELECT * FROM sensores.silo WHERE id = %s"
    cursor.execute(sql,(id,))
    result = cursor.fetchone()

    cursor.close()
    connection.close()

    return result