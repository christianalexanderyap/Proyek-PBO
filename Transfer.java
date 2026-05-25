package SistemKasir;

public class Transfer implements Pembayaran {
    @Override
    public void bayar(double total) {
        System.out.println("Pembayaran Transfer");
        System.out.println("Total Bayar : Rp " + total);    
    }
}
