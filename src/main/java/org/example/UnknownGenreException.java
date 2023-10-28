package org.example;

public class UnknownGenreException extends Exception{
    public UnknownGenreException()
    {
        super("unknown genre");
    }
    public UnknownGenreException(String errorMessage)
    {
        super(errorMessage);
    }
    public String getMessage()
    {
        return super.getMessage();
    }

}
