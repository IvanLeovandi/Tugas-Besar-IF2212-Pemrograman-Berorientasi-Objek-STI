package com.simplicity;

import java.util.*;

import com.simplicity.Components.WorldPanel;
import com.simplicity.Exceptions.OverlapingHouseException;
import com.simplicity.Foods.Ingredients.Ingredient;
import com.simplicity.Furniture.Furniture;
import com.simplicity.Interfaces.Purchasable;
import com.simplicity.Interfaces.SimplicityPrintable;


public class World implements SimplicityPrintable {
    private Dimension2D dimension;
    private Map<Point, Sim> map = new HashMap<>();
    private WorldPanel panel;
    private java.util.List<Point> availableLands = new ArrayList<>();

    public static GameTimer gameTimer = new GameTimer();

    public World(int width, int height) {
        dimension = new Dimension2D(width, height);
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                Point p = new Point(i, j);
                map.put(p, null);
                availableLands.add(p);
            }
        }
    }

    public void setSim(int x, int y, Sim sim) throws OverlapingHouseException {
        setSim(new Point(x, y), sim);
    }

    public void setSim(Point location, Sim sim) throws OverlapingHouseException {
        if (!isLandAvailable(location)) {
            throw new OverlapingHouseException();
        }

        Point p = location.clone();
        map.put(p, sim);
        availableLands.remove(p);
        updatePanel();
    }

    public void removeSim(int x, int y) {
        removeSim(new Point(x, y));
    }

    public void removeSim(Point location) {
        Point p = location.clone();
        map.remove(p);
        availableLands.add(p);
        updatePanel();
    }

    public Sim getSim(int x, int y) {
        return getSim(new Point(x, y));
    }

    public Sim getSim(Point p) {
        return map.get(p);
    }

    public Dimension2D getDimension() {
        return dimension;
    }

    public boolean isLandAvailable(Point location) {
        return availableLands.contains(location);
    }

    public java.util.List<Point> getAvailableLands() {
        return availableLands;
    }

    public void checkUpgrade(int duration){
        for (Sim sim : map.values()){
            if (sim.getIsUpgradeHouse().getFirst().equals(true)){
                int x = sim.getIsUpgradeHouse().getSecond() -  duration;
                if (x <= 0){
                    sim.setIsUpgradeHouse(false, 0);
                    Point upgradeRoom = sim.getHouse().getUpgradeState().getFirst();
                    String direction = sim.getHouse().getUpgradeState().getSecond();
                    String name = sim.getHouse().getUpgradeState().getThird();
                    sim.getHouse().upgradeRoom(sim.getHouse().getRoomList().get(upgradeRoom), direction, name);
                }
                else{
                    sim.setIsUpgradeHouse(true, x);
                }
            }
        }
    }

    public void updatePanel() {
        if (this.panel != null) {
            panel.onUpdate();
        }
    }

    public void checkBuying(int duration){
        for (Sim sim : map.values()){
            if (!sim.getDeliveryList().isEmpty()){
                int size = sim.getDeliveryList().size();
                for (int i = 0; i < size; i++){
                    //menyimpan seluruh index yang akan dihapus
                    int x = sim.getDeliveryList().get(i).getThird() - duration;
                    if (x <= 0){
                        sim.getDeliveryList().get(i).setThird(0);
                        Purchasable item = sim.getDeliveryList().get(i).getFirst();
                        int quantity = sim.getDeliveryList().get(i).getSecond();
                        if (item instanceof Furniture){
                            Furniture furniture = (Furniture) item;
                            sim.getFurnitureInventory().addItem(furniture, quantity);
                        }
                        else{
                            Ingredient ingredient = (Ingredient) item;
                            sim.getIngredientsInventory().addItem(ingredient, quantity);
                        }
                    }
                    else{
                        sim.getDeliveryList().get(i).setThird(x);
                    }
                }
                //menghapus dari list item yang delivery time = 0
                for (int i = 0; i < size; i++){
                    if (sim.getDeliveryList().get(i).getThird() == 0){
                        sim.getDeliveryList().remove(i);
                        size--;
                        i--;
                    }
                }
            }
        }
    }

    public void updateSleep(int duration){
        //mengecek waktu saat ini - waktu terakhir sim tidur >= 600
        for (Sim sim : map.values()){
            int x = sim.getTimeSleep();
            if (GameTimer.gameTime - x >= 600){
                sim.notSleep();
            }
        }

        //Sistem reset waktu terakhir tidur
        //Jika terjadi pergantian hari dan waktu terakhir tidur adalah hari sebelumnya
        //Maka waktu terakhir tidur direset menjadi detik 0 di hari berikutnya
        if (World.gameTimer.getDay() != ((GameTimer.gameTime - duration)/720 + 1)){
            int x = World.gameTimer.getDay() - 1;
            for (Sim sim : map.values()){
                sim.setTimeSleep(x*720);
            }
        }
    }


    public void updateDefecate(int duration){
        for (Sim sim : map.values()){
            int x = sim.getTimeDefecateEat().getFirst();
            int y = sim.getTimeDefecateEat().getSecond();
            if (x < y && (GameTimer.gameTime - y > 240)){
                sim.notDefecate();
                sim.setTimeDefecateEat(new Pair<Integer, Integer>(x, GameTimer.gameTime));
            }
        }

        //Reset saat pergantian hari
        if (World.gameTimer.getDay() != ((GameTimer.gameTime - duration)/720 + 1)){
            int x = World.gameTimer.getDay() - 1;
            for (Sim sim : map.values()){
                sim.setTimeDefecateEat(new Pair<Integer, Integer>(x*720, x*720));
            }
        }
    }

    //Mengecek apakah ada sim yang mati
    //Jika ada kembalikan seluruh sim yang saat ini di rumahnya ke tempat aslinya
    public void updateDead(){
        for (Sim sim : map.values()){
            if(sim.getStatus().equals("Die")){
                for (Room rooms :  sim.getHouse().getRoomList().values()){
                    for (Sim sim2 : rooms.getSimList()){
                        if (!sim2.getName().equals(sim.getName())){
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
}
