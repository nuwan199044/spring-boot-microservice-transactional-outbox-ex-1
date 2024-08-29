package com.myapp.message_relay_service.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
@Slf4j
@RequiredArgsConstructor
public class KafkaMessagePublisher {

    private final KafkaTemplate<String,String> template;
    @Value("${order.poller.topic.name}")
    private String topicName;


    public void sendEventToTopic(String payload) {
        try {
            CompletableFuture<SendResult<String, String>> customerTopic = template.send(topicName, payload);
            customerTopic.whenComplete((result, exception)->{
                if (exception == null) {
                    System.out.println("Sent product=["+payload+ "] with offset=[ "+result.getRecordMetadata().offset()+" ]");
                } else {
                    System.out.println("Unable to send product=["+payload+ "] due to: "+exception.getMessage());
                }
            });
        } catch(Exception ex) {
            log.error("ERROR {} ",ex.getMessage());
        }
    }
}
