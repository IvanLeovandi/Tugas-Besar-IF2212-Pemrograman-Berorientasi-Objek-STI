package com.simplicity;

import java.util.*;
import java.util.Map.Entry;

import com.simplicity.Exceptions.InAppendableSimWorld;
import com.simplicity.Exceptions.OverlapingHouseException;
import com.simplicity.Foods.Ingredient;
import com.simplicity.Furniture.Furniture;
import com.simplicity.Interfaces.Purchasable;

public class World {
    private static World instance = new World(64, 64);
    private Dimension2D size;
    private Map<Point, Sim> map = new HashMap<>();
    // private WorldPanel panel;
    private GameTimer gameTimer = new GameTimer();
    private int appendCooldown = 0;

    private World(int width, int height) {
        size = new Dimension2D(width, height);
        // for (int i = 0; i < width; i++) {
        // for (int j = 0; j < height; j++) {
        // Point p = new Point(i, j);
        // map.put(p, null);
        // availableLands.add(p);
        // }
        // }
    }

    public static World getInstance() {
        return instance;
    }

    public GameTimer gameTimer() {
        return gameTimer;
    }

    public void setSim(int x, int y, Sim sim) throws OverlapingHouseException, InAppendableSimWorld {
        setSim(new Point(x, y), sim);
    }

    public void setSim(Point location, Sim sim) throws OverlapingHouseException, InAppendableSimWorld {
        if (!checkSimAppendable()) {
            throw new InAppendableSimWorld();
        }

        if (map.get(location) != null) {
            throw new OverlapingHouseException();
        }

        if (!map.isEmpty()) {
            appendCooldown = 720;
        }

        Point p = location.clone();
        map.put(p, sim);
        // availableLands.remove(p);
        // updatePanel();
    }

    public boolean checkSimAppendable() {
        return appendCooldown == 0;
    }

    public void removeSim(int x, int y) {
        removeSim(new Point(x, y));
    }

    public void removeSim(Point location) {
        Point p = location.clone();
        map.remove(p);
        // availableLands.add(p);
        // updatePanel();
    }

    public Sim getSim(int x, int y) {
        return getSim(new Point(x, y));
    }

    public Sim getSim(Point p) {
        return map.get(p);
    }

    public Dimension2D getSize() {
        return size;
    }

    public Map<Point, Sim> getMap() {
        return map;
    }

    // public boolean isLandAvailable(Point location) {
    // return availableLands.contains(location);
    // }

    // public java.util.List<Point> getAvailableLands() {
    // return availableLands;
    // }

    public GameTimer getGameTimer() {
        return gameTimer;
    }

    public void checkUpgrade(int duration) {
        for (Sim sim : map.values()) {
            if (sim.getIsUpgradeHouse().getFirst().equals(true)) {
                int x = sim.getIsUpgradeHouse().getSecond() - duration;
                if (x <= 0) {
                    sim.setIsUpgradeHouse(false, 0);
                    Point upgradeRoom = sim.getHouse().getUpgradeState().getFirst();
                    String direction = sim.getHouse().getUpgradeState().getSecond();
                    String name = sim.getHouse().getUpgradeState().getThird();
                    sim.getHouse().upgradeRoom(sim.getHouse().getRoomList().get(upgradeRoom), direction, name);
                    System.out.println(sim.getName() + "'s house has been upgraded!");
                } else {
                    sim.setIsUpgradeHouse(true, x);
                }
            }
        }
    }

    // public void updatePanel() {
    // if (this.panel != null) {
    // panel.onUpdate();
    // }
    // }

    public void checkBuying(int duration) {
        for (Sim sim : map.values()) {
            if (!sim.getDeliveryList().isEmpty()) {
                int size = sim.getDeliveryList().size();
                for (int i = 0; i < size; i++) {
                    int x = sim.getDeliveryList().get(i).getThird() - duration;
                    if (x <= 0) {
                        sim.getDeliveryList().get(i).setThird(0);
                        Purchasable item = sim.getDeliveryList().get(i).getFirst();
                        int quantity = sim.getDeliveryList().get(i).getSecond();
                        if (item instanceof Furniture) {
                            Furniture furniture = (Furniture) item;
                            sim.getFurnitureInventory().addItem(furniture, quantity);
                        } else {
                            Ingredient ingredient = (Ingredient) item;
                            sim.getIngredientsInventory().addItem(ingredient, quantity);
                        }
                    } else {
                        sim.getDeliveryList().get(i).setThird(x);
                    }
                }
                // menghapus dari list item yang delivery time = 0
                for (int i = 0; i < size; i++) {
                    if (sim.getDeliveryList().get(i).getThird() == 0) {
                        sim.getDeliveryList().remove(i);
                        size--;
                        i--;
                    }
                }
            }
        }
    }

    public void updateSleep(int duration) {
        // mengecek waktu saat ini - waktu terakhir sim tidur >= 600
        for (Sim sim : map.values()) {
            int x = sim.getTimeSleep();// waktu terakhir kali sim tidur
            if (GameTimer.gameTime - x >= 600) {
                sim.notSleep();
            }
        }

        // Sistem reset waktu terakhir tidur
        // Jika terjadi pergantian hari dan waktu terakhir tidur adalah hari sebelumnya
        // Maka waktu terakhir tidur direset menjadi detik 0 di hari berikutnya
        if (gameTimer.getDay() != ((GameTimer.gameTime - duration) / 720 + 1)) {
            int x = gameTimer.getDay() - 1;
            for (Sim sim : map.values()) {
                sim.setTimeSleep(x * 720);
            }
        }
    }

    public void updateDefecate(int duration) {
        for (Sim sim : map.values()) {
            int x = sim.getTimeDefecateEat().getFirst();
            int y = sim.getTimeDefecateEat().getSecond();
            if (x < y && (GameTimer.gameTime - y > 240)) {
                sim.notDefecate();
                System.out.println(sim.getName() + " hasn't defecated for 4 minutes!");
                sim.setTimeDefecateEat(new Pair<Integer, Integer>(x, GameTimer.gameTime));
            }
        }

        // Reset saat pergantian hari
        if (gameTimer.getDay() != ((GameTimer.gameTime - duration) / 720 + 1)) {
            int x = gameTimer.getDay() - 1;
            for (Sim sim : map.values()) {
                sim.setTimeDefecateEat(new Pair<Integer, Integer>(x * 720, x * 720));
            }
        }
    }

    // Mengecek apakah ada sim yang mati
    // Jika ada kembalikan seluruh sim yang saat ini di rumahnya ke tempat aslinya
    public void updateDead() {
        for (Sim sim : map.values()) {
            if (sim.getStatus().equals("Die")) {
                for (Room rooms : sim.getHouse().getRoomList().values()) {
                    for (Sim sim2 : rooms.getSimList()) {
                        if (!sim2.getName().equals(sim.getName())) {
                            sim2.setCurrentHouse(sim2.getHouse());
                            sim2.setCurrentRoom(sim2.getHouse().getRoomList().get(new Point(0, 0)));
                            sim2.setCurrentPosition(new Point(0, 0));
                        }
                    }
                }
            }

            Point location = sim.getHouse().getLocation();
            map.remove(location, sim);
        }
    }

    public void updateWorld(int duration) {
        if (appendCooldown < duration) {
            appendCooldown = 0;
        } else {
            appendCooldown -= duration;
        }

        checkUpgrade(duration);
        checkBuying(duration);
        updateSleep(duration);
        updateDefecate(duration);
        updateDead();
    }

    public void printHouseList() {
        int num = 1;
        String header = String.format("| %-4s | %-20s | %-20s |", "No", "Point", "Owner");
        String line = "-".repeat(header.length());

        System.out.println(line);
        System.out.println(header);
        System.out.println(line);

        for (Entry<Point, Sim> house : map.entrySet()) {
            String row = String.format("| %-4s | %-20s | %-20s |", num, house.getKey(), house.getValue().getName());
            System.out.println(row);
            num++;
        }

        System.out.println(line);
    }

    public void printSimsList() {
        int num = 1;
        String header = String.format("| %-4s | %-20s |", "No", "Sims Name");
        String line = "-".repeat(header.length());

        System.out.println(line);
        System.out.println(header);
        System.out.println(line);

        for (Entry<Point, Sim> house : map.entrySet()) {
            String row = String.format("| %-4s | %-20s |", num, house.getValue().getName());
            System.out.println(row);
            num++;
        }

        System.out.println(line);
    }

    // @Override
    // public WorldPanel getPanel() {
    // if (panel == null) {
    // panel = new WorldPanel(64, 64, this);
    // }

    // return panel;
    // }

    // @Override
    // public void clearPanel() {
    // panel = null;
    // }
}
