package com.simplicity.Furniture;

import com.simplicity.*;
public class Furniture /* extends GameObject */ {
    private String name;
    private Dimension2D size;
    private int price, id;

    public Furniture(String name, Dimension2D size, int price, int id) {
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
    
    public void setName(String name) {
        this.name = name;
    }

    public void setSize(Dimension2D size) {
        this.size = size;
    }

    public void setPrice(int price) {
        this.price = price;
    }

}