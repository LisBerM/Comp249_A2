package org.example;
// -----------------------------------------------------
// Assignment 2
// Written by: Liseth Berdeja 40089347
// -----------------------------------------------------

public class BadIsbn13Exception extends Exception{
    public BadIsbn13Exception()
    {
        super("BadIsbn13Exception");
    }
    public BadIsbn13Exception(String errorMessage)
    {
        super(errorMessage);
    }
    public String getMessage()
    {
        return super.getMessage();
    }
}
