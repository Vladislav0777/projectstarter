package ru.project.starter.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.project.starter.model.dto.Product;
import ru.project.starter.repository.ProductRepository;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GetSomeProductsUseCase {
    private final ProductRepository countriesRepository;
    public List<Product> process(){
        return List.of(Product
                .builder()
                .id(1L)
                .name("Flower")
                .inStock(false)
                .comments(Collections.emptyList())
                .build());
    }
}
