package com.dpdagrofresh.backend.controller;

import com.dpdagrofresh.backend.model.Offer;
import com.dpdagrofresh.backend.service.OfferService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/offers")
@CrossOrigin(origins = "*")
public class OfferController {

    private final OfferService offerService;

    public OfferController(OfferService offerService) {
        this.offerService = offerService;
    }

    @GetMapping
    public ResponseEntity<List<Offer>> getAllOffers() {
        return ResponseEntity.ok(offerService.getAllOffers());
    }

    @GetMapping("/active")
    public ResponseEntity<List<Offer>> getActiveOffers() {
        return ResponseEntity.ok(offerService.getActiveOffers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Offer> getOfferById(@PathVariable Long id) {
        return offerService.getOfferById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping(
            value = {"/upload", "/with-image"},
            consumes = "multipart/form-data",
            produces = "application/json"
    )
    public ResponseEntity<?> addOffer(
            @RequestParam(value = "file", required = false) MultipartFile file,
            @RequestParam(value = "image", required = false) MultipartFile image) {

        try {
            MultipartFile uploadFile = (file != null) ? file : image;

            if (uploadFile == null || uploadFile.isEmpty()) {
                return ResponseEntity.badRequest().body("No image file uploaded.");
            }

            Offer savedOffer = offerService.addOfferWithImage(uploadFile);
            return ResponseEntity.ok(savedOffer);

        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body("Upload failed: " + e.getMessage());
        }
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<Offer> updateStatus(
            @PathVariable Long id,
            @RequestParam boolean active) {

        Offer offer = offerService.updateOfferStatus(id, active);
        if (offer == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(offer);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOffer(@PathVariable Long id) {
        boolean deleted = offerService.deleteOffer(id);
        if (!deleted) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }
}