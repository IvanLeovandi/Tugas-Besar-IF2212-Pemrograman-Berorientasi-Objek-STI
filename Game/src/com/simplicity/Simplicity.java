package com.simplicity;

// // gui version
// import java.util.Random;

// import com.simplicity.UI.MainMenu;
// import com.simplicity.UI.SimplicityFrame;

// public class Simplicity {
//     public static void main(String[] args) {
//         Random rand = new Random();

//         SimplicityManager manager = new SimplicityManager();
//         Point p = new Point(rand.nextInt(64), rand.nextInt(64));
//         manager.setWorld(new World(64, 64));
//         manager.getWorld().setHouse(p, new House(p));
//         manager.setMainMenuPanel(new MainMenu(manager));
//         manager.setFrame(new SimplicityFrame(manager.getMainMenuPanel()));
//         manager.startWindow();

// cli version
import com.simplicity.Exceptions.OverlapingRoomObjectException;
import com.simplicity.Foods.FoodFactory;
import com.simplicity.Foods.CookedFood.CookedFood;
import com.simplicity.Foods.Ingredients.Ingredient;
import com.simplicity.Foods.Ingredients.Rice;
import com.simplicity.Furniture.*;
import com.simplicity.Interfaces.Edible;

import java.util.*;

public class Simplicity {
    // private static Point lastHouse = new Point(0,0);

    private static int x = 0;
    private static int y = 0;
    private static Sim currentSim;
    private static ArrayList<Sim> simList = new ArrayList<Sim>();
    private static final String[] mainMenu = { "ACTION", "SIM INFO", "CURRENT LOCATION", "VIEW INVENTORY",
            "UPGRADE HOUSE", "MOVE ROOM", "EDIT ROOM", "ADD SIM", "CHANGE SIM", "LIST OBJECT", "GO TO OBJECT", "HELP",
            "QUIT GAME" };
    private static final String[] actionMenu = { "WORK", "WORKOUT", "SLEEP", "EAT", "COOK", "VISIT", "DEFECATE",
            "NUBES", "PLAY GAME", "LISTENS TO MUSIC", "WATCH TV", "BATH", "MEET UP", "CALL", "BUY", "PLACE ITEM",
            "VIEW TIME", "HELP" };
    private static final String[] ingredientMenu = { "BEEF", "CARROT", "CHICKEN", "MILK", "PEANUT", "POTATO", "RICE",
            "SPINACH" };
    private static final String[] furnitureMenu = { "KING BED", "QUEEN BED", "SINGLE BED", "ELECTRIC STOVE",
            "GAS STOVE", "CLOCK", "SHOWER", "SOFA", "TABLE AND CHAIR", "TELEPHONE", "TOILET", "TV" };

    // private GameMenu menu = new GameMenu();

    public static void main(String[] args) throws Exception {
        Simplicity simplicity = new Simplicity();
        World world = World.getInstance();
        // SimplicityManager manager = SimplicityManager.getInstance();
        // manager.setWorld(new World(64, 64));
        // manager.getWorld().setHouse(p, new House(p));
        Scanner scan = new Scanner(System.in);
        int duration;
        int mainMenuChoice, playMenuChoice, actionMenuChoice, choice;
        String input;

        boolean exit = false;
        boolean play = false;

        while (!exit) {
            System.out.println("\nMain Menu");
            System.out.println("1. START GAME");
            System.out.println("2. HELP");
            System.out.println("3. EXIT\n");

            System.out.print(">> ");
            mainMenuChoice = scan.nextInt();

            try {
                switch (mainMenuChoice) {
                    case 1:
                        play = true;

                        simplicity.newSim();
                        world.setSim(currentSim.getHouse().getLocation(), currentSim);

                        System.out.println("\nHalo " + currentSim.getName());

                        while (play) {
                            simplicity.printMainMenu();
                            System.out.print(">> ");
                            playMenuChoice = scan.nextInt();

                            switch (playMenuChoice) {
                                case 1:
                                    simplicity.printActionMenu();
                                    System.out.print(">> ");
                                    actionMenuChoice = scan.nextInt();

                                    switch (actionMenuChoice) {
                                        case 1:
                                            // work
                                            System.out.println("Enter work duration: ");
                                            duration = scan.nextInt();
                                            currentSim.work(duration);
                                            break;

                                        case 2:
                                            // workout
                                            System.out.println("Enter workout duration: ");
                                            duration = scan.nextInt();
                                            currentSim.workout(duration);
                                            break;

                                        case 3:
                                            // sleep
                                            System.out.println("Enter sleep duration: ");
                                            duration = scan.nextInt();
                                            currentSim.sleep(duration, currentSim);
                                            break;

                                        case 4:
                                            // TODO: eat
                                            System.out.println("What do you want to eat?");
                                            currentSim.viewInventory();
                                            String food = scan.nextLine();
                                            // currentSim.eat(new FoodFactory());

                                            break;

                                        case 5:
                                            // TODO: cook
                                            System.out.println("What do you want to cook?");
                                            CookedFood.printListCookedFood();
                                            int cookedFoodChoice = scan.nextInt();
                                            currentSim.cook(new CookedFood(
                                                    CookedFood.getCookedFoodList().keySet().toArray()[cookedFoodChoice]
                                                            .toString()),
                                                    currentSim);
                                            break;

                                        case 6:
                                            // TODO: visit
                                            System.out.println("You are now in " + currentSim.getHouse().getLocation() + "house");
                                            world.printHouseList();
                                            System.out.println("Which house do you want to visit?");
                                            System.out.print("\n>> ");
                                            choice = scan.nextInt();
                                            currentSim.visit(currentSim.getCurrentHouse(), (House)(world.getMap().keySet().toArray()[choice-1]));
                                            break;

                                        case 7:
                                            // defecate
                                            System.out.println("Enter defecate duration: ");
                                            System.out.print("\n>> ");
                                            duration = scan.nextInt();
                                            currentSim.defecate();
                                            break;

                                        case 8:
                                            // nubes;
                                            System.out.println("Enter nubes duration: ");
                                            System.out.print("\n>> ");
                                            duration = scan.nextInt();
                                            currentSim.nubes(duration);
                                            break;

                                        case 9:
                                            // play game
                                            System.out.println("Enter play game duration: ");
                                            System.out.print("\n>> ");
                                            duration = scan.nextInt();
                                            currentSim.playGame(duration);
                                            break;

                                        case 10:
                                            // listens to music
                                            System.out.println("Enter listens to music duration: ");
                                            System.out.print("\n>> ");
                                            duration = scan.nextInt();
                                            currentSim.listenMusic(duration);
                                            break;

                                        case 11:
                                            // watch tv
                                            System.out.println("Enter watch tv duration: ");
                                            System.out.print("\n>> ");
                                            duration = scan.nextInt();
                                            currentSim.watchTV(duration);
                                            break;

                                        case 12:
                                            // bath
                                            System.out.println("Enter bath duration: ");
                                            System.out.print("\n>> ");
                                            duration = scan.nextInt();
                                            currentSim.bath(duration);
                                            break;

                                        case 13:
                                            // meetup
                                            if(world.getMap().size() >= 2) {
                                                System.out.println("Here are the list of sims that are alive");
                                                world.printSimsList();
                                                System.out.println("Which sims do you want to meet up with?");
                                                choice = scan.nextInt();
                                                System.out.println("Enter meet up duration: ");
                                                System.out.print("\n>> ");
                                                duration = scan.nextInt();
                                                currentSim.meetup(duration, currentSim,((Sim)world.getMap().values().toArray()[choice-1]));
                                            }
                                            break;

                                        case 14:
                                            // call
                                            System.out.println("Enter call duration: ");
                                            System.out.print("\n>> ");
                                            duration = scan.nextInt();
                                            currentSim.call(duration);
                                            break;

                                        case 15:
                                            // TODO: buy
                                            System.out.println("What kind of item do you want to buy?");
                                            System.out.println("1. INGREDIENT");
                                            System.out.println("2. FURNITURE");
                                            System.out.print("\n>> ");
                                            playMenuChoice = scan.nextInt();
                                            int i;
                                            if (playMenuChoice == 1) {
                                                System.out.println("What ingredient do you want to buy?");
                                                simplicity.printIngredientPrice();
                                                System.out.print("\n>> ");
                                                playMenuChoice = scan.nextInt();
                                                while (playMenuChoice < 1 || playMenuChoice > ingredientMenu.length) {
                                                    System.out.println("Your input is out of range!");
                                                    System.out.println("What ingredient do you want to buy?");
                                                    simplicity.printIngredientPrice();
                                                    playMenuChoice = scan.nextInt();
                                                }
                                                System.out.println("How many would you like to buy?");
                                                System.out.print("\n>> ");
                                                int amount = scan.nextInt();
                                                currentSim.buy(new Ingredient(ingredientMenu[playMenuChoice - 1]),
                                                        amount);
                                            } else if (playMenuChoice == 2) {
                                                System.out.println("What furniture would you like to buy?");
                                                simplicity.printFurniturePrice();
                                                System.out.print("\n>> ");
                                                playMenuChoice = scan.nextInt();
                                                while (playMenuChoice < 1 || playMenuChoice > furnitureMenu.length) {
                                                    System.out.println("Your input is out of range!");
                                                    System.out.println("What ingredient do you want to buy?");
                                                    simplicity.printFurniturePrice();
                                                    playMenuChoice = scan.nextInt();
                                                }
                                                System.out.println("How many would you like to buy?");
                                                System.out.print("\n>> ");
                                                int amount = scan.nextInt();
                                                currentSim.buy(new Ingredient(furnitureMenu[playMenuChoice - 1]),
                                                        amount);
                                            } else {
                                                System.out.println("That is not a valid input!");
                                            }

                                            // list purchaseable item
                                            // currentSim.buy(null, duration);
                                            break;

                                        case 16:
                                            // place item
                                            currentSim.getHouse().printRoomList();
                                            currentSim.printFurnitureInventory();
                                            System.out.println("What do you want to place?");
                                            System.out.print("\n>> ");
                                            int idx = scan.nextInt();
                                            currentSim.getCurrentRoom().printRoom();
                                            System.out.println("Please select the rotation (0/1/2/3): ");
                                            int rotation = scan.nextInt();
                                            while (rotation < 0 || rotation > 3) {
                                                System.out.println("Thats not a valid rotation!");
                                                rotation = scan.nextInt();
                                            }
                                            System.out.println("Please select the point you want to place the item");
                                            System.out.println("X");
                                            System.out.print(">> ");
                                            int x = scan.nextInt();
                                            System.out.println("Y");
                                            System.out.print(">> ");
                                            int y = scan.nextInt();
                                            while ((x < 0 || x > 5) || (y < 0 || y > 5)) {
                                                System.out.println("Thats not a valid point!");
                                            }
                                            try {
                                                currentSim.setUpObject(new Point(x, y), rotation, idx - 1);
                                            } catch (OverlapingRoomObjectException e) {
                                                System.out.println(e.getMessage());
                                            }
                                            currentSim.getCurrentRoom().printRoom();
                                            break;

                                        case 17:
                                            // view time
                                            currentSim.viewTime(currentSim);
                                            break;

                                        case 18:
                                            // help
                                            int help = 1;
                                            Help.printMessage(help);
                                            System.out.println("BACK/NEXT/EXIT");
                                            input = scan.nextLine();
                                            while (!(input.equals("EXIT"))) {
                                                if (input.equals("BACK")) {
                                                    if (help == 1) {
                                                        System.out.println("You are already at the first page!");
                                                    } else {
                                                        help--;
                                                    }
                                                } else if (input.equals("NEXT")) {
                                                    if (help == 3) {
                                                        System.out.println("You are already at the final page!");
                                                    } else {
                                                        help++;
                                                    }
                                                } else {
                                                    System.out.println("That is not a valid input!");
                                                }
                                                Help.printMessage(help);
                                                System.out.println("BACK/NEXT/EXIT");
                                                System.out.print("\n>>");
                                                input = scan.nextLine();
                                            }
                                            break;

                                        default:
                                            System.out.println("Please input the correct menu number");
                                            break;
                                    }
                                    break;

                                case 2:
                                    // sim info
                                    GameMenu.showSimInfo(currentSim);
                                    break;

                                case 3:
                                    // current location
                                    System.out.println("You are now in x: " + currentSim.getHouse().getLocation().getX()
                                            + " and y: " + currentSim.getHouse().getLocation().getY() + " house");
                                    System.out.println("You are now in " + currentSim.getCurrentRoom().getName()
                                            + " which located in x: "
                                            + currentSim.getCurrentRoom().getLocationInHouse().getX() + " and y: "
                                            + currentSim.getCurrentRoom().getLocationInHouse().getY());
                                    System.out.println("And you are now in x: " + currentSim.getCurrentPosition().getX()
                                            + " and y: " + currentSim.getCurrentPosition().getY());
                                    break;

                                case 4:
                                    // view inventory
                                    currentSim.viewInventory();
                                    break;

                                case 5:
                                    // upgrade house
                                    if (currentSim.getHouse().getNumberofRoom() == 1) {
                                        System.out.println("You have to choose the direction of adding the room");
                                        System.out.println("You can select top/bottom/left/right");
                                        scan.nextLine();
                                        input = scan.nextLine();

                                        System.out.println("Please create the name of the room");
                                        String name = scan.nextLine();

                                        currentSim.upgradeHouse(currentSim.getCurrentRoom().getLocationInHouse(), input,
                                                name);
                                    } else {
                                        currentSim.getCurrentRoom().printUpgradeable();
                                        System.out.println("Please choose the position for the new room!");
                                        scan.nextLine();
                                        input = scan.nextLine();
                                        while (!(input.equals("BOTTOM")) && !(input.equals("TOP"))
                                                && !(input.equals("RIGHT")) && !(input.equals("LEFT"))) {
                                            System.out.println("That is not a valid direction!");
                                            input = scan.nextLine();
                                        }

                                        System.out.println("Please choose a name for the new room!");
                                        String name = scan.nextLine();
                                        while (currentSim.getCurrentHouse().checkName(name) == true) {
                                            System.out.println("The house already has a room with that name!");
                                            System.out.println("Please choose another name!");
                                            name = scan.nextLine();
                                        }
                                        currentSim.upgradeHouse(currentSim.getCurrentRoom().getLocationInHouse(), input,
                                                name);
                                    }
                                    break;

                                case 6:
                                    // move room
                                    System.out.println("Where do you want to go?");
                                    currentSim.getHouse().printRoomList();
                                    System.out.print("\n>> ");
                                    int roomNumber = scan.nextInt();
                                    if (roomNumber <= currentSim.getHouse().getNumberofRoom()) {
                                        currentSim.moveToRoom(currentSim.getHouse(),
                                                currentSim.getHouse().getRoomList().get(currentSim.getHouse()
                                                        .getRoomList().keySet().toArray()[roomNumber - 1]));
                                        System.out.println("You have successfully move to "
                                                + currentSim.getCurrentRoom().getName());
                                    } else {
                                        System.out.println("There is no room in the house with that room number");
                                    }
                                    break;

                                case 7:
                                    // TODO:edit room
                                    System.out.println("What do you want to do?");
                                    System.out.println("1. MOVE ITEM");
                                    System.out.println("2. BUY ITEM");
                                    input = scan.nextLine();
                                    if (input.equals("BUY ITEM")) {
                                        int i;
                                        System.out.println("What furniture would you like to buy?");
                                        for (i = 0; i < furnitureMenu.length; i++) {
                                            System.out.println(i + 1 + ". " + furnitureMenu[i]);
                                        }
                                        System.out.print("\n>> ");
                                        playMenuChoice = scan.nextInt();
                                        while (playMenuChoice < 1 || playMenuChoice > furnitureMenu.length) {
                                            System.out.println("Your input is out of range!");
                                            System.out.println("What ingredient do you want to buy?");
                                            for (i = 0; i < furnitureMenu.length; i++) {
                                                System.out.println(i + 1 + ". " + furnitureMenu[i]);
                                            }
                                            playMenuChoice = scan.nextInt();
                                        }
                                        System.out.println("How many would you like to buy?");
                                        System.out.print("\n>> ");
                                        int amount = scan.nextInt();
                                        currentSim.buy(new Ingredient(furnitureMenu[playMenuChoice - 1]), amount);

                                    } else if (input.equals("MOVE ITEM")) {
                                        currentSim.getCurrentRoom().printRoom();
                                        System.out.println("Please chose the point you want to move");
                                        System.out.println("X: ");
                                        int x = scan.nextInt();
                                        System.out.println("Y: ");
                                        int y = scan.nextInt();
                                        if (currentSim.getCurrentRoom().checkPoint(new Point(x, y)) == null) {
                                            System.out.println("There is no object there!");
                                        } else {
                                            Furniture movedFurniture = currentSim.getCurrentRoom()
                                                    .checkPoint(new Point(x, y));
                                            currentSim.getCurrentRoom().removeFurniture(new Point(x, y));
                                            System.out.println("Please chose the new point");
                                            System.out.println("X: ");
                                            int x2 = scan.nextInt();
                                            System.out.println("Y: ");
                                            int y2 = scan.nextInt();
                                            System.out.println("Please chose the rotation 1/2/3/4!");
                                            int rotation = scan.nextInt();
                                            do {
                                                try {
                                                    currentSim.getCurrentRoom().placeFurniture(new Point(x2, y2),
                                                            rotation, movedFurniture);
                                                    break;
                                                } catch (OverlapingRoomObjectException e) {
                                                    System.out.println(e.getMessage());
                                                }
                                                System.out.println("Please chose the new point");
                                                System.out.println("X: ");
                                                x2 = scan.nextInt();
                                                System.out.println("Y: ");
                                                y2 = scan.nextInt();
                                                System.out.println("Please chose the rotation 1/2/3/4!");
                                                rotation = scan.nextInt();
                                            } while (true);
                                        }
                                    } else {
                                        System.out.println("That is a not a valid input!");
                                    }
                                    break;

                                case 8:
                                    // TODO: add sim
                                    simplicity.newSim();
                                    break;

                                case 9:
                                    // TODO: change sim
                                    System.out.println("Here is the list of Sim that has been generated");
                                    for (int i = 0; i < simList.size(); i++) {
                                        System.out.println(i + 1 + ". " + simList.get(i).getName());
                                    }
                                    System.out.println("Which Sim do you want to play with?");
                                    choice = scan.nextInt();
                                    currentSim = simList.get(choice-1);
                                    break;

                                case 10:
                                    // TODO:list object
                                    currentSim.getCurrentRoom().printFurnitureList();
                                    break;

                                case 11:
                                    // go to object
                                    if (currentSim.getCurrentRoom().getfurnitureList().size() != 0) {
                                        System.out.println(currentSim.getCurrentRoom().getfurnitureList().toArray());
                                        System.out.println("Which object do you want to go to?");
                                        System.out.print("\n>> ");
                                        int idx = scan.nextInt();
                                        Furniture furniture = currentSim.getCurrentRoom().getfurnitureList().get(idx);
                                        int idx2 = 1;
                                        if (currentSim.getCurrentRoom().getFurnitureCount(furniture) > 1) {
                                            System.out.println("There is"
                                                    + currentSim.getCurrentRoom().getFurnitureCount(furniture) + " "
                                                    + furniture.getName());
                                            System.out.println("Please choose which one would you like to go to.");
                                            System.out.print("\n>>");
                                            idx2 = scan.nextInt();
                                            while (idx2 < 1 || idx2 > currentSim.getCurrentRoom()
                                                    .getFurnitureCount(furniture)) {
                                                System.out.println(
                                                        "Thats not a valid input!\nPlease choose which one would you like to go to. ");
                                                System.out.print("\n>>");
                                                idx2 = scan.nextInt();
                                            }
                                        }
                                        currentSim.moveToObject(currentSim.getCurrentRoom().getfurnitureList().get(idx),
                                                idx2);
                                        System.out.println("You have successfully move to desired object in"
                                                + currentSim.getCurrentPosition());
                                    } else {
                                        System.out.println(
                                                "There is no object in current room. Please move to other room or place some items.");
                                    }
                                    break;

                                case 12:
                                    // help
                                    int help = 1;
                                    Help.printMessage(help);
                                    System.out.println("BACK/NEXT/EXIT");
                                    input = scan.nextLine();
                                    while (!(input.equals("EXIT"))) {

                                        if (input.equals("BACK")) {
                                            if (help == 1) {
                                                System.out.println("You are already at the first page!");
                                            } else {
                                                help--;
                                            }
                                        } else if (input.equals("NEXT")) {
                                            if (help == 3) {
                                                System.out.println("You are already at the final page!");
                                            } else {
                                                help++;
                                            }
                                        } else {
                                            System.out.println("That is not a valid input!");
                                        }
                                        Help.printMessage(help);
                                        System.out.println("BACK/NEXT/EXIT");
                                        System.out.print("\n>>");
                                        input = scan.nextLine();
                                    }
                                    break;

                                case 13:
                                    // quit game
                                    System.out.println("Thank you for playing!!");
                                    System.exit(0);
                                    break;

                                default:
                                    System.out.println("Wrong command, please input the right number.");
                                    break;
                            }
                        }
                        break;
                    case 2:
                        // TODO:
                        int help = 1;
                        Help.printMessage(help);
                        System.out.println("BACK/NEXT/EXIT");
                        input = scan.nextLine();
                        while (!(input.equals("EXIT"))) {

                            if (input.equals("BACK")) {
                                if (help == 1) {
                                    System.out.println("You are already at the first page!");
                                } else {
                                    help--;
                                }
                            } else if (input.equals("NEXT")) {
                                if (help == 3) {
                                    System.out.println("You are already at the final page!");
                                } else {
                                    help++;
                                }
                            } else {
                                System.out.println("That is not a valid input!");
                            }
                            Help.printMessage(help);
                            System.out.println("BACK/NEXT/EXIT");
                            System.out.print("\n>>");
                            input = scan.nextLine();
                        }
                        break;
                    case 3:
                        System.out.println("Thank you for playing!!");
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Please enter the number in range that given");
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Wrong input, please input integer number");
            }
        }

        scan.close();
    }

    public void newSim() {
        String simName;
        Scanner scan = new Scanner(System.in);

        System.out.println("Masukkan nama lengkap untuk sim kamu");
        System.out.print(">> ");
        simName = scan.nextLine();
        Sim sim = new Sim(simName, new Point(x, y));
        simList.add(sim);
        currentSim = sim;

        // for updating house location everytime new sims generated
        if (x == 64) {
            x = 0;
            y += 1;
        } else {
            x++;
        }
    }

    public void printMainMenu() {
        System.out
                .println("\nWelcome to Main Menu, here some command that you can choose. Please enter the menu number");
        for (int i = 0; i < mainMenu.length; i++) {
            System.out.println(i + 1 + ". " + mainMenu[i]);
        }
    }

    public void printActionMenu() {
        System.out.println(
                "\nWelcome to Action Menu, here some action that you can choose. Please enter the menu number");
        for (int i = 0; i < actionMenu.length; i++) {
            System.out.println(i + 1 + ". " + actionMenu[i]);
        }
    }

    public void printFurniturePrice() {
        int num = 1;
        System.out.println("Furniture Shop");
        String header = String.format("| %-4s | %-20s | %-10s |", "No", "Furniture", "Price");
        String line = "-".repeat(header.length());

        System.out.println(line);
        System.out.println(header);
        System.out.println(line);

        for (String furniturex : furnitureMenu) {
            int price = new Furniture(furniturex).getPrice();
            String row = String.format("| %-4s | %-20s | %-10d |", num,furniturex, price);
            System.out.println(row);
            num++;
        }
        System.out.println(line);
    }

    public void printIngredientPrice() {
        int num = 1;
        System.out.println("Ingredient Shop");
        String header = String.format("| %-4s | %-20s | %-10s |", "No", "Ingredient", "Price");
        String line = "-".repeat(header.length());

        System.out.println(line);
        System.out.println(header);
        System.out.println(line);

        for (String ingredientx : ingredientMenu) {
            int price = new Ingredient(ingredientx).getPrice();
            String row = String.format("| %-4s | %-20s | %-10d |", num,ingredientx, price);
            System.out.println(row);
            num++;
        }
        System.out.println(line);
    }
}
