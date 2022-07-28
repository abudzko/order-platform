package com.budzko.orderplatform.service;

import com.budzko.orderplatform.controller.dto.OrderByUserIdDto;
import com.budzko.orderplatform.controller.dto.OrderDto;
import com.budzko.orderplatform.mapper.OrderMapper;
import com.budzko.orderplatform.repo.OrderRepo;
import com.budzko.orderplatform.repo.entity.OrderEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepo orderRepo;
    private final OrderMapper orderMapper;

    public List<OrderByUserIdDto> findOrdersBy(UUID userId) {
        List<OrderEntity> orders = orderRepo.findOrderByUserId(userId);
        return orderMapper.convert(orders);
    }

    public OrderDto saveOrder(OrderDto orderDto) {
        OrderEntity save = orderRepo.save(orderMapper.convert(orderDto));
        return orderMapper.convert(save);
    }
}
