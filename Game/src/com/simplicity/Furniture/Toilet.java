package com.simplicity.Furniture;

import com.simplicity.*;

public class Toilet extends Furniture {
    private boolean isUsed;

    public Toilet(){
        super("Toilet", new Dimension2D(1,1), 50, 4);
        isUsed = false;
    }

    public boolean getIsUsed() {
        return isUsed;
    }

    // method defecate
}