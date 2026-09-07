package com.dpdagrofresh.backend.service;
import com.dpdagrofresh.backend.model.Order;
import com.dpdagrofresh.backend.repository.OrderRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
@Service
public class OrderService {
    private final OrderRepository orderRepository;
    // ============================================================
    // CONSTRUCTOR
    // ============================================================
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }
    // ============================================================
    // GET ALL ORDERS
    // ============================================================
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }
    // ============================================================
    // GET ORDER BY ID
    // ============================================================
    public Optional<Order> getOrderById(Long id) {
        return orderRepository.findById(id);
    }// ============================================================
    // ADD / CREATE ORDER
    // ============================================================
    public Order addOrder(Order order) {
        return orderRepository.save(order);
    } // ============================================================
    // UPDATE ORDER
    // ============================================================
    public Order updateOrder(
            Long id,
            Order updatedOrder
    ) {
        return orderRepository.findById(id)
                .map(existingOrder -> {
                    /*
                     * IMPORTANT:
                     *
                     * The exact fields that can be updated
                     * depend on the fields present inside
                     * your Order.java entity.
                     *
                     * For now we are using the setters that
                     * will normally be present in the Order
                     * entity.
                     */
                    if (updatedOrder.getProductName() != null) {
                        existingOrder.setProductName(
                                updatedOrder.getProductName()
                        );
                    }
                    if (updatedOrder.getQuantity() != null) {

                        existingOrder.setQuantity(
                                updatedOrder.getQuantity()
                        );
                    }
                    if (updatedOrder.getCustomerName() != null) {
                        existingOrder.setCustomerName(
                                updatedOrder.getCustomerName()
                        );
                    }
                    if (updatedOrder.getCustomerPhone() != null) {

                        existingOrder.setCustomerPhone(
                                updatedOrder.getCustomerPhone()
                        );
                    }
                    if (updatedOrder.getDeliveryLocation() != null) {

                        existingOrder.setDeliveryLocation(
                                updatedOrder.getDeliveryLocation()
                        );
                    }
                    if (updatedOrder.getDeliveryAddress() != null) {

                        existingOrder.setDeliveryAddress(
                                updatedOrder.getDeliveryAddress()
                        );
                    }

                    if (updatedOrder.getAdditionalDetails() != null) {

                        existingOrder.setAdditionalDetails(
                                updatedOrder.getAdditionalDetails()
                        );
                    }
                    if (updatedOrder.getStatus() != null) {

                        existingOrder.setStatus(
                                updatedOrder.getStatus()
                        );
                    }
                    return orderRepository.save(existingOrder);
                })
                .orElse(null);
    }
    // ============================================================
    // DELETE ORDER
    // ============================================================
    public boolean deleteOrder(Long id) {
        if (!orderRepository.existsById(id)) {
            return false;
        }
        orderRepository.deleteById(id);
        return true;
    }}