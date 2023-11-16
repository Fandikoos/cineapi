package com.svalero.cineapi.exception;

public class ShowtimeNotFoundException extends Exception {

    public ShowtimeNotFoundException(){
        super();
    }

    public ShowtimeNotFoundException(String message){
        super(message);
    }

    public ShowtimeNotFoundException(long id){
        super("The showtime " + id + " doesn't exist");
    }
}
