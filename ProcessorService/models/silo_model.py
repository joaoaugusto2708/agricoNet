from dataclasses import dataclass

@dataclass
class SiloModel:
    id: int
    nome: str
    localidade: str
    modelo: str