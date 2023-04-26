package com.simplicity;

public enum  Furniture{
    SINGLEBED("Single Bed", new Dimension2D(1,4), 50, 1), 
    QUEENBED("Queen Bed", new Dimension2D(2,4), 100, 2), 
    KINGBED("King Bed", new Dimension2D(2,5), 150, 3), 
    TOILET("Toilet", new Dimension2D(1,1), 50, 4), 
    GASSTOVE("Gas Stove", new Dimension2D(1,2), 100, 5), 
    ELECTRICSTOVE("Electric Stove", new Dimension2D(1,1), 200, 6), 
    TABLEANDCHAIR("Table And Chair", new Dimension2D(3,3), 50, 7), 
    CLOCK("Clock", new Dimension2D(1,1), 10, 8);

    private final String name;
    private final Dimension2D size;
    private final int price, id;
    private boolean isUsed = false;

    Furniture(String name, Dimension2D size, int price, int id) {
        this.name = name;
        this.size = size;
        this.price = price;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Dimension2D getSize() {
        return size;
    }

    public int getPrice() {
        return price;
    }

    public int getId(){
        return id;
    }

    public boolean getIsUsed(){
        return isUsed;
    }

    public void setIsUsed(boolean isUsed){
        this.isUsed = isUsed;
    }

    // method sleep
    // method defecate
    // method cook
    // method eat
    // method show time
}