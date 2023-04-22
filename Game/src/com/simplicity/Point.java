package com.simplicity;

public class Point {
    private int x;
    private int y;

    public Point(int x, int y) {
        setPoint(x, y);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setPoint(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || !getClass().isAssignableFrom(o.getClass())) {
            return false;
        }
        return getX() == ((Point)o).getX() && getY() == ((Point)o).getY();
    }

    public String toString() {
        return  "Point{" +
                "x= " + x +
                ", y= " + y +
                "}";
    }
}
