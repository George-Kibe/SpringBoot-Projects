package com.kibe.QuizApplication;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaTopicConfig {
    @Bean
    public NewTopic createTopic() {
        return new NewTopic("my-topic-new-two", 1, (short) 1);
    }
}
