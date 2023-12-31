package com.svalero.cineapi.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    //Obligatorio
    private String username;
    @Column
    //Obligatorio
    private String password;
    @Column
    //Obligatorio
    private String email;
    @Column
    private int phone;
    @Column(name = "registration_date")
    private LocalDate registrationDate;

    @OneToMany(mappedBy = "user")
    private List<Booking> bookings;
}
