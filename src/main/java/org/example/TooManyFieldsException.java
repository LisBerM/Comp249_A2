package org.example;

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
