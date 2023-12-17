package com.svalero.cineapi.service;

import com.svalero.cineapi.domain.Booking;
import com.svalero.cineapi.domain.Showtime;
import com.svalero.cineapi.domain.User;
import com.svalero.cineapi.dto.BookingInDto;
import com.svalero.cineapi.dto.BookingOutDto;
import com.svalero.cineapi.exception.BookingNotFoundException;
import com.svalero.cineapi.exception.ShowtimeNotFoundException;
import com.svalero.cineapi.exception.UserNotFoundException;
import com.svalero.cineapi.repository.BookingRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    private ModelMapper modelMapper;

    @Autowired
    private UserService userService;

    public List<Booking> findAll(){
        return bookingRepository.findAll();
    }

    public List<Booking> findByIdAndNumberAndBookingDate(long id, String number, LocalDateTime bookingDate){
        return bookingRepository.findByIdAndNumberAndBookingDate(id, number, bookingDate);
    }

    public BookingOutDto addBooking(BookingInDto bookingInDto, long userId) throws UserNotFoundException, ShowtimeNotFoundException{
        Booking booking = new Booking();
        modelMapper.map(bookingInDto, booking);

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
        Booking newBooking = bookingRepository.save(booking);

        BookingOutDto bookingOutDto = new BookingOutDto();
        modelMapper.map(newBooking, bookingOutDto);

        return bookingOutDto;
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
            existingBooking.setRow(newBooking.getRow());
            existingBooking.setSeat(newBooking.getSeat());
            bookingRepository.save(existingBooking);
        }
    }

}
