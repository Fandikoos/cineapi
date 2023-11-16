package com.svalero.cineapi.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "showtimes")
public class Showtime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //Obligatorio
    private long id;
    @Column
    private String screenName;
    @Column
    //Obligatorio
    private float price;
    @Column
    private boolean isSoldOut;
    @Column(name = "start_time")
    private LocalDateTime startTime;
    @Column(name = "end_time")
    private LocalDateTime endTime;

    @ManyToMany(mappedBy = "showtimes")
    private List<Booking> bookings;
}
