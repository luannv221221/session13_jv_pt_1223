package com.ra.util.validation;

import com.ra.model.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


public class UniqueValidator implements ConstraintValidator<Unique,String> {
    @Autowired
    ProductService productService;
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

        return !productService.checkProductNameExits(value);
    }
}
