package com.simplicity.Furniture;

public class Bed extends Furniture{
    private String type;
    private boolean isUsed;

    public Bed(String type){
        if(type.equals("Single")) {
            super("Kasur Single", new Point(4,1), 50);
        } else if(type.equals("Queen")) {
            super("Kasur Queen Size", new Point(4,2), 100);
        } else if(type.equals("King")) {
            super("Kasur King Size", new Point(5,2), 150);
        }
        this.type = type;
        isUsed = false;
    }

    public boolean getIsUsed(){
        return isUsed;
    }

    // method sleep
}