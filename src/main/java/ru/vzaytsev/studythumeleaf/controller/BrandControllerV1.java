package ru.vzaytsev.studythumeleaf.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.vzaytsev.studythumeleaf.model.entity.BrandEntity;
import ru.vzaytsev.studythumeleaf.model.entity.CategoryEntity;
import ru.vzaytsev.studythumeleaf.repository.BrandRepository;
import ru.vzaytsev.studythumeleaf.repository.CategoryRepository;
import ru.vzaytsev.studythumeleaf.service.GetProductUseCase;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class BrandControllerV1 {
    private final BrandRepository brandRepository;
    private final CategoryRepository categoryRepository;

    @GetMapping("/brands/new")
    public String showCreateBrandNew (Model model) {
        var listCategories = categoryRepository.findAll();
        model.addAttribute("brand", new BrandEntity());
        model.addAttribute("listCategories", listCategories);
        return "brand_form";
    }

    @PostMapping("/brands/save")
    public String saveBrand (BrandEntity brand) {
        brandRepository.save(brand);
        return "redirect:/";
    }

    @GetMapping("/brands")
    public String listBrands(Model model) {
        List<BrandEntity> brandList = brandRepository.findAll();
        model.addAttribute("brandList",brandList);
        return "brands";
    }

    @GetMapping("/brands/edit/{id}")
    public String showEditBrandForm(@PathVariable("id") Long id, Model model){
        var brand = brandRepository.findById(id).orElse(null);
        var listCategories = categoryRepository.findAll();
        model.addAttribute("brand", brand);
        model.addAttribute("listCategories", listCategories);
        return "brand_form";
    }
}
