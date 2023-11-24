package com.svalero.cineapi.repository;

import com.svalero.cineapi.domain.Showtime;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShowtimeRepository extends CrudRepository<Showtime, Long> {
    List<Showtime> findAll();

    //Utilizar filtrados aqui
    List<Showtime> findByScreenNameAndPriceAndIsSoldOut(String screenName, float price, boolean isSoldOut);
}
