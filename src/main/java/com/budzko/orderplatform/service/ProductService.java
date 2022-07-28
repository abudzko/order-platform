package com.budzko.orderplatform.service;

import com.budzko.orderplatform.controller.dto.ProductDto;
import com.budzko.orderplatform.mapper.ProductMapper;
import com.budzko.orderplatform.repo.ProductRepo;
import com.budzko.orderplatform.repo.entity.ProductEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepo productRepo;
    private final ProductMapper productMapper;

    public List<ProductDto> getAllProducts() {
        List<ProductEntity> productEntities = productRepo.findAll();
        return productMapper.convert(productEntities);
    }
}
