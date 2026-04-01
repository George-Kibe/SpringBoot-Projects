package com.fitness.artificialService.services;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class GeminiService {
    private final WebClient webClient;

    @Value("${gemini.api.url}")
    private String geminiApiUrl;
    @Value("${gemini.api.key}")
    private String geminiApiKey;

    public GeminiService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.build();
    }
    public String getGeminiAnswer(String question){
        // log.info("Gemini API URL: " + geminiApiUrl);
        // log.info("Gemini API KEY: " + geminiApiKey);
        Map<String,Object> requestBody = Map.of("contents",new Object[]{
                Map.of("parts",new Object[]{
                        Map.of("text",question)
                })
        });
        String response = webClient.post().uri(geminiApiUrl+ "?key="+geminiApiKey)
                .header("Content-Type", "application/json")
                .bodyValue(requestBody)
                .retrieve()
                .bodyToMono(String.class)
                .block();
        // log.info("Gemini API RESPONSE: " + response);
        return response;
    }
}
