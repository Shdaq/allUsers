package com.example.usertask.kafka.producer;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaProducer {
    private final KafkaTemplate<String, String> kafkaTemplate;
    private static final Logger LOGGER = LoggerFactory.getLogger((KafkaProducer.class));

    public void send(String message) {
        LOGGER.info(String.format("Message sent %s", message));
        kafkaTemplate.send("myTopic", message);

    }
}
