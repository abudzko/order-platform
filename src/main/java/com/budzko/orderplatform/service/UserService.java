package com.budzko.orderplatform.service;

import com.budzko.orderplatform.controller.dto.UserDto;
import com.budzko.orderplatform.mapper.UserMapper;
import com.budzko.orderplatform.repo.UserRepo;
import com.budzko.orderplatform.repo.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepo userRepo;
    private final UserMapper userMapper;

    public List<UserDto> getAllUsers() {
        List<UserEntity> userEntities = userRepo.findAll();
        return userMapper.convert(userEntities);
    }
}
