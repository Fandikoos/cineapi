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

    private long id;
    private String screenName;

    private float price;
    private boolean isSoldOut;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    private List<ShowtimeOutDto> showtimeIds;
}
