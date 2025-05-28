from dataclasses import dataclass
from datetime import datetime

@dataclass
class Alarme:
    id: int
    silo_id: int
    tipo: str
    descricao: str
    data_hora: datetime