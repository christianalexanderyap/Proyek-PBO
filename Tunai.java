package SistemKasir;

public class Tunai implements Pembayaran {
    @Override
    public void bayar(double total) {
        System.out.println("Pembayaran Tunai");
        System.out.println("Total Bayar : Rp " + total);    
    }
}
