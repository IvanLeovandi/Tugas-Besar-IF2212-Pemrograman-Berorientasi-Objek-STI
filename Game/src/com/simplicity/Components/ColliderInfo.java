package com.simplicity.Components;

import java.awt.Component;
import java.awt.List;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.HashSet;

import com.simplicity.Interfaces.Collider;

public class ColliderInfo {
    Rectangle rectangle;
    Component owner;
    java.util.Set<Collider> contacts = new HashSet<>();

    public ColliderInfo(Component c) {
        rectangle = c.getBounds();
        owner = c;
    }

    public java.util.Set<Collider> getContacts() {
        return contacts;
    }
}
