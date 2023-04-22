package com.simplicity.Exceptions;

import com.simplicity.Interfaces.WorldObject;

public class MisplaceWorldObjectException extends Exception {
    public MisplaceWorldObjectException(WorldObject wo) {
        super(wo.getType() + " tidak dapat ditempatkan di tempat tersebut.");
    }

    public MisplaceWorldObjectException(String message) {
        super(message);
    }
}
