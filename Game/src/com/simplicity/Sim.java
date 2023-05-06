package com.simplicity;

import java.util.*;

import com.simplicity.Exceptions.InvalidDurationException;
import com.simplicity.Exceptions.MissingFoodTypeException;
import com.simplicity.Exceptions.OverlapingRoomObjectException;
import com.simplicity.Foods.Food;
import com.simplicity.Foods.FoodFactory;
import com.simplicity.Foods.Ingredient;
import com.simplicity.Furniture.*;

import com.simplicity.Interfaces.*;

public class Sim {
    private String name;
    private Job job;
    private int balance;
    private Inventory<Furniture> furnitureInventory;
    private Inventory<Ingredient> ingredientsInventory;
    private Inventory<Food> cookedFoodInventory;
    private int satiety;
    private int mood;
    private int health;
    private String status;
    private House house;
    private House currentHouse;
    private Room currentRoom;
    private Point currentPosition;
    private int simNumber;
    private Pair<Boolean, Integer> changeJob;
    private Pair<Boolean, Integer> isUpgradeHouse;
    private ArrayList<UpgradeState<Purchasable, Integer, Integer>> deliveryList;
    private int timeSleep;
    private Pair<Integer, Integer> timeDefecateEat;

    private World currentWorld = World.getInstance();

    public static int numberOfSims = 0;

    // Konstruktor
    public Sim(String name, Point location) {
        setName(name);
        this.job = new Job();
        this.balance = 100;
        this.furnitureInventory = new Inventory<Furniture>();
        this.ingredientsInventory = new Inventory<Ingredient>();
        this.cookedFoodInventory = new Inventory<Food>(); 
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
        this.changeJob = new Pair<Boolean, Integer>(false, 0);
        this.isUpgradeHouse = new Pair<Boolean, Integer>(false, 0);
        this.deliveryList = new ArrayList<UpgradeState<Purchasable, Integer, Integer>>();
        this.timeSleep = GameTimer.gameTime;
        this.timeDefecateEat = new Pair<Integer, Integer>(GameTimer.gameTime, GameTimer.gameTime);
    }

    // Getter
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

    public Inventory<Food> getCookedFoodInventory() {
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

    public Pair<Boolean, Integer> getChangeJob() {
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

    // Setter
    public void setName(String name) {
        this.name = name;
    }

    public void setJob(String jobName) {
        float pay = job.getJobList().get(jobName) / 2;
        if (this.balance >= pay) {
            if (this.job.getDurationOfWork() < 12 * 60) {
                System.out.println("You can't change your job now because you haven't worked for 12 minutes");
            } else {
                this.balance -= (int) pay;
                this.job = new Job(jobName);
                setChangeJob(true);
            }
        } else {
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

    public void setCookedFoodInventory(Inventory<Food> inventory) {
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
        this.changeJob = new Pair<Boolean, Integer>(changeJob, currentWorld.gameTimer().getDay());
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

    // Method
    public void changeSatiety(int x) {
        int i = satiety + x;
        if (i > 100) {
            setSatiety(100);
        } else if (i <= 0) {
            setSatiety(0);
            die();
        } else {
            setSatiety(i);
        }

    }

    public void changeMood(int x) {
        int i = mood + x;
        if (i > 100) {
            setMood(100);
        } else if (i <= 0) {
            setMood(0);
            die();
        } else {
            setMood(i);
        }
    }

    public void changeHealth(int x) {
        int i = health + x;
        if (i > 100) {
            setHealth(100);
        } else if (i <= 0) {
            setHealth(0);
            die();
        } else {
            setHealth(i);
        }
    }

    public Boolean validationDuration(int duration, int modulo) {
        if (duration % modulo == 0) {
            return true;
        } else {
            return false;
        }

    }

    public Furniture currentObject() {
        return this.currentRoom.checkPoint(this.currentPosition);
    }

    // ---------Active Action---------
    public void work(int duration) throws InvalidDurationException {
        if (!getStatus().equals("Die")) {
            if (!getChangeJob().getFirst() || (getChangeJob().getFirst()
                    && (currentWorld.gameTimer().getDay() - getChangeJob().getSecond() >= 1))) {
                if (validationDuration(duration, 120) == false) {
                    throw new InvalidDurationException("Duration must be multiple of 120 seconds");
                } else {
                    currentWorld.gameTimer().startTimer(duration);
                    int satietyDecrease = (-10) * (duration / 30);
                    int moodDecrease = (-10) * (duration / 30);
                    changeSatiety(satietyDecrease);
                    changeMood(moodDecrease);
                    job.setDurationOfWork(job.getDurationOfWork() + duration);

                    // Penambahan uang
                    job.setDurationNotPaid(job.getDurationNotPaid() + duration);
                    if (job.getDurationNotPaid() >= 4 * 60) {
                        int x = job.getDurationNotPaid() / (4 * 60);
                        {
                            for (int i = 0; i < x; i++) {
                                int moneyIncrease = job.getSalary();
                                setBalance(getBalance() + moneyIncrease);
                                job.setDurationNotPaid(job.getDurationNotPaid() - (4 * 60));
                            }
                        }
                    }

                    if (!(getStatus() == "Die")) {
                        System.out.println(getName() + " has worked for " + duration + " seconds");
                    }
                    currentWorld.updateWorld(duration);
                }
            } else {
                System.out.println("You can't work now because you have changed your job less than 1 day ago");
            }
        }
    }

    public void workout(int duration) {
        if (!getStatus().equals("Die")) {
            if (validationDuration(duration, 20) == false) {
                System.out.println("Duration must be multiple of 20 seconds");
            } else {
                currentWorld.gameTimer().startTimer(duration);
                int satietyDecrease = (-5) * (duration / 20);
                int moodIncrease = 10 * (duration / 20);
                int healthIncrease = 5 * (duration / 20);

                changeSatiety(satietyDecrease);
                changeMood(moodIncrease);
                changeHealth(healthIncrease);

                if (!(getStatus() == "Die")) {
                    System.out.println(getName() + " has worked out for " + duration + " seconds");
                }
                currentWorld.updateWorld(duration);
            }
        }
    }

    public void sleep(int duration, Sim currentSim) {
        if (!getStatus().equals("Die")) {
            if (currentObject() != null && (this.currentObject().getName().equals("KING BED")
                    || this.currentObject().getName().equals("SINGLE BED")
                    || this.currentObject().getName().equals("QUEEN BED"))) {
                if (duration < 240) {
                    System.out.println("The minimum duration of sleep is 240 seconds");
                } else {
                    int x = duration / 240;
                    changeMood(30 * x);
                    changeHealth(20 * x);
                    currentWorld.gameTimer().startTimer(duration);
                    setTimeSleep(GameTimer.gameTime);
                    if (!(getStatus() == "Die")) {
                        System.out.println(getName() + " has slept for " + duration + " seconds");
                    }
                    currentWorld.updateWorld(duration);
                }
            } else {
                System.out.println("You can't sleep here");
                System.out.println("You need a bed to sleep.");
            }
        }
    }

    public void notSleep() {
        changeMood(-5);
        changeHealth(-5);
    }

    public void eat(Edible food) {
        if (!getStatus().equals("Die")) {
            if (currentObject() != null && currentObject().getName().equals("TABLE AND CHAIR")) {
                if (food instanceof Ingredient) {
                    Ingredient food1 = (Ingredient) food;
                    if (this.ingredientsInventory.getInventory().containsKey(food1)) {
                        currentWorld.gameTimer().startTimer(30);
                        if (timeDefecateEat.getFirst() > timeDefecateEat.getSecond()) {
                            setTimeDefecateEat(
                                    new Pair<Integer, Integer>(timeDefecateEat.getFirst(), GameTimer.gameTime));
                        }
                        changeSatiety(food1.getSatietyPoint());
                        this.ingredientsInventory.removeItem(food1);

                        if (!(getStatus() == "Die")) {
                            System.out.println(getName() + " has eaten " + food1.getType());
                        }

                        currentWorld.updateWorld(30);
                    } else {
                        System.out.println("You don't have this food");
                    }
                } else if (food instanceof Food) {
                    Food food1 = (Food) food;
                    if (this.cookedFoodInventory.getInventory().containsKey(food1)) {
                        currentWorld.gameTimer().startTimer(30);
                        if (timeDefecateEat.getFirst() > timeDefecateEat.getSecond()) {
                            setTimeDefecateEat(
                                    new Pair<Integer, Integer>(timeDefecateEat.getFirst(), GameTimer.gameTime));
                        }
                        changeSatiety(food1.getSatietyPoint());
                        this.cookedFoodInventory.removeItem(food1);

                        if (!(getStatus() == "Die")) {
                            System.out.println(getName() + " has eaten " + food1.getType());
                        }

                        currentWorld.updateWorld(30);
                    } else {
                        System.out.println("You don't have this food");
                    }
                }
            } else {
                System.out.println("You can't eat here");
                System.out.println("You need a table and chair to eat.");
            }
        }
    }

    public void cook(String foodName, Sim currentSim) {
        if (!getStatus().equals("Die")) {
            try {
                if (currentObject() != null && (currentObject().getName().equals("GAS STOVE")
                        || currentObject().getName().equals("ELECTRIC STOVE"))) {
                    Set<String> ingredients = FoodFactory.getInstance().getIngredientsOfFood(foodName);
                    Boolean flag = true;
                    for (String ingredientName : ingredients) {
                        Ingredient currentIngredient;

                        currentIngredient = (Ingredient) FoodFactory.getInstance().createFood(ingredientName);
                        if (this.ingredientsInventory.getInventory().containsKey(currentIngredient)) {
                            if (this.ingredientsInventory.getInventory().get(currentIngredient) < 1) {
                                System.out.println("Sorry, " + currentIngredient.getType() + " is out of stock");
                                flag = false;
                            }

                        } else {
                            System.out.println("Sorry, " + currentIngredient.getType() + " is out of stock");
                            flag = false;
                        }

                    }

                    if (flag == true) {
                        Food cookedFood = FoodFactory.getInstance().createFood(foodName);
                        Double duration = 1.5 * cookedFood.getSatietyPoint();
                        int duration1 = duration.intValue();
                        currentWorld.gameTimer().startTimer(duration1);
                        for (String ingredientName : ingredients) {
                            this.ingredientsInventory
                                    .removeItem((Ingredient) FoodFactory.getInstance().createFood(ingredientName));
                        }
                        this.cookedFoodInventory.addItem(cookedFood, 1);
                        changeMood(10);
                        if (!(getStatus() == "Die")) {
                            System.out
                                    .println(currentSim.getName() + " has successfully cooked " + cookedFood.getType());
                        }
                        currentWorld.updateWorld(duration1);
                    } else {
                        System.out.println("You can't cook this because you don't have the ingredients");
                    }
                } else {
                    System.out.println("You can't cook here");
                    System.out.println("You need a stove to cook.");
                }
            } catch (MissingFoodTypeException e) {
                e.printStackTrace();
            }
        }
    }

    public void visit(House house1, House house2) {
        if (!getStatus().equals("Die")) {
            int x1 = house1.getLocation().getX();
            int y1 = house1.getLocation().getY();

            int x2 = house2.getLocation().getX();
            int y2 = house2.getLocation().getY();

            // menghitung jarak (waktu) antara titik Sim dan rumah yang dikunjungi
            double distance = Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
            int duration = (int) distance;
            currentWorld.gameTimer().startTimer(duration);

            // Efek berkunjung
            int x = duration / 30;
            changeMood(10 * x);
            changeSatiety(-10 * x);
            this.currentHouse = house2;
            if (!(getStatus() == "Die")) {
                System.out.println(
                        getName() + " has successfully visited " + house2.getHouseOwner().getName() + "'s house");
            }
            currentWorld.updateWorld(duration);
        }
    }

    public void defecate() {
        if (!getStatus().equals("Die")) {
            if (currentObject() != null && (currentObject().getName().equals("TOILET"))) {
                setTimeDefecateEat(
                        new Pair<Integer, Integer>(GameTimer.gameTime, getTimeDefecateEat().getSecond()));
                currentWorld.gameTimer().startTimer(10);
                changeSatiety(-20);
                changeMood(10);
                if (!(getStatus() == "Die")) {
                    System.out.println(getName() + " has successfully defecated");
                }
                currentWorld.updateWorld(10);
            } else {
                System.out.println("You can't defecate here");
                System.out.println("You need a toilet to defecate.");

            }
        }
    }

    public void notDefecate() {
        changeHealth(-5);
        changeMood(-5);
    }

    // ---------Upgrade Action---------
    public void upgradeHouse(Point upgradeRoom, String direction, String name) {
        if (!getStatus().equals("Die")) {
            if (isUpgradeHouse.getFirst() == false) {
                if (balance < 1500) {
                    System.out.println("You can't upgrade the house");
                } else {
                    if (!currentHouse.equals(house)) {
                        System.out.println("You can't upgrade the house because this is not your house");
                    } else {
                        balance -= 1500;
                        this.setIsUpgradeHouse(true, 1080);
                        this.house
                                .setUpgradeState(new UpgradeState<Point, String, String>(upgradeRoom, direction, name));
                    }
                }
            } else {
                System.out.println("You can't upgrade the house because you are upgrading the house");
            }
        }
    }

    public void buy(Purchasable item, int quantity) {
        if (!getStatus().equals("Die")) {
            if (item instanceof Furniture) {
                Furniture furniture = (Furniture) item;
                int itemPrice = furniture.getPrice() * quantity;
                if (balance < itemPrice) {
                    System.out.println("You can't buy the furniture");
                } else {
                    balance -= itemPrice;
                    int deliveryTime = (new Random().nextInt(5) + 1) * 30;
                    System.out.println("Delivery time: " + deliveryTime);
                    deliveryList.add(new UpgradeState<Purchasable, Integer, Integer>(item, quantity, deliveryTime));
                }
            } else if (item instanceof Ingredient) {
                Ingredient ingredient = (Ingredient) item;
                int itemPrice = ingredient.getPrice() * quantity;
                if (balance < itemPrice) {
                    System.out.println("You can't buy the food");
                } else {
                    balance -= itemPrice;
                    int deliveryTime = (new Random().nextInt(5) + 1) * 30;
                    System.out.println("Delivery time: " + deliveryTime);
                    deliveryList.add(new UpgradeState<Purchasable, Integer, Integer>(item, quantity, deliveryTime));
                }
            } else {
                throw new IllegalArgumentException("The item is not purchasable");
            }
        }
    }

    // ---------Non Active Action---------
    public void moveToRoom(House house, Room room) {
        if (!getStatus().equals("Die")) {
            if (house.getRoomList().containsValue(room) == false) {
                System.out.println("You can't move to the room");
            } else {
                room.addSim(this);
                currentRoom.removeSim(this);
                this.currentRoom = room;
            }
        }
    }

    public void setUpObject(Point placement, int rotation, int idx) throws OverlapingRoomObjectException {
        // Mengecek apakah furniture yang dipilih ada di inventory
        if (!getStatus().equals("Die")) {
            String name = null;
            if (furnitureInventory.getInventory()
                    .containsKey(furnitureInventory.getInventory().keySet().toArray()[idx]) == false) {
                System.out.println("You don't have the furniture");
            } else {
                try {
                    for (Furniture furniture : furnitureInventory.getInventory().keySet()) {
                        if (idx == 0) {
                            name = furniture.getName();
                            break;
                        } else {
                            idx--;
                        }
                    }
                    currentRoom.placeFurniture(placement, rotation,
                            new Furniture(name));
                } catch (OverlapingRoomObjectException e) {
                    System.out.println(e.getMessage());
                    return;
                }
                // Menghapus furniture dari inventory
                furnitureInventory.removeItem(new Furniture(name));
            }
        }
    }

    public void viewInventory() {
        if (!getStatus().equals("Die")) {
            int num = 1;
            // Menampilkan inventory furniture
            String header = String.format("| %-4s | %-20s |  %-20s | %-10s |", "No", "Item Name", "Category",
                    "Quantity");
            String line = "-".repeat(header.length());

            System.out.println(line);
            System.out.println(header);
            System.out.println(line);

            for (Furniture item : furnitureInventory.getInventory().keySet()) {
                int quantity = furnitureInventory.getInventory().get(item);
                String row = String.format("| %-4s | %-20s | %-20s | %-10d |", num, item.getName(), item.getType(),
                        quantity);
                System.out.println(row);
            }
            System.out.println(line);

            // Menampilkan inventory cooked food
            System.out.println("Cooked Food Inventory");
            System.out.println(line);
            System.out.println(header);
            System.out.println(line);

            for (Food item : cookedFoodInventory.getInventory().keySet()) {
                int quantity = cookedFoodInventory.getInventory().get(item);
                String row = String.format("| %-4s | %-20s | %-20s | %-10d |", num, item.getType(), "COOKED FOOD",
                        quantity);
                System.out.println(row);
            }
            System.out.println(line);

            // Menampilkan inventory ingredients
            System.out.println("Ingredients Inventory");
            System.out.println(line);
            System.out.println(header);
            System.out.println(line);

            for (Ingredient item : ingredientsInventory.getInventory().keySet()) {
                int quantity = ingredientsInventory.getInventory().get(item);
                String row = String.format("| %-4s | %-20s | %-20s | %-10d |", num, item.getType(), "INGREDIENT",
                        quantity);
                System.out.println(row);
            }
            System.out.println(line);
        }
    }

    public void printFurnitureInventory() {
        if (!getStatus().equals("Die")) {
            // Menampilkan inventory furniture
            int num = 1;
            System.out.println("Furniture Inventory");
            String header = String.format("| %-4s | %-20s | %-10s |", "No", "Item", "Quantity");
            String line = "-".repeat(header.length());

            System.out.println(line);
            System.out.println(header);
            System.out.println(line);

            for (Furniture item : furnitureInventory.getInventory().keySet()) {
                int quantity = furnitureInventory.getInventory().get(item);
                String row = String.format("| %-4s | %-20s | %-10d |", num, item.getName(), quantity);
                System.out.println(row);
                num++;
            }
            System.out.println(line);
        }
    }

    public void printFoodInventory() {
        if (!getStatus().equals("Die")) {
            // Menampilkan inventory food
            int i = 1;
            int j = 1;
            System.out.println("Food Inventory");
            String header = String.format("| %-4s | %-20s | %-10s |", "No", "Item", "Quantity");
            String line = "-".repeat(header.length());

            // Menampilkan inventory cooked food
            System.out.println("Cooked Food Inventory");
            System.out.println(line);
            System.out.println(header);
            System.out.println(line);

            for (Food item : cookedFoodInventory.getInventory().keySet()) {
                int quantity = cookedFoodInventory.getInventory().get(item);
                String row = String.format("| %-4s | %-20s | %-10d |", i, item.getType(), quantity);
                System.out.println(row);
                i++;
            }
            System.out.println(line);

            System.out.println("Ingredients Inventory");
            System.out.println(line);
            System.out.println(header);
            System.out.println(line);

            for (Ingredient item : ingredientsInventory.getInventory().keySet()) {
                int quantity = ingredientsInventory.getInventory().get(item);
                String row = String.format("| %-4s | %-20s | %-10d |", j, item.getType(), quantity);
                System.out.println(row);
                j++;
            }
            System.out.println(line);
        }
    }

    public void moveToObject(Furniture furniture, int furnitureX) {
        if (!getStatus().equals("Die")) {
            if (!currentRoom.getfurnitureList().contains(furniture)) {
                System.out.println("You can't move to the object because the object is not in the room");
            } else {
                Point furniturePosition = currentRoom.getFurnitureLocation(furniture, furnitureX);
                this.currentPosition = furniturePosition;
            }
        }
    }

    public void viewTime(Sim currentSim) {
        if (!getStatus().equals("Die")) {
            if (currentObject() != null && (currentObject().getName().equals("CLOCK"))) {
                System.out.println("The time is : Day " + currentWorld.gameTimer().getDay() +
                        " Minutes " + currentWorld.gameTimer().getSecond() / 60 +
                        " Seconds " + currentWorld.gameTimer().getSecond() % 60);
                // Menampilkan sisa waktu yang ada pada hari ini
                int remainingTime = 720 - currentWorld.gameTimer().getSecond();
                System.out.println("Remaining time : " + remainingTime + " seconds");

                // Menampilkan sisa waktu untuk tindakan yang bisa ditinggal
                if (isUpgradeHouse.getFirst()) {
                    System.out.println("Remaining time for Upgrade House : " + isUpgradeHouse.getSecond() / 60
                            + " minutes " + isUpgradeHouse.getSecond() % 60 + " seconds");
                }
                if (!deliveryList.isEmpty()) {
                    for (UpgradeState<Purchasable, Integer, Integer> ingredient : deliveryList) {
                        System.out.println("Remaining time for Delivery " + ingredient.getFirst().getType()
                                + " : " + ingredient.getThird() / 60 + " minutes " + ingredient.getThird() % 60
                                + " seconds");
                    }
                }
            } else {
                System.out.println("You can't view the time");
                System.out.println("You need a clock to view time.");
            }
        }
    }

    // Another Action Note : Masih disesuain sama keinginan kelompok
    public void nubes(int duration) {
        if (!getStatus().equals("Die")) {
            if (currentObject() != null && currentObject().getName().equals("COMPUTER")) {
                if (duration < 20) {
                    System.out.println("You have to nubes for at least 20 seconds");
                } else {
                    currentWorld.gameTimer().startTimer(duration);
                    changeMood(-10 * (duration / 20));
                    changeHealth(-5 * (duration / 20));
                    if (!getStatus().equals("Die")) {
                        System.out.println("You have nubes for " + duration + " seconds");
                    }
                    currentWorld.updateWorld(duration);
                }
            } else {
                System.out.println("You can't nubes here");
                System.out.println("You need a computer to nubes.");
            }
        }
    }

    public void playGame(int duration) {
        if (!getStatus().equals("Die")) {
            if (currentObject() != null && currentObject().getName().equals("COMPUTER")) {
                currentWorld.gameTimer().startTimer(duration);
                changeMood(10 * (duration / 20));
                changeHealth(-1 * (duration / 20));
                if (!getStatus().equals("Die")) {
                    System.out.println("You have play game for " + duration + " seconds");
                }
                currentWorld.updateWorld(duration);
            } else {
                System.out.println("You can't play game here");
                System.out.println("You need a computer to play game.");
            }
        }
    }

    public void listenMusic(int duration) {
        if (!getStatus().equals("Die")) {
            if (currentObject() != null
                    && (currentObject().getName().equals("COMPUTER") || currentObject().getName().equals("SPEAKER"))) {
                currentWorld.gameTimer().startTimer(duration);
                changeMood(duration / 10);
                if (!getStatus().equals("Die")) {
                    System.out.println("You have listen music for " + duration + " seconds");
                }
                currentWorld.updateWorld(duration);
            } else {
                System.out.println("You can't listen music here");
                System.out.println("You need a computer or speaker to listen music.");
            }
        }
    }

    public void watchTV(int duration) {
        if (!getStatus().equals("Die")) {
            if (currentObject() != null && currentObject().getName().equals("TV")) {
                currentWorld.gameTimer().startTimer(duration);
                changeMood(5 * (duration / 10));
                if (!getStatus().equals("Die")) {
                    System.out.println("You have watch TV for " + duration + " seconds");
                }
                currentWorld.updateWorld(duration);
            } else {
                System.out.println("You can't watch here");
                System.out.println("You need a TV to watch.");
            }
        }
    }

    public void bath(int duration) {
        if (!getStatus().equals("Die")) {
            if (currentObject() != null && currentObject().getName().equals("SHOWER")) {
                currentWorld.gameTimer().startTimer(duration);
                changeMood(5 * (duration / 10));
                changeHealth(5 * (duration / 10));
                if (!getStatus().equals("Die")) {
                    System.out.println("You have bath for " + duration + " seconds");
                }
                currentWorld.updateWorld(duration);
            } else {
                System.out.println("You can't bath here");
                System.out.println("You need a shower to bath.");
            }
        }
    }

    public void meetup(int duration, Sim sim1, Sim sim2) {
        if (!getStatus().equals("Die")) {
            if (this.currentHouse != sim2.currentHouse) {
                System.out.println("You can't meet up with the sim because the sim is not in the same house");
            } else {
                if (this.currentRoom != sim2.currentRoom) {
                    System.out.println("You can't meet up with the sim because the sim is not in the same room");
                } else {
                    if (this.currentObject() != null && sim2.currentObject() != null
                            && sim1.currentObject().getName().equals("SOFA")
                            && sim2.currentObject().getName().equals("SOFA")) {
                        currentWorld.gameTimer().startTimer(duration);
                        sim1.changeMood(5 * (duration / 10));
                        sim1.changeHealth(5 * (duration / 10));
                        sim2.changeMood(5 * (duration / 10));
                        sim2.changeHealth(5 * (duration / 10));
                        if (!getStatus().equals("Die")) {
                            System.out.println(
                                    "You have meet up with " + sim2.getName() + " for " + duration + " seconds");
                        }
                    } else {
                        System.out.println("You can't meet up here");
                        System.out.println("You need a sofa to meet up.");
                    }
                }
            }
        }
    }

    public void call(int duration) {
        if (!getStatus().equals("Die")) {
            if (currentObject() != null && currentObject().getName().equals("Telephone")) {
                currentWorld.gameTimer().startTimer(duration);
                changeMood(5 * (duration / 10));
                balance -= 5 * (duration / 30);
                if (!getStatus().equals("Die")) {
                    System.out.println("You have call for " + duration + " seconds");
                }
                currentWorld.updateWorld(duration);
            } else {
                System.out.println("You can't call here");
                System.out.println("You need a telephone to call.");
            }
        }
    }

    // ------------
    private void die() {
        setStatus("Die");
    }
}
