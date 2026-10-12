package com.dpdagrofresh.backend.controller;

import com.dpdagrofresh.backend.model.Admin;
import com.dpdagrofresh.backend.service.AdminService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin(origins = "*", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.OPTIONS})
public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    // ============================================================
    // CREATE ADMIN
    // ============================================================

    @PostMapping("/create")
    public ResponseEntity<?> createAdmin(
            @RequestParam String username,
            @RequestParam String password
    ) {

        try {

            Admin admin =
                    adminService.createAdmin(
                            username,
                            password
                    );

            return ResponseEntity.ok(admin);

        } catch (RuntimeException e) {

            return ResponseEntity
                    .badRequest()
                    .body(e.getMessage());
        }
    }


    // ============================================================
    // LOGIN
    // ============================================================

    @PostMapping("/login")
    public ResponseEntity<?> login(
            @RequestParam String username,
            @RequestParam String password
    ) {

        boolean authenticated =
                adminService.login(
                        username,
                        password
                );

        if (!authenticated) {

            return ResponseEntity
                    .status(401)
                    .body("Invalid username or password");
        }

        return ResponseEntity.ok(
                "Login successful"
        );
    }


    // ============================================================
    // RESET PASSWORD (ADDED)
    // ============================================================

    @PostMapping("/reset-password")
    public ResponseEntity<?> resetPassword(@RequestBody Map<String, String> request) {
        String username = request.get("username");
        String newPassword = request.get("newPassword");

        if (username == null || newPassword == null || username.isBlank() || newPassword.isBlank()) {
            return ResponseEntity.badRequest().body("Username and new password are required.");
        }

        try {
            // Update password using your adminService logic
            boolean updated = adminService.resetPassword(username, newPassword);

            if (!updated) {
                return ResponseEntity.status(404).body("Admin user not found.");
            }

            return ResponseEntity.ok("Password updated successfully!");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error resetting password: " + e.getMessage());
        }
    }
}