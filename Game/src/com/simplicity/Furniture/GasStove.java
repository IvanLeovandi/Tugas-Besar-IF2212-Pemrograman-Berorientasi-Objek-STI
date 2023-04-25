package com.simplicity.Furniture;

import com.simplicity.*;

public class GasStove extends Furniture {
    private boolean isUsed;

    public GasStove(){
        super("Kompor Gas", new Dimension2D(1,2), 100, 5);
        isUsed = false;
    }

    public boolean getIsUsed() {
        return isUsed;
    }

    // method cook
}