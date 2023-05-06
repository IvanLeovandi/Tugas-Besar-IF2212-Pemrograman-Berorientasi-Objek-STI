package com.simplicity.Exceptions;

public class FullWorldException extends InAppendableSimWorld {
    public FullWorldException() {
        super("World is already full.");
    }

    public FullWorldException(String message) {
        super(message);
    }
}
