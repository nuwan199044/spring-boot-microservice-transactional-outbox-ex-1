package com.myapp.message_relay_service.repository;


import com.myapp.message_relay_service.entity.Outbox;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OutboxRepository extends JpaRepository<Outbox, Long> {
    List<Outbox> findByIsProcessedFalse();
}
