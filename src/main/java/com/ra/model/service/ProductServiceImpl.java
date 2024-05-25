package com.ra.model.service;

import com.ra.model.dao.ProductDAO;
import com.ra.model.entity.Product;
import com.ra.model.entity.dto.ProductDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.List;
@Service
public class ProductServiceImpl implements ProductService{
    private final ProductDAO productDAO;

    @Autowired
    public ProductServiceImpl(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }

    @Override
    public List<Product> getAll() {
        return productDAO.findAll();
    }

    @Override
    public Boolean create(ProductDTO product) {
        // xu ly upload file
        //laays teen file
        MultipartFile file = product.getFileImage();
        String fileName = file.getOriginalFilename();
        String path = "D:\\Luannv\\JAVA-PT-2312\\MD3\\session13\\src\\main\\webapp\\uploads";
        File destination = new File(path+"/"+fileName);
        try {
            Files.write(destination.toPath(),file.getBytes(), StandardOpenOption.CREATE);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        // convert DTO -> Entity
        Product productEntity = new Product();
        productEntity.setId(product.getId());
        productEntity.setProductName(product.getProductName());
        productEntity.setPrice(product.getPrice());
        productEntity.setCategory(product.getCategory());
        productEntity.setImage(fileName);
        return productDAO.saveOrUpdate(productEntity);
    }
}
