package com.svalero.cineapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingInDto {

    private long id;
    private String number;
    private LocalDateTime bookingDate;
    private List<Long> showtimeIds;
}
