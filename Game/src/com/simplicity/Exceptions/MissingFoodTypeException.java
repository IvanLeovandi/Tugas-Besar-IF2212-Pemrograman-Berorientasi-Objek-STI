package com.simplicity.Exceptions;

public class MissingFoodTypeException extends Exception{
    public MissingFoodTypeException() {
        super("That food hasn't been added to global menu");
    }

    public MissingFoodTypeException(String message) {
        super(message);
    }
}