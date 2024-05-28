package com.ra.controller;

import com.ra.model.entity.Category;
import com.ra.model.entity.Product;
import com.ra.model.entity.dto.ProductDTO;
import com.ra.model.service.CategoryService;
import com.ra.model.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ProductController {
    private final ProductService productService;
    private final CategoryService categoryService;
    @Autowired
    public ProductController(ProductService productService,CategoryService categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
    }

    @GetMapping("/product")
    public String index(Model model,
                        @RequestParam(value = "keyword",required = false) String keyword,
                        @RequestParam(value = "page",defaultValue = "1",required = false) Integer noPage){
        List<Product> products;
        int limit = 2;
        if(keyword != null){
            products = productService.searchByName(keyword,noPage,limit);
            model.addAttribute("keyword",keyword);
        } else {
            products = productService.pagination(noPage,limit);
        }

        model.addAttribute("totalPage",productService.getTotalPage(limit));
        model.addAttribute("currentPage",noPage);
        model.addAttribute("products",products);
        return "product/index";
    }
    @GetMapping("/add-product")
    public String add(Model model, ProductDTO product){
        List<Category> categories = categoryService.getAll();
        model.addAttribute("categories",categories);
        model.addAttribute("product",product);
        return "product/add";
    }
    @PostMapping("/add-product")
    public String save(@Valid @ModelAttribute("product") ProductDTO product,
                       BindingResult bindingResult,
                       Model model
            ){

        if(bindingResult.hasErrors()){
            List<Category> categories = categoryService.getAll();
            model.addAttribute("categories",categories);

            return "product/add";
        }


        productService.create(product);
        return "redirect:/product";
    }
}
