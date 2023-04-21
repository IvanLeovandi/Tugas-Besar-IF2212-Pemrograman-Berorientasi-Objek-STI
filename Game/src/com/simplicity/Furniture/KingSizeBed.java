package com.simplicity.Furniture;

import com.simplicity.*;

public class KingSizeBed extends Furniture{
    private boolean isUsed;

    public KingSizeBed(String type){
        super("Kasur King Size", new Point(5,2), 150, 3);
        isUsed = false;
    }

    public boolean getIsUsed(){
        return isUsed;
    }

    // method sleep
}