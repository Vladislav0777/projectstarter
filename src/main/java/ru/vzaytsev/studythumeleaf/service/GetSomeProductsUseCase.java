package ru.vzaytsev.studythumeleaf.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.vzaytsev.studythumeleaf.model.dto.Product;
import ru.vzaytsev.studythumeleaf.repository.ProductRepository;

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
