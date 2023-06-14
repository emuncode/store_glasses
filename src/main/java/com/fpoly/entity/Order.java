package com.fpoly.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "orders")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "userid")
    private int userId;

    @Column(name = "totalamount")
    private double totalOrders;

    @Column(name = "shippingaddress")
    private String shippingAddress;

    @Column(name = "orderdate")
    private Date orderDate = new Date();

    @Column(name = "paymentmethodid")
    private Integer paymentmethodId;

    @Column(name = "statusid")
    private String statusId;

    @ManyToOne
    @JoinColumn(name = "statusid", insertable = false, updatable = false)
    @JsonIgnore
    Status status;

    @ManyToOne
    @JoinColumn(name = "userid", insertable = false, updatable = false)
    @JsonIgnore
    User user;

    @ManyToOne
    @JoinColumn(name = "paymentmethodid", insertable = false, updatable = false)
    @JsonIgnore
    PaymentMethod paymentMethod;

    @OneToMany(mappedBy = "order")
    List<OrderDetail> orderDetails;
}
