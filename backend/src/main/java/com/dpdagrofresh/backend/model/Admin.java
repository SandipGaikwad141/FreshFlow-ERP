package com.dpdagrofresh.backend.model;

import jakarta.persistence.*;

@Entity
@Table(name = "admins")
public class Admin {

    // ============================================================
    // PRIMARY KEY
    // ============================================================

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    // ============================================================
    // ADMIN USERNAME
    // ============================================================

    @Column(nullable = false, unique = true)
    private String username;


    // ============================================================
    // ADMIN PASSWORD
    // ============================================================

    @Column(nullable = false)
    private String password;


    // ============================================================
    // CONSTRUCTOR
    // ============================================================

    public Admin() {
    }


    // ============================================================
    // CONSTRUCTOR WITH PARAMETERS
    // ============================================================

    public Admin(String username, String password) {

        this.username = username;

        this.password = password;
    }


    // ============================================================
    // GET ID
    // ============================================================

    public Long getId() {

        return id;
    }


    // ============================================================
    // SET ID
    // ============================================================

    public void setId(Long id) {

        this.id = id;
    }


    // ============================================================
    // GET USERNAME
    // ============================================================

    public String getUsername() {

        return username;
    }


    // ============================================================
    // SET USERNAME
    // ============================================================

    public void setUsername(String username) {

        this.username = username;
    }


    // ============================================================
    // GET PASSWORD
    // ============================================================

    public String getPassword() {

        return password;
    }


    // ============================================================
    // SET PASSWORD
    // ============================================================

    public void setPassword(String password) {

        this.password = password;
    }
}