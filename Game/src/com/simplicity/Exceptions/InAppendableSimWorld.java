package com.simplicity.Exceptions;

public class InAppendableSimWorld extends Exception {
    public InAppendableSimWorld() {
        super("Cannot add sim right now.");
    }

    public InAppendableSimWorld(String message) {
        super(message);
    }
}
