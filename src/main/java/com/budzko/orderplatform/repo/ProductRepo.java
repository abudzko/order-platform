package com.budzko.orderplatform.repo;

import com.budzko.orderplatform.repo.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProductRepo extends JpaRepository<ProductEntity, UUID> {
}
