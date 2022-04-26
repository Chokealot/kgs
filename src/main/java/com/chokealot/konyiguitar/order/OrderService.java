package com.chokealot.konyiguitar.order;

import com.chokealot.konyiguitar.customer.CustomerRepository;
import com.chokealot.konyiguitar.customer.CustomerService;
import com.chokealot.konyiguitar.guitar.GuitarRepository;
import com.chokealot.konyiguitar.guitar.GuitarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.UUID;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@Service
public class OrderService {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    CustomerService customerService;

    @Autowired
    GuitarService guitarService;

    @Autowired
    OrderMapper mapper;

    @Transactional
    public Order create(OrderModel model) {
        Order order = new Order();
        order.setCustomer(customerService.getCustomerById(model.getCustomerId()));
        order.setGuitar(guitarService.get(model.getGuitarId()));
        order.setOrderNumber(generateUuid());
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

    public String generateUuid() {
        String id = UUID.randomUUID().toString();
        return id;
    }
}
