package com.ra.util.validation;

import org.springframework.web.multipart.MultipartFile;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Objects;

public class FileNotNullValidator implements ConstraintValidator<FileNotNull, MultipartFile> {
    @Override
    public boolean isValid(MultipartFile value, ConstraintValidatorContext context) {
        return !Objects.requireNonNull(value.getOriginalFilename()).isEmpty();
    }
}
