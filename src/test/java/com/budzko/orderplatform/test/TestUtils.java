package com.budzko.orderplatform.test;

import com.budzko.orderplatform.controller.dto.OrderDto;
import com.budzko.orderplatform.repo.entity.ProductEntity;
import com.budzko.orderplatform.repo.entity.UserEntity;

import java.util.Random;
import java.util.UUID;

public class TestUtils {

    protected static final Random RANDOM = new Random();

    public static UserEntity createUserEntity() {
        UserEntity userEntity = new UserEntity();
        userEntity.setName(UUID.randomUUID().toString());
        return userEntity;
    }

    public static ProductEntity createProductEntity() {
        ProductEntity productEntity = new ProductEntity();
        productEntity.setPrice(randomPrice());
        productEntity.setTitle(UUID.randomUUID().toString());
        return productEntity;
    }

    public static OrderDto createOrderDto() {
        OrderDto orderDto = new OrderDto();
        orderDto.setQuantity(randomQuantity());
        return orderDto;
    }

    public static float randomPrice() {
        return RANDOM.nextFloat() * 1000;
    }

    public static float randomQuantity() {
        return RANDOM.nextFloat() * 100;
    }
}
