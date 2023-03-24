package com.webshop.webshopbackend.domain.DTO;

import lombok.Data;

@Data
public class UserDTO {

    private String id;
    private String name;
    private String lastName;
    private String email;
    private int strikes;
}
