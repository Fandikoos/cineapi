package com.svalero.cineapi.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CinemaInDto {
    @NotNull(message = "Es obligatorio establecer el nombre")
    private String name;
    @NotNull(message = "Es obligatorio establecer la capacidad")
    @Max(value = 500, message = "Los cines no pueden pueden tener una capacidad de 500 personas como máximo")
    private int capacity;
    private Double latitude;
    private Double longitude;
    private float rating;
}
