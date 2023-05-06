package com.simplicity;

import com.simplicity.Exceptions.InAppendableSimWorld;
import com.simplicity.Exceptions.InvalidDurationException;
import com.simplicity.Exceptions.MissingFoodTypeException;
import com.simplicity.Exceptions.OverlapingHouseException;

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
import com.simplicity.Foods.*;
import com.simplicity.Furniture.*;
import com.simplicity.Interfaces.Edible;

import java.util.*;

public class Simplicity {
    // private static Point lastHouse = new Point(0,0);

    // private static int x = 0;
    // private static int y = 0;
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

    private static Simplicity simplicity = new Simplicity();
    private static World world = World.getInstance();
    private static Scanner scan = new Scanner(System.in);
    private static int duration;
    private static int mainMenuChoice, playMenuChoice, actionMenuChoice, choice;
    private static String input;

    private static boolean exit = false;
    private static boolean play = false;
    private static boolean doneInput;

    private static int promptInt(String message) {
        int ret;
        String input;
        String currentMessage;

        if (message == null) {
            currentMessage = "";
        } else {
            currentMessage = message + "\n";
        }

        System.out.println(currentMessage);
        System.out.print("\n>> ");

        try {
            input = scan.nextLine();
            ret = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            System.out.println("Please input a number!");
            System.out.println("");
            System.out.print(currentMessage);
            System.out.print("\n>> ");
            ret = promptInt(message);
        }

        return ret;
    }

    private static int promptInt() {
        return promptInt(null);
    }

    // private GameMenu menu = new GameMenu();

    public static void main(String[] args) {
        while (!exit) {
            System.out.println("\nMain Menu");
            System.out.println("1. START GAME");
            System.out.println("2. HELP");
            System.out.println("3. EXIT\n");
            mainMenuChoice = promptInt();

            try {
                switch (mainMenuChoice) {
                    case 1:
                        play = true;
                        System.out.println("Enter your sim name");
                        System.out.print("\n>> ");
                        input = scan.nextLine();
                        Random random = new Random();
                        simplicity.newSim(input, new Point(random.nextInt(64), random.nextInt(64)));
                        try {
                            world.setSim(currentSim.getHouse().getLocation(), currentSim);
                        } catch (OverlapingHouseException e) {
                            e.printStackTrace();
                        } catch (InAppendableSimWorld e) {
                            System.out.println(e.getMessage());
                        }

                        System.out.println("\nHalo " + currentSim.getName());

                        while (play) {
                            // TODO: update dead
                            if (currentSim.getStatus().equals("Die")) {
                                if (simList.size() > 1) {
                                    simList.remove(currentSim);
                                    System.out.println("Sorry, your current sim has died");
                                    System.out.println("Do you want to play with other sim?");
                                    System.out.println("1. Yes");
                                    System.out.println("2. No");
                                    choice = promptInt();
                                    switch (choice) {
                                        case 1:
                                            simplicity.printSimsList();
                                            choice = promptInt("Which Sim do you want to play with?");
                                            currentSim = simList.get(choice - 1);
                                            break;
                                        case 2:
                                            System.exit(0);
                                            break;
                                    }
                                } else {
                                    System.out.println("You don't have any sim left. Game Over...");
                                    System.exit(0);
                                }
                            }

                            simplicity.printMainMenu();
                            playMenuChoice = promptInt();

                            switch (playMenuChoice) {
                                case 1:
                                    simplicity.printActionMenu();
                                    actionMenuChoice = promptInt();

                                    switch (actionMenuChoice) {
                                        case 1:
                                            doneInput = false;
                                            // work
                                            while (!doneInput) {
                                                duration = promptInt("Enter work duration");
                                                try {
                                                    currentSim.work(duration);
                                                    doneInput = true;
                                                } catch (InvalidDurationException e) {
                                                    System.out.println(e.getMessage());
                                                }
                                            }
                                            break;

                                        case 2:
                                            // workout
                                            duration = promptInt("Enter workout duration");
                                            currentSim.workout(duration);
                                            break;

                                        case 3:
                                            // sleep
                                            duration = promptInt("Enter sleep duration");
                                            currentSim.sleep(duration, currentSim);
                                            break;

                                        case 4:
                                            // TODO: eat
                                            currentSim.printFoodInventory();
                                            System.out.println("What do you want to eat?");
                                            System.out.println("1. Cooked Food");
                                            System.out.println("2. Ingredients");
                                            choice = promptInt();
                                            switch (choice) {
                                                case 1:
                                                    if (currentSim.getCookedFoodInventory().getInventory()
                                                            .size() == 0) {
                                                        System.out.println(
                                                                "You don't have any cooked food. Please cook some first before you eat");
                                                    } else {
                                                        doneInput = false;
                                                        while (!doneInput) {
                                                            choice = promptInt("Which cooked food do you want to eat?");
                                                            String food = currentSim.getCookedFoodInventory()
                                                                    .getInventory()
                                                                    .keySet().toArray()[choice - 1].toString();
                                                            try {
                                                                currentSim.eat(
                                                                        FoodFactory.getInstance().createFood(food));
                                                            } catch (MissingFoodTypeException e) {
                                                                System.out.println(e.getMessage());
                                                            }
                                                        }
                                                    }
                                                    break;
                                                case 2:
                                                    if (currentSim.getCookedFoodInventory().getInventory()
                                                            .size() == 0) {
                                                        System.out.println(
                                                                "You don't have any cooked food. Please cook some first before you eat");
                                                    } else {
                                                        System.out.println();
                                                        choice = promptInt("Which cooked food do you want to eat?");
                                                        String food = currentSim.getCookedFoodInventory().getInventory()
                                                                .keySet().toArray()[choice - 1].toString();
                                                        // currentSim.eat(foodFactory.createFood(food));
                                                    }
                                                    break;
                                            }
                                            // currentSim.eat(foodFactory.createFood(choice));

                                            break;

                                        case 5:
                                            // TODO: cook
                                            FoodFactory.getInstance().printCookableMenu();
                                            int cookedFoodChoice = promptInt("What do you want to cook?");
                                            currentSim.cook(FoodFactory.getInstance().getCookableMenu().keySet()
                                                    .toArray()[cookedFoodChoice - 1].toString(), currentSim);
                                            break;

                                        case 6:
                                            // TODO: visit
                                            System.out.println(
                                                    "You are now in " + currentSim.getHouse().getLocation() + "house");
                                            world.printHouseList();
                                            choice = promptInt("Which house do you want to visit?");
                                            currentSim.visit(currentSim.getCurrentHouse(),
                                                    (House) (world.getMap().keySet().toArray()[choice - 1]));
                                            break;

                                        case 7:
                                            // defecate
                                            duration = promptInt("Enter defecate duration");
                                            currentSim.defecate();
                                            break;

                                        case 8:
                                            // nubes;
                                            System.out.print("Enter nubes duration");
                                            duration = promptInt();
                                            currentSim.nubes(duration);
                                            break;

                                        case 9:
                                            // play game
                                            duration = promptInt("Enter play game duration");
                                            currentSim.playGame(duration);
                                            break;

                                        case 10:
                                            // listens to music
                                            duration = promptInt("Enter listens to music duration");
                                            currentSim.listenMusic(duration);
                                            break;

                                        case 11:
                                            // watch tv
                                            duration = promptInt("Enter watch tv duration");
                                            currentSim.watchTV(duration);
                                            break;

                                        case 12:
                                            // bath
                                            duration = promptInt("Enter bath duration");
                                            currentSim.bath(duration);
                                            break;

                                        case 13:
                                            // meetup
                                            if (world.getMap().size() >= 2) {
                                                System.out.println("Here are the list of sims that are alive");
                                                world.printSimsList();
                                                choice = promptInt("Which sims do you want to meet up with?");
                                                duration = promptInt("Enter meet up duration");
                                                currentSim.meetup(duration, currentSim,
                                                        ((Sim) world.getMap().values().toArray()[choice - 1]));
                                            }
                                            break;

                                        case 14:
                                            // call
                                            duration = promptInt("Enter call duration");
                                            currentSim.call(duration);
                                            break;

                                        case 15:
                                            // TODO: buy
                                            System.out.println("What kind of item do you want to buy?");
                                            System.out.println("1. INGREDIENT");
                                            System.out.println("2. FURNITURE");
                                            playMenuChoice = promptInt();
                                            // int i;
                                            if (playMenuChoice == 1) {
                                                System.out.println("What ingredient do you want to buy?");
                                                simplicity.printIngredientPrice();
                                                playMenuChoice = promptInt();
                                                while (playMenuChoice < 1 || playMenuChoice > ingredientMenu.length) {
                                                    System.out.println("Your input is out of range!");
                                                    System.out.println("What ingredient do you want to buy?");
                                                    simplicity.printIngredientPrice();
                                                    playMenuChoice = promptInt();
                                                }
                                                int amount = promptInt("How many would you like to buy?");
                                                try {
                                                    currentSim.buy(
                                                            (Ingredient) FoodFactory.getInstance()
                                                                    .createFood(ingredientMenu[playMenuChoice - 1]),
                                                            amount);
                                                } catch (MissingFoodTypeException e) {
                                                    e.printStackTrace();
                                                }
                                            } else if (playMenuChoice == 2) {
                                                System.out.println("What furniture would you like to buy?");
                                                simplicity.printFurniturePrice();
                                                playMenuChoice = promptInt();
                                                while (playMenuChoice < 1 || playMenuChoice > furnitureMenu.length) {
                                                    System.out.println("Your input is out of range!");
                                                    System.out.println("What furniture do you want to buy?");
                                                    simplicity.printFurniturePrice();
                                                    playMenuChoice = promptInt();
                                                }
                                                int amount = promptInt("How many would you like to buy?");
                                                currentSim.buy(new Furniture(furnitureMenu[playMenuChoice - 1]),
                                                        amount);
                                            } else {
                                                System.out.println("That is not a valid input!");
                                            }
                                            break;

                                        case 16:
                                            // place item
                                            currentSim.getHouse().printRoomList();
                                            currentSim.printFurnitureInventory();
                                            int idx = promptInt("What do you want to place?");
                                            currentSim.getCurrentRoom().printRoom();
                                            int rotation = promptInt("Please select the rotation (0/1/2/3): ");
                                            while (rotation < 0 || rotation > 3) {
                                                System.out.println("Thats not a valid rotation!");
                                                rotation = promptInt();
                                            }
                                            System.out.println("Please select the point you want to place the item");
                                            int x = promptInt("X");
                                            int y = promptInt("Y");
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
                                    int roomNumber = promptInt();
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
                                    System.out.println("1. BUY ITEM");
                                    System.out.println("2. MOVE ITEM");
                                    choice = promptInt();

                                    switch (choice) {
                                        case 1:
                                            int i;
                                            System.out.println("What furniture would you like to buy?");
                                            for (i = 0; i < furnitureMenu.length; i++) {
                                                System.out.println(i + 1 + ". " + furnitureMenu[i]);
                                            }
                                            playMenuChoice = promptInt();
                                            while (playMenuChoice < 1 || playMenuChoice > furnitureMenu.length) {
                                                System.out.println("Your input is out of range!");
                                                System.out.println("What ingredient do you want to buy?");
                                                for (i = 0; i < furnitureMenu.length; i++) {
                                                    System.out.println(i + 1 + ". " + furnitureMenu[i]);
                                                }
                                                playMenuChoice = promptInt();
                                            }
                                            System.out.println("How many would you like to buy?");
                                            int amount = promptInt();
                                            currentSim.buy(new Furniture(furnitureMenu[playMenuChoice - 1]), amount);
                                            break;
                                        case 2:
                                            // TODO:
                                            currentSim.getCurrentRoom().printRoom();
                                            System.out.println("Please chose the point you want to move");
                                            System.out.println();
                                            int x = promptInt("X");
                                            System.out.println();
                                            int y = promptInt("Y");
                                            if (currentSim.getCurrentRoom().checkPoint(new Point(x, y)) == null) {
                                                System.out.println("There is no object there!");
                                            } else {
                                                Furniture movedFurniture = currentSim.getCurrentRoom()
                                                        .checkPoint(new Point(x, y));
                                                currentSim.getCurrentRoom().removeFurniture(new Point(x, y));
                                                System.out.println("Please chose the new point");
                                                int x2 = promptInt("X");
                                                int y2 = promptInt("Y");
                                                int rotation = promptInt("Please chose the rotation 1/2/3/4!");
                                                do {
                                                    try {
                                                        currentSim.getCurrentRoom().placeFurniture(new Point(x2, y2),
                                                                rotation, movedFurniture);
                                                        break;
                                                    } catch (OverlapingRoomObjectException e) {
                                                        System.out.println(e.getMessage());
                                                    }
                                                    System.out.println("Please chose the new point");
                                                    x2 = promptInt("X");
                                                    y2 = promptInt("Y");
                                                    rotation = promptInt("Please chose the rotation 1/2/3/4!");
                                                } while (true);
                                            }
                                            break;
                                        default:
                                            System.out.println("That is not a valid input");
                                            break;
                                    }
                                    break;

                                case 8:
                                    // TODO: add sim
                                    System.out.println("Enter your sim name");
                                    System.out.print("\n>> ");
                                    input = scan.nextLine();
                                    System.out.println("Where do you want to place your sim house?");
                                    System.out.println("X: ");
                                    int x = promptInt();
                                    System.out.println("Y: ");
                                    int y = promptInt();
                                    simplicity.newSim(input, new Point(x, y));
                                    break;

                                case 9:
                                    // TODO: change sim
                                    simplicity.printSimsList();
                                    choice = promptInt("Which Sim do you want to play with?");
                                    if (choice > simList.size()) {
                                        System.out.println("Input out of range! Please input number in range");
                                    } else {
                                        currentSim = simList.get(choice - 1);
                                    }
                                    break;

                                case 10:
                                    // TODO:list object
                                    if (currentSim.getCurrentRoom().getfurnitureList().size() > 0) {
                                        currentSim.getCurrentRoom().printFurnitureList();
                                    } else {
                                        System.out.println("There is no object in this room.");
                                    }
                                    break;

                                case 11:
                                    // go to object
                                    if (currentSim.getCurrentRoom().getfurnitureList().size() != 0) {
                                        System.out.println(currentSim.getCurrentRoom().getfurnitureList().toArray());
                                        int idx = promptInt("Which object do you want to go to?");
                                        Furniture furniture = currentSim.getCurrentRoom().getfurnitureList().get(idx);
                                        int idx2 = 1;
                                        if (currentSim.getCurrentRoom().getFurnitureCount(furniture) > 1) {
                                            System.out.println("There is"
                                                    + currentSim.getCurrentRoom().getFurnitureCount(furniture) + " "
                                                    + furniture.getName());
                                            idx2 = promptInt("Please choose which one would you like to go to.");
                                            while (idx2 < 1 || idx2 > currentSim.getCurrentRoom()
                                                    .getFurnitureCount(furniture)) {
                                                idx2 = promptInt(
                                                        "Thats not a valid input!\nPlease choose which one would you like to go to. ");
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
                                    System.out.println("Input out of range!");
                                    break;
                            }
                        }
                        // if (currentSim.getStatus().equals("Die")) {
                        //     if (simList.size() > 1) {
                        //         simList.remove(currentSim);
                        //         System.out.println("Sorry, your current sim has died");
                        //         System.out.println("Do you want to play with other sim?");
                        //         System.out.println("1. Yes");
                        //         System.out.println("2. No");
                        //         choice = promptInt();
                        //         switch (choice) {
                        //             case 1:
                        //                 simplicity.printSimsList();
                        //                 choice = promptInt("Which Sim do you want to play with?");
                        //                 currentSim = simList.get(choice - 1);
                        //                 break;
                        //             case 2:
                        //                 System.exit(0);
                        //                 break;
                        //         }
                        //     } else {
                        //         System.out.println("You don't have any sim left. Game Over...");
                        //         System.exit(0);
                        //     }
                        // }
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
                System.out.println("Wrong input, please input a number");
            }
        }

        scan.close();
    }

    public void newSim(String name, Point house) {
        Sim sim = new Sim(name, house);
        try {
            World.getInstance().setSim(house, sim);
        } catch (OverlapingHouseException e) {
            e.printStackTrace();
        } catch (InAppendableSimWorld e) {
            e.printStackTrace();
        }
        simList.add(sim);
        currentSim = sim;
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
            String row = String.format("| %-4s | %-20s | %-10d |", num, furniturex, price);
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
            int price;
            try {
                price = ((Ingredient) FoodFactory.getInstance().createFood(ingredientx)).getPrice();
            } catch (MissingFoodTypeException e) {
                price = 0;
                e.printStackTrace();
            }
            String row = String.format("| %-4s | %-20s | %-10d |", num, ingredientx, price);
            System.out.println(row);
            num++;
        }
        System.out.println(line);
    }

    public void printSimsList() {
        System.out.println("Here is the list of Sim that has been generated");
        for (int i = 0; i < simList.size(); i++) {
            System.out.println(i + 1 + ". " + simList.get(i).getName());
        }
    }
}
