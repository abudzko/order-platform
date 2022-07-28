package com.budzko.orderplatform.controller;

import com.budzko.orderplatform.controller.dto.OrderByUserIdDto;
import com.budzko.orderplatform.controller.dto.OrderDto;
import com.budzko.orderplatform.repo.OrderRepo;
import com.budzko.orderplatform.repo.ProductRepo;
import com.budzko.orderplatform.repo.UserRepo;
import com.budzko.orderplatform.repo.entity.OrderEntity;
import com.budzko.orderplatform.repo.entity.ProductEntity;
import com.budzko.orderplatform.repo.entity.UserEntity;
import com.budzko.orderplatform.test.TestUtils;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class OrderControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private ProductRepo productRepo;
    @Autowired
    private OrderRepo orderRepo;

    @SneakyThrows
    @Test
    void testSaveOrder() {
        UserEntity userEntity = createUser();
        //Save first order for user
        OrderDto firstOrder = createOrder(userEntity);
        OrderDto firstSavedOrder = saveOrder(firstOrder);
        assertOrder(firstOrder, firstSavedOrder);

        //Save first order for user
        OrderDto secondOrder = createOrder(userEntity);
        OrderDto secondSavedOrder = saveOrder(secondOrder);
        assertOrder(secondOrder, secondSavedOrder);

        //Find orders by user id
        List<OrderByUserIdDto> orderEntities = findOrderByUserId(userEntity.getId());
        Assertions.assertEquals(2, orderEntities.size());
        Map<UUID, OrderByUserIdDto> orderMap = orderEntities.stream()
                .collect(Collectors.toMap(OrderByUserIdDto::getId, Function.identity()));
        assertOrder(firstSavedOrder, orderMap.get(firstSavedOrder.getId()));
        assertOrder(secondSavedOrder, orderMap.get(secondSavedOrder.getId()));
    }

    private void assertOrder(OrderDto expected, OrderDto savedOrderDto) {
        //Check returned by API value
        Assertions.assertNotNull(savedOrderDto.getId());
        Assertions.assertEquals(expected.getQuantity(), savedOrderDto.getQuantity());
        Assertions.assertEquals(expected.getUserId(), savedOrderDto.getUserId());
        Assertions.assertEquals(expected.getProductId(), savedOrderDto.getProductId());

        //Check saved by repo value
        OrderEntity orderEntity = orderRepo.findById(savedOrderDto.getId()).orElseThrow();
        Assertions.assertEquals(savedOrderDto.getQuantity(), orderEntity.getQuantity());
        Assertions.assertEquals(savedOrderDto.getUserId(), orderEntity.getUserId());
        Assertions.assertEquals(savedOrderDto.getProductId(), orderEntity.getProductId());
    }

    private void assertOrder(OrderDto savedOrder, OrderByUserIdDto orderByUserIdDto) {
        Assertions.assertEquals(savedOrder.getId(), orderByUserIdDto.getId());
        Assertions.assertEquals(savedOrder.getProductId(), orderByUserIdDto.getProductId());
        Assertions.assertEquals(savedOrder.getQuantity(), orderByUserIdDto.getQuantity());
        Assertions.assertEquals(savedOrder.getQuantity(), orderByUserIdDto.getQuantity());
        ProductEntity productEntity = productRepo.findById(savedOrder.getProductId()).orElseThrow();
        Assertions.assertEquals(productEntity.getTitle(), orderByUserIdDto.getProductTitle());
        Assertions.assertEquals(productEntity.getPrice(), orderByUserIdDto.getPrice());
    }

    private OrderDto saveOrder(OrderDto orderDto) throws Exception {
        String requestBody = objectMapper.writeValueAsString(orderDto);
        MvcResult mvcResult = mockMvc.perform(post("/api/v1/order")
                .content(requestBody)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        String responseBody = mvcResult.getResponse().getContentAsString();
        return objectMapper.readValue(responseBody, OrderDto.class);
    }

    private List<OrderByUserIdDto> findOrderByUserId(UUID userId) throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/api/v1/order/" + userId))
                .andExpect(status().isOk())
                .andReturn();
        String responseBody = mvcResult.getResponse().getContentAsString();
        return objectMapper.readValue(responseBody, new TypeReference<>() {
        });
    }

    private OrderDto createOrder(UserEntity userEntity) {
        ProductEntity productEntity = createProduct();
        OrderDto orderDto = TestUtils.createOrderDto();
        orderDto.setUserId(userEntity.getId());
        orderDto.setProductId(productEntity.getId());
        return orderDto;
    }

    private UserEntity createUser() {
        return userRepo.save(TestUtils.createUserEntity());
    }

    private ProductEntity createProduct() {
        return productRepo.save(TestUtils.createProductEntity());
    }
}
