package com.kibe.QuizApplication.controllers;

import com.kibe.QuizApplication.RiderLocation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Supplier;

@Configuration
public class KafkaProducerStreams {
    @Bean
    public Supplier<RiderLocation> sendRiderLocation() {
        return () -> {
            RiderLocation riderLocation = new RiderLocation("riderId123425", "George Sample", 0.2435562, 32.736364);
            System.out.println("Sending Rider Location: " + riderLocation);
            return riderLocation;
        };
    }
}
