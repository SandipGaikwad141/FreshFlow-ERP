package com.dpdagrofresh.backend.controller;

import com.dpdagrofresh.backend.model.Order;
import com.dpdagrofresh.backend.service.OrderService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@CrossOrigin(origins = "*")
public class OrderController {

    private final OrderService orderService;


    // ============================================================
    // CONSTRUCTOR
    // ============================================================

    public OrderController(OrderService orderService) {

        this.orderService = orderService;
    }


    // ============================================================
    // GET ALL ORDERS
    //
    // GET:
    // /api/orders
    //
    // Used by ADMIN DASHBOARD
    // ============================================================

    @GetMapping
    public ResponseEntity<List<Order>> getAllOrders() {

        try {

            List<Order> orders =
                    orderService.getAllOrders();

            return ResponseEntity.ok(orders);

        } catch (Exception e) {

            e.printStackTrace();

            return ResponseEntity
                    .internalServerError()
                    .build();
        }
    }


    // ============================================================
    // GET ORDER BY ID
    //
    // GET:
    // /api/orders/{id}
    // ============================================================

    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrderById(
            @PathVariable Long id) {

        try {

            return orderService
                    .getOrderById(id)
                    .map(ResponseEntity::ok)
                    .orElseGet(
                            () -> ResponseEntity
                                    .notFound()
                                    .build()
                    );

        } catch (Exception e) {

            e.printStackTrace();

            return ResponseEntity
                    .internalServerError()
                    .build();
        }
    }


    // ============================================================
    // CREATE NEW CUSTOMER ENQUIRY / ORDER
    //
    // POST:
    // /api/orders
    //
    // THIS IS CALLED FROM:
    // CUSTOMER ENQUIRY FORM
    // ============================================================

    @PostMapping(
            consumes = "application/json",
            produces = "application/json"
    )
    public ResponseEntity<Order> addOrder(
            @RequestBody Order order) {

        try {

            // ----------------------------------------------------
            // BASIC VALIDATION
            // ----------------------------------------------------

            if (order == null) {

                return ResponseEntity
                        .badRequest()
                        .build();
            }


            if (order.getCustomerName() == null ||
                    order.getCustomerName().trim().isEmpty()) {

                return ResponseEntity
                        .badRequest()
                        .build();
            }


            if (order.getCustomerPhone() == null ||
                    order.getCustomerPhone().trim().isEmpty()) {

                return ResponseEntity
                        .badRequest()
                        .build();
            }


            if (order.getDeliveryLocation() == null ||
                    order.getDeliveryLocation().trim().isEmpty()) {

                return ResponseEntity
                        .badRequest()
                        .build();
            }


            if (order.getDeliveryAddress() == null ||
                    order.getDeliveryAddress().trim().isEmpty()) {

                return ResponseEntity
                        .badRequest()
                        .build();
            }


            // ----------------------------------------------------
            // ALWAYS CREATE NEW ENQUIRY AS NEW
            // ----------------------------------------------------

            order.setStatus("NEW");


            // ----------------------------------------------------
            // SAVE ORDER
            // ----------------------------------------------------

            Order savedOrder =
                    orderService.addOrder(order);


            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(savedOrder);

        } catch (Exception e) {

            e.printStackTrace();

            return ResponseEntity
                    .internalServerError()
                    .build();
        }
    }


    // ============================================================
    // UPDATE ORDER
    //
    // PUT:
    // /api/orders/{id}
    //
    // USED BY ADMIN DASHBOARD
    // ============================================================

    @PutMapping(
            value = "/{id}",
            consumes = "application/json",
            produces = "application/json"
    )
    public ResponseEntity<Order> updateOrder(
            @PathVariable Long id,
            @RequestBody Order updatedOrder) {

        try {

            if (updatedOrder == null) {

                return ResponseEntity
                        .badRequest()
                        .build();
            }


            Order order =
                    orderService.updateOrder(
                            id,
                            updatedOrder
                    );


            if (order == null) {

                return ResponseEntity
                        .notFound()
                        .build();
            }


            return ResponseEntity.ok(order);

        } catch (Exception e) {

            e.printStackTrace();

            return ResponseEntity
                    .internalServerError()
                    .build();
        }
    }


    // ============================================================
    // DELETE ORDER
    //
    // DELETE:
    // /api/orders/{id}
    //
    // USED BY ADMIN DASHBOARD
    // ============================================================

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(
            @PathVariable Long id) {

        try {

            boolean deleted =
                    orderService.deleteOrder(id);


            if (!deleted) {

                return ResponseEntity
                        .notFound()
                        .build();
            }


            return ResponseEntity
                    .noContent()
                    .build();

        } catch (Exception e) {

            e.printStackTrace();

            return ResponseEntity
                    .internalServerError()
                    .build();
        }
    }
}