package com.webshop.webshopbackend.domain.mapper;

import com.webshop.webshopbackend.domain.DAO.OrderDAO;
import com.webshop.webshopbackend.domain.DTO.GetOrderDTO;
import com.webshop.webshopbackend.domain.entity.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GetOrderMapper implements Mapper<Order, GetOrderDTO> {

    private final OrderDAO orderDAO;
    private final UserMapper userMapper;

    @Override
    public Order fromDTOToEntity(GetOrderDTO orderDTO) {
        if (orderDTO == null) {
            return null;
        }

        Order order = new Order();

        order.setUser(userMapper.fromIdToEntity(orderDTO.getUserId()));
        order.setId(orderDTO.getId());
        order.setDate(orderDTO.getDate());

        return order;
    }

    @Override
    public GetOrderDTO fromEntityToDTO(Order order) {
        if (order == null) {
            return null;
        }

        GetOrderDTO orderDTO = new GetOrderDTO();

        orderDTO.setId(order.getId());
        orderDTO.setDate(order.getDate());
        orderDTO.setUsername(order.getUser().getName());

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
