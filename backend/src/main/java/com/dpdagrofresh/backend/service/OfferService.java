package com.dpdagrofresh.backend.service;

import com.dpdagrofresh.backend.model.Offer;
import com.dpdagrofresh.backend.repository.OfferRepository;
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
public class OfferService {

    private final OfferRepository offerRepository;

    private final Path offersImageRoot = Paths.get("../frontend/images/offers")
            .toAbsolutePath()
            .normalize();

    public OfferService(OfferRepository offerRepository) {
        this.offerRepository = offerRepository;
    }

    public List<Offer> getAllOffers() {
        return offerRepository.findAll();
    }

    public List<Offer> getActiveOffers() {
        return offerRepository.findByActiveTrueOrderByIdDesc();
    }

    public Optional<Offer> getOfferById(Long id) {
        return offerRepository.findById(id);
    }

    public Offer addOfferWithImage(MultipartFile image) {
        try {
            validateImage(image);

            Files.createDirectories(offersImageRoot);

            String fileName = generateFileName(image);

            Path imagePath = offersImageRoot.resolve(fileName).normalize();

            if (!imagePath.startsWith(offersImageRoot)) {
                throw new RuntimeException("Invalid image path traversal attempt");
            }

            Files.copy(
                    image.getInputStream(),
                    imagePath,
                    StandardCopyOption.REPLACE_EXISTING
            );

            String relativeImagePath = "images/offers/" + fileName;

            Offer offer = new Offer(relativeImagePath);
            offer.setActive(true);

            return offerRepository.save(offer);

        } catch (IOException e) {
            throw new RuntimeException("Failed to save offer image", e);
        }
    }

    public boolean deleteOffer(Long id) {
        Optional<Offer> optionalOffer = offerRepository.findById(id);

        if (optionalOffer.isEmpty()) {
            return false;
        }

        Offer offer = optionalOffer.get();
        deleteImageFile(offer.getImageUrl());
        offerRepository.delete(offer);

        return true;
    }

    public Offer updateOfferStatus(Long id, boolean active) {
        return offerRepository.findById(id)
                .map(offer -> {
                    offer.setActive(active);
                    return offerRepository.save(offer);
                })
                .orElse(null);
    }

    private void validateImage(MultipartFile image) {
        if (image == null || image.isEmpty()) {
            throw new IllegalArgumentException("Offer image is required");
        }

        String contentType = image.getContentType();
        if (contentType == null || !contentType.startsWith("image/")) {
            throw new IllegalArgumentException("Only image files are allowed");
        }
    }

    private String generateFileName(MultipartFile image) {
        String originalFileName = image.getOriginalFilename();

        if (originalFileName == null || originalFileName.isBlank()) {
            throw new IllegalArgumentException("Invalid image filename");
        }

        String cleanName = Paths.get(originalFileName).getFileName().toString();
        String extension = "";

        int dotIndex = cleanName.lastIndexOf(".");
        if (dotIndex >= 0) {
            extension = cleanName.substring(dotIndex).toLowerCase();
        }

        if (!extension.equals(".jpg") &&
            !extension.equals(".jpeg") &&
            !extension.equals(".png") &&
            !extension.equals(".webp") &&
            !extension.equals(".gif")) {
            throw new IllegalArgumentException(
                    "Unsupported image format. Use JPG, JPEG, PNG, WEBP or GIF."
            );
        }

        String baseName = dotIndex >= 0 ? cleanName.substring(0, dotIndex) : cleanName;
        baseName = baseName.replaceAll("[^a-zA-Z0-9_-]", "-");

        if (baseName.isBlank()) {
            baseName = "offer";
        }

        return baseName + "-" + System.currentTimeMillis() + extension;
    }

    private void deleteImageFile(String imageUrl) {
        if (imageUrl == null || imageUrl.isBlank()) {
            return;
        }

        try {
            String prefix = "images/offers/";
            String relativePath = imageUrl;

            if (relativePath.startsWith(prefix)) {
                relativePath = relativePath.substring(prefix.length());
            }

            Path imagePath = offersImageRoot.resolve(relativePath).normalize();

            if (!imagePath.startsWith(offersImageRoot)) {
                return;
            }

            Files.deleteIfExists(imagePath);
        } catch (IOException e) {
            System.err.println("Could not delete offer image: " + imageUrl);
        }
    }
}