package SistemKasir;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static Scanner sc = new Scanner(System.in);
    static double totalPendapatan = 0;

    public static void main(String[] args) {
        ArrayList<Konsumsi> daftarKonsumsi = new ArrayList<>();
        ArrayList<Pelanggan> daftarPelanggan = new ArrayList<>();
        ArrayList<Transaksi> daftarTransaksi = new ArrayList<>();
        
        // ================= MINUMAN =================
        daftarKonsumsi.add(new Minuman("Kopi Tarik Ice", 50, 12000));
        daftarKonsumsi.add(new Minuman("Teh Tarik", 50, 10000));
        daftarKonsumsi.add(new Minuman("Milo Malay", 40, 18000));
        daftarKonsumsi.add(new Minuman("Thai Tea", 35, 15000));
        daftarKonsumsi.add(new Minuman("Chocolate Latte", 30, 20000));
        daftarKonsumsi.add(new Minuman("Matcha Latte", 25, 22000));
        daftarKonsumsi.add(new Minuman("Lemon Tea", 40, 12000));
        daftarKonsumsi.add(new Minuman("Cappuccino", 20, 25000));
        daftarKonsumsi.add(new Minuman("Red Velvet", 18, 23000));
        daftarKonsumsi.add(new Minuman("Es Jeruk", 45, 10000));


        // ================= MAKANAN =================
        daftarKonsumsi.add(new Makanan("Roti Bakar", 25, 15000));
        daftarKonsumsi.add(new Makanan("Kentang Goreng", 20, 12000));
        daftarKonsumsi.add(new Makanan("Nasi Goreng Spesial", 15, 25000));
        daftarKonsumsi.add(new Makanan("Mie Ayam", 20, 18000));
        daftarKonsumsi.add(new Makanan("Chicken Katsu", 18, 28000));
        daftarKonsumsi.add(new Makanan("Ayam Geprek", 22, 20000));
        daftarKonsumsi.add(new Makanan("Burger Beef", 15, 30000));
        daftarKonsumsi.add(new Makanan("Spaghetti Carbonara", 12, 35000));
        daftarKonsumsi.add(new Makanan("Sosis Bakar", 30, 15000));
        daftarKonsumsi.add(new Makanan("Nugget Kentang", 28, 17000));
        
        int menu;
        
        do {

            System.out.println("\n================================================");
            System.out.println("||                   MENU                     ||");
            System.out.println("================================================");
            System.out.println("|| 1. Transaksi                               ||");
            System.out.println("|| 2. Daftar Stok Barang                      ||");
            System.out.println("|| 3. Tambah Stok Barang                      ||");
            System.out.println("|| 4. Tambah Barang Baru                      ||");
            System.out.println("|| 5. Laporan Pendapatan                      ||");
            System.out.println("|| 6. Exit                                    ||");
            System.out.println("================================================");

            System.out.print("Pilih menu : ");
            menu = sc.nextInt();
            sc.nextLine();

            switch (menu) {

                case 1:
                    transaksi(daftarKonsumsi, daftarPelanggan, daftarTransaksi);
                    break;

                case 2:
                    daftarStokBarang(daftarKonsumsi);
                    break;

                case 3:
                    tambahStok(daftarKonsumsi);
                    break;

                case 4:
                    tambahBarangBaru(daftarKonsumsi);
                    break;

                case 5:
                    laporanPendapatan(daftarPelanggan, daftarTransaksi);
                    break;

                case 6:
                    System.out.println("Terima kasih!");
                    break;

                default:
                    System.out.println("Menu tidak tersedia!");
            }

        } while (menu != 6);

        sc.close();
    }
    
    public static void transaksi(ArrayList<Konsumsi> daftarKonsumsi, ArrayList<Pelanggan> daftarPelanggan, ArrayList<Transaksi> daftarTransaksi) {
        System.out.println("\n===== TRANSAKSI =====");

        System.out.print("Nama pelanggan : ");
        String nama = sc.nextLine();

        System.out.print("Member? (y/n) : ");
        String memberInput = sc.nextLine();

        boolean member = memberInput.equalsIgnoreCase("y");

        
        daftarPelanggan.add(new Pelanggan(nama, member));
        daftarTransaksi.add(new Transaksi(new Pelanggan(nama, member)));
        ArrayList<Konsumsi> menuTampilan = new ArrayList<>();

        boolean lanjut = true;

        while (lanjut) {
            menuTampilan.clear();
            System.out.println("\n===== MAKANAN =====");

            int nomor = 1;
            for (Konsumsi item : daftarKonsumsi) {
                if (item instanceof Makanan) {
                    System.out.println(nomor + ". " + item);
                    menuTampilan.add(item);
                    nomor++;
                }
            }
            System.out.println("\n===== MINUMAN =====");
            for (Konsumsi item : daftarKonsumsi) {
                if (item instanceof Minuman) {
                    System.out.println(nomor + ". " + item);
                    menuTampilan.add(item);
                    nomor++;
                }
            }

            System.out.print("\nPilih menu : ");
            int pilih = sc.nextInt();

            if (pilih < 1 || pilih > menuTampilan.size()) {
                System.out.println("Menu tidak tersedia!");
                continue;
            }

            Konsumsi pilihMenu = menuTampilan.get(pilih - 1);
            System.out.print("Jumlah beli : ");
            int jumlah = sc.nextInt();

            daftarTransaksi.get(daftarPelanggan.size() - 1).tambahItem(pilihMenu, jumlah);
            sc.nextLine();

            System.out.print("Tambah lagi? (y/n) : ");
            String lagi = sc.nextLine();

            if (lagi.equalsIgnoreCase("n")) {
                lanjut = false;
            }
        }

        totalPendapatan += daftarTransaksi.get(daftarPelanggan.size() - 1).totalBayar();
        System.out.println("\nTransaksi berhasil!");
        cetakPembayaran(daftarTransaksi);
    }
    
    public static void cetakPembayaran(ArrayList<Transaksi> daftarTransaksi) {
        if (totalPendapatan == 0) {
            System.out.println("Belum ada transaksi!");
            return;
        }

        daftarTransaksi.get(daftarTransaksi.size() - 1).cetakStruk();
    }
    
    public static void daftarStokBarang(ArrayList<Konsumsi> daftarKonsumsi) {
        System.out.println("\n===== DAFTAR STOK =====");
        int no = 1;
        for (Konsumsi item : daftarKonsumsi) {
            System.out.println(no + ". " + item);
            no++;
        }
    }
    
    public static void tambahStok(ArrayList<Konsumsi> daftarKonsumsi) {
        daftarStokBarang(daftarKonsumsi);
        
        System.out.print("\nPilih barang : ");

        int pilih = sc.nextInt();

        if (pilih < 1 || pilih > daftarKonsumsi.size()) {
            System.out.println("Barang tidak tersedia!");
            return;
        }

        System.out.print("Jumlah tambah stok : ");
        int tambah = sc.nextInt();

        daftarKonsumsi.get(pilih - 1).tambahStok(tambah);

        System.out.println("Stok berhasil ditambah!");
    }
    
    public static void tambahBarangBaru(ArrayList<Konsumsi> daftarKonsumsi) {
        System.out.println("\n===== TAMBAH BARANG =====");
        System.out.println("1. Makanan");
        System.out.println("2. Minuman");

        System.out.print("Pilih kategori : ");

        int kategori = sc.nextInt();
        sc.nextLine();

        System.out.print("Nama barang : ");
        String nama = sc.nextLine();

        System.out.print("Stok : ");
        int stok = sc.nextInt();

        System.out.print("Harga : ");
        int harga = sc.nextInt();

        if (kategori == 1) {
            daftarKonsumsi.add(new Makanan(nama, stok, harga));
        } else {
            daftarKonsumsi.add(new Minuman(nama, stok, harga));
        }

        System.out.println("Barang berhasil ditambahkan!");
    }

    public static void laporanPendapatan(ArrayList<Pelanggan>  daftarPelanggan, ArrayList<Transaksi> daftarTransaksi) {
        System.out.println("\n===== LAPORAN PENDAPATAN =====");
        for (int i = 0; i < daftarPelanggan.size(); i++) {
            System.out.println("Pelanggan ke-" + (i + 1) + " " + daftarPelanggan.get(i).toString() + " : Rp " +daftarTransaksi.get(i).totalBayar());
        }
        System.out.println("Total Pendapatan : Rp " + totalPendapatan);
    }
}