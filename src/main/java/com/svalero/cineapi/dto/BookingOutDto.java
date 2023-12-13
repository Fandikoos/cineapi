package com.svalero.cineapi.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingOutDto {

    //Añadir butaca, fila,
    private long id;
    private String number;
    private LocalDateTime bookingDate;
    private int row;
    private int seat;
    private boolean menu;
    private List<ShowtimeOutDto> showtimeIds;
}
