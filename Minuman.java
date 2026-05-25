package SistemKasir;

public class Minuman extends Konsumsi{
    public Minuman(String nama, int stok, int harga) {
        super(nama, stok, harga);
    }
    @Override
    public void kategori() {
        System.out.println("Kategori: Minuman");
    }
}
