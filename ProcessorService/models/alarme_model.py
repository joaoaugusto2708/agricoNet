from dataclasses import dataclass
from datetime import datetime

@dataclass
class Alarme:
    id: int
    siloId : int
    tipo: str
    descricao: str
    dataHoraAlarme: datetime