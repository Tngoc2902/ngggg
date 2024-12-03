package ThoXay;

import java.io.Serializable;
import java.util.Scanner;

@SuppressWarnings("serial")
public abstract class ThoXay implements HanhDongNganHang, Serializable {
    private String maNhanVien;
    private int namKinhNghiem;
    
    public ThoXay() {
    	 this.maNhanVien = "Chưa xác định";
         this.namKinhNghiem = 0;
    }

    public ThoXay(String maNhanVien, int namKinhNghiem) {
        this.maNhanVien = maNhanVien;
        this.namKinhNghiem = namKinhNghiem;
    }  


    public int getnamKinhNghiem() {
        return namKinhNghiem;
    }

    public void setnamKinhNghiem(int namKinhNghiem) throws IllegalArgumentException {
        if (namKinhNghiem <= 0) {
            throw new IllegalArgumentException("Năm không hợp lệ!.");
        }
        this.namKinhNghiem = namKinhNghiem;
    }

    public String getmaNhanVien() {
        return maNhanVien;
    }

    public void setmaNhanVien(String maNhanVien) {
        this.maNhanVien = maNhanVien;
    }
    
    public abstract Object[] toRowData();

    public abstract String xuatThongTin1();

    @Override
    public void nhapThongTin(Scanner scanner) {
        System.out.print("Nhập mã nhân viên: ");
        this.maNhanVien = scanner.nextLine();

        System.out.print("Nhập năm kinh nghiệm: ");
        while (true) {
            try {
                this.namKinhNghiem = Integer.parseInt(scanner.nextLine());
                if (this.namKinhNghiem < 0) {
                    System.out.print("Năm kinh nghiệm không hợp lệ. Vui lòng nhập lại: ");
                } else {
                    break; // Năm hợp lệ
                }
            } catch (NumberFormatException e) {
                System.out.print("Lỗi định dạng. Vui lòng nhập lại: ");
            }
        }
    }

    @Override
    public void xuatThongTin() {
        System.out.println("Mã nhân viên: " + maNhanVien);
        System.out.println("Năm kinh nghiệm: " + namKinhNghiem);
    }

    public String toTextFormat() {
        return maNhanVien + "," + namKinhNghiem;
    }


}
