package com.budzko.orderplatform.controller.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.UUID;

@Data
public class OrderDto {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private UUID id;
    private UUID userId;
    private UUID productId;
    private Float quantity;
}
