package ru.vzaytsev.studythumeleaf.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.vzaytsev.studythumeleaf.model.entity.ProductEntity;
import ru.vzaytsev.studythumeleaf.repository.ProductDetailsRepository;
import ru.vzaytsev.studythumeleaf.repository.ProductRepository;
import ru.vzaytsev.studythumeleaf.repository.CategoryRepository;

@Controller
@RequiredArgsConstructor
public class ProductControllerV1 {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ProductDetailsRepository productDetailsRepository;

    @GetMapping("/products/new")
    public String showCommentNew (Model model) {
        var list = categoryRepository.findAll();
        model.addAttribute("product", new ProductEntity());
        model.addAttribute("listCategories", list);
        return "product_form";
    }

    @PostMapping("/products/save")
    public String saveComment (ProductEntity product, HttpServletRequest request) {
        String[] detailIdList = request.getParameterValues("detailID");
        String[] detailNameList = request.getParameterValues("detailName");
        String[] detailDescriptionList = request.getParameterValues("detailDescription");

        for (int i = 0; i< detailNameList.length; i++) {
            if(detailIdList != null && detailIdList.length>0) {
                product.addDetail(detailNameList[i], detailDescriptionList[i], Long.valueOf(detailIdList[i]));
            } else {
                product.addDetail(detailNameList[i], detailDescriptionList[i], null);
            }
        }

        var test = productRepository.save(product);
        return "redirect:/products";
    }


    @GetMapping("/products")
    public String listProducts(Model model){
        var list = productRepository.findAll();
        model.addAttribute("listProducts", list);

        return "products";
    }

    @GetMapping("/products/edit/{id}")
    public String showEditProductForm(@PathVariable("id") Long id, Model model){
        var product = productRepository.findById(id).orElse(null);
        var list = categoryRepository.findAll();
        model.addAttribute("product", product);
        model.addAttribute("listCategories", list);
        return "product_form";
    }

    @GetMapping("/products/delete/{id}")
    public String deleteProduct(@PathVariable("id") Long id, Model model){
        productRepository.deleteById(id);
        return "redirect:/products";
    }
}
