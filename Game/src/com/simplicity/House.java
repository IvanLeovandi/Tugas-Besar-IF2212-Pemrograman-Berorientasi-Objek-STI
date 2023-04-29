package com.simplicity;
import java.util.*;

public class House {
    private Point location; //Lokasi di World
    private int numberofRoom; //Jumlah ruangan di rumah
    private Map<Point,Room> roomList; //List ruangan di rumah
    private Sim houseOwner;

    //Konstruktor
    public House(Point location,Sim sim)
    {
        this.location = location;
        this.numberofRoom = 1;
        this.roomList = new HashMap<Point,Room>();
        this.houseOwner = sim;
        roomList.put(new Point(0,0),new Room(numberofRoom,new Point(0,0),"Starting Room"));
        roomList.get(new Point(0,0)).addSim(sim);
    }

    public House(Point location)
    {
        this.location = location;
        this.numberofRoom = 1;
        this.roomList = new HashMap<Point,Room>();
        roomList.put(new Point(0,0),new Room(numberofRoom,new Point(0,0),"Starting Room"));
    }

    //Getter Room List
    public Map<Point,Room> getRoomList()
    {
        return this.roomList;
    }

    //Untuk mencetak room list
    public void printRoomList()
    {
        System.out.println("These are the rooms available in this house: ");
        for (Map.Entry<Point,Room> room : roomList.entrySet())
        {
            System.out.println(room.getValue().getroomNumber() + ". " + room.getValue().getName());
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

    //Getter House Owner
    public Sim getHouseOwner()
    {
        return this.houseOwner;
    }

    //Validator untuk mengecek nama ruangan baru
    public Boolean checkName(String name)
    {
        Boolean flag = false;
        for (Map.Entry<Point,Room> room : roomList.entrySet())
        {
            if (room.getValue().getName().equals(name))
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
                for (Map.Entry<Point,Room> room: roomList.entrySet())
                {
                    if (room.getValue().getLocationInHouse().getX() == x+1 && room.getValue().getLocationInHouse().getY() == y)
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
                for (Map.Entry<Point,Room> room: roomList.entrySet())
                {
                    if (room.getValue().getLocationInHouse().getX() == x-1 && room.getValue().getLocationInHouse().getY() == y)
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
                for (Map.Entry<Point,Room> room: roomList.entrySet())
                {
                    if (room.getValue().getLocationInHouse().getX() == x && room.getValue().getLocationInHouse().getY() == y+1)
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
                for (Map.Entry<Point,Room> room: roomList.entrySet())
                {
                    if (room.getValue().getLocationInHouse().getX() == x && room.getValue().getLocationInHouse().getY() == y-1)
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
        Room tempRoom=null;

        if (this.checkUpgradeable(room, direction))
        {
            this.increaseNumberofRoom();
            if (direction.equals("right"))
            {
                roomList.put(new Point(x+1,y),new Room(this.getNumberofRoom(),new Point(x+1,y),name));
                tempRoom =  roomList.get(new Point(x+1,y));
                tempRoom.setLeft(room);
                room.setRight(tempRoom);
            }

            else if (direction.equals("left"))
            {
                roomList.put(new Point(x-1,y),new Room(this.getNumberofRoom(),new Point(x-1,y),name));
                tempRoom = roomList.get(new Point(x-1,y));
                tempRoom.setRight(room);
                room.setLeft(tempRoom);
            }

            else if (direction.equals("bottom"))
            {
                roomList.put(new Point(x,y-1),new Room(this.getNumberofRoom(),new Point(x,y-1),name));
                tempRoom = roomList.get(new Point(x,y-1));
                tempRoom.setTop(room);
                room.setBottom(tempRoom);
            }


            else if (direction.equals("top"))
            {
                roomList.put(new Point(x,y+1),new Room(this.getNumberofRoom(),new Point(x,y+1),name));
                tempRoom = roomList.get(new Point(x,y+1));
                tempRoom.setBottom(room);
                room.setTop(tempRoom);
            }

            int x2 = tempRoom.getLocationInHouse().getX();
            int y2 = tempRoom.getLocationInHouse().getY();
            for (Map.Entry<Point,Room> room2: roomList.entrySet())
            {
                if (room2.getValue().getLocationInHouse().getX() == x2-1 && room2.getValue().getLocationInHouse().getY() == y2)
                {
                    room2.getValue().setRight(tempRoom);
                    tempRoom.setLeft(room2.getValue());
                }
                if (room2.getValue().getLocationInHouse().getX() == x2+1 && room2.getValue().getLocationInHouse().getY() == y2)
                {
                    room2.getValue().setLeft(tempRoom);
                    tempRoom.setRight(room2.getValue());
                }
                if (room2.getValue().getLocationInHouse().getX() == x2 && room2.getValue().getLocationInHouse().getY() == y2-1)
                {
                    room2.getValue().setTop(tempRoom);
                    tempRoom.setBottom(room2.getValue());
                }
                if (room2.getValue().getLocationInHouse().getX() == x2 && room2.getValue().getLocationInHouse().getY() == y2+1)
                {
                    room2.getValue().setBottom(tempRoom);
                    tempRoom.setTop(room2.getValue());
                }
            }
        }
    }


}
