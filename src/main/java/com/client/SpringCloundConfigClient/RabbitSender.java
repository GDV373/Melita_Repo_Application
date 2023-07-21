package com.client.SpringCloundConfigClient;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class RabbitSender {

    private final ObjectMapper mapper = new ObjectMapper();

    private final AmqpTemplate amqpTemplate;

    public void sendMessage (NewOrderEvent newOrderEvent) throws JsonProcessingException {
        log.info(String.format("Message sent -> %s", newOrderEvent));
        amqpTemplate.convertAndSend("onNewOrderEvent-in-0", "orders", mapper.writeValueAsString(newOrderEvent));
    }
}