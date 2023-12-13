package com.svalero.cineapi.controller;

import com.svalero.cineapi.domain.ErrorResponse;
import com.svalero.cineapi.domain.Showtime;
import com.svalero.cineapi.dto.ShowtimeInDto;
import com.svalero.cineapi.dto.ShowtimeOutDto;
import com.svalero.cineapi.exception.ShowtimeNotFoundException;
import com.svalero.cineapi.service.ShowtimeService;
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
public class ShowtimeController {

    @Autowired
    private ShowtimeService showtimeService;

    @GetMapping("/showtimes")
    public ResponseEntity<List<Showtime>> getAll(@RequestParam(defaultValue = "") String screenName, @RequestParam(defaultValue = "0") float price, @RequestParam(defaultValue = "false") boolean isSoldOut){
        if (!(screenName.isEmpty()) && (price != 0) && (!isSoldOut)){
            return new ResponseEntity<>(showtimeService.findByscreenNamePriceAndIsSoldOut(screenName, price, false), HttpStatus.OK);
        }
        return new ResponseEntity<>(showtimeService.findAll(), HttpStatus.OK);
    }

    @PostMapping("/showtimes")
    public ResponseEntity<ShowtimeOutDto> saveShowtime(@Valid @RequestBody ShowtimeInDto showtime){
        ShowtimeOutDto newShowtime = showtimeService.saveShowtime(showtime);
        return new ResponseEntity<>(newShowtime, HttpStatus.CREATED);
    }


    @DeleteMapping("/showtime/{showtimeId}")
    public ResponseEntity<Void> removeShowtime(@PathVariable long showtimeId) throws ShowtimeNotFoundException{
        showtimeService.removeShowtime(showtimeId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    //Ojear el put que no se si funciona
    @PutMapping("/showtime/{showtimeId}")
    public ResponseEntity<Void> modifyShowtime(@RequestBody Showtime showtime, @PathVariable long showtimeId){
        showtimeService.modifyShowtime(showtime, showtimeId);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @ExceptionHandler(ShowtimeNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleException(ShowtimeNotFoundException snfe){
        ErrorResponse errorResponse = ErrorResponse.generalError(404, snfe.getMessage());
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
