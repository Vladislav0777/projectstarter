package ru.vzaytsev.studythumeleaf.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.vzaytsev.studythumeleaf.model.entity.CategoryEntity;
import ru.vzaytsev.studythumeleaf.service.GetProductUseCase;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("v1")
public class ProductRestControllerV1 {
    private final GetProductUseCase getProduct;
    @GetMapping
    public ResponseEntity test () {
        List<CategoryEntity> productEntityList = getProduct.process();
        return ResponseEntity.ok().body(productEntityList);
    }
}
