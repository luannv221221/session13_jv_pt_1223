package com.ra.model.entity.dto;

import com.ra.model.entity.Category;
import com.ra.util.validation.FileNotNull;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.Column;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class ProductDTO {
    private Integer id;
    @NotBlank(message = "Tên sản phẩm không rỗng")
    private String productName;
    @NotNull(message = "Giá phẩm không rỗng")
    @Min(value = 1,message = "giá phải lớn hơn 0")
    private Float price;
    @FileNotNull(message = "Ảnh ko rỗng")
    private MultipartFile fileImage;
    private Category category;

    public ProductDTO() {
    }

    public ProductDTO(Integer id, String productName, Float price, MultipartFile fileImage, Category category) {
        this.id = id;
        this.productName = productName;
        this.price = price;
        this.fileImage = fileImage;
        this.category = category;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public MultipartFile getFileImage() {
        return fileImage;
    }

    public void setFileImage(MultipartFile fileImage) {
        this.fileImage = fileImage;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
