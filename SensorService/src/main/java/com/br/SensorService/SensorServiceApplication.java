package com.br.SensorService;

import com.br.SensorService.service.LeitorArquivoService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SensorServiceApplication {
	public static void main(String[] args) {
		SpringApplication.run(SensorServiceApplication.class, args);
	}
}
