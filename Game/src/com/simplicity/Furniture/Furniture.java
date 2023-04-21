package com.simplicity.Furniture;

import com.simplicity.*;
public class Furniture /* extends GameObject */ {
    private String name;
    private Point size;
    private int price, id;

    public Furniture(String name, Point size, int price, int id) {
        this.name = name;
        this.size = size;
        this.price = price;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Point getSize() {
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

    public void setSize(Point size) {
        this.size = size;
    }

    public void setPrice(int price) {
        this.price = price;
    }

}