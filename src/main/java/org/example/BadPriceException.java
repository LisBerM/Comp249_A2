package org.example;

public class BadPriceException extends Exception{
    public BadPriceException()
    {
        super("BadPriceException");
    }
    public BadPriceException(String errorMessage)
    {
        super(errorMessage);
    }
    public String getMessage()
    {
        return super.getMessage();
    }
}
