package SistemKasir;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    static Scanner sc = new Scanner(System.in);
    static Transaksi transaksiTerakhir;
    static double totalPendapatan = 0;

    public static void main(String[] args) {

        ArrayList<Konsumsi> daftarKonsumsi = new ArrayList<>();
        
        daftarKonsumsi.add(new Minuman("Kopi Tarik Ice", 50, 12000));
        daftarKonsumsi.add(new Minuman("Teh Tarik", 50, 10000));
        daftarKonsumsi.add(new Minuman("Milo Malay", 40, 18000));
        daftarKonsumsi.add(new Makanan("Roti Bakar", 25, 15000));
        daftarKonsumsi.add(new Makanan("Kentang Goreng", 20, 12000));
        
        int menu;

        do {

            System.out.println("\n================================================");
            System.out.println("||                   MENU                     ||");
            System.out.println("================================================");
            System.out.println("|| 1. Transaksi                               ||");
            System.out.println("|| 2. Cetak Struk                             ||");
            System.out.println("|| 3. Daftar Stok Barang                      ||");
            System.out.println("|| 4. Tambah Stok Barang                      ||");
            System.out.println("|| 5. Tambah Barang Baru                      ||");
            System.out.println("|| 6. Laporan Pendapatan                      ||");
            System.out.println("|| 7. Exit                                    ||");
            System.out.println("================================================");

            System.out.print("Pilih menu : ");
            menu = sc.nextInt();
            sc.nextLine();

            switch (menu) {

                case 1:
                    transaksi(daftarKonsumsi);
                    break;

                case 2:
                    cetakPembayaran();
                    break;

                case 3:
                    daftarStokBarang(daftarKonsumsi);
                    break;

                case 4:
                    tambahStok(daftarKonsumsi);
                    break;

                case 5:
                    tambahBarangBaru(daftarKonsumsi);
                    break;

                case 6:
                    laporanPendapatan();
                    break;

                case 7:
                    System.out.println("Terima kasih!");
                    break;

                default:
                    System.out.println("Menu tidak tersedia!");
            }

        } while (menu != 7);

        sc.close();
    }
    
    public static void transaksi(ArrayList<Konsumsi> daftarKonsumsi) {
        System.out.println("\n===== TRANSAKSI =====");

        System.out.print("Nama pelanggan : ");
        String nama = sc.nextLine();

        System.out.print("Member? (y/n) : ");
        String memberInput = sc.nextLine();

        boolean member = memberInput.equalsIgnoreCase("y");

        Pelanggan pelanggan = new Pelanggan(nama, member);
        transaksiTerakhir = new Transaksi(pelanggan);
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

            transaksiTerakhir.tambahItem(pilihMenu, jumlah);
            sc.nextLine();

            System.out.print("Tambah lagi? (y/n) : ");
            String lagi = sc.nextLine();

            if (lagi.equalsIgnoreCase("n")) {
                lanjut = false;
            }
        }

        totalPendapatan += transaksiTerakhir.totalBayar();
        System.out.println("\nTransaksi berhasil!");
    }
    
    public static void cetakPembayaran() {
        if (transaksiTerakhir == null) {
            System.out.println("Belum ada transaksi!");
            return;
        }

        transaksiTerakhir.cetakStruk();

        System.out.println("\nMetode Pembayaran");
        System.out.println("1. Tunai");
        System.out.println("2. Transfer");

        System.out.print("Pilih metode : ");

        int metode = sc.nextInt();

        Pembayaran pembayaran;

        if (metode == 1) {
            pembayaran = new Tunai();
        } else {
            pembayaran = new Transfer();
        }
        pembayaran.bayar(transaksiTerakhir.totalBayar());
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

    public static void laporanPendapatan() {
        System.out.println("\n===== LAPORAN PENDAPATAN =====");
        System.out.println("Total Pendapatan : Rp " + totalPendapatan);
    }
}