package com.myapp.notification_service.kafka;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class KafkaMessageListener {
    @KafkaListener(topics = "order-topic", groupId = "order-group-1")
    public void consume(String payload) {
        log.info("Sending the notification for order {} ", payload);
    }
}
