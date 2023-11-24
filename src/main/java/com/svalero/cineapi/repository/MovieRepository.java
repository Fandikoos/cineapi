package com.svalero.cineapi.repository;

import com.svalero.cineapi.domain.Movie;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends CrudRepository<Movie, Long> {

    List<Movie> findAll();

    List<Movie> findByDirectorAndGenreAndDuration(String director, String genre, int duration);
}
