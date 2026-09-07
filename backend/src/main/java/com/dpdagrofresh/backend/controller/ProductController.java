package com.dpdagrofresh.backend.controller;

import com.dpdagrofresh.backend.model.Product;
import com.dpdagrofresh.backend.service.ProductService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@CrossOrigin(origins = "*")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }


    // ============================================================
    // GET ALL PRODUCTS
    // ============================================================

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {

        try {

            List<Product> products =
                    productService.getAllProducts();

            return ResponseEntity.ok(products);

        } catch (Exception e) {

            e.printStackTrace();

            return ResponseEntity.internalServerError().build();
        }
    }


    // ============================================================
    // GET PRODUCT BY ID
    // ============================================================

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(
            @PathVariable Long id) {

        try {

            return productService
                    .getProductById(id)
                    .map(ResponseEntity::ok)
                    .orElseGet(
                            () -> ResponseEntity.notFound().build()
                    );

        } catch (Exception e) {

            e.printStackTrace();

            return ResponseEntity.internalServerError().build();
        }
    }


    // ============================================================
    // ADD PRODUCT - JSON ONLY
    //
    // This endpoint is kept for compatibility.
    // It is NOT used when uploading a new image.
    // ============================================================

    @PostMapping(
            consumes = "application/json",
            produces = "application/json"
    )
    public ResponseEntity<Product> addProduct(
            @RequestBody Product product) {

        try {

            Product savedProduct =
                    productService.addProduct(product);

            return ResponseEntity.ok(savedProduct);

        } catch (Exception e) {

            e.printStackTrace();

            return ResponseEntity.internalServerError().build();
        }
    }


    // ============================================================
    // ADD PRODUCT WITH IMAGE
    //
    // POST:
    // /api/products/with-image
    //
    // Multipart parts:
    // product = JSON Product object
    // image   = uploaded image file
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

            System.out.println(
                    "=========================================="
            );

            System.out.println(
                    "ADD PRODUCT WITH IMAGE REQUEST"
            );

            System.out.println(
                    "Product name: " + product.getName()
            );

            System.out.println(
                    "Product category: " + product.getCategory()
            );

            System.out.println(
                    "Image: " +
                            (
                                    image != null
                                            ? image.getOriginalFilename()
                                            : "NULL"
                            )
            );

            System.out.println(
                    "=========================================="
            );


            if (product.getName() == null ||
                    product.getName().trim().isEmpty()) {

                return ResponseEntity.badRequest().build();
            }


            if (product.getCategory() == null ||
                    product.getCategory().trim().isEmpty()) {

                return ResponseEntity.badRequest().build();
            }


            if (image == null || image.isEmpty()) {

                return ResponseEntity.badRequest().build();
            }


            Product savedProduct =
                    productService.addProductWithImage(
                            product,
                            image
                    );


            return ResponseEntity.ok(savedProduct);

        } catch (Exception e) {

            e.printStackTrace();

            return ResponseEntity.internalServerError().build();
        }
    }


    // ============================================================
    // UPDATE PRODUCT - JSON ONLY
    //
    // Kept for compatibility.
    // ============================================================

    @PutMapping(
            value = "/{id}",
            consumes = "application/json",
            produces = "application/json"
    )
    public ResponseEntity<Product> updateProduct(
            @PathVariable Long id,
            @RequestBody Product product) {

        try {

            Product updatedProduct =
                    productService.updateProduct(
                            id,
                            product
                    );

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
    // UPDATE PRODUCT WITH IMAGE
    //
    // PUT:
    // /api/products/{id}/with-image
    //
    // Multipart parts:
    // product = JSON Product object
    // image   = optional replacement image
    //
    // If image is NOT supplied:
    // existing image remains unchanged.
    //
    // If image IS supplied:
    // existing image is replaced.
    // ============================================================

    @PutMapping(
            value = "/{id}/with-image",
            consumes = "multipart/form-data",
            produces = "application/json"
    )
    public ResponseEntity<Product> updateProductWithImage(

            @PathVariable Long id,

            @RequestPart("product") Product product,

            @RequestPart(
                    value = "image",
                    required = false
            )
            MultipartFile image

    ) {

        try {

            System.out.println(
                    "=========================================="
            );

            System.out.println(
                    "UPDATE PRODUCT WITH IMAGE REQUEST"
            );

            System.out.println(
                    "Product ID: " + id
            );

            System.out.println(
                    "Product name: " + product.getName()
            );

            System.out.println(
                    "Product category: " + product.getCategory()
            );

            System.out.println(
                    "New image: " +
                            (
                                    image != null &&
                                    !image.isEmpty()
                                            ? image.getOriginalFilename()
                                            : "KEEP EXISTING IMAGE"
                            )
            );

            System.out.println(
                    "=========================================="
            );


            if (product.getName() == null ||
                    product.getName().trim().isEmpty()) {

                return ResponseEntity.badRequest().build();
            }


            if (product.getCategory() == null ||
                    product.getCategory().trim().isEmpty()) {

                return ResponseEntity.badRequest().build();
            }


            Product updatedProduct =
                    productService.updateProductWithImage(
                            id,
                            product,
                            image
                    );


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
    // DELETE PRODUCT
    // ============================================================

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(
            @PathVariable Long id) {

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