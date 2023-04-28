package com.simplicity;
import java.util.ArrayList;
import javax.swing.*;

public class House {
    private Point location; //Lokasi di World
    private int numberofRoom; //Jumlah ruangan di rumah
    private ArrayList<Room> roomList; //List ruangan di rumah

    //Konstruktor
    public House(Point location,Sim sim)
    {
        this.location = location;
        this.numberofRoom = 1;
        this.roomList = new ArrayList<Room>();
        roomList.add(new Room(numberofRoom,new Point(0,0),"Starting Room"));
        roomList.get(0).addSim(sim);
    }

    //Getter Room List
    public ArrayList<Room> getRoomList()
    {
        return this.roomList;
    }
    
    //Untuk mencetak room list
    public void printRoomList()
    {
        System.out.println("These are the rooms available in this house: ");
        for (Room room :roomList)
        {
            System.out.println(room.getroomNumber() + ". " + room.getName());
        }
    }
    
    //Getter location
    public Point getLocation()
    {
        return this.location;
    }

    //Setter Location
    public void setLocation(Point location)
    {
        this.location = location;
    }

    //Getter numberOfRoom
    public int getNumberofRoom() {
        return numberofRoom;
    }

    //Meningkatkan numberofRoom
    public void increaseNumberofRoom() {
        this.numberofRoom++;
    }

    //Validator untuk mengecek nama ruangan baru
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

    //Validator apakah ruangan bisa di upgrade atau tidak
    public Boolean checkUpgradeable(Room roomUp, String direction)
    {
        Boolean flag = true;
        int x = roomUp.getLocationInHouse().getX();
        int y = roomUp.getLocationInHouse().getY();
        if (direction.equals("right")) //Mengecek apakah ruangan sebelah kanan terisi atau tidak
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
            else
            {
                flag = false;
            }
        }

        else if (direction.equals("left")) //Mengecek apakah ruangan sebelah kiri terisi atau tidak
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
            else
            {
                flag = false;
            }
        }

        else if (direction.equals("top")) //Mengecek apakah ruangan sebelah atas terisi atau tidak
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
            else
            {
                flag = false;
            }
        }

        else if (direction.equals("bottom")) //Mengecek apakah ruangan sebelah bawah terisi atau tidak
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
            else
            {
                flag = false;
            }
        }
        return flag;
    }  

    //Untuk upgrade ruangan
    public void upgradeRoom(Room room, String direction, String name)
    {
        int x = room.getLocationInHouse().getX();
        int y = room.getLocationInHouse().getY();
        
        if (this.checkUpgradeable(room, direction))
        {
            this.increaseNumberofRoom();
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
            
            Room tempRoom = roomList.get(roomList.size()-1);
            int x2 = tempRoom.getLocationInHouse().getX();
            int y2 = tempRoom.getLocationInHouse().getY();
            for (Room room2 : roomList)
            {
                if (room2.getLocationInHouse().getX() == x2-1 && room2.getLocationInHouse().getY() == y2)
                {
                    room2.setRight(tempRoom);
                    tempRoom.setLeft(room2);
                }
                if (room2.getLocationInHouse().getX() == x2+1 && room2.getLocationInHouse().getY() == y2)
                {
                    room2.setLeft(tempRoom);
                    tempRoom.setRight(room2);
                }
                if (room2.getLocationInHouse().getX() == x2 && room2.getLocationInHouse().getY() == y2-1)
                {
                    room2.setTop(tempRoom);
                    tempRoom.setBottom(room2);
                }
                if (room2.getLocationInHouse().getX() == x2 && room2.getLocationInHouse().getY() == y2+1)
                {
                    room2.setBottom(tempRoom);
                    tempRoom.setTop(room2);
                }
            }
        }
    }
}
 