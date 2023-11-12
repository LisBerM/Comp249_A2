package org.example;
// -----------------------------------------------------
// Assignment 2
// Written by: Liseth Berdeja 40089347
// -----------------------------------------------------

public class BadIsbn10Exception extends Exception{

    public BadIsbn10Exception()
    {
        super("Bad Isbn 10 Exception");
    }
    public BadIsbn10Exception(String errorMessage)
    {
        super(errorMessage);
    }
    public String getMessage()
    {
        return super.getMessage();
    }

}
