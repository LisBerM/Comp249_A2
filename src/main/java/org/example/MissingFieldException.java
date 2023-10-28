package org.example;

public class MissingFieldException extends Exception{

    public MissingFieldException()
    {
        super("Missing field");
    }
    public MissingFieldException(String errorMessage)
    {
        super(errorMessage);
    }
    public String getMessage()
    {
        return super.getMessage();
    }
}
