package com.webshop.webshopbackend.domain.mapper;

import com.webshop.webshopbackend.domain.DAO.OrderDAO;
import com.webshop.webshopbackend.domain.DTO.OrderDTO;
import com.webshop.webshopbackend.domain.entity.Order;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;

@Component
public class OrderMapper implements Mapper<Order, OrderDTO> {

    private final OrderDAO orderDAO;
    private final UserMapper userMapper;
    private final ProductMapper productMapper;

    public OrderMapper(OrderDAO orderDAO, UserMapper userMapper, ProductMapper productMapper) {
        this.orderDAO = orderDAO;
        this.userMapper = userMapper;
        this.productMapper = productMapper;
    }

    @Override
    public Order fromDTOToEntity(OrderDTO orderDTO) throws ParseException {
        if (orderDTO == null) {
            return null;
        }

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        Order order = new Order();

        order.setId(orderDTO.getId());

        String dateString = orderDTO.getDate();
        java.util.Date date = sdf.parse(dateString);
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());
        order.setDate(sqlDate);

        order.setUser(userMapper.fromIdToEntity(orderDTO.getUserId()));
        order.setProduct(productMapper.fromIdToEntity(orderDTO.getProductId()));

        return order;
    }

    @Override
    public OrderDTO fromEntityToDTO(Order order) {
        if (order == null) {
            return null;
        }

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        OrderDTO orderDTO = new OrderDTO();

        orderDTO.setId(order.getId());
        orderDTO.setDate(sdf.format(order.getDate()));
        orderDTO.setUserId(order.getUser().getId());
        orderDTO.setProductId(order.getProduct().getId());

        return orderDTO;
    }

    @Override
    public Order fromIdToEntity(String id) {
        if (id == null) {
            return null;
        }

        return orderDAO.getById(id);
    }

}
