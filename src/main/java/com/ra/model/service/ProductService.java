package com.ra.model.service;

import com.ra.model.entity.Product;
import com.ra.model.entity.dto.ProductDTO;

import java.util.List;

public interface ProductService {
    List<Product> getAll();
    Boolean create(ProductDTO product);
    Boolean checkProductNameExits(String productName);
    List<Product> searchByName(String keyword,Integer noPage,Integer limit);
    List<Product> pagination(Integer noPage,Integer limit);
    int getTotalPage(Integer limit);
}
