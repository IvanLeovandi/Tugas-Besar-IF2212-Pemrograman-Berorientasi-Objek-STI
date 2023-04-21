package com.simplicity.Furniture;

import com.simplicity.*;

public class QueenSizeBed extends Furniture{
    private boolean isUsed;

    public QueenSizeBed(String type){
        super("Kasur Queen Size", new Point(4,2), 100, 2);
        isUsed = false;
    }

    public boolean getIsUsed(){
        return isUsed;
    }

    // method sleep
}