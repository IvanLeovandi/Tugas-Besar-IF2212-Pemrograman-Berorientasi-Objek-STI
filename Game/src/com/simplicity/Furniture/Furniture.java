package com.simplicity.Furniture;

import java.util.Objects;

import com.simplicity.Dimension2D;
import com.simplicity.GameObject;
import com.simplicity.Interfaces.Purchasable;


public class Furniture extends GameObject implements Purchasable{
    
    private MyFurniture myFurniture;
    private final static String type = "FURNITURE";
    
    public enum MyFurniture {
        SINGLEBED("SINGLE BED", new Dimension2D(1,4), 50, 1), 
        QUEENBED("QUEEN BED", new Dimension2D(2,4), 100, 2), 
        KINGBED("KING BED", new Dimension2D(2,5), 150, 3), 
        TOILET("TOILET", new Dimension2D(1,1), 50, 4), 
        GASSTOVE("GAS STOVE", new Dimension2D(1,2), 100, 5), 
        ELECTRICSTOVE("ELECTRIC STOVE", new Dimension2D(1,1), 200, 6), 
        TABLEANDCHAIR("TABLE AND CHAIR", new Dimension2D(3,3), 50, 7), 
        CLOCK("CLOCK", new Dimension2D(1,1), 10, 8),
        TV("TV", new Dimension2D(2,1), 100, 9),
        SHOWER("SHOWER", new Dimension2D(1,1), 50, 10),
        SOFA("SOFA", new Dimension2D(1,1), 50, 11),
        TELEPHONE("TELEPHONE", new Dimension2D(1,1), 50, 12),
        COMPUTER("COMPUTER", new Dimension2D(1,1), 80, 13),
        SPEAKER("SPEAKER", new Dimension2D(1,1), 50, 14);
        
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
            case "SINGLE BED":
                this.myFurniture = MyFurniture.SINGLEBED;
                break;
            case "QUEEN BED":
                this.myFurniture = MyFurniture.QUEENBED;
                break;
            case "KING BED":
                this.myFurniture = MyFurniture.KINGBED;
                break;
            case "TOILET":
                this.myFurniture = MyFurniture.TOILET;
                break;
            case "GAS STOVE":
                this.myFurniture = MyFurniture.GASSTOVE;
                break;
            case "ELECTRIC STOVE":
                this.myFurniture = MyFurniture.ELECTRICSTOVE;
                break;
            case "TABLE AND CHAIR":
                this.myFurniture = MyFurniture.TABLEANDCHAIR;
                break;
            case "CLOCK":
                this.myFurniture = MyFurniture.CLOCK;
                break;
            case "TV":
                this.myFurniture = MyFurniture.TV;
                break;
            case "SHOWER":
                this.myFurniture = MyFurniture.SHOWER;
                break;
            case "SOFA":
                this.myFurniture = MyFurniture.SOFA;
                break;
            case "TELEPHONE":
                this.myFurniture = MyFurniture.TELEPHONE;
                break;
            case "COMPUTER":
                this.myFurniture = MyFurniture.COMPUTER;
                break;
            case "SPEAKER":
                this.myFurniture = MyFurniture.SPEAKER;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || !getClass().isAssignableFrom(o.getClass())) {
            return false;
        }
        return getName() == ((Furniture)o).getName();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName());
    }

}