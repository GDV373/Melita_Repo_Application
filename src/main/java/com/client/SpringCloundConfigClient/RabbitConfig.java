package com.client.SpringCloundConfigClient;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {
    @Bean
    Queue newOrderEventQueue() {
        return new Queue("orderEventQueue", false);
    }

    @Bean
    TopicExchange exchange() {
        return new TopicExchange("onNewOrderEvent-in-0");
    }

    @Bean
    Binding newOrderBinding(Queue orderEventQueue, TopicExchange exchange) {
        return BindingBuilder.bind(orderEventQueue).to(exchange).with("orders");
    }
}
