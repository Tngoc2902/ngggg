package Java.OOP.ThoXay;

import java.io.Serializable;
import java.util.Scanner;

public class ThoHo extends ThoXay implements Serializable {
    private String loaiThoHo;
    private int HeSoLuongThoHo;
    private String KyNangThoHo;
    private int SoGioLamViec;
    // private String MaNhanVienThoHo;
    
    private static final long serialVersionUID = 1L;
    //khởi tạo không tham số
    public ThoHo(){
        super();
        loaiThoHo = "";
        HeSoLuongThoHo = 0;
        SoGioLamViec = 0;
        KyNangThoHo = "";
        // MaNhanVienThoHo = "";
    }
    // Constructor
    public ThoHo(String loaiThoHo, int HeSoLuongThoHo,String KyNangThoHo,double SoGioLamViec) {
        super(); // kế thùa thuộc tính lớp cha
        this.loaiThoHo = loaiThoHo;
        this.HeSoLuongThoHo = HeSoLuongThoHo;
        this.KyNangThoHo = KyNangThoHo;
        this.SoGioLamViec = (int) SoGioLamViec;
        // this.MaNhanVienThoHo = MaNhanVienThoHo;
    }

    // public String getMaNhanVienThoHo() {
    //     return MaNhanVienThoHo;
    // }
    // public void setMaNhanVienThoHo(String MaNhanVienThoHo) {
    //     this.MaNhanVienThoHo = MaNhanVienThoHo;
    // }
    // Getter và setter
    public String getloaiThoHo() {
        return loaiThoHo;
    }
    public void setloaiThoHo( String loaiThoHo) {
        this.loaiThoHo = loaiThoHo;
    }

    public String getKyNangThoHo(){
        return KyNangThoHo;
    }
    public void setKyNangThoHo(String KyNangThoHo){
        this.KyNangThoHo = KyNangThoHo;
    }

    public int getHeSoLuongThoHo(){
        return HeSoLuongThoHo;
    }
    public void setHeSoLuongThoHo( int HeSoLuongThoHo){
        this.HeSoLuongThoHo = HeSoLuongThoHo;
    }

    public float SoGioLamViec(){
        return SoGioLamViec;
    }
    public void setSoGioLamViec(float SoGioLamViec){
        this.SoGioLamViec = (int) SoGioLamViec;
    }


// Phương thức hiển thị thông tin riêng của thợ hồ
    @Override
    public void NhapthongTin(){
        super.NhapthongTin();
        @SuppressWarnings("resource")
        Scanner scanner = new Scanner(System.in);

        // System.out.print("Nhap ma nhan vien: ");
        // while (!scanner.hasNextInt()){
        //     System.out.println("Nhap ma nhan vien khong hop le. Vui long nhap lai.");
        //     System.out.print("Nhap ma nhan vien: ");
        //     scanner.next();
        // }
        // MaNhanVienThoHo = scanner.nextLine();
        
        //xu lý ngoại lệ loại hình thợ hồ
        do{
            try{
                System.out.print("Nhap loai hinh tho ho: ");
                loaiThoHo = scanner.nextLine();
                // Kiểm tra thêm các điều kiện khác nếu cần
                if (loaiThoHo.isEmpty())  throw new Exception("Khong duoc de trong.");
                // Kiểm tra tên chỉ chứa chữ cái và khoảng trắng
                if (!loaiThoHo.matches("[a-zA-Z\\s]+"))   throw new Exception("Chi duoc chua chu cai va khoang trang.");
                break; // Thoát vòng lặp nếu nhập đúng
            }
            catch (Exception e) {
                System.out.println("Loi: " + e.getMessage());
            }
        }while(true);

        // xử lý ngoai lệ kỹ nang thợ hồ
        do{
            try{
                System.out.print("Nhap ky nang tho ho: ");
                KyNangThoHo = scanner.nextLine();
                // Kiểm tra thêm các điều kiện khác nếu cần
                if (KyNangThoHo.isEmpty())  System.out.println("Khong duoc de trong.");
                // Kiểm tra tên chỉ chứa chữ cái và khoảng trắng
                if (!KyNangThoHo.matches("[a-zA-Z\\s]+"))   System.out.println("Chi duoc chua chu cai va khoang trang.");
                break; // Thoát vòng lặp nếu nhập đúng
            }
            catch (Exception e) {
                System.out.println("Loi: " + e.getMessage());
            }
        }while(true);

        //xu ly ngoai le he so luong
        System.out.print("Nhap he so luong: ");
        while (!scanner.hasNextInt()){
            System.out.println("Nhap he so luong khong hop le. Vui long nhap lai.");
            System.out.print("Nhap he so luong: ");
            scanner.next();
        }
        HeSoLuongThoHo = scanner.nextInt();
        scanner.nextLine();


        //xư lý ngoại lệ nhập số giờ
        while (true)
        {
            try {
                System.out.print("Nhap so gio lam viec: ");
                this.SoGioLamViec = scanner.nextInt();
                if (this.SoGioLamViec < 0) {
                    System.out.println("So gio lam viec khong duoc am. Vui long nhap lai.");
                    continue; // Đưa vào vòng lặp lại
                }
                break; // Thoát khỏi vòng lặp nếu nhập thành công
            }
            catch (Exception e){
                System.out.println("So gio lam khong hop le,vui long nhap lai.");
                scanner.next(); // Xóa loi
            }
        }
    }



    //xuất thong tin tho hồ
    @Override
    public void hienThiThongTin() {
        super.hienThiThongTin(); // Hiển thị thông tin từ lớp cha
        // System.out.println("Ma nhan vien tho ho: " + MaNhanVienThoHo);
        System.out.println("Loai hinh tho ho: " + loaiThoHo);
        System.out.println("Ky nang hien co: " + KyNangThoHo);
        System.out.println("He so luong tho ho: " + HeSoLuongThoHo + " VND");
        System.out.println("So gio lam viec tho ho: "+ SoGioLamViec + " gio");
    }

    public String toString(){
        return super.toString() + ", Loai hinh tho ho: " + loaiThoHo + ", Ky nang hien co: " + KyNangThoHo +", He so luong tho ho: "+HeSoLuongThoHo+", So gio lam viec tho ho: "+SoGioLamViec;
    }
}
