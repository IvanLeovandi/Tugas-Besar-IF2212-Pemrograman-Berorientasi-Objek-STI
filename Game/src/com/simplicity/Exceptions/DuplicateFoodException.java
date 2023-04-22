package com.simplicity.Exceptions;

public class DuplicateFoodException extends Exception {
    public DuplicateFoodException(String type) {
        super(type + " sudah ada dalam menu global.");
    }
}
