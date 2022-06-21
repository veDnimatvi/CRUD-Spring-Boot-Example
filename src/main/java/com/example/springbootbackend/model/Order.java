package com.example.springbootbackend.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name= "orders")
@Data
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long orderId;

    @Column(name = "order_name")
    private String orderName;

    @Column(name = "order_price")
    private Integer orderPrice;

    @Column(name = "order_quantity")
    private Integer orderQuantity;
}
