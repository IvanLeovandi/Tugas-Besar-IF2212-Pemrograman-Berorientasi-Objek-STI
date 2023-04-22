package com.simplicity;

import java.util.*;
import com.simplicity.House;
import com.simplicity.Interfaces.Edible;
import com.simplicity.Interfaces.Purchasable;

public class Sim {
    private String name;
    private Job job;
    private int balance;
    private Inventory inventory;
    private int satiety;
    private int mood;
    private int health;
    private String status;
    private House house;

    //Konstruktor
    public Sim(String name) {
        this.name = name;
        this.job = new Job();
        this.balance = 100;
        this.inventory = new Inventory();
        this.satiety = 80;
        this.mood = 80;
        this.health = 80;
        this.status = "Idle";
    }

    //Getter
    public String getName() {
        return name;
    }

    public Job getJob() {
        return job;
    }

    public int getBalance() {
        return balance;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public int getSatiety() {
        return satiety;
    }

    public int getMood() {
        return mood;
    }

    public int getHealth() {
        return health;
    }

    public String getStatus() {
        return status;
    }

    //Setter
    public void setName(String name) {
        this.name = name;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    public void setSatiety(int satiety) {
        this.satiety = satiety;
    }

    public void setMood(int mood) {
        this.mood = mood;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    //Method
    public void changeSatiety(int x) {
        int i = satiety + x;
        if (i > 100) {
            setSatiety(100);
        }
        else if(i <= 0) {
            setSatiety(0);
            die();
        }
        else {
            setSatiety(i);
        }
        
    }

    public void changeMood(int x) {
        int i = mood + x;
        if (i > 100) {
            setMood(100);
        }
        else if(i <= 0) {
            setMood(0);
            die();
        }
        else {
            setMood(i);
        }
    }

    public void changeHealth(int x) {
        int i = health + x;
        if (i > 100) {
            setHealth(100);
        }
        else if(i <= 0) {
            setHealth(0);
            die();
        }
        else {
            setHealth(i);
        }
    }

    public void work(int duration){
        int satietyDecrease = (-10)*(duration/30);
        int moodDecrease = (-10)*(duration/30);
        
        changeSatiety(satietyDecrease);
        changeMood(moodDecrease);

        //Penambahan uang
        int moneyIncrease = job.getSalary();
        setBalance(getBalance() + moneyIncrease);
    }

    public void workout(int duration) {
        int satietyIncrease = (-5)*(duration/20);
        int moodDecrease = 10*(duration/20);
        int healthIncrease = 5*(duration/20);
        
        changeSatiety(satietyIncrease);
        changeMood(moodDecrease);
        changeHealth(healthIncrease); 
    }

    public void sleep(int duration) {
        int moodIncrease = 30*(duration/4*60);
        int healthIncrease = 20*(duration/4*60);

        changeMood(moodIncrease);
        changeHealth(healthIncrease);
    }

    public void notSleep(){
        changeMood(-5);
        changeHealth(-5);
    }

    public void eat(Edible food) {
        //Menunggu implementasi inventory

    }

    // public void cook(List<Ingredient> Ingredients) {
    //     //Menunggu implementasi inventory n objek food
    // }

    public void visit(House house1, House house2) {
        int x1 = house1.getLocation().getX();
        int y1 = house1.getLocation().getY();
        
        int x2 = house2.getLocation().getX();
        int y2 = house2.getLocation().getY();

        //menghitung jarak (waktu) antara titik Sim dan rumah yang dikunjungi
        double distance = Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));

        //Efek berkunjung
        int moodIncrease = 10*(int)distance/30;
        int satietyDecrease = -10*(int)distance/30;

        changeMood(moodIncrease);
        changeSatiety(satietyDecrease);
    }

    public void defecate() {
        int satietyDecrease = -20;
        int moodIncrease = 10;

        changeSatiety(satietyDecrease);
        changeMood(moodIncrease);
    }

    public void notDefecate() {
        changeHealth(-5);
        changeMood(-5);
    }

    public void upgradeHouse() {
        Scanner input = new Scanner(System.in);
        if (balance < 1500){
            System.out.println("You can't upgrade the house");
        }
        else{
            balance -= 1500;
            ArrayList<Room> rooms = house.getRoomList();
            if (house.getNumberofRoom() == 1){
                System.out.println("You have to choose the direction of adding the room");
                System.out.println("You can select top/bottom/left/right");
                String direction = input.nextLine();
                house.upgradeRoom(rooms.get(0), direction);              
            }
            else
            if (house.getNumberofRoom() >= 2){
                System.out.println("You have " + rooms.size() + " rooms.");
                System.out.println("Please choose the room you want to upgrade by entering its number:");
                house.printRoomList();

                int roomNumber = input.nextInt();
                System.out.println("You have to choose the direction of adding the room");
                System.out.println("You can select top/bottom/left/right");
                String direction = input.nextLine();
                house.upgradeRoom(rooms.get(roomNumber-1), direction);    
            }
        }


        
    }

    public void buy(Purchasable item) {

    }

    public void move() {

    }

    public void openInventory() {

    }

    public void viewTime() {

    }

    private void die(){
        setStatus("Die");
    }
}
