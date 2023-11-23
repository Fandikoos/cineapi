package com.svalero.cineapi.controller;

import com.svalero.cineapi.domain.Booking;
import com.svalero.cineapi.domain.ErrorResponse;
import com.svalero.cineapi.dto.BookingInDto;
import com.svalero.cineapi.exception.BookingNotFoundException;
import com.svalero.cineapi.exception.ShowtimeNotFoundException;
import com.svalero.cineapi.exception.UserNotFoundException;
import com.svalero.cineapi.service.BookingService;
import com.svalero.cineapi.service.ShowtimeService;
import com.svalero.cineapi.service.UserService;
import org.hibernate.engine.spi.Resolution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BookingController {

    @Autowired
    private UserService userService;

    @Autowired
    private BookingService bookingService;

    @Autowired
    private ShowtimeService showtimeService;

    @GetMapping("/bookings")
    public List<Booking> getBookings(){
        return bookingService.findAll();
    }

    @PostMapping("/user/{userId}/bookings")
    public void addBooking(@RequestBody BookingInDto bookingInDto, @PathVariable long userId) throws UserNotFoundException, ShowtimeNotFoundException{
        bookingService.addBooking(bookingInDto, userId);
    }

    @DeleteMapping("/booking/{bookingId}")
    public ResponseEntity<Void> removeBooking(@PathVariable long bookingId) throws BookingNotFoundException{
        bookingService.removeBooking(bookingId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/booking/{bookingId}")
    public void modifyBooking(@RequestBody Booking booking, @PathVariable long bookingId){
        bookingService.modifyBooking(booking, bookingId);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponse> userNotFoundException(UserNotFoundException unfe) {
        ErrorResponse errorResponse = ErrorResponse.generalError(404, unfe.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ShowtimeNotFoundException.class)
    public ResponseEntity<ErrorResponse> showtimeNotFoundException(ShowtimeNotFoundException pnfe) {
        ErrorResponse errorResponse = ErrorResponse.generalError(404, pnfe.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }
}
