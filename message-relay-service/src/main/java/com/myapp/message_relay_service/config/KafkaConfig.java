package com.myapp.message_relay_service.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaConfig {

    @Value("${order.poller.topic.name}")
    private String topicName;

    public NewTopic createTopic() {
        return new NewTopic(topicName, 3, (short) 1);
    }
}
