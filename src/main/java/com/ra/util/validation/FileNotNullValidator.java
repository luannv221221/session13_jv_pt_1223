package com.ra.util.validation;

import com.ra.model.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Objects;
@Component
public class FileNotNullValidator implements ConstraintValidator<FileNotNull, MultipartFile> {

    @Override
    public boolean isValid(MultipartFile value, ConstraintValidatorContext context) {
        return !Objects.requireNonNull(value.getOriginalFilename()).isEmpty();

    }
}
