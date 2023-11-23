package com.svalero.cineapi.service;

import com.svalero.cineapi.domain.Booking;
import com.svalero.cineapi.domain.Showtime;
import com.svalero.cineapi.domain.User;
import com.svalero.cineapi.dto.BookingInDto;
import com.svalero.cineapi.exception.BookingNotFoundException;
import com.svalero.cineapi.exception.ShowtimeNotFoundException;
import com.svalero.cineapi.exception.UserNotFoundException;
import com.svalero.cineapi.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.print.Book;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private ShowtimeService showtimeService;

    @Autowired
    private UserService userService;

    public List<Booking> findAll(){
        return bookingRepository.findAll();
    }

    public void addBooking(BookingInDto bookingInDto,long userId) throws UserNotFoundException, ShowtimeNotFoundException{
        Booking booking = new Booking();

        User user = userService.findById(userId);
        booking.setUser(user);

        List<Showtime> showtimes = new ArrayList<>();
        for (long showtimeId: bookingInDto.getShowtimeIds()){
            Showtime showtime = showtimeService.findById(showtimeId).orElseThrow(()->new ShowtimeNotFoundException(showtimeId));
            showtimes.add(showtime);
        }
        booking.setShowtimes(showtimes);

        booking.setNumber(UUID.randomUUID().toString());
        booking.setBookingDate(LocalDateTime.now());
        bookingRepository.save(booking);
    }

    public void removeBooking(long bookingId) throws BookingNotFoundException{
        Booking booking = bookingRepository.findById(bookingId).orElseThrow(()-> new BookingNotFoundException(bookingId));
        bookingRepository.delete(booking);
    }

    public void modifyBooking(Booking newBooking, long bookingId){
        Optional<Booking> booking = bookingRepository.findById(bookingId);
        if (booking.isPresent()){
            Booking existingBooking = booking.get();
            existingBooking.setNumber(newBooking.getNumber());
            bookingRepository.save(existingBooking);
        }
    }

}
