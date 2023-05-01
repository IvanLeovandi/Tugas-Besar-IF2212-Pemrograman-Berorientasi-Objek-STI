package com.simplicity.Exceptions;

public class InvalidSimName extends Exception {
    public InvalidSimName(String message) {
        super(message);
    }

    public InvalidSimName() {
        this("Sim name is invalid");
    }
}
