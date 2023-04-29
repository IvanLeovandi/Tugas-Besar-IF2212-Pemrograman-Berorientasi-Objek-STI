package com.simplicity;

import java.util.*;
import javax.swing.*;
import java.io.File;

import com.simplicity.Interfaces.Interactable;
import com.simplicity.Layouts.House;

public class GameMenu extends JPanel {
    public static void showSimInfo(Sim sim) {
        System.out.println("Berikut informasi sim,");
        System.out.println("Nama        : " + sim.getName());
        System.out.println("Pekerjaan   : " + sim.getJob());
        System.out.println("Kesehatan   : " + sim.getHealth());
        System.out.println("Kekenyangan : " + sim.getSatiety());
        System.out.println("Mood        : " + sim.getMood());
        System.out.println("Uang        : " + sim.getBalance());
        System.out.println("");
    }

    public static void showSimLocation(Sim sim) {}

    public static void showSimInventory(Sim sim) {}

    // need House
    public static void openHouseUpgrade(House house) {}

    // need room
    public static void openRoomEdit() {}

    public static void openSimAdd() {}

    public static void openSimSelect() {}

    public static void showRoomObjects() {}

    public static void openSimMove(Sim sim) {}

    public static void showInteractActions(Sim sim, Interactable object) {
        List<Class<?>> interfaces = new ArrayList<>();

        for (Class<?> i : object.getClass().getInterfaces()) {
            if (Interactable.class.isAssignableFrom(i) && Interactable.class != i) {
                interfaces.add(i);
            }
        }

        for (int j = 0; j < interfaces.size(); j++) {
            System.out.println((j+1) + ". " + interfaces.get(j).getName());
        }
    }
}
