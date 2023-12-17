package com.svalero.cineapi.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "bookings")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    private String number;
    @Column(name = "booking_date")
    private LocalDateTime bookingDate;
    @Column
    private int row;
    @Column
    private int seat;
    @Column
    private boolean menu;

    @JsonBackReference("booking_user")
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @JsonBackReference("booking_showtime")
    @ManyToMany
    @JoinTable(name = "showtime_booking",
            joinColumns = @JoinColumn(name = "showtime_id"),
            inverseJoinColumns = @JoinColumn(name = "booking_id"))
    private List<Showtime> showtimes;
}
