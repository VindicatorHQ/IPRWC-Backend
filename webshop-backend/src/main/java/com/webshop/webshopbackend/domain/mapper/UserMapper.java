package com.webshop.webshopbackend.domain.mapper;

import com.webshop.webshopbackend.domain.DAO.UserDAO;
import com.webshop.webshopbackend.domain.DTO.UserDTO;
import com.webshop.webshopbackend.domain.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper implements Mapper<User, UserDTO> {

    private final UserDAO userDAO;

    public UserMapper(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public User fromDTOToEntity(UserDTO userDTO) {
        if ( userDTO == null ) {
            return null;
        }

        User user = new User();

        user.setId( userDTO.getId() );
        user.setName( userDTO.getName() );
        user.setLastName( userDTO.getLastName() );
        user.setEmail( userDTO.getEmail() );

        return user;
    }

    @Override
    public UserDTO fromEntityToDTO(User user) {
        if ( user == null ) {
            return null;
        }

        UserDTO userDTO = new UserDTO();

        userDTO.setId( user.getId() );
        userDTO.setName( user.getName() );
        userDTO.setLastName( user.getLastName() );
        userDTO.setEmail( user.getEmail() );

        return userDTO;
    }

    @Override
    public User fromIdToEntity(String id) {

        if ( id == null ) {
            return null;
        }

        return userDAO.getById(id);
    }
}
