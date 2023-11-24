package com.svalero.cineapi.controller;

import com.svalero.cineapi.domain.Cinema;
import com.svalero.cineapi.domain.ErrorResponse;
import com.svalero.cineapi.dto.CinemaInDto;
import com.svalero.cineapi.dto.CinemaOutDto;
import com.svalero.cineapi.exception.CinemaNotFoundException;
import com.svalero.cineapi.service.CinemaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class CinemaController {

    @Autowired
    private CinemaService cinemaService;

    @GetMapping("/cinemas")
    public ResponseEntity<List<Cinema>> getAll(@RequestParam(defaultValue = "") String name, @RequestParam(defaultValue = "0") int capacity, @RequestParam(defaultValue = "0") float rating){
        if (!(name.isEmpty()) && (capacity != 0) && (rating != 0)){
            return new ResponseEntity<>(cinemaService.findByNameAndCapacityAndRating(name, capacity, rating), HttpStatus.OK);
        }
        return new ResponseEntity<>(cinemaService.findAll(), HttpStatus.OK);
    }

    @PostMapping("/cinemas")
    public ResponseEntity<CinemaOutDto> saveCinema(@Valid @RequestBody CinemaInDto cinema){
        CinemaOutDto newCinema = cinemaService.saveCinema(cinema);
        return new ResponseEntity<>(newCinema, HttpStatus.CREATED);
    }

    @DeleteMapping("/cinema/{cinemaId}")
    public ResponseEntity<Void> removeCinema(@PathVariable long cinemaId) throws CinemaNotFoundException{
        cinemaService.removeCinema(cinemaId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/cinema/{cinemaId}")
    public void modifyCinema(@RequestBody Cinema cinema, @PathVariable long cinemaId){
        cinemaService.modifyCinema(cinema, cinemaId);
    }

    @ExceptionHandler(CinemaNotFoundException.class)
    public ResponseEntity<ErrorResponse> cinemaNotFoundException(CinemaNotFoundException cnfe){
        ErrorResponse errorResponse = ErrorResponse.generalError(404, cnfe.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleException(MethodArgumentNotValidException manve) {
        Map<String, String> errors = new HashMap<>();
        manve.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String message = error.getDefaultMessage();
            errors.put(fieldName, message);
        });

        return ResponseEntity.badRequest().body(ErrorResponse.validationError(errors));
    }
}
