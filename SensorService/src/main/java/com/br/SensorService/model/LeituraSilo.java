package com.br.SensorService.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LeituraSilo {
    private String siloId;
    private LocalDate data;
    private LocalTime hora;
    private double temperatura;
    private String statusSensor;
}
