package SistemKasir;

public class DetailTransaksi {
    private Konsumsi konsumsi;
    private int jumlah;
    
    public DetailTransaksi(Konsumsi konsumsi, int jumlah) {
        this.konsumsi = konsumsi;
        this.jumlah = jumlah;
    }

    public Konsumsi getBarang() {
        return konsumsi;
    }
    
    public int getJumlah() {
        return jumlah;
    }
    
    public int hitungTotal(){
        return konsumsi.getHarga() * jumlah;
    }
    
    public void cetak(){
        System.out.println(konsumsi.getNama() + " x " + jumlah + " = Rp " + hitungTotal());
    }
}
