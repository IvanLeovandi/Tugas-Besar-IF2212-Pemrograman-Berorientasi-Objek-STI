package com.simplicity.Interfaces;

import java.awt.*;

import com.simplicity.Components.ColliderInfo;


public interface Collider {
    public Rectangle getBounds();
    public void onCollisionEnter(java.util.Set<Collider> contacts);
    public void onCollisionExit(java.util.Set<Collider> contacts);
    public ColliderInfo getColliderInfo();
}
