package com.svalero.cineapi.repository;

import com.svalero.cineapi.domain.Showtime;
import com.svalero.cineapi.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    List<User> findAll();


    //Utilizar filtrados aquí
}
