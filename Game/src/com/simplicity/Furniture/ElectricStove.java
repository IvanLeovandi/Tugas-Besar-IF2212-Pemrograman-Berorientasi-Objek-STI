package com.simplicity.Furniture;

import com.simplicity.*;

public class ElectricStove extends Furniture {
    private boolean isUsed;

    public ElectricStove(){
        super("Kompor Listrik", new Point(1,1), 200, 6);
        isUsed = false;
    }

    public boolean getIsUsed() {
        return isUsed;
    }

    // method cook
}