package com.dpdagrofresh.backend.controller;

import com.dpdagrofresh.backend.model.Product;
import com.dpdagrofresh.backend.repository.ProductRepository;
import com.dpdagrofresh.backend.service.ImageUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@CrossOrigin(origins = "*")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ImageUploadService imageUploadService;

    // 1. GET ALL PRODUCTS
    @GetMapping
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    // 2. GET PRODUCT BY ID
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        return productRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // 3. ADD PRODUCT WITH IMAGE (Correctly uses Cloudinary)
    @PostMapping(value = "/with-image", consumes = "multipart/form-data")
    public ResponseEntity<?> addProductWithImage(
            @RequestPart("product") Product product,
            @RequestPart("image") MultipartFile image) {
        try {
            // Upload to Cloudinary and get the secure HTTPS URL
            String imageUrl = imageUploadService.uploadImage(image);
            
            // Set that URL on the product
            product.setImageUrl(imageUrl);
            
            // Save to Database
            Product savedProduct = productRepository.save(product);
            return ResponseEntity.ok(savedProduct);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Upload failed: " + e.getMessage());
        }
    }

    // 4. UPDATE PRODUCT WITH IMAGE
    @PutMapping(value = "/{id}/with-image", consumes = "multipart/form-data")
    public ResponseEntity<?> updateProductWithImage(
            @PathVariable Long id,
            @RequestPart("product") Product productDetails,
            @RequestPart(value = "image", required = false) MultipartFile image) {
        try {
            Product existingProduct = productRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Product not found"));

            // Update text fields
            existingProduct.setName(productDetails.getName());
            existingProduct.setCategory(productDetails.getCategory());
            existingProduct.setDescription(productDetails.getDescription());
            existingProduct.setPrice(productDetails.getPrice());
            existingProduct.setAvailable(productDetails.isAvailable());

            // If a new image is provided, upload it. Otherwise, keep the old one.
            if (image != null && !image.isEmpty()) {
                String newImageUrl = imageUploadService.uploadImage(image);
                existingProduct.setImageUrl(newImageUrl);
            }

            Product updatedProduct = productRepository.save(existingProduct);
            return ResponseEntity.ok(updatedProduct);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Update failed: " + e.getMessage());
        }
    }

    // 5. DELETE PRODUCT
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id) {
        try {
            productRepository.deleteById(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Delete failed: " + e.getMessage());
        }
    }
}
