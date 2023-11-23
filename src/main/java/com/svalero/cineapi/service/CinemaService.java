package com.svalero.cineapi.service;

import com.svalero.cineapi.domain.Cinema;
import com.svalero.cineapi.dto.CinemaInDto;
import com.svalero.cineapi.dto.CinemaOutDto;
import com.svalero.cineapi.exception.CinemaNotFoundException;
import com.svalero.cineapi.repository.CinemaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class CinemaService {

    @Autowired
    private CinemaRepository cinemaRepository;

    @Autowired
    private ModelMapper modelMapper;

    public List<Cinema> findAll(){
        return cinemaRepository.findAll();
    }

    public CinemaOutDto saveCinema(CinemaInDto cinemaInDto){
        Cinema cinema = new Cinema();
        modelMapper.map(cinemaInDto, cinema);
        cinema.setOpeningDate(LocalDate.now());
        Cinema newCinema = cinemaRepository.save(cinema);

        CinemaOutDto cinemaOutDto = new CinemaOutDto();
        modelMapper.map(newCinema, cinemaOutDto);

        return cinemaOutDto;
    }

    public void removeCinema(long cinemaId) throws CinemaNotFoundException {
        Cinema cinema = cinemaRepository.findById(cinemaId).orElseThrow(() -> new CinemaNotFoundException(cinemaId));
        cinemaRepository.delete(cinema);
    }

    public void modifyCinema (Cinema newCinema, long cinemaId){
        Optional<Cinema> cinema = cinemaRepository.findById(cinemaId);
        if (cinema.isPresent()){
            Cinema existingCinema = cinema.get();
            existingCinema.setName(newCinema.getName());
            existingCinema.setCapacity(newCinema.getCapacity());
            existingCinema.setLatitude(newCinema.getLatitude());
            //existingCinema.setLocation(newCinema.getLocation());
            existingCinema.setLongitude(newCinema.getLongitude());
            existingCinema.setRating(newCinema.getRating());
            cinemaRepository.save(existingCinema);
        }
    }
}
