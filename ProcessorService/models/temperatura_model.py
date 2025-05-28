from dataclasses import dataclass
from datetime import datetime

@dataclass
class Temperatura:
    id: int
    silo_id: int
    temperatura: float
    data_hora: datetime