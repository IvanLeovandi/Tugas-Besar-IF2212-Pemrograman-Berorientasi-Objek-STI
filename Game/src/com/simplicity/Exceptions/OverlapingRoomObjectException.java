package com.simplicity.Exceptions;

public class OverlapingRoomObjectException extends Exception{
    public OverlapingRoomObjectException (String message)
    {
        super(message);
    }

    public String getMessage()
    {
        return ("Theres already an object there!");
    }
}  

