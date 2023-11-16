package com.svalero.cineapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShowtimeOutDto {

    private long id;
    private String screenName;
    private LocalDateTime startTime;
    private float price;
    private boolean isSoldOut;
    private LocalDateTime endTime;
}
