package com.ra.model.service;

import com.ra.model.entity.Product;
import com.ra.model.entity.dto.ProductDTO;

import java.util.List;

public interface ProductService {
    List<Product> getAll();
    Boolean create(ProductDTO product);
}
