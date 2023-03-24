package com.webshop.webshopbackend.controller;

import com.webshop.webshopbackend.domain.DAO.OrderDAO;
import com.webshop.webshopbackend.domain.DTO.OrderDTO;
import com.webshop.webshopbackend.domain.entity.Order;
import com.webshop.webshopbackend.domain.mapper.GetOrderMapper;
import com.webshop.webshopbackend.domain.mapper.OrderMapper;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/order")
public class OrderController {
    private final OrderDAO orderDAO;
    private final OrderMapper orderMapper;
    private final GetOrderMapper getOrderMapper;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public OrderDTO createOrder(@RequestBody @Valid OrderDTO orderDTO) throws ParseException {
        Order orderRequest = orderMapper.fromDTOToEntity(orderDTO);
        Order order = this.orderDAO.saveToDatabase(orderRequest);

        return orderMapper.fromEntityToDTO(order);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public List<OrderDTO> getAllOrders() {
        return orderDAO.getAll().stream().map(orderMapper::fromEntityToDTO)
                .collect(Collectors.toList());
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public OrderDTO getOrderById(@PathVariable String id) {
        Order order = this.orderDAO.getById(id);

        return orderMapper.fromEntityToDTO(order);
    }

    @GetMapping(value = "/search", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public List<Object> searchOrders(@RequestParam(name = "user", required = false) String user) {
        List<Order> searchedOrders;

        if (user != null) {
            searchedOrders = this.orderDAO.getByUser(user);

            return searchedOrders.stream().map(getOrderMapper::fromEntityToDTO)
                    .collect(Collectors.toList());
        } else {
            searchedOrders = this.orderDAO.getAll();
        }

        return searchedOrders.stream().map(orderMapper::fromEntityToDTO)
                .collect(Collectors.toList());
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public OrderDTO updateOrder(@PathVariable String id, @RequestBody OrderDTO orderDTO) throws ParseException {
        orderDTO.setId(id);
        Order orderRequest = orderMapper.fromDTOToEntity(orderDTO);
        Order order = orderDAO.update(id, orderRequest);

        return orderMapper.fromEntityToDTO(order);
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public String deleteOrder(@PathVariable String id) {
        this.orderDAO.delete(id);

        return "Order deleted.";
    }
}
