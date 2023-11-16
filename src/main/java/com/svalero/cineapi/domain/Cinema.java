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
    //Obligatorio
    private int capacity;
    @Column
    //Obligatorio
    private String name;
    @Column
    private String location;
    @Column
    private float rating;
    @Column(name = "opening_date")
    private LocalDate openingDate;
}
