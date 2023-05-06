package com.simplicity;

public class Help {
    static String[] Page1 = {"Pada saat pertama kali memulai permainan akan ditampilkan start screen. \nPada start screen ini, pemain akan diberikan 3 opsi, yaitu Start Game, Help, dan Quit. \nBerikut adalah penjelasan untuk setiap opsi yang ada :" 
    ,"1. Start Game : Pemain akan memulai game dengan world    baru. World hanya memiliki 1 Sims dan 1 Rumah. Sims akan memiliki Inventory yang berisi Single Bed, Toilet, Stove, Table and Chair, dan Clock."
    ,"2. Help  : Pemain akan diberikan tampilan User Manual untuk membantu mengerti cara memainkan Simplicity."
    ,"3. Quit : Pemain akan keluar dari permainan Simplicity."};
    
    static String[] Page2 = {"Waktu dalam Simplicity sedikit berbeda dengan dunia asli. Sehari dalam Simplicity berlangsung selama 12 menit dan waktu hanya berjalan ketika melakukan suatu aksi. \nAksi-aksi dalam  dapat dibedakan menjadi :"
    ,"1. Aksi aktif, yaitu aksi yang membutuhkan waktu, tidak bisa ditinggal, dan mempengaruhi kondisi SIM. Contohnya : \nMakan, Masak, dan Buang air"
    ,"2. Aksi  upgrade, yaitu aksi yang membutuhkan waktu, tetapi bisa ditinggal untuk melakukan aksi lain. Contohnya : \nUpgrade Rumah dan Beli Barang"
    ,"3. Aksi pasif, yaitu aksi yang dapat dilakukan tanpa menggunakan waktu. Contohnya \nPindah Ruangan, Melihat Jam, dan Memasang Barang"
    ,"4. Aksi menambah Sim, yaitu aksi yang dapat digunakan satu hari sekali untuk melahirkan Sims baru"
    ,"Terdapat juga beberapa aturan dalam Simplicity, yaitu :"
    ,"1. Sims dapat mengganti pekerjaan jika sudah bekerja 12 menit dan membayar setengah dari gaji baru"
    ,"2. Mood dan kesehatan Sims akan menurun jika tidak tidur minimum 3 menit dalam sehari"
    ,"3. Mood dan kesehatan Sims akan menurun jika tidak buang air 4 menit dari selesai makan"};

    static String[] Page3 = {"Game Simplicity tidak memiliki tujuan akhir yang menjadi tanda selesainya game. Akan tetapi, game Simplicity dapat selesai dengan 2 cara, yaitu :"
    ,"1. Semua Sims di dunia Simplicity sudah meninggal sehingga tidak ada karakter yang dapat dimainkan."
    ,"2. Keluar game Simplicity dengan melakukan Exit dari CLI ataupun dengan memilih menu quit."};
    

    public static void printMessage(int i)
    {
        if (i == 1)
        {
            for (String string :Page1)
            {
                System.out.println(string);
            } 
        }
        else if (i ==2)
        {
            for (String string :Page2)
            {
                System.out.println(string);
            } 
        }
        else if (i == 3)
        {
            for (String string :Page3)
            {
                System.out.println(string);
            } 
        }
    }
}
