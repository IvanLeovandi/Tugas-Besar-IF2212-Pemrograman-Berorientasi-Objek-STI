package com.simplicity.Exceptions;

public class FullWorldException extends Exception {
    public FullWorldException() {
        super("World is already full.");
    }

    public FullWorldException(String message) {
        super(message);
    }
}
