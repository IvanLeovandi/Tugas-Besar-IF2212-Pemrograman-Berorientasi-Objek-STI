package com.simplicity;

import java.util.HashMap;
import java.util.Map.Entry;

import com.simplicity.Furniture.Furniture;

public class Inventory<T> {
    private HashMap<T, Integer> inventory;
    private int capacity;

    public Inventory() {
        inventory = new HashMap<T, Integer>();
        capacity = 20;
    }

    public void addItem(T itemName, int quantity) {
        if (inventory.containsKey(itemName)) {
            int currentQuantity = inventory.get(itemName);
            inventory.put(itemName, currentQuantity + quantity);
        } else {
            if(inventory.size() == capacity){
                System.out.println("Inventory sudah penuh, mohon untuk menghapus barang atau mengeluarkannya dari inventory.");
            }
            inventory.put(itemName, quantity);
        }
    }

    public void removeItem(T itemName) {
        if (inventory.containsKey(itemName)) {
            int currentQuantity = inventory.get(itemName);
            if (currentQuantity > 1) {
                inventory.put(itemName, currentQuantity - 1);
            } else {
                inventory.remove(itemName);
            }
        } else {
            System.out.println("Benda tidak ada di inventory");
        }
    }
    

    public int getItemQuantity(T itemName) {
        if (inventory.containsKey(itemName)) {
            return inventory.get(itemName);
        } else {
            return 0;
        }
    }

    public HashMap<T, Integer> getInventory() {
        return inventory;
    }

    // public T getFurniture(String string){
    //     for (Entry<T, Integer> furniture : inventory.entrySet())
    //     {
    //         if(furniture.getKey().toString().equals(string))
    //         {
    //                 return furniture.getKey();
    //         }
    //     }
    //     return null; 
    // }

    
    // public HashMap<Food, Integer> getFoodInventory() {
    //     HashMap<Food, Integer> foodInventory = new HashMap<>();
    //     for (Map.Entry<Food, Integer> entry : inventory.entrySet()) {
    //         if (entry.getKey() instanceof Food) {
    //             filteredInventory.put(entry.getKey(), entry.getValue());
    //         }
    //     }
    //     return foodInventory;
    // }

    // public HashMap<Furniture, Integer> getFurnitureInventory() {
    //     HashMap<Furniture, Integer> furnitureInventory = new HashMap<>();
    //     for (Map.Entry<Furniture, Integer> entry : inventory.entrySet()) {
    //         if (entry.getKey() instanceof Furniture) {
    //             filteredInventory.put(entry.getKey(), entry.getValue());
    //         }
    //     }
    //     return furnitureInventory;
    // }
}
