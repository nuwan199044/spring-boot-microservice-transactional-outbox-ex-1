package com.myapp.order_service.service.impl;

import com.myapp.order_service.dto.OrderDTO;
import com.myapp.order_service.entity.Order;
import com.myapp.order_service.entity.OrderLineItem;
import com.myapp.order_service.entity.Outbox;
import com.myapp.order_service.mapper.OutboxMapper;
import com.myapp.order_service.repository.OrderRepository;
import com.myapp.order_service.repository.OutboxRepository;
import com.myapp.order_service.service.OrderService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderServiceImpl implements OrderService {

    private final ModelMapper modelMapper;
    private final OrderRepository orderRepository;
    private final OutboxRepository outboxRepository;
    private final OutboxMapper outboxMapper;

    @Override
    @Transactional
    public OrderDTO createOrder(OrderDTO orderDTO) {
        try {
            Order order = modelMapper.map(orderDTO, Order.class);
            for (OrderLineItem item : order.getLineItems()) {
                item.setOrder(order);
            }
            Order savedOrder = orderRepository.save(order);
            Outbox outbox = outboxMapper.mapOrderToOutbox(savedOrder);
            outboxRepository.save(outbox);

            return modelMapper.map(savedOrder, OrderDTO.class);
        } catch (Exception ex) {
            log.error(ex.getMessage());
        }
        return null;
    }
}
