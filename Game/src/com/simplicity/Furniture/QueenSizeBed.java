package com.simplicity.Furniture;

import com.simplicity.*;

public class QueenSizeBed extends Furniture{
    private boolean isUsed;

    public QueenSizeBed(String type){
        super("Kasur Queen Size", new Dimension2D(2,4), 100, 2);
        isUsed = false;
    }

    public boolean getIsUsed(){
        return isUsed;
    }

    // method sleep
}