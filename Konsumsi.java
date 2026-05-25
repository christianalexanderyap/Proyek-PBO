package SistemKasir;

public abstract class Konsumsi {
    protected String nama;
    protected int stok;
    protected int harga;
    
    public Konsumsi(String nama, int stok, int harga) {
        this.nama = nama;
        this.stok = stok;
        this.harga = harga;
    }
    public String getNama() {
        return nama;
    }
    public int getStok() {
        return stok;
    }
    public int getHarga() {
        return harga;
    }
    
    public void stokBerkurang(int jumlah){
        stok -= jumlah;
    }
    public void tambahStok(int jumlah) {
        stok += jumlah;
    }
    public String toString(){
        return nama + " | Harga: Rp " + harga + " | Stok: " + stok;
    }

    public abstract void kategori();
}
