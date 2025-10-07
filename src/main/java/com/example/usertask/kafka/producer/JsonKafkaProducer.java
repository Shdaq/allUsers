package com.example.usertask.kafka.producer;

import com.example.usertask.kafka.payload.Student;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JsonKafkaProducer {
    private final KafkaTemplate<String, String> kafkaTemplate;

    private static final Logger LOGGER = LoggerFactory.getLogger((JsonKafkaProducer.class));

    public void send(String data) {
        LOGGER.info(String.format("Message sent -> %s", data));
        kafkaTemplate.send("jsonTopic", data);
    }
}
