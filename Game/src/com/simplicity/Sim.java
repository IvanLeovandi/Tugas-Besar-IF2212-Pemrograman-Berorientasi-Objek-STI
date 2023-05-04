package com.simplicity;

import java.util.*;

import com.simplicity.Exceptions.OverlapingRoomObjectException;
import com.simplicity.Foods.CookedFood.CookedFood;
import com.simplicity.Foods.Ingredients.Ingredient;
import com.simplicity.Furniture.Furniture;
import com.simplicity.Interfaces.*;

public class Sim {
    private String name;
    private Job job;
    private int balance;
    private Inventory<Furniture> furnitureInventory;
    private Inventory<Ingredient> ingredientsInventory;
    private Inventory<CookedFood> cookedFoodInventory;
    private int satiety;
    private int mood;
    private int health;
    private String status;
    private House house;
    private House currentHouse;
    private Room currentRoom;
    private Point currentPosition;
    private int simNumber;
    private Pair<Boolean,Integer> changeJob;
    private Pair<Boolean, Integer> isUpgradeHouse;
    private ArrayList<UpgradeState<Purchasable, Integer, Integer>> deliveryList;
    private int timeSleep;
    private Pair <Integer, Integer> timeDefecateEat;

    public static int numberOfSims = 0;

    //Konstruktor
    public Sim(String name, Point location) {
        setName(name);
        this.job = new Job();
        this.balance = 100;
        this.furnitureInventory = new Inventory<Furniture>();
        this.ingredientsInventory = new Inventory<Ingredient>();
        this.cookedFoodInventory = new Inventory<CookedFood>();
        this.satiety = 80;
        this.mood = 80;
        this.health = 80;
        this.status = "Idle";
        this.house = new House(location, this);
        this.currentHouse = house;
        this.currentRoom = house.getRoomList().get(new Point(0, 0));
        this.currentPosition = new Point(0, 0);
        numberOfSims++;
        this.simNumber = numberOfSims;
        this.changeJob = new Pair<Boolean,Integer>(false,0);
        this.isUpgradeHouse = new Pair<Boolean, Integer>(false,0);
        this.deliveryList = new ArrayList<UpgradeState<Purchasable, Integer, Integer>>();
        this.timeSleep = GameTimer.gameTime;
        this.timeDefecateEat = new Pair<Integer, Integer>(GameTimer.gameTime, GameTimer.gameTime);
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

    public Inventory<Furniture> getFurnitureInventory() {
        return furnitureInventory;
    }

    public Inventory<Ingredient> getIngredientsInventory() {
        return ingredientsInventory;
    }

    public Inventory<CookedFood> getCookedFoodInventory() {
        return cookedFoodInventory;
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

    public House getHouse() {
        return house;
    }

    public House getCurrentHouse() {
        return currentHouse;
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public Point getCurrentPosition() {
        return currentPosition;
    }

    public int getSimNumber() {
        return simNumber;
    }

    public Pair<Boolean,Integer> getChangeJob() {
        return changeJob;
    }

    public Pair<Boolean, Integer> getIsUpgradeHouse() {
        return isUpgradeHouse;
    }

    public ArrayList<UpgradeState<Purchasable, Integer, Integer>> getDeliveryList() {
        return deliveryList;
    }

    public int getTimeSleep() {
        return timeSleep;
    }

    public Pair<Integer, Integer> getTimeDefecateEat() {
        return timeDefecateEat;
    }

    //Setter
    public void setName(String name) {
        this.name = name;
    }

    public void setJob(String jobName) {
        float pay =  job.getJobList().get(jobName) / 2;
        if (this.balance >= pay) {
            if (this.job.getDurationOfWork() < 12*60){
                System.out.println("You can't change your job now because you haven't worked for 12 minutes");
            }
            else {
                this.job = new Job(jobName);
                setChangeJob(true);
            }
        }
        else {
            System.out.println("You don't have enough money to change your job");
        }
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public void setFurnitureInventory(Inventory<Furniture> inventory) {
        this.furnitureInventory = inventory;
    }

    public void setIngredientsInventory(Inventory<Ingredient> inventory) {
        this.ingredientsInventory = inventory;
    }

    public void setCookedFoodInventory(Inventory<CookedFood> inventory) {
        this.cookedFoodInventory = inventory;
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

    public void setHouse(House house) {
        this.house = house;
    }

    public void setCurrentHouse(House currentHouse) {
        this.currentHouse = currentHouse;
    }

    public void setCurrentRoom(Room currentRoom) {
        this.currentRoom = currentRoom;
    }

    public void setCurrentPosition(Point currentPosition) {
        this.currentPosition = currentPosition;
    }

    public void setSimNumber(int simNumber) {
        this.simNumber = simNumber;
    }

    public void setChangeJob(Boolean changeJob) {
        this.changeJob = new Pair<Boolean,Integer>(changeJob, World.gameTimer.getDay());
    }

    public void setIsUpgradeHouse(Boolean isUpgradeHouse, int duration) {
        this.isUpgradeHouse = new Pair<Boolean, Integer>(isUpgradeHouse, duration);
    }

    public void setDeliveryList(ArrayList<UpgradeState<Purchasable, Integer, Integer>> deliveryList) {
        this.deliveryList = deliveryList;
    }

    public void setTimeSleep(int timeSleep) {
        this.timeSleep = timeSleep;
    }

    public void setTimeDefecateEat(Pair<Integer, Integer> timeDefecateEat) {
        this.timeDefecateEat = timeDefecateEat;
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

    public Boolean validationDuration(int duration, int modulo){
        if (duration % modulo == 0){
            return true;
        }
        else{
            return false;
        }

    }

    public Furniture currentObject(){
        return this.currentRoom.checkPoint(this.currentPosition);
    }


    //---------Active Action---------
    public void work(int duration){
        if (!getChangeJob().getFirst() || (getChangeJob().getFirst() && (World.gameTimer.getDay() - getChangeJob().getSecond() >= 1))){
            if (validationDuration(duration, 120) == false){
                System.out.println("Duration must be multiple of 120 seconds");
            }
            else {
                World.gameTimer.startTimer(duration);
                int satietyDecrease = (-10)*(duration/30);
                int moodDecrease = (-10)*(duration/30);
                changeSatiety(satietyDecrease);
                changeMood(moodDecrease);
                job.setDurationOfWork(job.getDurationOfWork() + duration);

                //Penambahan uang
                job.setDurationNotPaid(job.getDurationNotPaid() + duration);
                if (job.getDurationNotPaid() >= 4*60){
                    int x = job.getDurationNotPaid()/ (4*60);{
                        for (int i = 0; i < x; i++){
                            int moneyIncrease = job.getSalary();
                            setBalance(getBalance() + moneyIncrease);
                            job.setDurationNotPaid(job.getDurationNotPaid() - (4*60));
                        }
                    }
                }
            }
        }
        else {
            System.out.println("You can't work now because you have changed your job less than 1 day ago");
        }
    }

    public void workout(int duration) {
        if (validationDuration(duration, 20) == false){
            System.out.println("Duration must be multiple of 20 seconds");
        }
        else {
            World.gameTimer.startTimer(duration);
            int satietyDecrease = (-5)*(duration/20);
            int moodIncrease = 10*(duration/20);
            int healthIncrease = 5*(duration/20);

            changeSatiety(satietyDecrease);
            changeMood(moodIncrease);
            changeHealth(healthIncrease);
        }
    }

    public void sleep(int duration) {
        if (this.currentObject() != null) {
            if (this.currentObject().getName().equals("King Bed") || this.currentObject().getName().equals("Single Bed") || this.currentObject().getName().equals("Single Bed")) {
                    if (duration < 240){
                        System.out.println("The minimum duration of sleep is 240 seconds");
                    }
                    else {
                        int x = duration/240;
                        changeMood(30*x);
                        changeHealth(20*x);
                        World.gameTimer.startTimer(duration);
                    }
                }
            else {
                System.out.println("You can't sleep here");
            }
        }
        else {
            System.out.println("You can't sleep here");
        }
    }

    public void notSleep(){
        changeMood(-5);
        changeHealth(-5);
    }

    public void eat(Edible food) {
        if (currentObject() != null) {
            if (currentObject().getName().equals("Table And Chair")){
                if(food instanceof CookedFood){
                    CookedFood food1 = (CookedFood) food;
                   if (this.cookedFoodInventory.getInventory().containsKey(food1)){
                        World.gameTimer.startTimer(30);
                        if (timeDefecateEat.getFirst() > timeDefecateEat.getSecond()){
                            setTimeDefecateEat(new Pair<Integer, Integer>(timeDefecateEat.getFirst(), GameTimer.gameTime));
                        }
                        changeSatiety(food1.getSatietyPoint());
                        this.cookedFoodInventory.getInventory().remove(food1);
                    }
                    else{
                        System.out.println("You don't have this food");
                    }
                }
                else if(food instanceof Ingredient){
                    Ingredient food1 = (Ingredient) food;
                    if (this.ingredientsInventory.getInventory().containsKey(food1)){
                        World.gameTimer.startTimer(30);
                        if (timeDefecateEat.getFirst() > timeDefecateEat.getSecond()){
                            setTimeDefecateEat(new Pair<Integer, Integer>(timeDefecateEat.getFirst(), GameTimer.gameTime));
                        }
                        changeSatiety(food1.getSatietyPoint());
                        this.ingredientsInventory.getInventory().remove(food1);
                    }
                    else{
                        System.out.println("You don't have this food");
                    }
                }
                else {
                    System.out.println("You can't eat this");
                }
            }
            else{
                System.out.println("You can't eat here");
            }
        }
        else {
            System.out.println("You can't eat here");
        }

    }

    public void cook(CookedFood cookedFood) {
        if (currentObject() != null) {
            if (currentObject().getName().equals("Stove")){
                List<Ingredient> ingredients = cookedFood.getIngredients();
                Boolean flag = true;
                for (Ingredient ingredient : ingredients) {
                    if (this.ingredientsInventory.getInventory().containsKey(ingredient)){
                        if (this.ingredientsInventory.getInventory().get(ingredient) < 1) {
                            System.out.println("Sorry, " + ingredient.getName() + " is out of stock");
                            flag = false;
                        }
                    }
                    else {
                        System.out.println("Sorry, " + ingredient.getName() + " is out of stock");
                        flag = false;
                    }
                }

                if (flag == true){
                    Double duration = 1.5 * cookedFood.getSatietyPoint();
                    int duration1 = duration.intValue();
                    World.gameTimer.startTimer(duration1);
                    for (Ingredient ingredient : ingredients) {
                        this.ingredientsInventory.removeItem(ingredient);
                    }
                    this.cookedFoodInventory.addItem(cookedFood, 1);
                    System.out.println("You have successfully cooked " + cookedFood.getName());
                    changeMood(10);
                }
                else{
                    System.out.println("You can't cook this because you don't have the ingredients");
                }
            }
            else{
                System.out.println("You can't cook here");
            }
        }
        else {
            System.out.println("You can't cook here");
        }
    }

    public void visit(House house1, House house2) {
        int x1 = house1.getLocation().getX();
        int y1 = house1.getLocation().getY();

        int x2 = house2.getLocation().getX();
        int y2 = house2.getLocation().getY();

        //menghitung jarak (waktu) antara titik Sim dan rumah yang dikunjungi
        double distance = Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
        int duration = (int)distance;
        World.gameTimer.startTimer(duration);

        //Efek berkunjung
        int moodIncrease = 10*(int)distance/30;
        int satietyDecrease = -10*(int)distance/30;

        this.currentHouse = house2;

        changeMood(moodIncrease);
        changeSatiety(satietyDecrease);
    }

    public void defecate(int duration) {
        if (currentObject() != null) {
            if (currentObject().getName().equals("Toilet")) {
                if (validationDuration(duration, 10) == false){
                    System.out.println("Duration must be multiple of 10 seconds");
                }
                else {
                    setTimeDefecateEat(new Pair<Integer,Integer>(GameTimer.gameTime, getTimeDefecateEat().getSecond()));
                    World.gameTimer.startTimer(duration);
                    int satietyDecrease = -20;
                    int moodIncrease = 10;
                    changeSatiety(satietyDecrease);
                    changeMood(moodIncrease);
                }
            }
            else {
                System.out.println("You can't defecate here");
            }
        }
        else {
            System.out.println("You can't defecate here");
        }
    }

    public void notDefecate() {
        changeHealth(-5);
        changeMood(-5);
    }

    //---------Upgrade Action---------
    public void upgradeHouse(Point upgradeRoom, String direction, String name) {
        if (isUpgradeHouse.getFirst() == false){
            if (balance < 1500){
                System.out.println("You can't upgrade the house");
            }
            else{
                if(!currentHouse.equals(house)){
                    System.out.println("You can't upgrade the house because this is not your house");
                }
                else{
                    balance -= 1500;
                    this.setIsUpgradeHouse(true, 1080);
                    this.house.setUpgradeState(new UpgradeState<Point,String,String>(upgradeRoom, direction, name));
                }
            }
        }
        else{
            System.out.println("You can't upgrade the house because you are upgrading the house");
        }
    }

    public void buy(Purchasable item, int quantity) {
        if (item instanceof Furniture){
            Furniture furniture = (Furniture) item;
            int itemPrice = furniture.getPrice() * quantity;
            if (balance < itemPrice){
                System.out.println("You can't buy the furniture");
            }
            else{
                balance -= itemPrice;
                int deliveryTime = (new Random().nextInt(5) + 1) * 30;
                deliveryList.add(new UpgradeState<Purchasable,Integer,Integer>(item, quantity, deliveryTime));
            }
        }
        else if (item instanceof Ingredient){
            Ingredient ingredient = (Ingredient) item;
            int itemPrice = ingredient.getPrice() * quantity;
            if (balance < itemPrice){
                System.out.println("You can't buy the food");
            }
            else{
                balance -= itemPrice;
                int deliveryTime = (new Random().nextInt(5) + 1) * 30;
                deliveryList.add(new UpgradeState<Purchasable,Integer,Integer>(item, quantity, deliveryTime));
            }
        }
        else {
            throw new IllegalArgumentException("The item is not purchasable");
        }
    }

    //---------Non Active Action---------
    public void moveToRoom(House house,Room room) {
        if (house.getRoomList().containsValue(room) == false){
            System.out.println("You can't move to the room");
        }
        else{
            room.addSim(this);
            currentRoom.removeSim(this);
            this.currentRoom = room;
        }
    }

    public void setUpObject (Point placement, int rotation, Furniture furniture) throws OverlapingRoomObjectException {
        //Mengecek apakah furniture yang dipilih ada di inventory
        if (furnitureInventory.getInventory().containsKey(furniture) == false){
            System.out.println("You don't have the furniture");
        }
        else {
            currentRoom.placeFurniture(placement, rotation, furniture);
        }

        //Menghapus furniture dari inventory
        furnitureInventory.removeItem(furniture);
    }

    public void viewInventory() {
        //Menampilkan inventory furniture
        System.out.println("Furniture Inventory");
        String header = String.format("| %-20s | %-10s |", "Item", "Quantity");
        String line = "-".repeat(header.length());

        System.out.println(line);
        System.out.println(header);
        System.out.println(line);

        for (Furniture item : furnitureInventory.getInventory().keySet()) {
            int quantity = furnitureInventory.getInventory().get(item);
            String row = String.format("| %-20s | %-10d |", item.toString(), quantity);
            System.out.println(row);
        }
        System.out.println(line);

        //Menampilkan inventory cooked food
        System.out.println("Cooked Food Inventory");
        System.out.println(line);
        System.out.println(header);
        System.out.println(line);

        for (CookedFood item : cookedFoodInventory.getInventory().keySet()) {
            int quantity = cookedFoodInventory.getInventory().get(item);
            String row = String.format("| %-20s | %-10d |", item.toString(), quantity);
            System.out.println(row);
        }
        System.out.println(line);

        //Menampilkan inventory ingredients
        System.out.println("Ingredients Inventory");
        System.out.println(line);
        System.out.println(header);
        System.out.println(line);

        for (Ingredient item : ingredientsInventory.getInventory().keySet()) {
            int quantity = ingredientsInventory.getInventory().get(item);
            String row = String.format("| %-20s | %-10d |", item.toString(), quantity);
            System.out.println(row);
        }
        System.out.println(line);


    }
    public void moveToObject(Furniture furniture, int furnitureX) {
        if (currentRoom.getfurnitureList().contains(furniture) == false){
            System.out.println("You can't move to the object");
        }
        else{
            Point furniturePosition = currentRoom.getFurnitureLocation(furniture, furnitureX);
            this.currentPosition = furniturePosition;
        }
    }

    public void viewTime() {
        if(currentObject().getName().equals("Clock")){
            System.out.println("The time is : Day " + World.gameTimer.getDay() + " Seconds " + World.gameTimer.getSecond());
            //Menampilkan sisa waktu yang ada pada hari ini
            int remainingTime = 720 - World.gameTimer.getSecond();
            System.out.println("Remaining time : " + remainingTime + " seconds");
        }
        else{
            System.out.println("You can't view the time");
        }
    }

    //Another Action Note : Masih disesuain sama keinginan kelompok
    public void nubes(){
        changeMood(-50);
        changeHealth(-50);
    }

    public void sayHello(){
        changeMood(10);
        System.out.println("Hello, my name is " + getName());
    }

    public void listenMusic(int duration){
        changeMood(duration/10);
    }

    public void watchTV(int duration){
        changeMood(duration/10);
    }

    public void bath(int duration){
        changeMood(duration/10);
        changeHealth(duration/10);
    }

    public void meetup(int duration){
        changeMood(duration/10);
        balance -= duration/10;
    }

    public void missyou(int duration){
        changeMood(-duration/10);
        changeHealth(-duration/10);
    }
    //------------
    private void die(){
        setStatus("Die");
    }
}


