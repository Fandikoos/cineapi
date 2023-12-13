package com.svalero.cineapi.controller;

import com.svalero.cineapi.domain.Booking;
import com.svalero.cineapi.domain.ErrorResponse;
import com.svalero.cineapi.dto.BookingInDto;
import com.svalero.cineapi.dto.BookingOutDto;
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

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.RecursiveTask;

@RestController
public class BookingController {

    @Autowired
    private UserService userService;

    @Autowired
    private BookingService bookingService;

    @Autowired
    private ShowtimeService showtimeService;

    @GetMapping("/bookings")
    public ResponseEntity<List<Booking>> getAll(@RequestParam(defaultValue = "0") long id, @RequestParam(defaultValue = "") String number, @RequestParam(defaultValue = "")LocalDateTime bookingDate) throws BookingNotFoundException{
        if ((id != 0) && !(number.isEmpty()) && !(bookingDate.toString().isEmpty())){
            return new ResponseEntity<>(bookingService.findByIdAndNumberAndBookingDate(id, number, bookingDate), HttpStatus.OK);
        }
        return new ResponseEntity<>(bookingService.findAll(), HttpStatus.OK);
    }

    @PostMapping("/user/{userId}/bookings")
    public ResponseEntity<BookingOutDto> addBooking(@RequestBody BookingInDto booking, @PathVariable long userId) throws UserNotFoundException, ShowtimeNotFoundException{
        BookingOutDto newBooking = bookingService.addBooking(booking, userId);
        return new ResponseEntity<>(newBooking, HttpStatus.CREATED);
    }

    @DeleteMapping("/booking/{bookingId}")
    public ResponseEntity<Void> removeBooking(@PathVariable long bookingId) throws BookingNotFoundException{
        bookingService.removeBooking(bookingId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/booking/{bookingId}")
    public ResponseEntity<Void> modifyBooking(@RequestBody Booking booking, @PathVariable long bookingId){
        bookingService.modifyBooking(booking, bookingId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ExceptionHandler(BookingNotFoundException.class)
    public ResponseEntity<ErrorResponse> bookingNotFoundException(BookingNotFoundException bnfe) {
        ErrorResponse errorResponse = ErrorResponse.generalError(404, bnfe.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponse> userNotFoundException(UserNotFoundException unfe) {
        ErrorResponse errorResponse = ErrorResponse.generalError(404, unfe.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }
}
