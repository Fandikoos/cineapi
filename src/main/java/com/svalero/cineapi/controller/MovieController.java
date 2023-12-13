package com.svalero.cineapi.controller;

import com.svalero.cineapi.domain.ErrorResponse;
import com.svalero.cineapi.domain.Movie;
import com.svalero.cineapi.dto.MovieInDto;
import com.svalero.cineapi.dto.MovieOutDto;
import com.svalero.cineapi.exception.MovieNotFoundException;
import com.svalero.cineapi.service.MovieService;
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
public class MovieController {

    @Autowired
    private MovieService movieService;

    @GetMapping("/movies")
    public ResponseEntity<List<Movie>> getAll(@RequestParam(defaultValue = "") String director, @RequestParam(defaultValue = "") String genre, @RequestParam(defaultValue = "0") int duration){
        if (!(director.isEmpty()) && !(genre.isEmpty()) && (duration != 0)){
            return new ResponseEntity<>(movieService.findByDirectorAndGenreAndDuration(director, genre, duration), HttpStatus.OK);
        }
        return new ResponseEntity<>(movieService.findAll(), HttpStatus.OK);
    }

    @PostMapping("/movies")
    public ResponseEntity<MovieOutDto> saveMovie(@Valid @RequestBody MovieInDto movie){
        MovieOutDto newMovie = movieService.saveMovie(movie);
        return new ResponseEntity<>(newMovie,HttpStatus.CREATED);
    }

    @DeleteMapping("/movie/{movieId}")
    public ResponseEntity<Void> removeMovie(@PathVariable long movieId) throws MovieNotFoundException{
        movieService.removeMovie(movieId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/movie/{movieId}")
    public ResponseEntity<Void> modifyMovie(@RequestBody Movie movie, @PathVariable long movieId){
        movieService.modifyMovie(movie, movieId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ExceptionHandler(MovieNotFoundException.class)
    public ResponseEntity<ErrorResponse> movieNotFoundException(MovieNotFoundException mnfe){
        ErrorResponse errorResponse = ErrorResponse.generalError(404, mnfe.getMessage());
        return new ResponseEntity<>(errorResponse,HttpStatus.NOT_FOUND);
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
