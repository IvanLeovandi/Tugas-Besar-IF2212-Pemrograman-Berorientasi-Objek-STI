package com.simplicity.Furniture;

import com.simplicity.*;

public class GasStove extends Furniture {
    private boolean isUsed;

    public GasStove(){
        super("Kompor Gas", new Point(2,1), 100, 5);
        isUsed = false;
    }

    public boolean getIsUsed() {
        return isUsed;
    }

    // method cook
}