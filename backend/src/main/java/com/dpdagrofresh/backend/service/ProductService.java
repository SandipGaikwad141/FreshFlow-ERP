package com.dpdagrofresh.backend.service;

import com.dpdagrofresh.backend.model.Product;
import com.dpdagrofresh.backend.repository.ProductRepository;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;


    // =========================================================
    // PROJECT FRONTEND IMAGE DIRECTORY
    // =========================================================
    //
    // Your project structure is:
    //
    // FreshFlow-ERP
    // ├── backend
    // │   └── ...
    // │
    // └── frontend
    //     ├── products.html
    //     └── images
    //         └── products
    //
    // Backend runs from:
    //
    // FreshFlow-ERP/backend
    //
    // Therefore:
    //
    // ../frontend/images/products
    //
    // points to the correct frontend image folder.
    // =========================================================

    private final Path productsImageRoot =
            Paths.get(
                    "../frontend/images/products"
            )
            .toAbsolutePath()
            .normalize();


    // =========================================================
    // CONSTRUCTOR
    // =========================================================

    public ProductService(
            ProductRepository productRepository) {

        this.productRepository =
                productRepository;
    }


    // =========================================================
    // GET ALL PRODUCTS
    // =========================================================

    public List<Product> getAllProducts() {

        return productRepository.findAll();
    }


    // =========================================================
    // GET PRODUCT BY ID
    // =========================================================

    public Optional<Product> getProductById(
            Long id) {

        return productRepository.findById(id);
    }


    // =========================================================
    // ADD PRODUCT WITHOUT IMAGE
    // =========================================================

    public Product addProduct(
            Product product) {

        validateProduct(product);

        return productRepository.save(product);
    }


    // =========================================================
    // ADD PRODUCT WITH IMAGE
    // =========================================================

    public Product addProductWithImage(
            Product product,
            MultipartFile image) {

        try {

            // -------------------------------------------------
            // Validate product
            // -------------------------------------------------

            validateProduct(product);


            // -------------------------------------------------
            // Validate image
            // -------------------------------------------------

            validateImage(image);


            // -------------------------------------------------
            // Get category folder
            // -------------------------------------------------

            String folder =
                    getCategoryFolder(
                            product.getCategory()
                    );


            // -------------------------------------------------
            // Create folder
            // -------------------------------------------------

            Path uploadDirectory =
                    productsImageRoot
                            .resolve(folder)
                            .normalize();


            Files.createDirectories(
                    uploadDirectory
            );


            // -------------------------------------------------
            // Generate safe unique filename
            // -------------------------------------------------

            String fileName =
                    generateFileName(
                            image
                    );


            // -------------------------------------------------
            // Final image location
            // -------------------------------------------------

            Path imagePath =
                    uploadDirectory
                            .resolve(fileName)
                            .normalize();


            // -------------------------------------------------
            // Make sure image remains inside products folder
            // -------------------------------------------------

            if (!imagePath.startsWith(
                    uploadDirectory
            )) {

                throw new RuntimeException(
                        "Invalid image path"
                );
            }


            // -------------------------------------------------
            // Save image
            // -------------------------------------------------

            Files.copy(
                    image.getInputStream(),
                    imagePath,
                    StandardCopyOption.REPLACE_EXISTING
            );


            // -------------------------------------------------
            // Store RELATIVE path in database
            // -------------------------------------------------

            String relativeImagePath =
                    "images/products/"
                            + folder
                            + "/"
                            + fileName;


            product.setImageUrl(
                    relativeImagePath
            );


            // -------------------------------------------------
            // Save product
            // -------------------------------------------------

            return productRepository.save(
                    product
            );

        } catch (IOException e) {

            throw new RuntimeException(
                    "Failed to save product image",
                    e
            );
        }
    }


    // =========================================================
    // UPDATE PRODUCT WITHOUT IMAGE
    // =========================================================
    //
    // Existing image is NOT changed.
    // =========================================================

    public Product updateProduct(
            Long id,
            Product updatedProduct) {

        return productRepository.findById(id)
                .map(product -> {

                    product.setName(
                            updatedProduct.getName()
                    );

                    product.setCategory(
                            updatedProduct.getCategory()
                    );

                    product.setDescription(
                            updatedProduct.getDescription()
                    );

                    product.setAvailable(
                            updatedProduct.isAvailable()
                    );

                    // IMPORTANT:
                    //
                    // Do NOT update imageUrl here.
                    //
                    // Existing image remains unchanged.

                    return productRepository.save(
                            product
                    );

                })
                .orElse(null);
    }


    // =========================================================
    // UPDATE PRODUCT WITH OPTIONAL IMAGE
    // =========================================================

    public Product updateProductWithImage(
            Long id,
            Product updatedProduct,
            MultipartFile image) {

        try {

            // -------------------------------------------------
            // Find existing product
            // -------------------------------------------------

            Optional<Product> optionalProduct =
                    productRepository.findById(id);


            if (optionalProduct.isEmpty()) {

                return null;
            }


            Product existingProduct =
                    optionalProduct.get();


            // -------------------------------------------------
            // Save old image path
            // -------------------------------------------------

            String oldImagePath =
                    existingProduct.getImageUrl();


            // -------------------------------------------------
            // Update normal fields
            // -------------------------------------------------

            existingProduct.setName(
                    updatedProduct.getName()
            );

            existingProduct.setCategory(
                    updatedProduct.getCategory()
            );

            existingProduct.setDescription(
                    updatedProduct.getDescription()
            );

            existingProduct.setAvailable(
                    updatedProduct.isAvailable()
            );


            // -------------------------------------------------
            // IMAGE WAS SELECTED
            // -------------------------------------------------

            if (image != null &&
                    !image.isEmpty()) {


                // ---------------------------------------------
                // Validate image
                // ---------------------------------------------

                validateImage(image);


                // ---------------------------------------------
                // Determine new category folder
                // ---------------------------------------------

                String folder =
                        getCategoryFolder(
                                updatedProduct.getCategory()
                        );


                // ---------------------------------------------
                // Create new directory
                // ---------------------------------------------

                Path uploadDirectory =
                        productsImageRoot
                                .resolve(folder)
                                .normalize();


                Files.createDirectories(
                        uploadDirectory
                );


                // ---------------------------------------------
                // Generate new filename
                // ---------------------------------------------

                String fileName =
                        generateFileName(
                                image
                        );


                // ---------------------------------------------
                // New image path
                // ---------------------------------------------

                Path newImagePath =
                        uploadDirectory
                                .resolve(fileName)
                                .normalize();


                // ---------------------------------------------
                // Security check
                // ---------------------------------------------

                if (!newImagePath.startsWith(
                        uploadDirectory
                )) {

                    throw new RuntimeException(
                            "Invalid image path"
                    );
                }


                // ---------------------------------------------
                // Save new image
                // ---------------------------------------------

                Files.copy(
                        image.getInputStream(),
                        newImagePath,
                        StandardCopyOption.REPLACE_EXISTING
                );


                // ---------------------------------------------
                // Store new database path
                // ---------------------------------------------

                String newRelativePath =
                        "images/products/"
                                + folder
                                + "/"
                                + fileName;


                existingProduct.setImageUrl(
                        newRelativePath
                );


                // ---------------------------------------------
                // Delete old image
                //
                // Only after new image has successfully saved.
                // ---------------------------------------------

                deleteImageFile(
                        oldImagePath
                );
            }


            // -------------------------------------------------
            // Save updated product
            // -------------------------------------------------

            return productRepository.save(
                    existingProduct
            );

        } catch (IOException e) {

            throw new RuntimeException(
                    "Failed to update product image",
                    e
            );
        }
    }


    // =========================================================
    // DELETE PRODUCT
    // =========================================================

    public boolean deleteProduct(
            Long id) {

        Optional<Product> optionalProduct =
                productRepository.findById(id);


        if (optionalProduct.isEmpty()) {

            return false;
        }


        Product product =
                optionalProduct.get();


        // -------------------------------------------------
        // Delete image from frontend
        // -------------------------------------------------

        deleteImageFile(
                product.getImageUrl()
        );


        // -------------------------------------------------
        // Delete database record
        // -------------------------------------------------

        productRepository.delete(
                product
        );


        return true;
    }


   // =========================================================
// CATEGORY FOLDER
// =========================================================

private String getCategoryFolder(String category) {

    if (category == null || category.isBlank()) {

        throw new IllegalArgumentException(
                "Product category is required"
        );
    }

    return switch (category) {

        // =====================================================
        // OLD CATEGORIES
        // =====================================================

        case "exotic-vegetables" ->
                "exotic";

        case "vegetables" ->
                "vegetables";

        case "pulses" ->
                "pulses";

        case "domestic-fruits" ->
                "fruits/domestic";

        case "exotic-fruits" ->
                "fruits/exotic";


        // =====================================================
        // NEW CATEGORIES
        // =====================================================

        case "leafy-vegetables" ->
                "leafy";

        case "chillies" ->
                "chillies";

        case "cabbage-cruciferous" ->
                "cabbage-cruciferous";

        case "beans-peas" ->
                "beans-peas";

        case "root-vegetables" ->
                "root-vegetables";

        case "other-vegetables" ->
                "other-vegetables";


        // =====================================================
        // BACKWARD COMPATIBILITY
        // =====================================================

        case "root-vegetables-others" ->
                "root-vegetables-others";


        // =====================================================
        // INVALID CATEGORY
        // =====================================================

        default ->
                throw new IllegalArgumentException(
                        "Invalid product category: "
                                + category
                );
    };
}

    // =========================================================
    // IMAGE VALIDATION
    // =========================================================

    private void validateImage(
            MultipartFile image) {

        if (image == null ||
                image.isEmpty()) {

            throw new IllegalArgumentException(
                    "Product image is required"
            );
        }


        String contentType =
                image.getContentType();


        if (contentType == null ||
                !contentType.startsWith("image/")) {

            throw new IllegalArgumentException(
                    "Only image files are allowed"
            );
        }
    }


    // =========================================================
    // GENERATE SAFE IMAGE FILE NAME
    // =========================================================
    //
    // We do NOT trust the original filename.
    //
    // Example:
    //
    // mango.jpg
    //
    // becomes:
    //
    // mango-1724321234567.jpg
    //
    // This prevents two products with the same image name
    // from overwriting each other.
    // =========================================================

    private String generateFileName(
            MultipartFile image) {

        String originalFileName =
                image.getOriginalFilename();


        if (originalFileName == null ||
                originalFileName.isBlank()) {

            throw new IllegalArgumentException(
                    "Invalid image filename"
            );
        }


        String cleanName =
                Paths.get(
                        originalFileName
                )
                .getFileName()
                .toString();


        String extension = "";


        int dotIndex =
                cleanName.lastIndexOf(".");


        if (dotIndex >= 0) {

            extension =
                    cleanName.substring(
                            dotIndex
                    )
                    .toLowerCase();
        }


        // -------------------------------------------------
        // Allow only common image extensions
        // -------------------------------------------------

        if (!extension.equals(".jpg") &&
                !extension.equals(".jpeg") &&
                !extension.equals(".png") &&
                !extension.equals(".webp") &&
                !extension.equals(".gif")) {

            throw new IllegalArgumentException(
                    "Unsupported image format. "
                            + "Use JPG, JPEG, PNG, WEBP or GIF."
            );
        }


        // -------------------------------------------------
        // Clean base filename
        // -------------------------------------------------

        String baseName =
                dotIndex >= 0
                        ? cleanName.substring(
                                0,
                                dotIndex
                        )
                        : cleanName;


        baseName =
                baseName
                        .replaceAll(
                                "[^a-zA-Z0-9_-]",
                                "-"
                        );


        if (baseName.isBlank()) {

            baseName = "product";
        }


        // -------------------------------------------------
        // Add timestamp
        // -------------------------------------------------

        return baseName
                + "-"
                + System.currentTimeMillis()
                + extension;
    }


    // =========================================================
    // DELETE IMAGE FILE
    // =========================================================

    private void deleteImageFile(
            String imageUrl) {

        if (imageUrl == null ||
                imageUrl.isBlank()) {

            return;
        }


        try {

            // -------------------------------------------------
            // Database stores:
            //
            // images/products/vegetables/tomato-123.jpg
            //
            // Remove "images/products/"
            // -------------------------------------------------

            String relativePath =
                    imageUrl;


            String prefix =
                    "images/products/";


            if (relativePath.startsWith(prefix)) {

                relativePath =
                        relativePath.substring(
                                prefix.length()
                        );
            }


            // -------------------------------------------------
            // Resolve inside products folder
            // -------------------------------------------------

            Path imagePath =
                    productsImageRoot
                            .resolve(relativePath)
                            .normalize();


            // -------------------------------------------------
            // Security check
            // -------------------------------------------------

            if (!imagePath.startsWith(
                    productsImageRoot
            )) {

                return;
            }


            // -------------------------------------------------
            // Delete if exists
            // -------------------------------------------------

            Files.deleteIfExists(
                    imagePath
            );

        } catch (IOException e) {

            // Do not stop database deletion just because
            // an old image could not be deleted.
            //
            // Log the problem.
            System.err.println(
                    "Could not delete image: "
                            + imageUrl
            );

            e.printStackTrace();
        }
    }


    // =========================================================
    // PRODUCT VALIDATION
    // =========================================================

    private void validateProduct(
            Product product) {

        if (product == null) {

            throw new IllegalArgumentException(
                    "Product cannot be null"
            );
        }


        if (product.getName() == null ||
                product.getName().isBlank()) {

            throw new IllegalArgumentException(
                    "Product name is required"
            );
        }


        if (product.getCategory() == null ||
                product.getCategory().isBlank()) {

            throw new IllegalArgumentException(
                    "Product category is required"
            );
        }


        // Validate category
        getCategoryFolder(
                product.getCategory()
        );
    }
}