package com.example.usertask.controller;

import com.example.usertask.kafka.payload.Student;
import com.example.usertask.kafka.producer.JsonKafkaProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/kafka")
@RequiredArgsConstructor
public class JsonMessageController {

    private final JsonKafkaProducer kafkaProducer;


    @PostMapping("/sample-message/publish")
    ResponseEntity<String> publishSample(@RequestBody String message)
    {
        kafkaProducer.send(message);
        return ResponseEntity.ok("Message sent");

    }
}
