package ru.project.starter.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.project.starter.repository.CategoryRepository;
import ru.project.starter.model.entity.CategoryEntity;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetProductUseCase {
    private final CategoryRepository productRepository;
    public List<CategoryEntity> process(){
        return productRepository.findAll();
    }
}
