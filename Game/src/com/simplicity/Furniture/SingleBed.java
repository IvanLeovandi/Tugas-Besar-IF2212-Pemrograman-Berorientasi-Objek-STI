package com.simplicity.Furniture;

import com.simplicity.*;

public class SingleBed extends Furniture{
    private boolean isUsed;

    public SingleBed(String type){
        super("Kasur Single", new Point(4,1), 50, 1);
        isUsed = false;
    }

    public boolean getIsUsed(){
        return isUsed;
    }

    // method sleep
}