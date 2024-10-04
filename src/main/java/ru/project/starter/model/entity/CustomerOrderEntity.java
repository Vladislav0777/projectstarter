package ru.project.starter.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.OffsetDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity
@Table(name = "customer_order")
public class CustomerOrderEntity {
    @Id
    @Column(name = "id")
    private Long id;
    @Column(name = "name")
    private OffsetDateTime date;
    @Column(name = "customer_id")
    private Long customerId;
}
