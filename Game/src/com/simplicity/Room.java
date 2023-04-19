package com.simplicity;
import java.util.ArrayList;
import java.util.Objects;

public class Room {
    private String roomName;
    private ArrayList<Furniture> objectList;
    private Room left,right,top,bottom;
    private int[][] space;


    public Room(String roomName)
    {   
        this.roomName = roomName;
        left = null;
        right = null;
        top = null;
        bottom = null;
        space = new int[6][6];
    }

    public Room getLeft(Room room)
    {
        return this.left;
    }
    
    public Room getRight(Room room)
    {
        return this.right;
    }

    public Room getTop (Room room)
    {
        return this.top;
    }

    public Room getBottom (Room room)
    {
        return this.bottom;
    }

    public void setLeft(Room room)
    {
        this.left = room;
    }
    
    public void setRight(Room room)
    {
        this.right = room;
    }

    public void setTop (Room room)
    {
        this.top = room;
    }

    public void setBottom (Room room)
    {
        this.bottom = room;
    }

    public void placeObject(Furniture objek)
    {
        int itemID = objek.getID();
        
    }

    public String getRoomName()
    {
        return this.roomName;
    }

    public Boolean checkFilled(Point placement, int rotation, Furniture object)
    {
        int x,y;
        Boolean flag = false;
        if (rotation == 0 || rotation == 2)
        {
            x = object.getLengthX();
            y = object.getLengthY();
            if (rotation == 0)
            {
                if (placement.getX()+x > 6 || placement.getY()+y > 6)
                {
                    flag = true;
                }
                else
                {
                for (int i=placement.getY();i<placement.getY()+y;i++)
                    {
                        for (int j=placement.getX();j<placement.getX()+x;j++)
                        {
                            if (space[i][j] != 0)
                            {
                                flag = true;
                            } 
                        }
                    }
                }
            }
            else if (rotation == 2)
            {
                if (placement.getX()-x < 0 || placement.getY()+y > 6)
                {
                    flag = true;
                }
                else
                {
                    for (int i=placement.getY();i<placement.getY()+y;i++)
                    {
                        for (int j=placement.getX()+x-1;j>=placement.getY();j--)
                        {
                            if (space[i][j] != 0)
                            {
                                flag = true;
                            } 
                        }
                    }   
                }
            }
        }
        else if (rotation == 1 || rotation == 3)
        {
            x = object.getLengthY();
            y = object.getLengthX();
            if (rotation == 1)
            {
                if (placement.getX()+x > 6 || placement.getY()+y > 6)
                {
                    flag = true;
                }
                else
                {
                    for (int i=placement.getY();i<placement.getY()+y;i++)
                    {
                        for (int j=placement.getX();j<placement.getX()+x;j++)
                        {
                            if (space[i][j] != 0)
                            {
                                flag = true;
                            } 
                        }
                    }
                }
            }
            else if (rotation == 3)
            {
                if (placement.getX()+x > 6 || placement.getY() < 0)
                {
                    flag = true;
                }
                else
                {
                    for (int i=placement.getY()+y-1;i>=placement.getY();i--)
                    {
                        for (int j=placement.getX();j<placement.getX()+x;j++)
                        {
                            if (space[i][j] != 0)
                            {
                                flag = true;
                            } 
                        }
                    }
                }   
            }
        }
        return flag;
    }

    public Boolean checkValid(Room room, String string)
    {
        if (string.equals("left"))
        {
            return (this.left == null);
        }
        else if (string.equals("right"))
        {
             return (this.right == null);
        }
        if (string.equals("top"))
        {
            return (this.top == null);
        }
        else if (string.equals("bottom"))
        {
             return (this.bottom == null);
        }
        else
        {
            System.out.println("That's not a valid direction!");
            return false;
        }
    } 

    public void printRoom()
    {
        System.out.println("-------------------------");
        for (int i=0;i<6;i++)
        {
            System.out.print("| ");
            for (int j =0;j<6;j++)
            {
                System.out.print(space[i][j] + " | ");
            }
            System.out.println(" ");
            System.out.println("-------------------------");
        }
    }
}