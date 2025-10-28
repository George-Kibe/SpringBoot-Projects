package com.kibe.QuizApplication.controllers;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class KafkaProducerController {
    private final KafkaTemplate<String,  String> kafkaTemplate;
    public KafkaProducerController(KafkaTemplate<String,String> KafkaTemplate) {
        this.kafkaTemplate = KafkaTemplate;
    }
    @PostMapping("/send")
    public String sendMessage(@RequestParam String message) {
        kafkaTemplate.send("my-topic", message);
        return  "Message sent successfully" + message;
    }
}
