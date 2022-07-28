package com.budzko.orderplatform.controller.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class ProductDto {
    private UUID id;
    private String title;
    private Float price;
}
