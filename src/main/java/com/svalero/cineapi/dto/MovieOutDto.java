package com.svalero.cineapi.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MovieOutDto {

    private long id;
    //Obligatorio
    private String title;
    private String director;
    private String genre;
    private int duration;
    private LocalDate releaseDate;
}
