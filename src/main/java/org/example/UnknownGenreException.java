package org.example;
// -----------------------------------------------------
// Assignment 2
// Written by: Liseth Berdeja 40089347
// -----------------------------------------------------
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
