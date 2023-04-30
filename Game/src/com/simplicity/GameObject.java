package com.simplicity;

import java.util.Objects;

public abstract class GameObject {
    private String type;

    public GameObject(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        return getType().equals(((GameObject)o).getType());
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, GameObject.class);
    }
}
