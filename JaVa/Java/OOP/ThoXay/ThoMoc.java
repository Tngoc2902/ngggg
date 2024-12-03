package Java.OOP.ThoXay;

import java.io.Serializable;
import java.util.Scanner;

public class ThoMoc extends ThoXay implements Serializable {
    private String loaiGo;
    private String KyNangMoc;
    private int HeSoLuongThoMoc;
    protected String MaNhanVienThoMoc;
    private int ThoiGianLamViecThoMoc;
    
    private static final long serialVersionUID = 1L;
    public ThoMoc(String data, int i, String data2, String data3, double d){
        super();
        loaiGo = "";
        KyNangMoc = "";
        HeSoLuongThoMoc = 0;
        this.MaNhanVienThoMoc = "";
        this.ThoiGianLamViecThoMoc = 0;
    }
    
    public ThoMoc() {
        super(); // kế thùa thuộc tính lớp cha
        ThoMoc thoMoc = this;
        thoMoc.loaiGo = loaiGo;
        thoMoc.HeSoLuongThoMoc = HeSoLuongThoMoc;
        thoMoc.KyNangMoc = KyNangMoc;
        thoMoc.MaNhanVienThoMoc = MaNhanVienThoMoc;
        thoMoc.ThoiGianLamViecThoMoc = ThoiGianLamViecThoMoc;
    }


    public float getThoiGianLamViecThoMoc() {
        return ThoiGianLamViecThoMoc;
    }
    public void setThoiGianLamViecThoMoc(int thoiGianLamViecThoMoc) {
        ThoiGianLamViecThoMoc = thoiGianLamViecThoMoc;
    }


    public String getloaiGo(){
        return loaiGo;
    }
    public void setloaiGo(String loaiGo){
        this.loaiGo = loaiGo;
    }


    public String getMaNhanVienThoMoc(){
        return MaNhanVienThoMoc;
    }
    public void setMaNhanVienThoMocK(String MaNhanVienThoMoc){
        this.MaNhanVienThoMoc = MaNhanVienThoMoc;
    }


    public String getKyNangMoc(){
        return KyNangMoc;
    }
    public void setKyNangMoc( String KyNangMoc){
        this.KyNangMoc = KyNangMoc;
    }


    public int getHeSoLuongThoMoc(){
        return HeSoLuongThoMoc;
    }
    public void setLuongThoMoc(int HeSoLuongThoMoc){
        this.HeSoLuongThoMoc = HeSoLuongThoMoc;
    }


    @SuppressWarnings("resource")
    @Override
    public void NhapthongTin(){
        super.NhapthongTin();
        Scanner scanner = new Scanner(System.in);


        
        System.out.print("Nhap ma nhan vien: ");
        while (!scanner.hasNextInt()){
            System.out.println("Nhap ma nhan vien khong hop le. Vui long nhap lai.");
            System.out.print("Nhap ma nhan vien: ");
            scanner.next();
        }
        MaNhanVienThoMoc = scanner.nextLine();

        //xu ly ngoai le loai go
        do{
            try{
                System.out.print("Nhap loai go: ");
                loaiGo = scanner.nextLine();
                // Kiểm tra thêm các điều kiện khác nếu cần
                if (loaiGo.isEmpty()){
                    System.out.println("Loai go khong duoc de trong.");
                    continue;
                }
                // Kiểm tra tên chỉ chứa chữ cái và khoảng trắng
                if (!loaiGo.matches("[a-zA-Z\\s]+")) {
                    System.out.println("Loai go chi duoc chu cai va khoang trang.");
                    continue;
                }
                break; // Thoát vòng lặp nếu nhập đúng
            }
            catch (Exception e) {
                System.out.println("Loi: " + e.getMessage());
            }
        }while(true);


        //xu ly ngoai le ky nang moc
        do{
            try{
                System.out.print("Nhap loai ky nang moc: ");
                KyNangMoc = scanner.nextLine();
                // Kiểm tra thêm các điều kiện khác nếu cần
                if (KyNangMoc.isEmpty())  {
                    System.out.println("Ky nang khong duoc de trong.");
                    continue;
                }
                // Kiểm tra tên chỉ chứa chữ cái và khoảng trắng
                if (!KyNangMoc.matches("[a-zA-Z\\s]+")){
                    System.out.println("Ky nang chi duoc chu cai va khoang trang.");
                    continue;
                };
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
        HeSoLuongThoMoc = scanner.nextInt();


        System.out.print("Nhap so gio lam: ");
        while (!scanner.hasNextInt()){
            System.out.println("Nhap so gio lam khong hop le. Vui long nhap lai.");
            System.out.print("Nhap so gio lam: ");
            scanner.next();
        }
        ThoiGianLamViecThoMoc = scanner.nextInt();


    }
    @Override
    public void hienThiThongTin(){
        super.hienThiThongTin();
        System.out.println("Ma nhan vien: " + MaNhanVienThoMoc);
        System.out.println("Loai go: " + loaiGo);
        System.out.println("Ky nang moc: " + KyNangMoc);
        System.out.println("He so uong tho moc: " + HeSoLuongThoMoc+" VND");
        System.out.println("Thoi gian lam viec: "+ (int) ThoiGianLamViecThoMoc +" gio");
    }


    public String toString(){
        return super.toString() + ", Ma nhan vien:" + MaNhanVienThoMoc + ", Loai go: " + loaiGo +", Ky nang moc: "+KyNangMoc+", He so uong tho moc: "+HeSoLuongThoMoc+", Thoi gian lam viec: "+ ThoiGianLamViecThoMoc;
    }
}
