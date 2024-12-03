package DAGV;
public class Giangvien extends Person {
    private String donVi;
    private int soCT;

    // Constructor mặc định
    public Giangvien() {
        super();
        this.donVi = "";
        this.soCT = 0;
    }

    // Constructor đầy đủ tham số
    public Giangvien(String maDD, String hoTen, String GioiTinh, String donVi, int soCT) {
        super(maDD, hoTen, GioiTinh);
        this.donVi = donVi;
        this.soCT = soCT;
    }

    // Getters và setters
    public String getDonVi() {
        return donVi;
    }

    public void setDonVi(String donVi) {
        this.donVi = donVi;
    }

    public int getSoCT() {
        return soCT;
    }

    public void setSoCT(int soCT) {
        this.soCT = soCT;
    }

    @Override
    public String Xetthuong() {
        if (soCT > 10) {
            return "Khen thưởng";
        }
        return "Không khen thưởng";
    }
}
