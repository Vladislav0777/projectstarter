package ru.vzaytsev.studythumeleaf.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.vzaytsev.studythumeleaf.model.entity.CategoryEntity;
import ru.vzaytsev.studythumeleaf.repository.CategoryRepository;
import ru.vzaytsev.studythumeleaf.service.GetProductUseCase;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class CategoriesControllerV1 {
    private final GetProductUseCase getProduct;
    private final CategoryRepository productRepository;

    @Secured("developers")
    @GetMapping("/categories")
    public String listCategories (Model model) {
        List<CategoryEntity> productEntityList = getProduct.process();
        model.addAttribute("listCategories", productEntityList);
        return "categories";
    }

    @GetMapping("/categories/new")
    public String showCategoryNew (Model model) {
        model.addAttribute("category", new CategoryEntity());
        return "category_form";
    }

    @PostMapping("/categories/save")
    public String saveCategory (CategoryEntity category) {
        productRepository.save(category);
        return "redirect:/categories";
    }
}
