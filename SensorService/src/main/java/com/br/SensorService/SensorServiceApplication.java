package com.br.SensorService;

import com.br.SensorService.service.LeitorArquivoService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SensorServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SensorServiceApplication.class, args);
	}
	@Bean
	CommandLineRunner runner(LeitorArquivoService leitorArquivoService) {
		return args -> {
			String caminho = "C:\\Users\\joaor\\OneDrive\\Área de Trabalho\\Silos\\silos.txt";
			while (true) {
				leitorArquivoService.lerArquivo(caminho);
				Thread.sleep(60000);
			}
		};
	}

}
