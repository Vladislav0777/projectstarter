package ru.project.starter.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.RequiredArgsConstructor;

import java.time.OffsetDateTime;
import java.util.Set;

@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class Order {
    private Long id;
    private OffsetDateTime date;
    private Customer customer;
    private Set<OrderLine> orderLines;
}
