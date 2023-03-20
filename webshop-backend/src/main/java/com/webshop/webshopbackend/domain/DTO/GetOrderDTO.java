package com.webshop.webshopbackend.domain.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.sql.Date;

@Data
public class GetOrderDTO {
    private String id;
    @NotNull(message = "Date in cannot be empty.")
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date date;
    private String username;
    private String userId;
}
