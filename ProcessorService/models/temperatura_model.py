from dataclasses import dataclass
from datetime import datetime

@dataclass
class Temperatura:
    id: int
    siloId: int
    temperaturaSilo: float
    dataMedicao: datetime