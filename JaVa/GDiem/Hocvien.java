package GDiem;

public class Hocvien {
    private String MaHV;
    private String Hoten;
    private String Lop;
    private float Diem;
    
    public Hocvien() {}
    
    public Hocvien(String MaHV, String Hoten, String Lop, float Diem) {
        this.MaHV = MaHV;
        this.Hoten = Hoten;
        this.Lop = Lop;
        this.Diem = Diem;
    }
    
    public String getMaHV() {
        return MaHV;
    }
    
    public void setMaHV(String MaHV) {
        this.MaHV = MaHV;
    }
    
    public String getHoten() {
        return Hoten;
    }
    
    public void setHoten(String Hoten) {
        this.Hoten = Hoten;
    }
    
    public String getLop() {
        return Lop;
    }
    
    public void setLop(String Lop) {
        this.Lop = Lop;
    }
    
    public float getDiem() {
        return Diem;
    }
    
    public void setDiem(float Diem) {
        this.Diem = Diem;
    }
    
    public String Ketqua() {
        if (this.Diem >= 25)
            return "Dat";
        else
            return "Không đạt";
    }
}