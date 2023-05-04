package com.simplicity;

// gui version
import java.util.Random;

import com.simplicity.UI.MainMenu;
import com.simplicity.UI.SimplicityFrame;

// public class Simplicity {
//     public static void main(String[] args) {
//         SimplicityManager manager = new SimplicityManager(new World(64, 64));
//         manager.setMainMenuPanel(new MainMenu(manager));
//         manager.setFrame(new SimplicityFrame(manager.getMainMenuPanel()));
//         manager.startWindow();
        

// cli version
import com.simplicity.*;
import com.simplicity.Exceptions.OverlapingRoomObjectException;
import com.simplicity.Furniture.SingleBed;

import java.util.*;

public class Simplicity {
    // private static Point lastHouse = new Point(0,0);
    private static int x = 0;
    private static int y = 0;
    private static Sim currentSim;
    private static ArrayList<Sim> simList = new ArrayList<Sim>();
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
        String input;
        int duration;
        // int choice;

        boolean exit = false;
        boolean play = false;

        while (!exit) {
            System.out.println("Main Menu");
            System.out.println("1. START GAME");
            System.out.println("2. HELP");
            System.out.println("3. EXIT");

            System.out.print(">> ");

            try {
                input = scan.nextLine().toUpperCase();

                if(input.equals("START GAME")) {
                    play = true;
                    World world = new World(64,64);

                    simplicity.playGame();

                    while (play) {
                        System.out.println("Halo " + currentSim.getName());
                        System.out.println("What do you want to do?");
                        // System.out.println(World.gameTimer.getSecond());
                        input = scan.nextLine().toUpperCase();

                        if(input.equals("ACTION")){
                            System.out.println("What action do you want to do?");
                            input = scan.nextLine().toUpperCase();
                            if(input.equals("WORK")) {
                                System.out.println("Enter work duration: ");    
                                duration = scan.nextInt();  
                                currentSim.work(duration);
                            }
                            else if(input.equals("WORKOUT")){
                                System.out.println("Enter workout duration: ");    
                                duration = scan.nextInt();  
                                currentSim.workout(duration);
                            }
    
                            else if(input.equals("SLEEP")) {
                                System.out.println("Enter sleep duration: ");
                                duration = scan.nextInt();
                                currentSim.sleep(duration);
                            }
                            
                            else if(input.equals("EAT")) {
                                // System.out.println("Enter sleep duration: ");
                                // duration = scan.nextInt();
                                // currentSim.sleep(duration);
                            }
    
                            else if(input.equals("COOK")) {
    
                            }
    
                            else if(input.equals("VISIT")) {
                                
                            }
    
                            else if(input.equals("DEFECATE")) {
                                // System.out.println("Enter defecate duration: ");
                                // duration = scan.nextInt();
                                currentSim.defecate();
                            }
     
                            else if(input.equals("BUY")) {
                                // list purchaseable item
                                // currentSim.buy(null, duration);
                            }
                            
                            else if(input.equals("PLACE ITEM")) {
                                currentSim.getHouse().printRoomList();
                                // currentSim.getCurrentRoom().printRoom();
                                System.out.println("What do you want to place?");
                                currentSim.viewInventory(); 
                                input = scan.nextLine();
                                try {
                                    currentSim.setUpObject(new Point(1,1), 0, currentSim.getFurnitureInventory().getFurniture(input));
                                } catch (OverlapingRoomObjectException e) {
                                    // TODO Auto-generated catch block
                                    e.printStackTrace();
                                }
                                // currentSim.getCurrentRoom().printRoom();
                            }
                            
                            else if(input.equals("VIEW TIME")) {
                                currentSim.viewTime();
                            }
                            
                        }
                        
                        else if(input.equals("SIM INFO")){
                            GameMenu.showSimInfo(currentSim);
                        }

                        else if(input.equals("CURRENT LOCATION")){
                            System.out.println("You are now in " + currentSim.getHouse().getHouseOwner().getName() + "'s' house");
                            // System.out.println("You are now in " + currentSim.getCurrentRoom().getName() + " which located in " + currentSim.getCurrentRoom().getLocationInHouse());
                            // System.out.println("And you are now in " + currentSim.getCurrentPosition());
                        }

                        else if(input.equals("INVENTORY")) {
                            currentSim.viewInventory();
                        }
                        
                        else if(input.equals("UPGRADE HOUSE")) {
                            if(currentSim.getHouse().getNumberofRoom() == 1){
                                System.out.println("You have to choose the direction of adding the room");	
                                System.out.println("You can select top/bottom/left/right");
                                input = scan.nextLine();
    
                                System.out.println("Please create the name of the room");	
                                String name = scan.nextLine();
    
                                currentSim.upgradeHouse(currentSim.getCurrentRoom().getLocationInHouse() , input, name);
                            }
                            else {
                                System.out.println("Here are the available positions for the new room: ");
                                if (currentSim.getCurrentRoom().getTop() != null)
                                {
                                    System.out.println("TOP");
                                }
                                if (currentSim.getCurrentRoom().getBottom() != null)
                                {
                                    System.out.println("BOTTOM");
                                }
                                if (currentSim.getCurrentRoom().getRight() != null)
                                {
                                    System.out.println("RIGHT");
                                }
                                if (currentSim.getCurrentRoom().getLeft() != null)
                                {
                                    System.out.println("LEFT");
                                }
                                System.out.println("Please choose the position for the new room!");
                                input = scan.nextLine();
                                while (currentSim.getCurrentHouse().checkUpgradeable(currentSim.getCurrentRoom(), input))
                                {
                                    if (input.equals("BOTTOM") || input.equals("TOP") || input.equals("RIGHT") || input.equals("LEFT"))
                                    {
                                        System.out.println("That is not a valid direction!");
                                    }
                                    else
                                    {
                                        System.out.println("The current room is already upgraded in that direction!\nPlease chose another direction");
                                    }
                                    input = scan.nextLine();
                                }

                                System.out.println("Please choose a name for the new room!");
                                String name = scan.nextLine();
                                while (currentSim.getCurrentHouse().checkName(name) == true)
                                {
                                    System.out.println("The house already has a room with that name!");
                                    System.out.println("Please choose another name!");
                                    name = scan.nextLine();
                                }
                                currentSim.upgradeHouse(currentSim.getCurrentRoom().getLocationInHouse() , input, name);
                            }
                        }
                        
                        else if(input.equals("MOVE TO ROOM")) {
                            System.out.println("Where do you want to go?");
                            currentSim.getHouse().printRoomList();
                            currentSim.moveToRoom(currentSim.getHouse(), currentSim.getHouse().getRoomList().get(1));
                        } 

                        else if(input.equals("EDIT ROOM")){

                        }

                        else if(input.equals("ADD SIM")) {
                            // simplicity.playgame();
                        }

                        else if(input.equals("CHANGE SIM")) {
                            
                        }

                        else if(input.equals("LIST OBJECT")){
                            System.out.println(currentSim.getCurrentRoom().getfurnitureList());
                        }

                        else if(input.equals("GO TO OBJECT")){
                            System.out.println(currentSim.getCurrentRoom().getfurnitureList());
                            System.out.println("Which object do you want to go to?");
                            int idx = scan.nextInt();
                            currentSim.moveToObject(currentSim.getCurrentRoom().getfurnitureList().get(idx), 1);
                        }
                        
                        else if(input.equals("HELP")) {
                            System.out.println("ini help tar diganti method/class");
                        } 
                        
                        else if(input.equals("QUIT GAME")) {
                            System.out.println("Thank you for playing!!");
                            System.exit(0);
                        }

                        else {
                            System.out.println("Wrong command, please input the right command.");
                        }
                    }
                }

                else if(input.equals("HELP")) {
                    System.out.println("ini help tar diganti method/class");
                } 
                
                else if(input.equals("EXIT")) {
                    System.out.println("Terima kasih telah bermain!!");
                    System.exit(0);
                }
                
                else {
                    System.out.println("Tolong masukan pilihan yang sesuai.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Input salah, masukkan input berupa string.");
            }
            
            
            // try {
            //     choice = input.nextInt();

            //     switch (choice) {
            //         case 1:
            //             System.out.println("Mulai game");
            //             // playgame();
            //             break;
            //         case 2:
            //             System.out.println("Help page");
            //             break;
            //         case 3:
            //             System.out.println("Keluar game");
            //             exit = true;
            //             break;
            //         default:
            //             System.out.println("Input salah, masukkan input yang benar!");
            //             break;
            //     }
            // } catch (InputMismatchException e) {
            //     System.out.println("Masukkan bilangan bulat!");
            // }
        }

        scan.close();
    }
}
