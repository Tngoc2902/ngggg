package ThoXay;

import java.io.Serializable;
import java.util.Scanner;

@SuppressWarnings("serial")
public class ThoHo extends ThoXay implements Serializable {
    private int soChiNhanh;
    private String chinhSachQuanLy;
    private String diaChi;
    private int soDu;

    public ThoHo() {
    	super();
        this.soChiNhanh = 0;
        this.chinhSachQuanLy = "Chưa xác định";
        this.soDu = 0;
        this.diaChi = "Chưa xác định";
    }

    public ThoHo(String maNhanVien, int namKinhNghiem, int soChiNhanh, String chinhSachQuanLy, String diaChi) {
        super(maNhanVien, namKinhNghiem);
        this.soChiNhanh = soChiNhanh;
        this.chinhSachQuanLy = chinhSachQuanLy;
        this.diaChi = diaChi;
    }

    @Override
    public void nhapThongTin(Scanner scanner) {
        super.nhapThongTin(scanner); // Nhập thông tin chung

        System.out.print("Nhập số chi nhánh: ");
        this.soChiNhanh = Integer.parseInt(scanner.nextLine());

        System.out.print("Nhập chính sách quản lý: ");
        this.chinhSachQuanLy = scanner.nextLine();

        System.out.print("Nhập địa chỉ: ");
        this.diaChi = scanner.nextLine();
    
    }

    @Override
    public void xuatThongTin() {
        System.out.println("Thợ Hồ:");
        super.xuatThongTin(); // Xuất thông tin chung
        super.xuatThongTin(); // Xuất thông tin chung
        System.out.println("Số chi nhánh: " + soChiNhanh);
        System.out.println("Chính sách quản lý: " + chinhSachQuanLy);
        System.out.println("Địa chỉ: " + diaChi);
    }

    @Override
    public String toTextFormat() {
        return "Thợ Hồ," + super.toTextFormat() + "," + soChiNhanh + "," + diaChi + "," + chinhSachQuanLy;
    }
    @Override
    public Object[] toRowData() {
        return new Object[]{"Thợ Hồ",getmaNhanVien(), getnamKinhNghiem(), soChiNhanh, diaChi, chinhSachQuanLy};
    }


    @Override
    public String xuatThongTin1() {
        return "Thợ Hồ - Mã: " + getmaNhanVien() + ", Năm kinh nghiệm: " + getnamKinhNghiem()
                + ", Số chi nhánh: " + String.valueOf(soChiNhanh) 
                + ", Địa chỉ: " + String.valueOf(diaChi)
                + ", Chính sách quản lý: " + String.valueOf(chinhSachQuanLy);
    }


    @Override
    public void guiTien(int soTien) {
        if (soTien > 0) {
            soDu += soTien;
            System.out.println("Gửi thành công: " + soTien);
        } else {
            System.out.println("Số tiền gửi phải lớn hơn 0.");
        }
    }

    @Override
    public void rutTien(int soTien) {
        if (soTien > 0 && soTien <= soDu) {
            soDu -= soTien;
            System.out.println("Rút thành công: " + soTien);
        } else {
            System.out.println("Số tiền rút không hợp lệ.");
        }
    }

    @Override
    public int kiemTraSoDu() {
        return soDu;
    }
}
