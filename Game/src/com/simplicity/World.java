package com.simplicity;

import java.util.*;

import com.simplicity.Components.WorldPanel;
import com.simplicity.Exceptions.OverlapingHouseException;
import com.simplicity.Foods.Ingredients.Ingredient;
import com.simplicity.Furniture.Furniture;
import com.simplicity.Interfaces.Purchasable;
import com.simplicity.Interfaces.SimplicityPrintable;


public class World implements SimplicityPrintable {
    private Dimension2D size;
    private Map<Point, Sim> map = new HashMap<>();
    private WorldPanel panel;
    private java.util.List<Point> availableLands = new ArrayList<>();

    public static GameTimer gameTimer = new GameTimer();

    public World(int width, int height) {
        size = new Dimension2D(width, height);
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

    public Dimension2D getSize() {
        return size;
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



    @Override
    public WorldPanel getPanel() {
        if (panel == null) {
            panel = new WorldPanel(64, 64, this);
        }

        return panel;
    }

    @Override
    public void clearPanel() {
        panel = null;
    }
}
