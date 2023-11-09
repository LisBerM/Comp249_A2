package org.example;

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
