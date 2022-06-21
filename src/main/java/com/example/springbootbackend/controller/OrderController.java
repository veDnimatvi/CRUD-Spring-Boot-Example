package com.example.springbootbackend.controller;

import com.example.springbootbackend.exception.ResourceNotFoundExeption;
import com.example.springbootbackend.model.Order;
import com.example.springbootbackend.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/")
public class OrderController {
    @Autowired
    private OrderRepository orderRepository;

    //get all order api
    @GetMapping("/order")
    public List<Order> getAllOrders(){ return orderRepository.findAll();}

    // create order api

    @PostMapping("/order")
    public Order createOrder(@RequestBody Order order) { return orderRepository.save(order);}

    // get order by id
    @GetMapping("/order/{id}")
//    public List<Order> getOrderById(@PathVariable Long id){ return orderRepository.findAllById(Collections.singleton(id));}

    public ResponseEntity<Order> getOrderById(@PathVariable Long id){
        Order order = orderRepository.findById(id).orElseThrow(() -> new ResourceNotFoundExeption("Order not exist with id :" + id));
        return ResponseEntity.ok(order);
    }


    //update order api
    @PutMapping("/order/{id}")

    public ResponseEntity<Order> updateOrderById(@PathVariable Long id, @RequestBody Order orderRequest){
        Order order = orderRepository.findById(id).orElseThrow(() -> new ResourceNotFoundExeption("Order not exist with id :" + id));

        order.setOrderName(orderRequest.getOrderName());
        order.setOrderPrice(orderRequest.getOrderPrice());
        order.setOrderQuantity(orderRequest.getOrderQuantity());

        orderRepository.save(order);
        return ResponseEntity.ok(order);
    }

    //delete order api
    @DeleteMapping("/order/{id}")
    public ResponseEntity deleteOrderById(@PathVariable Long id){
        Order order = orderRepository.findById(id).orElseThrow(() -> new ResourceNotFoundExeption("Order not exist with id :" + id));

        orderRepository.delete(order);
        Map<String, Boolean> response = new HashMap<>();

        response.put("delete", Boolean.TRUE);

        return ResponseEntity.ok(response);
    }

    // get order > 10000000 api

    @GetMapping("/order/price")
    public List<Order> getOrderMax10P(){
        return orderRepository.findByOrderPrice();
    }

    //get order > pathvariable price

    @GetMapping("/order/price/{price}")
    public List<Order> getOrderMax10P(@PathVariable Long price){
        return orderRepository.findByOrderPrice(price);
    }



}
