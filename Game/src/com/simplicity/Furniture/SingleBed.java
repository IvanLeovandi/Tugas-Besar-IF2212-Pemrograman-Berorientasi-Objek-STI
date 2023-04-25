package com.simplicity.Furniture;

import com.simplicity.*;

public class SingleBed extends Furniture{
    private boolean isUsed;

    public SingleBed(String type){
        super("Kasur Single", new Dimension2D(1,4), 50, 1);
        isUsed = false;
    }

    public boolean getIsUsed(){
        return isUsed;
    }

    // method sleep
}