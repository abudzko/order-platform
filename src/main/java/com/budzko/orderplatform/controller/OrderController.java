package com.budzko.orderplatform.controller;

import com.budzko.orderplatform.config.SwaggerConfig;
import com.budzko.orderplatform.controller.dto.OrderByUserIdDto;
import com.budzko.orderplatform.controller.dto.OrderDto;
import com.budzko.orderplatform.service.OrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@Api(tags = SwaggerConfig.ORDER_TAG)
@RestController
@RequestMapping("/api/v1/order")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @GetMapping("{userId}")
    @ApiOperation(value = "Find orders by user id")
    public List<OrderByUserIdDto> findOrdersByUserID(@PathVariable UUID userId) {
        return orderService.findOrdersBy(userId);
    }

    @PostMapping
    @ApiOperation(value = "Create new order")
    public OrderDto saveOrder(@RequestBody OrderDto orderDto) {
        return orderService.saveOrder(orderDto);
    }
}
