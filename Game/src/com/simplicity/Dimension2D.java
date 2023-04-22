package com.simplicity;

public class Dimension2D {
    private int width;
    private int length;

    public Dimension2D(int width, int length) {
        setDimension(width, length);
    }

    public int getWidth() {
        return width;
    }

    public int getLength() {
        return length;
    }

    public void setDimension(int width, int length) {
        this.width = width;
        this.length = length;
    }
}
