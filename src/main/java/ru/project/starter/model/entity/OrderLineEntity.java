package ru.project.starter.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity
@Table(name = "order_line")
public class OrderLineEntity {
    @Id
    @Column(name = "id")
    private Long id;
    @Column(name = "amount")
    private Long amount;
    @Column(name = "purchase_price")
    private BigDecimal purchasePrice;
    @Column(name = "product_id")
    private Long productId;
    @Column(name = "customer_order_id")
    private Long customerOrderId;
}
