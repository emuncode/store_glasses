package com.fpoly.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "orderdetail")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "orderid")
    private int orderId;

    @Column(name = "cartid")
    private Integer cartId;

    @Column(name = "price")
    private double price;

    @Column(name = "quantity")
    private int quantity;

    @ManyToOne
    @JoinColumn(name = "orderid", insertable = false, updatable = false)
    @JsonIgnore
    Order order;

    @ManyToOne
    @JoinColumn(name = "cartid", insertable = false, updatable = false)
    @JsonIgnore
    Cart cart;
}
