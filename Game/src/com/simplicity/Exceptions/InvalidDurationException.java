package com.simplicity.Exceptions;

public class InvalidDurationException extends Exception {
    public InvalidDurationException() {
        super("Invalid duration.");
    }

    public InvalidDurationException(String message) {
        super(message);
    }

}
