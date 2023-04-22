package com.simplicity;
import com.simplicity.Furniture.*;
import java.util.ArrayList;

public class Room {
    public int roomNumber;
    public ArrayList<Furniture> objectList;
    public Room left,right,top,bottom;
    public int[][] space;
    public Point locationInHouse;


    public Room(int roomNumber,Point locationInHouse)
    {   
        this.roomNumber = roomNumber;
        left = null;
        right = null;
        top = null;
        bottom = null;
        this.locationInHouse = locationInHouse;
        objectList = new ArrayList<Furniture>();
        space = new int[6][6];
    }

    public Room getLeft()
    {
        return this.left;
    }
    
    public Room getRight()
    {
        return this.right;
    }

    public Room getTop ()
    {
        return this.top;
    }

    public Room getBottom ()
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

    public int getroomNumber()
    {
        return this.roomNumber;
    }

    public Point getLocationInHouse()
    {
        return this.locationInHouse;
    }

    public Boolean checkFilled(Point placement, int rotation, Furniture object)
    {
        int x,y;
        Boolean flag = false;
        if (rotation == 0 || rotation == 2)
        {
            x = object.getSize().getX();
            y = object.getSize().getY();
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
            x = object.getSize().getX();
            y = object.getSize().getY();
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

    public void placeObject (Point placement, int rotation, Furniture object)
    {
        if (!objectList.contains(object))
        {
            if (!checkFilled(placement, rotation, object))
            {
                objectList.add(object);
                int x,y;
                if (rotation == 0 || rotation == 2)
                {
                    x = object.getSize().getX();
                    y = object.getSize().getY();
                    if (rotation == 0)
                    {
                        for (int i=placement.getY();i<placement.getY()+y;i++)
                            {
                                for (int j=placement.getX();j<placement.getX()+x;j++)
                                {
                                    space[i][j] = object.getId();
                                }
                            }
                        }
                    
                    else if (rotation == 2)
                    {
                        for (int i=placement.getY();i<placement.getY()+y;i++)
                        {
                            for (int j=placement.getX()+x-1;j>=placement.getY();j--)
                            {
                                space[i][j] = object.getId();
                            }
                        }   
                    }
                }
                else if (rotation == 1 || rotation == 3)
                {
                    x = object.getSize().getX();
                    y = object.getSize().getY();
                    if (rotation == 1)
                    {
                        for (int i=placement.getY();i<placement.getY()+y;i++)
                        {
                            for (int j=placement.getX();j<placement.getX()+x;j++)
                            {
                                space    [i][j] = object.getId();
                            }
                        }
                    }
                    else if (rotation == 3)
                    {
                        for (int i=placement.getY()+y-1;i>=placement.getY();i--)
                        {
                            for (int j=placement.getX();j<placement.getX()+x;j++)
                            {
                                space[i][j] = object.getId();
                            }
                        }   
                    }
                }
            }
            else
            {
                System.out.printf("There is not enough space for you to place a %s\n",object.getName());
            }
        }
        else
        {
            System.out.printf("This room already has a %s\n",object.getName());
        }
    }
    
    public void removeObject(Point placement)
    {
        if (space[placement.getY()][placement.getX()] != 0)
        {
            int removedObjectId = space[placement.getY()][placement.getX()];
            for (Furniture furniture: objectList)
            {
                if (furniture.getId() == removedObjectId)
                {
                    objectList.remove(furniture);
                    break;
                }
            }
            for (int i=0; i<6 ; i++)
            {
                for (int j=0; j<6; j++)
                {
                    if (space[i][j] == removedObjectId)
                    {
                        space[i][j] = 0;
                    }
                }
            }
        }
    }

    public Boolean checkValid(String string)
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