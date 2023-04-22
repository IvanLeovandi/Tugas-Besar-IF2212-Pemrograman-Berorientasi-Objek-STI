package com.simplicity.Furniture;

import com.simplicity.*;

public class TableAndChair extends Furniture {
    private boolean isUsed;

    public TableAndChair(){
        super("TableAndChair", new Point(3,3), 50, 7);
        isUsed = false;
    }

    public boolean getIsUsed() {
        return isUsed;
    }

    // method eat
}