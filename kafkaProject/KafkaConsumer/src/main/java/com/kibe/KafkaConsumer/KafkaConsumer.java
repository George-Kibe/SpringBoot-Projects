package com.kibe.KafkaConsumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumer {
//    @KafkaListener(topics = "my-topic", groupId = "my-new-group")
//    public void listen(String message){
//        System.out.println("Received Message: "+message);
//    }
//
//    @KafkaListener(topics = "my-topic", groupId = "my-new-group-two")
//    public void listenTwo(String message){
//        System.out.println("Received Message Duplicate: " + message);
//    }

    @KafkaListener(topics = "my-topic", groupId = "my-third-groud-id")
    public void listenRiderLocation(RiderLocation riderLocation){
        System.out.println("Received New Rider Location: " + riderLocation);
    }
}

