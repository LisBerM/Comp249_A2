package org.example;

public class TooManyFieldsException extends Exception{

    public TooManyFieldsException()
    {
        super("Too many fields");
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
