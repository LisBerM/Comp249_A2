package org.example;

public class BadYearException extends Exception{
    public BadYearException()
    {
        super("BadYearException");
    }
    public BadYearException(String errorMessage)
    {
        super(errorMessage);
    }
    public String getMessage()
    {
        return super.getMessage();
    }
}
