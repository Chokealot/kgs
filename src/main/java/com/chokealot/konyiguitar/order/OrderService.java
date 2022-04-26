package com.chokealot.konyiguitar.order;

import com.chokealot.konyiguitar.customer.CustomerRepository;
import com.chokealot.konyiguitar.guitar.GuitarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@Service
public class OrderService {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    GuitarRepository guitarRepository;

    @Autowired
    OrderMapper mapper;

    public Order create(OrderModel model) {
        Order order = new Order();
        order.setCustomer(customerRepository.getById(model.getCustomerId()));
        order.setGuitar(guitarRepository.getById(model.getGuitarId()));
        return mapper.toDTO(orderRepository.save(mapper.fromDTO(order)));
    }

    public Order getOrder(String orderNumber) {
        return mapper.toDTO(orderRepository.findOrderEntitiesByOrderNumber(orderNumber));
    }

    public Stream<Order> getAllOrders() {
        return StreamSupport.stream(orderRepository.findAll().spliterator(), false)
                .map(mapper::toDTO);
    }

    public Order deleteOrder(String orderNumber) {
        Order orderToDelete = mapper.toDTO(orderRepository.findOrderEntitiesByOrderNumber(orderNumber));
        orderRepository.delete(mapper.fromDTO(orderToDelete));
        return orderToDelete;
    }
}
