package com.simplicity;

import com.simplicity.*;
import com.simplicity.Furniture.SingleBed;

import java.util.*;

public class Simplicity {
    private static Point lastHouse = new Point(0,0);
    private static Sim currentSim;
    // private GameMenu menu = new GameMenu();

    private void playGame(){
        String simName;
        Scanner scan = new Scanner(System.in);
    
        System.out.println("Masukkan nama lengkap untuk sim kamu");
        System.out.print(">> ");
        simName = scan.nextLine();
        Sim sim = new Sim(simName, lastHouse);
        currentSim = sim;
        
        if(lastHouse.getX() == 64) {
            lastHouse.setPoint(0, lastHouse.getY()+1);
        }
        lastHouse.setPoint(lastHouse.getX()+1, lastHouse.getY());
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
                                System.out.println("Enter defecate duration: ");
                                duration = scan.nextInt();
                                currentSim.defecate(duration);
                            }
     
                            else if(input.equals("BUY")) {
                                // list purchaseable item
                                // currentSim.buy(null, duration);
                            }
                            
                            else if(input.equals("PLACE ITEM")) {
                                currentSim.getHouse().printRoomList();
                                currentSim.getCurrentRoom().printRoom();
                                System.out.println("What do you want to place?");
                                currentSim.viewInventory(); 
                            
                                currentSim.setUpObject(new Point(1,1), 0, new SingleBed());
                                currentSim.getCurrentRoom().printRoom();
                            }
                            
                            else if(input.equals("VIEW TIME")) {
                                currentSim.viewTime();
                            }
                            
                        }
                        
                        else if(input.equals("SIM INFO")){
                            GameMenu.showSimInfo(currentSim);
                        }

                        else if(input.equals("CURRENT LOCATION")){
                            System.out.println("You are now in " + currentSim.getHouse().getLocation() + " house");
                            System.out.println("You are now in " + currentSim.getCurrentRoom().getName() + " which located in " + currentSim.getCurrentRoom().getLocationInHouse());
                            System.out.println("And you are now in " + currentSim.getCurrentPosition());
                        }

                        else if(input.equals("INVENTORY")) {
                            currentSim.viewInventory();
                        }
                        
                        else if(input.equals("MOVE TO ROOM")) {
                            System.out.println("Where do you want to go?");
                            currentSim.getHouse().printRoomList();
                            currentSim.moveToRoom(currentSim.getHouse(), currentSim.getHouse().getRoomList().get(1));
                        } 
                        
                        else if(input.equals("UPGRADE HOUSE")) {
                            currentSim.upgradeHouse();
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
