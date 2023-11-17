package com.svalero.cineapi.exception;

public class MovieNotFoundException  extends Exception{

    public MovieNotFoundException(){
        super();
    }

    public MovieNotFoundException(String message){
        super(message);
    }

    public MovieNotFoundException(long id){
        super("The movie " + id + "doesn't exist");
    }
}
