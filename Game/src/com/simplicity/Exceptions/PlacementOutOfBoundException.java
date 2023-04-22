package com.simplicity.Exceptions;

import com.simplicity.Interfaces.WorldObject;

public class PlacementOutOfBoundException extends MisplaceWorldObjectException {
    public PlacementOutOfBoundException(WorldObject wo) {
        super("Penempatan " + wo.getType() + " di luar batas map.");
    }

    public PlacementOutOfBoundException(String message) {
        super(message);
    }
}
