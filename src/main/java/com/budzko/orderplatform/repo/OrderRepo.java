package com.budzko.orderplatform.repo;

import com.budzko.orderplatform.repo.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface OrderRepo extends JpaRepository<OrderEntity, UUID> {
    List<OrderEntity> findOrderByUserId(UUID userId);
}
