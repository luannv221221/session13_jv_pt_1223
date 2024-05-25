package com.ra.controller;

import com.ra.model.entity.Category;
import com.ra.model.entity.Product;
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
    public String index(Model model){
        List<Product> products = productService.getAll();
        model.addAttribute("products",products);
        return "product/index";
    }
    @GetMapping("/add-product")
    public String add(Model model,Product product){
        List<Category> categories = categoryService.getAll();
        model.addAttribute("categories",categories);
        model.addAttribute("product",product);
        return "product/add";
    }
    @PostMapping("/add-product")
    public String save(@Valid @ModelAttribute("product") Product product,
                       BindingResult bindingResult,
                       @RequestParam(value = "fileImage",required = false) MultipartFile file,
                       Model model
            ){
        System.out.println(bindingResult);
        if(bindingResult.hasErrors()){
            List<Category> categories = categoryService.getAll();
            model.addAttribute("categories",categories);

            return "product/add";
        }
       // xu ly upload file
//         laays teen file
        String fileName = file.getOriginalFilename();
        String path = "D:\\Luannv\\JAVA-PT-2312\\MD3\\session13\\src\\main\\webapp\\uploads";
        File destination = new File(path+"/"+fileName);
        try {
            Files.write(destination.toPath(),file.getBytes(), StandardOpenOption.CREATE);
            product.setImage(fileName);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
//        System.out.println(fileName);
        productService.create(product);
        return "redirect:/product";
    }
}
