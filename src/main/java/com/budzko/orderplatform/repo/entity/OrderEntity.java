package com.budzko.orderplatform.repo.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import java.util.UUID;

@Data
@Entity(name = "orders")
public class OrderEntity {
    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "userId")
    private UUID userId;

    @Column(name = "product_id")
    private UUID productId;

    @OneToOne
    @JoinColumn(
            name = "product_id",
            referencedColumnName = "id",
            insertable = false,
            updatable = false
    )
    private ProductEntity productEntity;

    @Column(name = "quantity")
    private Float quantity;
}
