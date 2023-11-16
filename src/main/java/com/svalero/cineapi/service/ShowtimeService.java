package com.svalero.cineapi.service;

import com.svalero.cineapi.domain.Showtime;
import com.svalero.cineapi.dto.ShowtimeInDto;
import com.svalero.cineapi.dto.ShowtimeOutDto;
import com.svalero.cineapi.exception.ShowtimeNotFoundException;
import com.svalero.cineapi.repository.ShowtimeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
public class ShowtimeService {

    @Autowired
    private ShowtimeRepository showtimeRepository;
    @Autowired
    private ModelMapper modelMapper;

    public List<Showtime> findAll(){
        return showtimeRepository.findAll();
    }

    public Optional<Showtime> findById(long id){
        return showtimeRepository.findById(id);
    }

    public ShowtimeOutDto saveShowtime(ShowtimeInDto showtimeInDto) {
        Showtime showtime = new Showtime();
        modelMapper.map(showtimeInDto, showtime);
        Showtime newShowtime = showtimeRepository.save(showtime);

        ShowtimeOutDto showtimeOutDto = new ShowtimeOutDto();
        modelMapper.map(newShowtime, showtimeOutDto);

        return showtimeOutDto;
    }

    public void removeShowtime(long showtimeId) throws ShowtimeNotFoundException{
        Showtime showtime = showtimeRepository.findById(showtimeId).orElseThrow(() -> new ShowtimeNotFoundException(showtimeId));
        showtimeRepository.delete(showtime);
    }

    public void modifyShowtime(Showtime newShowtime, long showtimeId){
        Optional<Showtime> showtime = showtimeRepository.findById(showtimeId);
        if (showtime.isPresent()){
            Showtime existingShowtime = showtime.get();
            existingShowtime.setScreenName(newShowtime.getScreenName());
            existingShowtime.setStartTime(newShowtime.getStartTime());
            existingShowtime.setPrice(newShowtime.getPrice());
            existingShowtime.setSoldOut(newShowtime.isSoldOut());
            existingShowtime.setEndTime(newShowtime.getEndTime());
        }
    }
}
