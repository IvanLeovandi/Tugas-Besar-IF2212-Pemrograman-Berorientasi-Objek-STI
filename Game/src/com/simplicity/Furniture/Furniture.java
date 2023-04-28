package com.simplicity.Furniture;

import com.simplicity.Dimension2D;
import com.simplicity.GameObject;
import com.simplicity.Interfaces.Purchasable;


public class Furniture extends GameObject implements Purchasable{
    
    private MyFurniture myFurniture;
    private final static String type = "Furniture";
    
    public enum MyFurniture {
        SINGLEBED("Single Bed", new Dimension2D(1,4), 50, 1), 
        QUEENBED("Queen Bed", new Dimension2D(2,4), 100, 2), 
        KINGBED("King Bed", new Dimension2D(2,5), 150, 3), 
        TOILET("Toilet", new Dimension2D(1,1), 50, 4), 
        GASSTOVE("Gas Stove", new Dimension2D(1,2), 100, 5), 
        ELECTRICSTOVE("Electric Stove", new Dimension2D(1,1), 200, 6), 
        TABLEANDCHAIR("Table And Chair", new Dimension2D(3,3), 50, 7), 
        CLOCK("Clock", new Dimension2D(1,1), 10, 8);
        
        private final String type = "Furniture";
        private final String name;
        private final Dimension2D size;
        private final int price, id;
        
        MyFurniture(String name, Dimension2D size, int price, int id){
            this.name = name;
            this.size = size;
            this.price = price;
            this.id = id;
        }

        public String getType(){
            return type;
        }
    
        public String getName() {
            return name;
        }
    
        public Dimension2D getSize() {
            return size;
        }
    
        public int getPrice() {
            return price;
        }
    
        public int getId(){
            return id;
        }
    }
    
    public Furniture(String name) {
        super(type);
        switch (name) {
            case "Single Bed":
                this.myFurniture = MyFurniture.SINGLEBED;
                break;
            case "Queen Bed":
                this.myFurniture = MyFurniture.QUEENBED;
                break;
            case "King Bed":
                this.myFurniture = MyFurniture.KINGBED;
                break;
            case "Toilet":
                this.myFurniture = MyFurniture.TOILET;
                break;
            case "Gas Stove":
                this.myFurniture = MyFurniture.GASSTOVE;
                break;
            case "Electric Stove":
                this.myFurniture = MyFurniture.ELECTRICSTOVE;
                break;
            case "Table And Chair":
                this.myFurniture = MyFurniture.TABLEANDCHAIR;
                break;
            case "Clock":
                this.myFurniture = MyFurniture.CLOCK;
                break;
            default:
                System.out.println("No Match");
        }
    }
    
    public MyFurniture getMyFurniture(){
        return myFurniture;
    }

    public String getType(){
        return type;
    }

    public String getName() {
        return myFurniture.getName();
    }

    public Dimension2D getSize() {
        return myFurniture.getSize();
    }

    public int getPrice() {
        return myFurniture.getPrice();
    }

    public int getId(){
        return myFurniture.getId();
    }

}