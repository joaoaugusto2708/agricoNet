package com.br.SensorService.service;

import com.br.SensorService.model.LeituraSilo;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class LeitorArquivoService {
    private final PublicadorRabbitService publicadorRabbitService;
    public LeitorArquivoService(PublicadorRabbitService publicadorRabbitService) {
        this.publicadorRabbitService = publicadorRabbitService;
    }
    @Scheduled(fixedRate = 60000)
    public List<LeituraSilo> lerArquivo() {
        File file = new File("/data/silos.txt");
        List<LeituraSilo> leituras = new ArrayList<>();

        try(BufferedReader br = new BufferedReader(new FileReader(file))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                LeituraSilo leituraSilo = parseLinha(linha);
                leituras.add(leituraSilo);
                publicadorRabbitService.enviar(leituraSilo);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return leituras;
    }

    private LeituraSilo parseLinha(String linha) {
        String siloId = linha.substring(0,5).trim();
        String dataStr = linha.substring(5,14).trim();
        String horaStr = linha.substring(14,20).trim();
        String tempStr = linha.substring(20,25).trim();
        String status = linha.substring(25,27).trim();

        LocalDate data = LocalDate.of(
                Integer.parseInt(dataStr.substring(0,4)),
                Integer.parseInt(dataStr.substring(4,6)),
                Integer.parseInt(dataStr.substring(6,8))
        );
        LocalTime hora = LocalTime.of(
                Integer.parseInt(horaStr.substring(0,2)),
                Integer.parseInt(horaStr.substring(2,4))
        );
        double temperatura = Integer.parseInt(tempStr)/10.0;

        LeituraSilo leituraSilo = new LeituraSilo();
        leituraSilo.setSiloId(siloId);
        leituraSilo.setData(data);
        leituraSilo.setHora(hora);
        leituraSilo.setTemperatura(temperatura);
        leituraSilo.setStatusSensor(status);
        return leituraSilo;
    }


}
