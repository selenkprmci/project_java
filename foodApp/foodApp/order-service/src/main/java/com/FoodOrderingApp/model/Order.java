package com.FoodOrderingApp.model;


import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long userId;

    @Temporal(TemporalType.TIMESTAMP)
    private Date orderDate = new Date();

    @Column(nullable = false)
    private String status;

    // Siparişin toplam tutarı. Gerçek bir uygulamada BigDecimal kullanılması tercih edilir
    private Double totalAmount;

    // Siparişe ait ürünlerin listesi. Bu, bir ilişkiyi temsil eder ve JPA'nın @OneToMany veya @ManyToMany gibi annotation'ları kullanılarak konfigüre edilebilir
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<OrderItem> orderItems;

    // JPA için gerekli boş bir constructor
    public Order() {
    }

    // Getter ve Setter metodları
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }
}
