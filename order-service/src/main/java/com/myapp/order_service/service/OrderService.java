package com.myapp.order_service.service;

import com.myapp.order_service.dto.OrderDTO;

public interface OrderService {
    OrderDTO createOrder(OrderDTO orderDTO);
}
