package DAGV;

public class Person {
    protected String maDD;
    protected String hoTen;
    protected String GioiTinh;

    // Constructor mặc định
    public Person() {
        this.maDD = "";
        this.hoTen = "";
        this.GioiTinh = "";
    }

    // Constructor đầy đủ tham số
    public Person(String maDD, String hoTen, String GioiTinh) {
        this.maDD = maDD;
        this.hoTen = hoTen;
        this.GioiTinh = GioiTinh;
    }

    // Các getters và setters
    public String getMaDD() {
        return maDD;
    }

    public void setMaDD(String maDD) {
        this.maDD = maDD;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getGioiTinh() {
        return GioiTinh;
    }

    public void setGioiTinh(String GioiTinh) {
        this.GioiTinh = GioiTinh;
    }

    public String Xetthuong() {
        return "Không có thông tin khen thưởng";
    }
}
