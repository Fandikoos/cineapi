package com.svalero.cineapi.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MovieInDto {

    @NotNull(message = "El nombre de la película es obligatorio")
    private String title;
    private String director;
    private String genre;
    private int duration;
    @NotNull(message = "La fecha de la película es obligatoria")
    private LocalDate releaseDate;

}
