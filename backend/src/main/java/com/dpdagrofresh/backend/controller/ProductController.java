package com.dpdagrofresh.backend.controller;

import com.dpdagrofresh.backend.model.Product;
import com.dpdagrofresh.backend.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@CrossOrigin(origins = "*") // Allows Netlify frontend to interact safely with Render
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // ============================================================
    // 1. GET ALL PRODUCTS
    // ============================================================
    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        try {
            List<Product> products = productService.getAllProducts();
            return ResponseEntity.ok(products);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

    // ============================================================
    // 2. GET PRODUCT BY ID
    // ============================================================
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        try {
            return productService.getProductById(id)
                    .map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.notFound().build());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

    // ============================================================
    // 3. ADD PRODUCT WITH IMAGE (Used by Admin Panel)
    // ============================================================
    @PostMapping(
            value = "/with-image",
            consumes = "multipart/form-data",
            produces = "application/json"
    )
    public ResponseEntity<Product> addProductWithImage(
            @RequestPart("product") Product product,
            @RequestPart("image") MultipartFile image
    ) {
        try {
            if (product.getName() == null || product.getName().trim().isEmpty()) {
                return ResponseEntity.badRequest().build();
            }
            if (product.getCategory() == null || product.getCategory().trim().isEmpty()) {
                return ResponseEntity.badRequest().build();
            }
            if (image == null || image.isEmpty()) {
                return ResponseEntity.badRequest().build();
            }

            Product savedProduct = productService.addProductWithImage(product, image);
            return ResponseEntity.ok(savedProduct);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

    // ============================================================
    // 4. UPDATE PRODUCT WITH IMAGE
    // ============================================================
    @PutMapping(
            value = "/{id}/with-image",
            consumes = "multipart/form-data",
            produces = "application/json"
    )
    public ResponseEntity<Product> updateProductWithImage(
            @PathVariable Long id,
            @RequestPart("product") Product product,
            @RequestPart(value = "image", required = false) MultipartFile image
    ) {
        try {
            if (product.getName() == null || product.getName().trim().isEmpty()) {
                return ResponseEntity.badRequest().build();
            }
            if (product.getCategory() == null || product.getCategory().trim().isEmpty()) {
                return ResponseEntity.badRequest().build();
            }

            Product updatedProduct = productService.updateProductWithImage(id, product, image);
            return ResponseEntity.ok(updatedProduct);
        } catch (RuntimeException e) {
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

    // ============================================================
    // 5. DELETE PRODUCT
    // ============================================================
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        try {
            productService.deleteProduct(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }
}
