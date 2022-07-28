package com.budzko.orderplatform.dev;

import com.budzko.orderplatform.repo.ProductRepo;
import com.budzko.orderplatform.repo.UserRepo;
import com.budzko.orderplatform.repo.entity.ProductEntity;
import com.budzko.orderplatform.repo.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Insert temp test data in H2 db
 * Can be disable in application.yml by removing 'dev' profile
 */
@Service
@Profile("dev")
@RequiredArgsConstructor
public class TestDataService {
    protected static final Random RANDOM = new Random();
    private final UserRepo userRepo;
    private final ProductRepo productRepo;

    @Value("${dev.user-count}")
    private Integer userCount;
    @Value("${dev.product-count}")
    private Integer productCount;

    @PostConstruct
    public void insertTestData() {
        insertUsers();
        insertProducts();
    }

    private void insertUsers() {
        List<UserEntity> users = new ArrayList<>(userCount);
        for (int i = 0; i < userCount; i++) {
            UserEntity userEntity = new UserEntity();
            userEntity.setName("NAME-" + i);
            users.add(userEntity);
        }
        userRepo.saveAll(users);
    }

    private void insertProducts() {
        List<ProductEntity> products = new ArrayList<>(productCount);
        for (int i = 0; i < productCount; i++) {
            ProductEntity productEntity = new ProductEntity();
            productEntity.setTitle("PRODUCT-" + i);
            float price = RANDOM.nextFloat() * 1000;
            productEntity.setPrice(price);
            products.add(productEntity);
        }
        productRepo.saveAll(products);
    }
}
