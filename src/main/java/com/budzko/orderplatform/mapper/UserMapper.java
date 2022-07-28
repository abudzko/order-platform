package com.budzko.orderplatform.mapper;

import com.budzko.orderplatform.controller.dto.UserDto;
import com.budzko.orderplatform.repo.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface UserMapper {
    List<UserDto> convert(List<UserEntity> userEntities);
}
