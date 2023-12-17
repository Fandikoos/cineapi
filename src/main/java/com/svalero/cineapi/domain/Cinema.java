package com.svalero.cineapi.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "cinemas")
public class Cinema {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    private String name;
    @Column
    private int capacity;
    @Column
    private double latitude;
    @Column
    private double longitude;
    @Column
    private float rating;
    @Column(name = "opening_date")
    private LocalDate openingDate;
}
