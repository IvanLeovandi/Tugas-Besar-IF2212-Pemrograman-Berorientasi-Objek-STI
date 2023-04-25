package com.simplicity;
import com.simplicity.Furniture.*;
import java.util.ArrayList;

public class Room {
    public int roomNumber; //Nomor ruangan di rumah
    public ArrayList<Furniture> furnitureList; //List Furniture
    public int[] furnitureCount; //Jumlah setiap tipe furniture di ruangan
    public Room left,right,top,bottom; //Ruangan tetangga
    public Point[][] space; //Space dalam ruangan untuk meletakkan furniture dengan bentuk Point (x,y) (x: Furniture Number, y: Furniture dengan tipe x ke berapa di ruangan)
    public Point locationInHouse; //Lokasi di rumah
    private String name; //Nama Ruangan

    //Konstruktor
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

    //Setter Name
    public void setName(String name)
    {   
        this.name = name;
    }

    //Getter Name
    public String getName()
    {
        return this.name;
    }

    //Getter room sebelah
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

    //Setter room sebelah
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

    //Getter roomNumber
    public int getroomNumber()
    {
        return this.roomNumber;
    }

    //Getter locationInHouse
    public Point getLocationInHouse()
    {
        return this.locationInHouse;
    }

    //Getter furnitureList
    public ArrayList<Furniture> getfurnitureList()
    {
        return this.furnitureList;
    }

    //Untuk mengecek ada furniture lain di posisi yang akan diletakkan furniture atau mengecek apakah furniture yang akan diletakkan melewati border atau tidak
    public Boolean checkFilled(Point placement, int rotation, Furniture furniture)
    {
        int x,y;
        Boolean flag = false;
        if (rotation == 0 || rotation == 2) //Furniture horizontal
        {
            x = furniture.getSize().getLength();
            y = furniture.getSize().getWidth();
            if (rotation == 0) //Furniture menghadap ke arah kanan dari posisi placement awal 
            {
                if (placement.getX()+x > 6 || placement.getY()+y > 6) //Mengecek apakah melewati border
                {
                    flag = true;
                }
                else
                {
                for (int i=placement.getY();i<placement.getY()+y;i++) //Looping mengecek ada furniture lain atau tidak
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
            else if (rotation == 2) //Furniture menghadap ke arah krii dari posisi placement awal
            {
                if (placement.getX()-x < 0 || placement.getY()+y > 6) //Mengecek apakah melewati border
                {
                    flag = true;
                }
                else
                {
                    for (int i=placement.getY();i<placement.getY()+y;i++) //Looping mengecek ada furniture lain atau tidak
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
        else if (rotation == 1 || rotation == 3) //Furniture vertical
        {
            x = furniture.getSize().getLength();
            y = furniture.getSize().getWidth();
            if (rotation == 1) //Furniture menghadap ke arah bawah dari posisi placement awal 
            {
                if (placement.getX()+x > 6 || placement.getY()+y > 6) //Mengecek apakah melewati border
                {
                    flag = true;
                }
                else
                {
                    for (int i=placement.getY();i<placement.getY()+y;i++) //Looping mengecek ada furniture lain atau tidak
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
            else if (rotation == 3) //Furniture menghadap ke arah atas dari posisi palcement awal
            {
                if (placement.getX()+x > 6 || placement.getY() < 0) //Mengecek apakah melewati border
                {
                    flag = true;
                }
                else
                {
                    for (int i=placement.getY()+y-1;i>=placement.getY();i--) //Looping mengecek ada furniture lain atau tidak
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

    //Untuk meletakkan furniture di ruangan
    public void placeFurniture (Point placement, int rotation, Furniture furniture)
    {
        if (!checkFilled(placement, rotation, furniture)) //Melakukan pengecekan mengunakan checkFilled
        {
            furnitureCount[furniture.getId()-1]++; //Penambahan jumlah furniture untuk memberikan kode pada furniture yang diletakkan
            furnitureList.add(furniture); //Menambahkan furniture ke furnitureList
            int x,y;
            if (rotation == 0 || rotation == 2) //Furniture horizontal
            {
                x = furniture.getSize().getLength();
                y = furniture.getSize().getWidth();
                if (rotation == 0) //Furniture menghadap ke arah kanan dari posisi palcement awal
                {
                    for (int i=placement.getY();i<placement.getY()+y;i++) //Looping untuk memasukkan furniture pada space
                        {
                            for (int j=placement.getX();j<placement.getX()+x;j++)
                            {
                                space[i][j] = new Point(furniture.getId(),furnitureCount[furniture.getId()-1]);
                            }
                        }
                    }
                
                else if (rotation == 2) //Furniture menghadap ke arah kiri dari posisi palcement awal
                {
                    for (int i=placement.getY();i<placement.getY()+y;i++) //Looping untuk memasukkan furniture pada space
                    {
                        for (int j=placement.getX()+x-1;j>=placement.getY();j--)
                        {
                            space[i][j] = new Point(furniture.getId(),furnitureCount[furniture.getId()-1]);
                        }
                    }   
                }
            }
            else if (rotation == 1 || rotation == 3) //Furniture vertical
            {
                x = furniture.getSize().getLength();
                y = furniture.getSize().getWidth();
                if (rotation == 1) //Furniture menghadap ke arah bawah dari posisi palcement awal
                {
                    for (int i=placement.getY();i<placement.getY()+y;i++) //Looping untuk memasukkan furniture pada space
                    {
                        for (int j=placement.getX();j<placement.getX()+x;j++)
                        {
                            space[i][j] = new Point(furniture.getId(),furnitureCount[furniture.getId()-1]);
                        }
                    }
                }
                else if (rotation == 3) //Furniture menghadap ke arah atas dari posisi palcement awal
                { 
                    for (int i=placement.getY()+y-1;i>=placement.getY();i--) //Looping untuk memasukkan furniture pada space
                    {
                        for (int j=placement.getX();j<placement.getX()+x;j++)
                        {
                            space[i][j] = new Point(furniture.getId(),furnitureCount[furniture.getId()-1]);
                        }
                    }   
                }
            }
        }
        else //Placement yang dimasukkan tidak cukup untuk meletakkan furniture
        {
            System.out.printf("There is not enough space for you to place a %s\n",furniture.getName());
        }
    }
    
    //Untuk remove furniture dari ruangan
    public void removeFurniture(Point placement) 
    {
        if (space[placement.getY()][placement.getX()] != null) //Mengecek placement yang dimasukkan ada furniture atau tidak
        {
            Point removedfurnitureId = space[placement.getY()][placement.getX()]; 
            int furnitureX = removedfurnitureId.getY(); //Furniture dengan tipe x ke berapa 
            furnitureCount[removedfurnitureId.getX()-1]--; //Mengurangi count furniture dari furnitureCount
            for (Furniture furniture: furnitureList) //Menghilangkan furniture dari furnitureList
            {
                if (furniture.getId() == removedfurnitureId.getX() && furnitureX != 0)
                {
                    furnitureList.remove(furniture);
                    furnitureX--;
                }
            }
            for (int i=0; i<6 ; i++) //Looping untuk menghilangkan furniture dari space 
            {
                for (int j=0; j<6; j++)
                {
                    if (space[i][j] != null)
                    {
                        if (space[i][j].equals(removedfurnitureId)) //Space sama merupakan furniture yang akan diremove
                        {
                            space[i][j] = null;
                        }
                        else if (space[i][j].getX() == removedfurnitureId.getX() && space[i][j].getY() > removedfurnitureId.getY()) //Melakukan pengurangan terhadap furniture lain yang bertipe sama
                        {
                            space[i][j].setPoint(space[i][j].getX(),space[i][j].getY()-1);
                        }
                    }
                }
            }
        }
    }

    //Mengecek apakah direction yang dimasukkan valid atau tidak
    public Boolean checkValid(String direction) 
    {
        if (direction.equals("left"))
        {
            return (this.left == null);
        }
        else if (direction.equals("right"))
        {
             return (this.right == null);
        }
        if (direction.equals("top"))
        {
            return (this.top == null);
        }
        else if (direction.equals("bottom"))
        {
             return (this.bottom == null);
        }
        else
        {
            System.out.println("That's not a valid direction!");
            return false;
        }
    } 

    //Mencetak ruangan beserta furniture yang ada di dalamnya
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