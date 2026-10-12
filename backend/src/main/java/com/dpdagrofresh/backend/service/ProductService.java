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

            validateProduct(product);

            validateImage(image);

            String folder =
                    getCategoryFolder(
                            product.getCategory()
                    );

            Path uploadDirectory =
                    productsImageRoot
                            .resolve(folder)
                            .normalize();

            Files.createDirectories(
                    uploadDirectory
            );

            String fileName =
                    generateFileName(
                            image
                    );

            Path imagePath =
                    uploadDirectory
                            .resolve(fileName)
                            .normalize();

            if (!imagePath.startsWith(
                    uploadDirectory
            )) {

                throw new RuntimeException(
                        "Invalid image path"
                );
            }

            // Saves raw byte stream directly to support all image formats
            Files.copy(
                    image.getInputStream(),
                    imagePath,
                    StandardCopyOption.REPLACE_EXISTING
            );

            String relativeImagePath =
                    "images/products/"
                            + folder
                            + "/"
                            + fileName;

            product.setImageUrl(
                    relativeImagePath
            );

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

            Optional<Product> optionalProduct =
                    productRepository.findById(id);

            if (optionalProduct.isEmpty()) {

                return null;
            }

            Product existingProduct =
                    optionalProduct.get();

            String oldImagePath =
                    existingProduct.getImageUrl();

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

            if (image != null &&
                    !image.isEmpty()) {

                validateImage(image);

                String folder =
                        getCategoryFolder(
                                updatedProduct.getCategory()
                        );

                Path uploadDirectory =
                        productsImageRoot
                                .resolve(folder)
                                .normalize();

                Files.createDirectories(
                        uploadDirectory
                );

                String fileName =
                        generateFileName(
                                image
                        );

                Path newImagePath =
                        uploadDirectory
                                .resolve(fileName)
                                .normalize();

                if (!newImagePath.startsWith(
                        uploadDirectory
                )) {

                    throw new RuntimeException(
                            "Invalid image path"
                    );
                }

                Files.copy(
                        image.getInputStream(),
                        newImagePath,
                        StandardCopyOption.REPLACE_EXISTING
                );

                String newRelativePath =
                        "images/products/"
                                + folder
                                + "/"
                                + fileName;

                existingProduct.setImageUrl(
                        newRelativePath
                );

                deleteImageFile(
                        oldImagePath
                );
            }

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

        deleteImageFile(
                product.getImageUrl()
        );

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

    // Convert to lowercase and trim spaces/hyphens for robust matching
    String cleanCategory = category.trim().toLowerCase().replaceAll("[\\s_]+", "-");

    return switch (cleanCategory) {

        case "exotic-vegetables", "exotic" ->
                "exotic";

        case "indian-vegetables", "vegetables" ->
                "vegetables";

        case "pulses" ->
                "pulses";

        case "domestic-fruits" ->
                "fruits/domestic";

        case "exotic-fruits" ->
                "fruits/exotic";

        case "leafy-vegetables", "leafy" ->
                "leafy";

        case "chillies" ->
                "chillies";

        case "cabbage-cruciferous", "cabbage-cruciferous-vegetables" ->
                "cabbage-cruciferous";

        case "beans-peas", "beans-&-peas" ->
                "beans-peas";

        case "root-vegetables", "root-vegetables-/-others" ->
                "root-vegetables";

        case "other-vegetables" ->
                "other-vegetables";

        case "root-vegetables-others" ->
                "root-vegetables-others";

        default ->
                throw new IllegalArgumentException(
                        "Invalid product category: " + category
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

        // Expanded list of allowed image extensions including .avif and .heic
        if (!extension.equals(".jpg") &&
                !extension.equals(".jpeg") &&
                !extension.equals(".png") &&
                !extension.equals(".webp") &&
                !extension.equals(".gif") &&
                !extension.equals(".avif") &&
                !extension.equals(".heic") &&
                !extension.equals(".bmp") &&
                !extension.equals(".tiff") &&
                !extension.equals(".svg")) {

            throw new IllegalArgumentException(
                    "Unsupported image format: " + extension
                            + ". Use JPG, JPEG, PNG, WEBP, GIF, AVIF, HEIC, BMP, TIFF, or SVG."
            );
        }

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

            Path imagePath =
                    productsImageRoot
                            .resolve(relativePath)
                            .normalize();

            if (!imagePath.startsWith(
                    productsImageRoot
            )) {

                return;
            }

            Files.deleteIfExists(
                    imagePath
            );

        } catch (IOException e) {

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

        getCategoryFolder(
                product.getCategory()
        );
    }
}