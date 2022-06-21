package com.example.springbootbackend.repository;

import com.example.springbootbackend.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    // get order have price > 10000000
    @Query(value = "SELECT * FROM orders where order_price > 10000000", nativeQuery = true)
    List<Order> findByOrderPrice();
    // get order have price > param price
    @Query(value = "SELECT * FROM orders where order_price > :price", nativeQuery = true)
    List<Order> findByOrderPrice(@Param("price") Long price);
}
