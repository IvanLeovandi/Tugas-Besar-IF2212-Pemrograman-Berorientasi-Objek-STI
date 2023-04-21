package com.simplicity.Furniture;

public class Furniture /* extends GameObject */ {
    private String name;
    private Point size;
    private int price;

    public Furniture(String name, Point size, int price) {
        this.name = name;
        this.size = size;
        this.price = price;
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

    public setName(String name) {
        this.name = name;
    }

    public setSize(Point size) {
        this.size = size;
    }

    public setPrice(int price) {
        this.price = price;
    }
}