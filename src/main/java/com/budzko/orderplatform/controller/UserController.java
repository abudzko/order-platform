package com.budzko.orderplatform.controller;

import com.budzko.orderplatform.config.SwaggerConfig;
import com.budzko.orderplatform.controller.dto.UserDto;
import com.budzko.orderplatform.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(tags = SwaggerConfig.USER_TAG)
@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping
    @ApiOperation(value = "Get list of all users")
    public List<UserDto> getAllUsers() {
        return userService.getAllUsers();
    }
}
