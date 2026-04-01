package com.fitness.artificialService.services;

import com.fitness.artificialService.models.Activity;
import com.fitness.artificialService.models.Recommendation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ActivityAiService {
    private final GeminiService geminiService;
    public Recommendation generateRecommendation(Activity activity) {
        String prompt = createPromptForActivity(activity);
        String aiResponse = geminiService.getGeminiAnswer(prompt);
        // log.info("AI Response in Activity Service is {}", aiResponse);
        return processAiResponse(activity, aiResponse);
    }

    private Recommendation processAiResponse(Activity activity,  String aiResponse) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(aiResponse);

            JsonNode textNode = rootNode.get("candidates")
                    .get(0)
                    .get("content")
                    .get("parts")
                    .get(0)
                    .get("text");
            String jsonContent = textNode.asString()
                    .replaceAll("```json\\n", "")
                    .replaceAll("\\n```", "")
                    .trim();
            log.info("Parsed Json Data {}", jsonContent);
            JsonNode analysisJson =  objectMapper.readTree(jsonContent);
            JsonNode analysisNode = analysisJson.path("analysis");

            StringBuilder fullAnalysis = new StringBuilder();
            addAnalysisSection(fullAnalysis, analysisNode, "overall", "Overall: ");
            addAnalysisSection(fullAnalysis, analysisNode, "pace", "Pace: ");
            addAnalysisSection(fullAnalysis, analysisNode, "heartRate", "HeartRate: ");
            addAnalysisSection(fullAnalysis, analysisNode, "caloriesBurnt", "CaloriesBurnt: ");

            List<String> improvements = extractImprovemnts(analysisJson.path("improvements"));
            List<String> suggestions = extractSuggestions(analysisJson.path("suggestions"));
            List<String> safety = extractSafetyGuidelines(analysisJson.path("safety"));

            return Recommendation.builder()
                    .activityId(activity.getId())
                    .userId(activity.getUserId())
                    .activityType(activity.getType())
                    .recommendation(fullAnalysis.toString().trim())
                    .imporovements(improvements)
                    .suggestions(suggestions)
                    .safetyMeasures(safety)
                    .createdAt(LocalDateTime.now())
                    .build();

        }catch (Exception e){
            log.error("Error processing Json Data {}", aiResponse, e);
            return createDefaultRecommendation(activity);
        }
    }

    private Recommendation createDefaultRecommendation(Activity activity) {
        return Recommendation.builder()
                .activityId(activity.getId())
                .userId(activity.getUserId())
                .activityType(activity.getType())
                .recommendation("Unable to generate detailed analysis")
                .imporovements(Collections.singletonList("Continue with your current workouts"))
                .suggestions(Collections.singletonList("Consider consulting a gym expert"))
                .safetyMeasures(Arrays.asList(
                        "Always warm up before exercise",
                        "Stay hydrated",
                        "Listen to your body"
                ))
                .createdAt(LocalDateTime.now())
                .build();
    }

    private List<String> extractSafetyGuidelines(JsonNode safetyNode) {
        List<String> safetyGuidelines = new ArrayList<>();
        if (safetyNode.isArray()) {
            safetyNode.forEach(node -> {safetyGuidelines.add(node.asString());
            });
        }
        return safetyGuidelines.isEmpty() ? Collections.singletonList("Follow general safety guidelines!") : safetyGuidelines;
    }

    private List<String> extractSuggestions(JsonNode suggestionsNode) {
        List<String> suggestions = new ArrayList<>();
        if (suggestionsNode.isArray()){
            suggestionsNode.forEach(suggestion -> {
                String workout = suggestion.path("workout").asString();
                String description = suggestion.path("description").asString();
                suggestions.add(String.format("%s (%s)", workout, description));
            });
        }
        return suggestions.isEmpty()? Collections.singletonList("No Suggestions"): suggestions;
    }

    private List<String> extractImprovemnts(JsonNode improvementsNode) {
        List <String> improvements = new ArrayList<>();
        if  (improvementsNode.isArray()) {
            improvementsNode.forEach(improvement -> {
                String area = improvement.path("area").asString();
                String detail = improvement.path("detail").asString();
                improvements.add(String.format("%s :%s", area, detail));
            });
        }
        return improvements.isEmpty()? Collections.singletonList("No specific improvements provided!"): improvements;
    }

    private void addAnalysisSection(StringBuilder fullAnalysis, JsonNode analysisNode, String key, String prefix) {
        if (!analysisNode.path(key).isMissingNode()) {
            fullAnalysis.append(prefix)
                    .append(analysisNode.path(key).asString())
                    .append("\n\n");
        }
    }

    private String createPromptForActivity(Activity activity) {
        return String.format("""
                Analyze this fitness activity and provide detailed recommendation in the following exact JSON format:
                {
                    "analysis": {
                        "overall": "Overall analysis here",
                        "pace": "Pace analysis here",
                        "heartRate": "Heart rate analysis here",
                        "caloriesBurnt": "Calories burnt analysis here"
                    },
                    "improvements": [
                        {
                            "area": "Area name",
                            "recommendation": "Detailed recommendation"
                        }
                    ],
                    "suggestions": [
                        {
                            "workout": "Workout Name"
                            "description": "Detailed workout description"
                        }
                    ],
                    "safety": [
                        "Safety point 1",
                        "Safety point 2"
                    ]
                }
                Analyze this activity:
                Activity type: %s
                Duration: %d minutes
                Calories burnt: %d
                Additional Metrics: %s
                
                Provide detailed analysis focussing on performance, improvements, next workout suggestions and safety guidelines.
                Ensure the response follows the EXACT JSON format shown above.
                """,
                    activity.getType(),
                    activity.getDuration(),
                    activity.getCaloriesBurnt(),
                    activity.getAdditionalMetrics()
                );
    }
}
