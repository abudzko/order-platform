package com.budzko.orderplatform.controller;

import com.budzko.orderplatform.config.SwaggerConfig;
import com.budzko.orderplatform.controller.dto.ProductDto;
import com.budzko.orderplatform.service.ProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(tags = SwaggerConfig.PRODUCT_TAG)
@RestController
@RequestMapping("/api/v1/product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping
    @ApiOperation(value = "Get list of all products")
    public List<ProductDto> getAllProducts() {
        return productService.getAllProducts();
    }
}
