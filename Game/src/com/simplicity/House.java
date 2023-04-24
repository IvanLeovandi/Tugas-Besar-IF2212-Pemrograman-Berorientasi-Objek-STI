package com.simplicity;
import java.util.ArrayList;
import javax.swing.*;

public class House {
    private Point location;
    private int numberofRoom;
    private ArrayList<Room> roomList;

    public House(Point location)
    {
        this.location = location;
        this.numberofRoom = 1;
        this.roomList = new ArrayList<Room>();
        roomList.add(new Room(numberofRoom,new Point(0,0),"Starting Room"));
    }

    public ArrayList<Room> getRoomList()
    {
        return this.roomList;
    }
    public void printRoomList()
    {
        System.out.println("These are the rooms available in this house: ");
        for (Room room :roomList)
        {
            System.out.println(room.getroomNumber() + ". " + room.getName());
        }
    }
    
    public Point getLocation()
    {
        return this.location;
    }

    public void setLocation(Point location)
    {
        this.location = location;
    }

    public int getNumberofRoom() {
        return numberofRoom;
    }

    public void increaseNumberofRoom() {
        this.numberofRoom++;
    }

    public Boolean checkName(String name)
    {
        Boolean flag = false;
        for (Room room: roomList)
        {
            if (room.getName().equals(name))
            {
                flag = true;
            }
        }
        return flag;
    }

    public Boolean checkUpgradeable(Room roomUp, String direction)
    {
        Boolean flag = true;
        int x = roomUp.getLocationInHouse().getX();
        int y = roomUp.getLocationInHouse().getY();
        if (direction.equals("right"))
        {
            if (roomUp.getRight() == null)
            {
                for (Room room: roomList)
                {
                    if (room.getLocationInHouse().getX() == x+1 && room.getLocationInHouse().getY() == y)
                    {
                        flag = false;
                    }
                }
            }
        }

        else if (direction.equals("left"))
        {
            if (roomUp.getLeft() == null)
            {
                for (Room room: roomList)
                {
                    if (room.getLocationInHouse().getX() == x-1 && room.getLocationInHouse().getY() == y)
                    {
                        flag = false;
                    }
                }
            }
        }

        else if (direction.equals("top"))
        {
            if (roomUp.getTop() == null)
            {
                for (Room room: roomList)
                {
                    if (room.getLocationInHouse().getX() == x && room.getLocationInHouse().getY() == y+1)
                    {
                        flag = false;
                    }
                }
            }
        }

        else if (direction.equals("bottom"))
        {
            if (roomUp.getBottom() == null)
            {
                for (Room room: roomList)
                {
                    if (room.getLocationInHouse().getX() == x && room.getLocationInHouse().getY() == y-1)
                    {
                        flag = false;
                    }
                }
            }
        }
        return flag;
    }

    public void upgradeRoom(Room room, String direction, String name)
    {
        int x = room.getLocationInHouse().getX();
        int y = room.getLocationInHouse().getY();
        this.increaseNumberofRoom();
        
        if (this.checkUpgradeable(room, direction))
        {
            if (direction.equals("right"))
            {
                Room tempRoom = new Room(this.getNumberofRoom(),new Point(x+1,y),name);
                tempRoom.setLeft(room);
                room.setRight(tempRoom);
                roomList.add(tempRoom);
            }

            else if (direction.equals("left"))
            {
                Room tempRoom = new Room(this.getNumberofRoom(),new Point(x-1,y),name);
                tempRoom.setRight(room);
                room.setLeft(tempRoom);
                roomList.add(tempRoom);
            }

            else if (direction.equals("bottom"))
            {
                Room tempRoom = new Room(this.getNumberofRoom(),new Point(x,y-1),name);
                tempRoom.setTop(room);
                room.setBottom(tempRoom);
                roomList.add(tempRoom);
            }


            else if (direction.equals("top"))
            {
                Room tempRoom = new Room(this.getNumberofRoom(),new Point(x,y+1),name);
                tempRoom.setBottom(room);
                room.setTop(tempRoom);
                roomList.add(tempRoom);
            }
        }
    }
}
