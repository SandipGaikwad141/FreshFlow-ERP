package com.dpdagrofresh.backend.model;

import jakarta.persistence.*;

@Entity
@Table(name = "orders")
public class Order {

    // ============================================================
    // PRIMARY KEY
    // ============================================================

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    // ============================================================
    // PRODUCT INFORMATION
    // ============================================================

    @Column(nullable = false)
    private String productName;

    private String specificProduct;

    private String quantity;


    // ============================================================
    // CUSTOMER INFORMATION
    // ============================================================

    @Column(nullable = false)
    private String customerName;

    @Column(nullable = false)
    private String customerPhone;

    private String customerEmail;

    private String language;


    // ============================================================
    // DELIVERY INFORMATION
    // ============================================================

    @Column(nullable = false)
    private String deliveryLocation;

    private String pincode;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String deliveryAddress;


    // ============================================================
    // ADDITIONAL INFORMATION
    // ============================================================

    @Column(columnDefinition = "TEXT")
    private String additionalDetails;


    // ============================================================
    // ORDER STATUS
    // ============================================================

    @Column(nullable = false)
    private String status = "NEW";


    // ============================================================
    // CONSTRUCTORS
    // ============================================================

    public Order() {
    }


    public Order(
            String productName,
            String specificProduct,
            String quantity,
            String customerName,
            String customerPhone,
            String customerEmail,
            String language,
            String deliveryLocation,
            String pincode,
            String deliveryAddress,
            String additionalDetails
    ) {

        this.productName = productName;
        this.specificProduct = specificProduct;
        this.quantity = quantity;

        this.customerName = customerName;
        this.customerPhone = customerPhone;
        this.customerEmail = customerEmail;
        this.language = language;

        this.deliveryLocation = deliveryLocation;
        this.pincode = pincode;
        this.deliveryAddress = deliveryAddress;

        this.additionalDetails = additionalDetails;

        this.status = "NEW";
    }


    // ============================================================
    // GETTER - ID
    // ============================================================

    public Long getId() {
        return id;
    }


    // ============================================================
    // SETTER - ID
    // ============================================================

    public void setId(Long id) {
        this.id = id;
    }


    // ============================================================
    // GETTER - PRODUCT NAME
    // ============================================================

    public String getProductName() {
        return productName;
    }


    // ============================================================
    // SETTER - PRODUCT NAME
    // ============================================================

    public void setProductName(String productName) {
        this.productName = productName;
    }


    // ============================================================
    // GETTER - SPECIFIC PRODUCT
    // ============================================================

    public String getSpecificProduct() {
        return specificProduct;
    }


    // ============================================================
    // SETTER - SPECIFIC PRODUCT
    // ============================================================

    public void setSpecificProduct(String specificProduct) {
        this.specificProduct = specificProduct;
    }


    // ============================================================
    // GETTER - QUANTITY
    // ============================================================

    public String getQuantity() {
        return quantity;
    }


    // ============================================================
    // SETTER - QUANTITY
    // ============================================================

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }


    // ============================================================
    // GETTER - CUSTOMER NAME
    // ============================================================

    public String getCustomerName() {
        return customerName;
    }


    // ============================================================
    // SETTER - CUSTOMER NAME
    // ============================================================

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }


    // ============================================================
    // GETTER - CUSTOMER PHONE
    // ============================================================

    public String getCustomerPhone() {
        return customerPhone;
    }


    // ============================================================
    // SETTER - CUSTOMER PHONE
    // ============================================================

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }


    // ============================================================
    // GETTER - CUSTOMER EMAIL
    // ============================================================

    public String getCustomerEmail() {
        return customerEmail;
    }


    // ============================================================
    // SETTER - CUSTOMER EMAIL
    // ============================================================

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }


    // ============================================================
    // GETTER - LANGUAGE
    // ============================================================

    public String getLanguage() {
        return language;
    }


    // ============================================================
    // SETTER - LANGUAGE
    // ============================================================

    public void setLanguage(String language) {
        this.language = language;
    }


    // ============================================================
    // GETTER - DELIVERY LOCATION
    // ============================================================

    public String getDeliveryLocation() {
        return deliveryLocation;
    }


    // ============================================================
    // SETTER - DELIVERY LOCATION
    // ============================================================

    public void setDeliveryLocation(String deliveryLocation) {
        this.deliveryLocation = deliveryLocation;
    }


    // ============================================================
    // GETTER - PINCODE
    // ============================================================

    public String getPincode() {
        return pincode;
    }


    // ============================================================
    // SETTER - PINCODE
    // ============================================================

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }


    // ============================================================
    // GETTER - DELIVERY ADDRESS
    // ============================================================

    public String getDeliveryAddress() {
        return deliveryAddress;
    }


    // ============================================================
    // SETTER - DELIVERY ADDRESS
    // ============================================================

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }


    // ============================================================
    // GETTER - ADDITIONAL DETAILS
    // ============================================================

    public String getAdditionalDetails() {
        return additionalDetails;
    }


    // ============================================================
    // SETTER - ADDITIONAL DETAILS
    // ============================================================

    public void setAdditionalDetails(String additionalDetails) {
        this.additionalDetails = additionalDetails;
    }


    // ============================================================
    // GETTER - STATUS
    // ============================================================

    public String getStatus() {
        return status;
    }


    // ============================================================
    // SETTER - STATUS
    // ============================================================

    public void setStatus(String status) {
        this.status = status;
    }
}