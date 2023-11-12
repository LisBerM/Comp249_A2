package org.example;
// -----------------------------------------------------
// Assignment 2
// Written by: Liseth Berdeja 40089347
// -----------------------------------------------------
public class TooManyFieldsException extends Exception{

    public TooManyFieldsException()
    {
        super();
    }
    public TooManyFieldsException(String errorMessage)
    {
        super(errorMessage);
    }
    public String getMessage()
    {
        return super.getMessage();
    }

}
