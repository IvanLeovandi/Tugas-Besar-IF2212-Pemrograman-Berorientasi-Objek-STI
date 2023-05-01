package com.simplicity.Exceptions;

public class OverlapingHouseException extends MisplaceWorldObjectException {
    public OverlapingHouseException() {
        super("Terjadi tumpuk tindih rumah.");
    }

    public OverlapingHouseException(String message) {
        super(message);
    }
}
