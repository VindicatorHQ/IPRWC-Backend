package com.webshop.webshopbackend.domain.DAO;

import com.webshop.webshopbackend.domain.entity.Order;
import com.webshop.webshopbackend.domain.repository.OrderRepository;
import com.webshop.webshopbackend.exception.NotFound;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrderDAO implements DAO<Order>{
    private final OrderRepository orderRepository;

    public OrderDAO(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public Order getById(String id) throws NotFound {
        return orderRepository.findById(id)
                .orElseThrow(() -> new NotFound("Order", id));
    }

    @Override
    public Order saveToDatabase(Order order) {
        return this.orderRepository.save(order);
    }

    @Override
    public Order update(String id, Order orderRequest) throws NotFound {
        Order order = this.orderRepository.findById(id)
                .orElseThrow(() -> new NotFound("Order", id));

        order.setDate(orderRequest.getDate());
        order.setUser(orderRequest.getUser());
        return orderRepository.save(order);
    }

    @Override
    public void delete(String id) throws NotFound {
        if(orderRepository.existsById(id))
            this.orderRepository.deleteById(id);
        else {
            throw new NotFound("Order", id);
        }
    }

    @Override
    public List<Order> getAll() {
        return this.orderRepository.findAll();
    }

}
