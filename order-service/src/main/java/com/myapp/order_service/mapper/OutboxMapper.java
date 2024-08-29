package com.myapp.order_service.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.myapp.order_service.entity.Order;
import com.myapp.order_service.entity.Outbox;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class OutboxMapper {
    public Outbox mapOrderToOutbox(Order order) throws JsonProcessingException {
        return Outbox.builder()
                .aggregateId(order.getOrderId().toString())
                .payload(new ObjectMapper().writeValueAsString(order))
                .createdAt(LocalDateTime.now())
                .isProcessed(false)
                .build();
    }
}
