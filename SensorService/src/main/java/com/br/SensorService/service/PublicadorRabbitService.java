package com.br.SensorService.service;


import com.br.SensorService.model.LeituraSilo;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class PublicadorRabbitService {
    private final RabbitTemplate rabbitTemplate;

    @Value("${queue.nome}")
    private String nomeFila;
    public PublicadorRabbitService(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }
    public void enviar(LeituraSilo leitura) {
        rabbitTemplate.convertAndSend(nomeFila, leitura);
        System.out.println("Mensagem enviada para fila: " + leitura);
    }
}
