package SistemKasir;

public class Makanan extends Konsumsi {
    public Makanan(String nama, int stok, int harga) {
        super(nama, stok, harga);
    }
    @Override
    public void kategori() {
        System.out.println("Kategori: Makanan");
    }
}
