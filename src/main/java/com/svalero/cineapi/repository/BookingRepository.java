package com.svalero.cineapi.repository;

import com.svalero.cineapi.domain.Booking;
import com.svalero.cineapi.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRepository extends CrudRepository<Booking, Long> {
    List<Booking> findAll();

    //List<Booking> findByUser(User user);

}
