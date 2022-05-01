package com.chokealot.konyiguitar.order;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Stream;

@Controller
@RequestMapping("/api/v1/orders")
public class OrderController {

    Logger logger = LoggerFactory.getLogger(Logger.class);

    @Autowired
    private OrderService service;

    @PostMapping("")
    public ResponseEntity<Order> createOrder(@RequestBody OrderModel model) {
        try {
            Order order = service.create(model);
            logger.info("Creating new order");
            return ResponseEntity.ok(order);
        } catch (IllegalArgumentException e) {
            logger.warn(e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{orderNumber}")
    public ResponseEntity<Order> getOrderByOrderNumber(@PathVariable String orderNumber) {
        try {
            Order order = service.getOrder(orderNumber);
            logger.info("Found order "+order);
            return ResponseEntity.ok(order);
        } catch (IllegalArgumentException e) {
            logger.warn(e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("")
    public ResponseEntity<Stream<Order>> getAllOrders() {
        try {
            Stream<Order> stream = service.getAllOrders();
            logger.info("found stream: "+stream);
            return ResponseEntity.ok(stream);
        } catch (IllegalArgumentException e) {
            logger.warn(e.getMessage());
            return  ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{orderNumber}")
    public ResponseEntity<Order> deleteOrder(@PathVariable String orderNumber) {
        try {
            Order orderToDelete = service.getOrder(orderNumber);
            logger.info("Deleting order: "+orderToDelete);
            return ResponseEntity.ok(service.deleteOrder(orderNumber));
        } catch (IllegalArgumentException e) {
            logger.warn(e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }
}
