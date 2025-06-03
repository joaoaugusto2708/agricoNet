package com.br.SensorService.service;

import com.br.SensorService.model.LeituraSilo;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class LeitorArquivoService {

    private final PublicadorRabbitService publicadorRabbitService;

    private static final String ARQUIVO_SILOS = "/data/silos.txt";
    private static final String ARQUIVO_BACKUP = "/data/silosbkp.txt";
    private static final String ARQUIVO_REPROCESSO = "/data/silosReprocessamento.txt";

    public LeitorArquivoService(PublicadorRabbitService publicadorRabbitService) {
        this.publicadorRabbitService = publicadorRabbitService;
    }

    @Scheduled(fixedRate = 60000)
    public void processarLeituras() {
        processarArquivo(ARQUIVO_SILOS);
        processarArquivo(ARQUIVO_REPROCESSO);
    }

    private void processarArquivo(String caminhoArquivo) {
        File arquivo = new File(caminhoArquivo);
        if (!arquivo.exists()) {
            System.out.println("Arquivo não encontrado: " + caminhoArquivo);
            return;
        }

        List<String> linhasRestantes = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(arquivo))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                try {
                    LeituraSilo leitura = parseLinha(linha);
                    publicadorRabbitService.enviar(leitura);

                    salvarLinha(ARQUIVO_BACKUP, linha);

                } catch (Exception e) {
                    e.printStackTrace();
                    salvarLinha(ARQUIVO_REPROCESSO, linha);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        limparArquivo(arquivo);
    }

    private LeituraSilo parseLinha(String linha) {
        try {
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
                    Integer.parseInt(horaStr.substring(2,4)),
                    0
            );

            double temperatura = Integer.parseInt(tempStr) / 10.0;

            LeituraSilo leitura = new LeituraSilo();
            leitura.setSiloId(siloId);
            leitura.setData(data);
            leitura.setHora(hora);
            leitura.setTemperatura(temperatura);
            leitura.setStatusSensor(status);

            return leitura;
        } catch (Exception e) {
            throw new IllegalArgumentException("Erro ao fazer parse da linha: " + linha, e);
        }
    }

    private void salvarLinha(String caminhoArquivo, String linha) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(caminhoArquivo, true))) {
            bw.write(linha);
            bw.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void limparArquivo(File arquivo) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(arquivo, false))) {
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
