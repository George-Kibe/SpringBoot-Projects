package com.fitness.artificialService.services;
import com.fitness.artificialService.models.Activity;
import com.fitness.artificialService.models.Recommendation;
import com.fitness.artificialService.repositories.RecommendationRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class ActivityMessageListener {
    @Value("${rabbitmq.queue.name}")
    private  String queueName;
    private final ActivityAiService activityAiService;
    private final RecommendationRepository recommendationRepository;

    // @RabbitListener(queues = queueName)
    @RabbitListener(queues = "${rabbitmq.queue.name}")
    public void processActivity(Activity activity){
        log.info("Processing activity for AI processing for activity id {}", activity.getId());
        Recommendation recommendation = activityAiService.generateRecommendation(activity);
        //log.info("Generated recommendation : {}", recommendation);
        recommendationRepository.save(recommendation);
    }
}
