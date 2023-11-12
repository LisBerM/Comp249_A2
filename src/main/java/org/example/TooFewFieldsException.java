package org.example;
// -----------------------------------------------------
// Assignment 2
// Written by: Liseth Berdeja 40089347
// -----------------------------------------------------
public class TooFewFieldsException extends Exception{

    public TooFewFieldsException()
    {
        super();
    }

    public TooFewFieldsException(String errorMessage)
    {
        super(errorMessage);
    }
    public String getMessage()
    {
        return super.getMessage();
    }
}
