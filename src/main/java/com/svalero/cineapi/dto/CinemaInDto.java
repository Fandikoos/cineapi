package com.svalero.cineapi.dto;

import jakarta.persistence.Column;
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
    private int capacity;
    //private String location;
    private Double latitude;
    private Double longitude;
    private float rating;
}
