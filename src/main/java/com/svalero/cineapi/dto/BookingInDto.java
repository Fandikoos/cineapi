package com.svalero.cineapi.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingInDto {

    @NotNull(message = "Es obligatorio establecer número de fila")
    private int row;
    @NotNull(message = "Es obligatorio establecer número de asiento")
    private int seat;
    private boolean menu;
    private List<Long> showtimeIds;
}
