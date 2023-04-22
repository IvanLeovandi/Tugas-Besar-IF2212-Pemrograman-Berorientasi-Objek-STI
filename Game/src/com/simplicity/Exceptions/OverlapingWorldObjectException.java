package com.simplicity.Exceptions;

import com.simplicity.Interfaces.WorldObject;

public class OverlapingWorldObjectException extends MisplaceWorldObjectException {
    public OverlapingWorldObjectException(WorldObject wo, WorldObject objectCheck) {
        super(wo.getType() + " tumpuk tindih dengan " + objectCheck.getType());
    }

    public OverlapingWorldObjectException(String message) {
        super(message);
    }
}
