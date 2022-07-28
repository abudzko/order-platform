package com.budzko.orderplatform.mapper;

import com.budzko.orderplatform.controller.dto.ProductDto;
import com.budzko.orderplatform.repo.entity.ProductEntity;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface ProductMapper {
    List<ProductDto> convert(List<ProductEntity> userEntities);
}
