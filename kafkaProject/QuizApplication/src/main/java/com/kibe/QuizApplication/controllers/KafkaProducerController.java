package com.kibe.QuizApplication.controllers;

import com.kibe.QuizApplication.RiderLocation;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class KafkaProducerController {
    private final KafkaTemplate<String,  RiderLocation> kafkaTemplate;
    public KafkaProducerController(KafkaTemplate<String,RiderLocation> KafkaTemplate) {
        this.kafkaTemplate = KafkaTemplate;
    }
    @PostMapping("/send")
    public String sendMessage(@RequestParam String message) {
        RiderLocation riderLocation = new RiderLocation("rider6276832884", "Sample George Test", 0.672773, 35.253637);
        kafkaTemplate.send("my-topic", riderLocation);
        return  "Message sent successfully" + riderLocation;
    }
}
