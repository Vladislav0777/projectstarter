package ru.vzaytsev.studythumeleaf.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.vzaytsev.studythumeleaf.model.entity.CategoryEntity;
import ru.vzaytsev.studythumeleaf.repository.CategoryRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetProductUseCase {
    private final CategoryRepository productRepository;
    public List<CategoryEntity> process(){
        return productRepository.findAll();
    }
}
