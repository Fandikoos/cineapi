package com.svalero.cineapi.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInDto {

    @NotNull(message = "El nombre de usuario es obligatorio")
    private String username;
    @NotNull
    @Size(min = 4, max = 20)
    private String password;

    @Email
    private String email;

    @NotNull(message = "El telefono es obligatorio")
    private int phone;


}
