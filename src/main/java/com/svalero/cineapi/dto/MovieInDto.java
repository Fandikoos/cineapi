package com.svalero.cineapi.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Min;
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
    @NotNull(message = "El nombre del director de la película es obligatorio")
    private String director;
    private String genre;
    @Min(value = 40, message = "La película tiene que durar mas de 40 minutos")
    private int duration;
    private LocalDate releaseDate;

}
