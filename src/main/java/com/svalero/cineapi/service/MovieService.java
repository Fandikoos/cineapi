package com.svalero.cineapi.service;

import com.svalero.cineapi.domain.Movie;
import com.svalero.cineapi.dto.MovieInDto;
import com.svalero.cineapi.dto.MovieOutDto;
import com.svalero.cineapi.exception.MovieNotFoundException;
import com.svalero.cineapi.repository.MovieRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import java.util.List;
import java.util.Optional;

@Service
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private ModelMapper modelMapper;

    public List<Movie> findAll(){
        return movieRepository.findAll();
    }

    public List<Movie> findByDirectorAndGenreAndDuration(String director, String genre, int duration){
        return movieRepository.findByDirectorAndGenreAndDuration(director, genre, duration);
    }

    public MovieOutDto saveMovie(MovieInDto movieInDto){
        Movie movie = new Movie();
        modelMapper.map(movieInDto, movie);
        Movie newMovie = movieRepository.save(movie);

        MovieOutDto movieOutDto = new MovieOutDto();
        modelMapper.map(newMovie, movieOutDto);

        return movieOutDto;
    }

    public void removeMovie (long movieId) throws MovieNotFoundException{
        Movie movie = movieRepository.findById(movieId).orElseThrow(() -> new MovieNotFoundException(movieId));
        movieRepository.delete(movie);
    }

    public void modifyMovie (Movie newMovie, long movieId){
        Optional<Movie> movie = movieRepository.findById(movieId);
        if (movie.isPresent()){
            Movie existingMovie = movie.get();
            existingMovie.setTitle(newMovie.getTitle());
            existingMovie.setDirector(newMovie.getDirector());
            existingMovie.setGenre(newMovie.getGenre());
            existingMovie.setDuration(newMovie.getDuration());
            existingMovie.setReleaseDate(newMovie.getReleaseDate());
            movieRepository.save(existingMovie);
        }
    }
}
