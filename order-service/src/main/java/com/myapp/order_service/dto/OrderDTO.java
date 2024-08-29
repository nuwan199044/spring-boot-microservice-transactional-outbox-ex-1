package com.myapp.order_service.dto;

import lombok.Getter;
import lombok.Setter;
import java.util.Date;
import java.util.List;

@Getter
@Setter
public class OrderDTO {

    private Long orderId;
    private String customerCode;
    private Date orderDate;
    private String status;
    private Double totalAmount;
    private List<OrderLineItemDTO> lineItems;
}
