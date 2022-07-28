package com.budzko.orderplatform.mapper;

import com.budzko.orderplatform.controller.dto.OrderByUserIdDto;
import com.budzko.orderplatform.controller.dto.OrderDto;
import com.budzko.orderplatform.repo.entity.OrderEntity;
import com.budzko.orderplatform.repo.entity.ProductEntity;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface OrderMapper {
    List<OrderByUserIdDto> convert(List<OrderEntity> orderEntities);

    OrderDto convert(OrderEntity orderEntity);

    @Mapping(target = "id", ignore = true)
    OrderEntity convert(OrderDto orderDto);

    @AfterMapping
    default void afterMapping(
            OrderEntity orderEntity,
            @MappingTarget OrderByUserIdDto orderByUserIdDto
    ) {
        ProductEntity productEntity = orderEntity.getProductEntity();
        if (productEntity != null) {
            Float price = productEntity.getPrice();
            orderByUserIdDto.setPrice(price);
            orderByUserIdDto.setTotalPrice(price * orderByUserIdDto.getQuantity());
            orderByUserIdDto.setProductTitle(productEntity.getTitle());
        }
    }
}
