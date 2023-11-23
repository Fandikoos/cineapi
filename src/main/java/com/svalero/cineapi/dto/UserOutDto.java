package com.svalero.cineapi.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserOutDto {

    private long id;

    private String username;

    private String password;

    private String email;
    private int phone;
    private LocalDate registrationDate;
}
