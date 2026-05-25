package SistemKasir;

import java.util.ArrayList;

public class Transaksi {
    private Pelanggan pelanggan;
    private ArrayList<DetailTransaksi> daftarBelanja;
    
    public Transaksi(Pelanggan pelanggan){
        this.pelanggan = pelanggan;
        daftarBelanja = new ArrayList<>();
    }
    
    public void tambahItem(Konsumsi konsumsi, int jumlah){
        if(jumlah > konsumsi.getStok()){
            System.out.println("Stok tidak tersedia");
            return;
        }
        konsumsi.stokBerkurang(jumlah);
        daftarBelanja.add(new DetailTransaksi(konsumsi, jumlah));
    }
    
    public void tambahItem(Konsumsi konsumsi) {
        tambahItem(konsumsi, 1);
    }
    
    public int totalBelanja(){
        int total = 0;
        for(DetailTransaksi item : daftarBelanja){
            total += item.hitungTotal();
        }
        return total;
    }
    
    public double hitungDiskon(){
        if(pelanggan.isMember()){
            return totalBelanja() * 0.15;
        }
        return 0;
    }
    
    public double totalBayar(){
        return totalBelanja() - hitungDiskon();
    }
    
    public void cetakStruk() {

        System.out.println("\n=================================");
        System.out.println("         STRUK PEMBAYARAN");
        System.out.println("=================================");
        System.out.println("Pelanggan : " + pelanggan.getNama());
        System.out.println("---------------------------------");

        for (DetailTransaksi item : daftarBelanja) {
            item.cetak();
        }
        System.out.println("---------------------------------");
        System.out.println("Total   : Rp " + totalBelanja());
        System.out.println("Diskon  : Rp " + hitungDiskon());
        System.out.println("Bayar   : Rp " + totalBayar());
        System.out.println("=================================");
    }
}
