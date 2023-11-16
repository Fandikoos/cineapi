package com.svalero.cineapi.exception;

public class CinemaNotFoundException extends Exception{

    public CinemaNotFoundException(){
        super();
    }

    public CinemaNotFoundException(String message){
        super(message);
    }

    public CinemaNotFoundException(long id){
        super("The cinema " + "doesn't exist");
    }
}
