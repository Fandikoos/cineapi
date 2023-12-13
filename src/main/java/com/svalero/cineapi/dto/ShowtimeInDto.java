package com.svalero.cineapi.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShowtimeInDto {

    @NotNull(message = "El nombre de sala es obligatorio")
    private String screenName;
    private LocalDateTime startTime;
    @NotNull(message = "El precio es obligatorio")
    @Min(value = 0, message = "El precio debe ser mayor que 0 euros")
    private float price;
    private boolean isSoldOut;
    private LocalDateTime endTime;
}
