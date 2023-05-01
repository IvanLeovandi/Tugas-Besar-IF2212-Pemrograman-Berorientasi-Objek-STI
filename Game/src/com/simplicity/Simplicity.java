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
//     }
// }

// cli version
import com.simplicity.Exceptions.OverlapingRoomObjectException;

import java.util.*;

public class Simplicity {
    // private static Point lastHouse = new Point(0,0);
    private static int x = 0;
    private static int y = 0;
    private static Sim currentSim;
    private static ArrayList<Sim> simList = new ArrayList<Sim>();
    private static String[] mainMenu = {"ACTION", "SIM INFO", "CURRENT LOCATION", "VIEW INVENTORY", "UPGRADE HOUSE", "MOVE ROOM", "EDIT ROOM", "ADD SIM", "CHANGE SIM", "LIST OBJECT", "GO TO OBJECT", "HELP", "QUIT GAME"};
    private static String[] actionMenu = {"WORK", "WORKOUT", "SLEEP", "EAT","COOK", "VISIT", "DEFECATE", "NUBES", "SAY HELLO", "LISTENS TO MUSIC", "WATCH TV", "BATH", "MEET UP", "MISS YOU", "BUY", "PLACE ITEM", "VIEW TIME", "HELP"};
    // private GameMenu menu = new GameMenu();

    private void playGame(){
        String simName;
        Scanner scan = new Scanner(System.in);
    
        System.out.println("Masukkan nama lengkap untuk sim kamu");
        System.out.print(">> ");
        simName = scan.nextLine();
        Sim sim = new Sim(simName, new Point(x, y));
        simList.add(sim);
        currentSim = sim;
        
        // for updating house location everytime new sims generated
        if(x == 64) {
            x = 0;
            y += 1;
        } else {
            x++;
        }
    }
    public static void main(String[] args) {
        Simplicity simplicity = new Simplicity();
        Scanner scan = new Scanner(System.in);
        int duration;
        int mainMenuChoice, playMenuChoice, actionMenuChoice;
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
                        World world = new World(64,64);
    
                        simplicity.playGame();
                        System.out.println("\nHalo " + currentSim.getName());
    
                        while (play) {
                            simplicity.printMainMenu();
                            System.out.print(">> ");
                            // System.out.println(World.gameTimer.getSecond());
                            playMenuChoice = scan.nextInt();

                            switch (playMenuChoice)
                            {    
                                case 1 :
                                    simplicity.printActionMenu();
                                    System.out.print(">> ");
                                    actionMenuChoice = scan.nextInt();

                                    switch (actionMenuChoice)
                                    {
                                        case 1:
                                            // work
                                            System.out.println("Enter work duration: ");    
                                            duration = scan.nextInt();  
                                            currentSim.work(duration);
                                            break;

                                        case 2:
                                            //workout
                                            System.out.println("Enter workout duration: ");    
                                            duration = scan.nextInt();  
                                            currentSim.workout(duration);
                                            break;
                
                                        case 3:
                                            //sleep
                                            System.out.println("Enter sleep duration: ");
                                            duration = scan.nextInt();
                                            currentSim.sleep(duration, currentSim);
                                            break;
                                        
                                        case 4:
                                            // TODO: eat
                                            // System.out.println("What do you want to eat?");
                                            // currentSim.viewInventory();

                                            break;
                
                                        case 5:
                                            // TODO: cook
                                            break;
                
                                        case 6:
                                            // visit
                                            System.out.println("You are now in " + currentSim.getHouse().getLocation() + "house");
                                            System.out.println("Which house do you want to visit?");
                                            break;
                
                                        case 7:
                                            // defecate
                                            System.out.println("Enter defecate duration: ");
                                            duration = scan.nextInt();
                                            currentSim.defecate(duration);
                                            break;
        
                                        case 8:
                                            // nubes;
                                            currentSim.nubes();
                                            break;

                                        case 9:
                                            // say hello
                                            currentSim.sayHello();
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
                                            System.out.println("Enter meet up duration: ");
                                            System.out.print("\n>> ");
                                            duration = scan.nextInt();
                                            currentSim.meetup(duration);
                                            break;

                                        case 14:
                                            // miss you
                                            System.out.println("Enter miss you duration: ");
                                            System.out.print("\n>> ");
                                            duration = scan.nextInt();
                                            currentSim.missyou(duration);
                                            break;

                 
                                        case 15:
                                            // TODO: buy
                                            // list purchaseable item
                                            // currentSim.buy(null, duration);
                                            break;
                                        
                                        case 16:
                                            // place item
                                            currentSim.getHouse().printRoomList();
                                            currentSim.getCurrentRoom().printRoom();
                                            System.out.println("What do you want to place?");
                                            currentSim.viewInventory(); 
                                            int idx = scan.nextInt();
                                            try {
                                                currentSim.setUpObject(new Point(1,1), 0, idx-1);
                                            } catch (OverlapingRoomObjectException e) {
                                                e.printStackTrace();
                                            }
                                            currentSim.getCurrentRoom().printRoom();
                                            break;
        
                                        case 17:
                                            // view time
                                            currentSim.viewTime(currentSim);
                                            break;
        
                                        case 18:
                                            //help
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
                                    System.out.println("You are now in x: " + currentSim.getHouse().getLocation().getX() + " and y: " + currentSim.getHouse().getLocation().getY() + " house");
                                    System.out.println("You are now in " + currentSim.getCurrentRoom().getName() + " which located in x: " + currentSim.getCurrentRoom().getLocationInHouse().getX() + " and y: " + currentSim.getCurrentRoom().getLocationInHouse().getY());
                                    System.out.println("And you are now in x: " + currentSim.getCurrentPosition().getX() + " and y: " + currentSim.getCurrentPosition().getY());
                                    break;
        
                                case 4:
                                    // view inventory
                                    currentSim.viewInventory();
                                    break;
                                
                                case 5:
                                    // upgrade house
                                    if(currentSim.getHouse().getNumberofRoom() == 1){
                                        System.out.println("You have to choose the direction of adding the room");	
                                        System.out.println("You can select top/bottom/left/right");
                                        input = scan.nextLine();
            
                                        System.out.println("Please create the name of the room");	
                                        String name = scan.nextLine();
            
                                        currentSim.upgradeHouse(currentSim.getCurrentRoom().getLocationInHouse() , input, name);
                                    }
                                    break;
                                
                                case 6:
                                    // move room
                                    System.out.println("Where do you want to go?");
                                    currentSim.getHouse().printRoomList();
                                    System.out.print("\n>> ");
                                    int roomNumber = scan.nextInt();
                                    if(roomNumber <= currentSim.getHouse().getNumberofRoom()){
                                        currentSim.moveToRoom(currentSim.getHouse(), currentSim.getHouse().getRoomList().get(currentSim.getHouse().getRoomList().keySet().toArray()[roomNumber-1]));
                                        System.out.println("You have successfully move to " + currentSim.getCurrentRoom().getName());
                                    } else{
                                        System.out.println("Wrong number of room");
                                    }
                                    break;
        
                                case 7:
                                    // TODO:edit room
                                    break;
        
                                case 8:
                                    // TODO: add sim
                                    // simplicity.playgame();
                                    break;
        
                                case 9:
                                    // TODO: change sim
                                    break;
        
                                case 10:
                                    // TODO:list object
                                    System.out.println(currentSim.getCurrentRoom().getfurnitureList());
                                    break;
        
                                case 11:
                                    // go to object
                                    if(currentSim.getCurrentRoom().getfurnitureList().size()!=0){
                                        System.out.println(currentSim.getCurrentRoom().getfurnitureList());
                                        System.out.println("Which object do you want to go to?");
                                        System.out.print("\n>> ");
                                        int idx = scan.nextInt();
                                        currentSim.moveToObject(currentSim.getCurrentRoom().getfurnitureList().get(idx), 1);
                                        System.out.println("You have successfully move to desired object in" + currentSim.getCurrentPosition());
                                    } else{
                                        System.out.println("There is no object in current room. Please move to other room or place some items.");
                                    }
                                    break;
                                
                                case 12:
                                    // help
                                    System.out.println("ini help tar diganti method/class");
                                    break;
                                
                                case 13:
                                    // quit game
                                    System.out.println("Thank you for playing!!");
                                    System.exit(0);
                                    break;
        
                                default:
                                    System.out.println("Wrong command, please input the right command.");
                                    break;
                            }
                        }
                        break;
                    case 2:
                            System.out.println("ini help tar diganti method/class");
                        break;
                    case 3:
                        System.out.println("Terima kasih telah bermain!!");
                        System.exit(0);
                        break;
                    default:   
                        System.out.println("Tolong masukan pilihan yang sesuai.");
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Input salah, masukkan input berupa string.");
            }
        }

        scan.close();
    }

    public void printMainMenu(){
        System.out.println("\nWelcome to Main Menu, here some command that you can choose");
        for(int i = 0; i < mainMenu.length; i++){
            System.out.println(i+1 + ". " + mainMenu[i]);
        }
    }

    public void printActionMenu(){
        System.out.println("\nWelcome to Action Menu, here some action that you can choose");
        for(int i = 0; i < actionMenu.length; i++){
            System.out.println(i+1 + ". " + actionMenu[i]);
        }
    }
}
