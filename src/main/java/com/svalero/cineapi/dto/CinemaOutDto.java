package com.svalero.cineapi.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CinemaOutDto {
    private long id;
    private String name;
    private int capacity;
    private double latitude;
    private double longitude;
    private float rating;
    private LocalDate openingDate;
}

