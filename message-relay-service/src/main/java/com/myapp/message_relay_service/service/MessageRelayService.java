package com.myapp.message_relay_service.service;

import com.myapp.message_relay_service.entity.Outbox;
import com.myapp.message_relay_service.repository.OutboxRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@EnableScheduling
@Slf4j
public class MessageRelayService {

    private final OutboxRepository outboxRepository;
    private final KafkaMessagePublisher kafkaMessagePublisher;

    @Scheduled(fixedDelay = 60000)
    public void pollOutboxMessageAndPublish() {
        List<Outbox> unprocessedRecords = outboxRepository.findByIsProcessedFalse();
        log.info("Fetch {} unprocessed records for processing...", unprocessedRecords.size());
        unprocessedRecords.forEach(outbox -> {
            try {
                kafkaMessagePublisher.sendEventToTopic(outbox.getPayload());
                outbox.setIsProcessed(true);
                outboxRepository.save(outbox);
            } catch(Exception ex) {
                log.error(ex.getMessage());
            }
        });
    }

}
