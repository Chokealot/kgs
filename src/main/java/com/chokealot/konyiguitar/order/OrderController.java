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
            logger.info(makeItPretty("Creating new order"));
            return ResponseEntity.ok(order);
        } catch (Exception e) {
            logger.warn(makeItPretty(e.getMessage()));
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{orderNumber}")
    public ResponseEntity<Order> getOrderByOrderNumber(@PathVariable String orderNumber) {
        try {
            Order order = service.getOrder(orderNumber);
            logger.info(makeItPretty("Found order "+order));
            return ResponseEntity.ok(order);
        } catch (IllegalArgumentException e) {
            logger.warn(makeItPretty(e.getMessage()));
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("")
    public ResponseEntity<Stream<Order>> getAllOrders() {
        try {
            Stream<Order> stream = service.getAllOrders();
            logger.info(makeItPretty("found stream: "+stream));
            return ResponseEntity.ok(stream);
        } catch (IllegalArgumentException e) {
            logger.warn(makeItPretty(e.getMessage()));
            return  ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{orderNumber}")
    public ResponseEntity<Order> deleteOrder(@PathVariable String orderNumber) {
        try {
            Order orderToDelete = service.getOrder(orderNumber);
            logger.info(makeItPretty("Deleting order: "+orderToDelete));
            return ResponseEntity.ok(service.deleteOrder(orderNumber));
        } catch (IllegalArgumentException e) {
            logger.warn(makeItPretty(e.getMessage()));
            return ResponseEntity.badRequest().build();
        }
    }
    public String makeItPretty(String message) {
        String msgLength = "|| "+message+" ||";
        String layer = "";
        for (int x=0; x <= msgLength.length(); x++) {
            layer += "*";
        }
        return "\n" + layer + "\n" + msgLength + "\n" + layer;
    }
}
