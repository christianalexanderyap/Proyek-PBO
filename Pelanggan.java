package SistemKasir;

public class Pelanggan {
    private String nama;
    private boolean member;
    
    public Pelanggan(String nama, boolean member){
        this.nama = nama;
        this.member = member;
    }
    
    public String getNama() {
        return nama;
    }
    
    public boolean isMember() {
        return member;
    }
}
