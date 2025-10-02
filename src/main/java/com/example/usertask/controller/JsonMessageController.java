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

    private JsonKafkaProducer kafkaProducer;

    @PostMapping("/publish")
    public ResponseEntity<String> publish(@RequestParam Student student) {
        kafkaProducer.send(student);
        return ResponseEntity.ok("Hson message sent to kafka topic");


    }
}
