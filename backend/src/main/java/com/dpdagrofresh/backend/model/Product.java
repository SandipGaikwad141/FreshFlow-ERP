package com.dpdagrofresh.backend.model;

import jakarta.persistence.*;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String category;
    
    @Column(length = 1000)
    private String description;
    
    private double price;
    private String imageUrl;
    private boolean available = true;

    // =================================================================
    // CONSTRUCTORS
    // =================================================================

    // 1. Default No-Args Constructor (Required by JPA/Hibernate)
    public Product() {
    }

    // 2. All-Args Constructor (Used by your Controller)
    public Product(String name, String category, double price, String imageUrl) {
        this.name = name;
        this.category = category;
        this.price = price;
        this.imageUrl = imageUrl;
    }

    // 3. String-Only Constructor (Fixes your "Argument Mismatch" build error)
    // This catches cases where old tests pass "price" as a String.
    public Product(String name, String category, String description, String imageUrl) {
        this.name = name;
        this.category = category;
        this.description = description;
        this.imageUrl = imageUrl;
        this.price = 0.0; // Default price if not provided
    }

    // =================================================================
    // GETTERS AND SETTERS
    // =================================================================

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}
