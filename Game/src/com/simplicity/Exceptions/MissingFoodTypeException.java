package com.simplicity.Exceptions;

public class MissingFoodTypeException extends Exception{
    public MissingFoodTypeException() {
        super("Makanan tersebut belum masuk ke menu global.");
    }

    public MissingFoodTypeException(String message) {
        super(message);
    }
}
