package com.simplicity.Components;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import com.simplicity.Interfaces.Collider;

public class SimplicityComponentHandler {
    static java.util.List<SimplicityPanel> simplicityPanelGlobalObjects = new ArrayList<>();
    static java.util.List<Collider> colliderGlobalObjects = new ArrayList<>();

    public static void update() {
        synchronized(simplicityPanelGlobalObjects) {
            for (SimplicityPanel panel: simplicityPanelGlobalObjects) {
                panel.onUpdate();
            }
        }

        synchronized(colliderGlobalObjects) {
            for (Collider colliderA: colliderGlobalObjects) {
                java.util.Set<Collider> exitContactsA = new HashSet<>();
                java.util.Set<Collider> enterContactsA = new HashSet<>();

                for (Collider colliderB: colliderGlobalObjects) {
                    if (colliderA != colliderB) {
                        Set<Collider> contactsA = colliderA.getColliderInfo().contacts;
                        if (colliderA.getBounds().intersects(colliderB.getBounds()) && !contactsA.contains(colliderB)) {
                            contactsA.add(colliderB);
                            enterContactsA.add(colliderB);
                        } else if (!colliderA.getBounds().intersects(colliderB.getBounds()) && contactsA.contains(colliderB)) {
                            contactsA.remove(colliderB);
                            exitContactsA.add(colliderB);
                        }
                    }
                }

                if (!enterContactsA.isEmpty()) {
                    colliderA.onCollisionEnter(enterContactsA);
                }

                if (!exitContactsA.isEmpty()) {
                    colliderA.onCollisionExit(exitContactsA);
                }
            }
        }
    }
}
