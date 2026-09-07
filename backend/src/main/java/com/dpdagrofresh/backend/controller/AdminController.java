package com.dpdagrofresh.backend.controller;

import com.dpdagrofresh.backend.model.Admin;
import com.dpdagrofresh.backend.service.AdminService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin(origins = "*")
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
}