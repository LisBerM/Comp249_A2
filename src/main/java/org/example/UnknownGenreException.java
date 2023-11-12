package org.example;

public class UnknownGenreException extends Exception{
    public UnknownGenreException()
    {
        super();
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
