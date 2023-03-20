package com.webshop.webshopbackend.controller;


import com.webshop.webshopbackend.domain.DAO.UserDAO;
import com.webshop.webshopbackend.domain.DTO.UserDTO;
import com.webshop.webshopbackend.domain.entity.User;
import com.webshop.webshopbackend.domain.mapper.UserMapper;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/user")
public class UserController {

    private final UserDAO userDAO;
    private final UserMapper userMapper;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public UserDTO createUser(@RequestBody @Valid UserDTO userDTO){
        User userRequest = userMapper.fromDTOToEntity(userDTO);
        User user = this.userDAO.saveToDatabase(userRequest);

        return userMapper.fromEntityToDTO(user);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public List<UserDTO> getAllUsers(){
        return userDAO.getAll().stream().map(userMapper::fromEntityToDTO)
                .collect(Collectors.toList());
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public UserDTO getUserByID(@PathVariable String id){
        User user = this.userDAO.getById(id);

        return userMapper.fromEntityToDTO(user);
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public String deleteUser(@PathVariable String id) {
        this.userDAO.delete(id);

        return "User deleted.";
    }

    @GetMapping(value = "/info", produces = MediaType.APPLICATION_JSON_VALUE)
    public UserDTO getUserDetails(){
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = this.userDAO.getByEmail(userDetails.getUsername());

        return userMapper.fromEntityToDTO(user);
    }
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public UserDTO updateUser(@PathVariable String id, @RequestBody UserDTO userDTO) {
        userDTO.setId(id);
        User userRequest = userMapper.fromDTOToEntity(userDTO);
        User user = userDAO.update(id, userRequest);

        return userMapper.fromEntityToDTO(user);
    }
}
