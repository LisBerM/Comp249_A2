package org.example;
// -----------------------------------------------------
// Assignment 2
// Written by: Liseth Berdeja 40089347
// -----------------------------------------------------
public class MissingFieldException extends Exception{

    public MissingFieldException()
    {
        super("Missing field");
    }
    public MissingFieldException(String errorMessage)
    {
        super(errorMessage);
    }
    public String getMessage()
    {
        return super.getMessage();
    }
}
