package com.svalero.cineapi.exception;

import com.svalero.cineapi.domain.Cinema;
import com.svalero.cineapi.dto.CinemaInDto;

public class CinemaNotFoundException extends Exception{

    public CinemaNotFoundException(){
        super();
    }

    public CinemaNotFoundException(String message){
        super(message);
    }

    public CinemaNotFoundException(long id){
        super("The cinema " + " doesn't exist");
    }

    public CinemaNotFoundException(CinemaInDto cinema){
        super("Error 500");
    }

}
