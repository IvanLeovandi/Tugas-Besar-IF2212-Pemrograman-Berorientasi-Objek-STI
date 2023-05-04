package com.simplicity;

public class Dimension2D {
    private int width;
    private int height;

    public Dimension2D(int width, int height) {
        setDimension(width, height);
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void setDimension(int width, int height) {
        this.width = width;
        this.height = height;
    }
}
