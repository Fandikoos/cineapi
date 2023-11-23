package com.svalero.cineapi.exception;

public class BookingNotFoundException extends Exception{
    public BookingNotFoundException(){
        super();
    }

    public BookingNotFoundException(String message){
        super(message);
    }

    public BookingNotFoundException(long id){
        super("The booking " + id + "doesn't exist");
    }
}
