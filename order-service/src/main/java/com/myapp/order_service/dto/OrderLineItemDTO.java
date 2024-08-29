package com.myapp.order_service.dto;

import com.myapp.order_service.entity.OrderLineItem;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderLineItemDTO {

    private Long id;
    private String productCode;
    private String productDescription;
    private Integer quantity;
    private Double price;
}
