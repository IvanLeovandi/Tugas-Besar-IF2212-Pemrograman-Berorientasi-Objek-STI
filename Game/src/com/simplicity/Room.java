package com.simplicity;
import com.simplicity.Furniture.*;
import java.util.ArrayList;

public class Room {
    public int roomNumber;
    public ArrayList<Furniture> furnitureList;
    public int[] furnitureCount;
    public Room left,right,top,bottom;
    public Point[][] space;
    public Point locationInHouse;
    private String name;

    public Room(int roomNumber,Point locationInHouse, String name)
    {   
        this.roomNumber = roomNumber;
        left = null;
        right = null;
        top = null;
        bottom = null;
        this.locationInHouse = locationInHouse;
        furnitureList = new ArrayList<Furniture>();
        furnitureCount = new int[8];
        space = new Point[6][6];
        this.name = name;   
    }

    public void setName(String name)
    {   
        this.name = name;
    }

    public String getName()
    {
        return this.name;
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

    public ArrayList<Furniture> getfurnitureList()
    {
        return this.furnitureList;
    }

    public Boolean checkFilled(Point placement, int rotation, Furniture furniture)
    {
        int x,y;
        Boolean flag = false;
        if (rotation == 0 || rotation == 2)
        {
            x = furniture.getSize().getX();
            y = furniture.getSize().getY();
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
                            if (space[i][j] != null)
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
                            if (space[i][j] != null)
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
            x = furniture.getSize().getX();
            y = furniture.getSize().getY();
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
                            if (space[i][j] != null)
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
                            if (space[i][j] != null)
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

    public void placeFurniture (Point placement, int rotation, Furniture furniture)
    {
        if (!furnitureList.contains(furniture))
        {
            if (!checkFilled(placement, rotation, furniture))
            {
                furnitureCount[furniture.getId()-1]++;
                furnitureList.add(furniture);
                int x,y;
                if (rotation == 0 || rotation == 2)
                {
                    x = furniture.getSize().getX();
                    y = furniture.getSize().getY();
                    if (rotation == 0)
                    {
                        for (int i=placement.getY();i<placement.getY()+y;i++)
                            {
                                for (int j=placement.getX();j<placement.getX()+x;j++)
                                {
                                    space[i][j] = new Point(furniture.getId(),furnitureCount[furniture.getId()-1]);
                                }
                            }
                        }
                    
                    else if (rotation == 2)
                    {
                        for (int i=placement.getY();i<placement.getY()+y;i++)
                        {
                            for (int j=placement.getX()+x-1;j>=placement.getY();j--)
                            {
                                space[i][j] = new Point(furniture.getId(),furnitureCount[furniture.getId()-1]);
                            }
                        }   
                    }
                }
                else if (rotation == 1 || rotation == 3)
                {
                    x = furniture.getSize().getX();
                    y = furniture.getSize().getY();
                    if (rotation == 1)
                    {
                        for (int i=placement.getY();i<placement.getY()+y;i++)
                        {
                            for (int j=placement.getX();j<placement.getX()+x;j++)
                            {
                                space[i][j] = new Point(furniture.getId(),furnitureCount[furniture.getId()-1]);
                            }
                        }
                    }
                    else if (rotation == 3)
                    {
                        for (int i=placement.getY()+y-1;i>=placement.getY();i--)
                        {
                            for (int j=placement.getX();j<placement.getX()+x;j++)
                            {
                                space[i][j] = new Point(furniture.getId(),furnitureCount[furniture.getId()-1]);
                            }
                        }   
                    }
                }
            }
            else
            {
                System.out.printf("There is not enough space for you to place a %s\n",furniture.getName());
            }
        }
        else
        {
            System.out.printf("This room already has a %s\n",furniture.getName());
        }
    }
    
    public void removeFurniture(Point placement)
    {
        if (space[placement.getY()][placement.getX()] != null)
        {
            Point removedfurnitureId = space[placement.getY()][placement.getX()];
            furnitureCount[removedfurnitureId.getX()-1]--;
            for (Furniture furniture: furnitureList)
            {
                if (furniture.getId() == removedfurnitureId.getX())
                {
                    furnitureList.remove(furniture);
                    break;
                }
            }
            for (int i=0; i<6 ; i++)
            {
                for (int j=0; j<6; j++)
                {
                    if (space[i][j] != null)
                    {
                        if (space[i][j].equals(removedfurnitureId))
                        {
                            space[i][j] = null;
                        }
                        else if (space[i][j].getX() == removedfurnitureId.getX() && space[i][j].getY() > removedfurnitureId.getY())
                        {
                            space[i][j].setPoint(space[i][j].getX(),space[i][j].getY()-1);
                        }
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
        System.out.println("-------------------------------------");
        for (int i=0;i<6;i++)
        {
            System.out.print("| ");
            for (int j =0;j<6;j++)
            {
                if (space[i][j] == null)
                {
                    System.out.print("0,0 | ");
                }
                else
                {
                    System.out.print(space[i][j].getX() + "," + space[i][j].getY() + " | ");
                }
            }
            System.out.println(" ");
            System.out.println("-------------------------------------");
        }
    }
}