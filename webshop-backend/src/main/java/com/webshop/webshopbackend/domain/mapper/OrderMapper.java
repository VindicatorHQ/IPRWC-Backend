package com.webshop.webshopbackend.domain.mapper;

import com.webshop.webshopbackend.domain.DAO.OrderDAO;
import com.webshop.webshopbackend.domain.DTO.OrderDTO;
import com.webshop.webshopbackend.domain.entity.Order;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class OrderMapper implements Mapper<Order, OrderDTO> {

    private final OrderDAO orderDAO;
    private final UserMapper userMapper;

    public OrderMapper(OrderDAO orderDAO, UserMapper userMapper) {
        this.orderDAO = orderDAO;
        this.userMapper = userMapper;
    }

    @Override
    public Order fromDTOToEntity(OrderDTO orderDTO) throws ParseException {
        if ( orderDTO == null ) {
            return null;
        }

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        Order order = new Order();

        order.setUser( userMapper.fromIdToEntity( orderDTO.getUserId() ) );
        order.setId( orderDTO.getId() );

        String dateString = orderDTO.getDate() ;
        java.util.Date date = sdf.parse(dateString);
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());
        order.setDate(sqlDate);

        return order;
    }

    @Override
    public OrderDTO fromEntityToDTO(Order order) {
        if ( order == null ) {
            return null;
        }

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        OrderDTO orderDTO = new OrderDTO();

        orderDTO.setId( order.getId() );
        orderDTO.setDate(sdf.format(order.getDate()));
        orderDTO.setUserId( order.getUser().getId() );

        return orderDTO;
    }

    @Override
    public Order fromIdToEntity(String id) {
        if ( id == null ) {
            return null;
        }

        return orderDAO.getById(id);
    }

}
