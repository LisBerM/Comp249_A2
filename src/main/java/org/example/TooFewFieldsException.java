package org.example;

public class TooFewFieldsException extends Exception{

    public TooFewFieldsException()
    {
        super("Too few fields");
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
