package com.ra.model.entity;

import javax.persistence.*;

@Entity
public class Category {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "category_name",length = 100)
    private String categoryName;
    @Column(name = "status")
    private Boolean categoryStatus;

    public Category() {
    }

    public Category(Integer id, String categoryName, Boolean categoryStatus) {
        this.id = id;
        this.categoryName = categoryName;
        this.categoryStatus = categoryStatus;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Boolean getCategoryStatus() {
        return categoryStatus;
    }

    public void setCategoryStatus(Boolean categoryStatus) {
        this.categoryStatus = categoryStatus;
    }
}
