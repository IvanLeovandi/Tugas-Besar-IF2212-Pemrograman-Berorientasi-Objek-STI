package com.simplicity.Exceptions;

public class DuplicateFoodException extends Exception {
    public DuplicateFoodException(String type) {
        super(type + " have been added to global menu.");
    }
}
