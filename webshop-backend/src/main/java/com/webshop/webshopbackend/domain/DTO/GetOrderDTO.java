package com.webshop.webshopbackend.domain.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.sql.Date;

@Data
public class GetOrderDTO {
    private String id;

    @NotNull(message = "Date cannot be empty.")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date date;

    @NotBlank(message = "User id cannot be empty.")
    private String userId;

    @NotBlank(message = "Product id cannot be empty.")
    private String productId;
}
