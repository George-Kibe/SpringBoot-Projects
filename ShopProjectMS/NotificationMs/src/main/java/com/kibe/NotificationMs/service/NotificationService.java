package com.kibe.NotificationMs.service;

import com.kibe.order.event.OrderPlacedEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationService {
    private final JavaMailSender javaMailSender;

    @KafkaListener(topics ="order-placed", groupId = "notificationMs")
    public void listen(OrderPlacedEvent orderPlacedEvent){
        log.info("Received a new order with details OrderPlacedEvent: {}", orderPlacedEvent);
        //send mail to the customer/ admin
//        MimeMessagePreparator messagePreparator = mimeMessage -> {
//            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
//            messageHelper.setFrom("buenasconsultants@email.com");
//            messageHelper.setTo(orderPlacedEvent.getEmail());
//            messageHelper.setSubject(String.format("Your Order with OrderNumber %s is placed successfully", orderPlacedEvent.getOrderNumber()));
//            messageHelper.setText(String.format("""
//                            Hi %s,%s
//
//                            Your order with order number %s is now placed successfully.
//
//                            Best Regards
//                            Spring Shop
//                            """,
//                    // orderPlacedEvent.getFirstName(),
//                    // orderPlacedEvent.getLastName(),
//                    orderPlacedEvent.getOrderNumber()));
//        };
//        try {
//            javaMailSender.send(messagePreparator);
//            log.info("Order Notification email sent!!");
//        } catch (MailException e) {
//            log.error("Exception occurred when sending mail", e);
//            throw new RuntimeException("Exception occurred when sending mail to springshop@email.com", e);
//        }
    }
}
