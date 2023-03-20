package com.webshop.webshopbackend.domain.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class OrderDTO {
    private String id;
    @NotNull(message = "Date cannot be empty.")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private String date;
    @NotBlank(message = "User id cannot be empty.")
    private String userId;
}
