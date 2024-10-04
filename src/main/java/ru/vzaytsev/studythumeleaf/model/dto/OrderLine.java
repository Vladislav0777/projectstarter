package ru.vzaytsev.studythumeleaf.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class OrderLine {
    private Long id;
    private Long amount;
    private BigDecimal purchasePrice;
    private Product product;
}
