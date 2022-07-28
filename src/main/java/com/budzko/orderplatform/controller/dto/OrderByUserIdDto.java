package com.budzko.orderplatform.controller.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class OrderByUserIdDto {
    private UUID id;

    private UUID productId;
    private String productTitle;
    private Float price;

    private Float quantity;
    private Float totalPrice;
}
