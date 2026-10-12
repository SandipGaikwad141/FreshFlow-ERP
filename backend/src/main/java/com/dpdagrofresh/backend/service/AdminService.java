package com.dpdagrofresh.backend.service;

import com.dpdagrofresh.backend.model.Admin;
import com.dpdagrofresh.backend.repository.AdminRepository;

import org.springframework.stereotype.Service;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.Optional;

@Service
public class AdminService {

    private final AdminRepository adminRepository;

    // ============================================================
    // CONSTRUCTOR
    // ============================================================

    public AdminService(
            AdminRepository adminRepository
    ) {

        this.adminRepository = adminRepository;

    }


    // ============================================================
    // CREATE ADMIN
    // ============================================================

    public Admin createAdmin(
            String username,
            String password
    ) {

        if (username == null || username.trim().isEmpty()) {

            throw new RuntimeException(
                    "Username is required"
            );
        }


        if (password == null || password.trim().isEmpty()) {

            throw new RuntimeException(
                    "Password is required"
            );
        }


        if (adminRepository.existsByUsername(username)) {

            throw new RuntimeException(
                    "Admin username already exists"
            );
        }


        Admin admin = new Admin();

        admin.setUsername(username);

        admin.setPassword(hashPassword(password));


        return adminRepository.save(admin);
    }


    // ============================================================
    // LOGIN
    // ============================================================

    public boolean login(
            String username,
            String password
    ) {

        if (
                username == null ||
                password == null
        ) {

            return false;
        }


        Admin admin =
                adminRepository
                        .findByUsername(username)
                        .orElse(null);


        if (admin == null) {

            return false;
        }


        return matchesPassword(password, admin.getPassword());
    }


    // ============================================================
    // RESET PASSWORD (ADDED)
    // ============================================================

    public boolean resetPassword(String username, String newPassword) {
        if (username == null || newPassword == null || newPassword.trim().isEmpty()) {
            return false;
        }

        Optional<Admin> adminOptional = adminRepository.findByUsername(username);

        if (adminOptional.isPresent()) {
            Admin admin = adminOptional.get();
            // Hash the new password using PBKDF2 scheme
            admin.setPassword(hashPassword(newPassword));
            adminRepository.save(admin);
            return true;
        }

        return false;
    }


    // ============================================================
    // PASSWORD HASHING HELPERS
    // ============================================================

    private String hashPassword(String password) {
        byte[] salt = new byte[16];
        new SecureRandom().nextBytes(salt);
        byte[] hash = deriveKey(password, salt);
        return Base64.getEncoder().encodeToString(salt) + ":"
                + Base64.getEncoder().encodeToString(hash);
    }

    private boolean matchesPassword(String password, String storedPassword) {
        try {
            String[] parts = storedPassword.split(":", 2);
            byte[] salt = Base64.getDecoder().decode(parts[0]);
            byte[] expected = Base64.getDecoder().decode(parts[1]);
            return MessageDigest.isEqual(expected, deriveKey(password, salt));
        } catch (IllegalArgumentException | ArrayIndexOutOfBoundsException ex) {
            return false;
        }
    }

    private byte[] deriveKey(String password, byte[] salt) {
        try {
            PBEKeySpec spec = new PBEKeySpec(
                    password.toCharArray(), salt, 120_000, 256);
            return SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256")
                    .generateSecret(spec).getEncoded();
        } catch (GeneralSecurityException ex) {
            throw new IllegalStateException("Unable to hash password", ex);
        }
    }
}