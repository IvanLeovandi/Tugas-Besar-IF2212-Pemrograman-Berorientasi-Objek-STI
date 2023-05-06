package com.simplicity;

import java.util.Objects;

public class Point {
    private int x;
    private int y;

    public Point(int x, int y) {
        setPoint(Integer.valueOf(x), Integer.valueOf(y));
    }

    public int getX() {
        return Integer.valueOf(x);
    }

    public int getY() {
        return Integer.valueOf(y);
    }

    public void setPoint(int x, int y) {
        this.x = Integer.valueOf(x);
        this.y = Integer.valueOf(y);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || !getClass().isAssignableFrom(o.getClass())) {
            return false;
        }
        return getX() == ((Point) o).getX() && getY() == ((Point) o).getY();
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return "Point{" +
                "x= " + x +
                ", y= " + y +
                "}";
    }

    @Override
    public Point clone() {
        return new Point(getX(), getY());
    }
}
