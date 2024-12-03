package DAGV;

import java.util.List;
import java.util.Scanner;

public class Test_findGV {
    public static void dogetGV(String donVi, int soCT) {
        XLGV xlgv = new XLGV();
        List<Giangvien> dsGV = xlgv.getGV(donVi, soCT);

        System.out.println("Danh sách giảng viên thuộc " + donVi +
                " có số công trình >= " + soCT + ":");
        System.out.println("MaDD | Họ tên | Giới tính | Đơn vị | Số CT | Xét thưởng");
        System.out.println("------------------------------------------------");

        for (Giangvien gv : dsGV) {
            System.out.printf("%s | %s | %s | %s | %d | %s%n",
                    gv.getMaDD(), gv.getHoTen(), gv.getGioiTinh(),
                    gv.getDonVi(), gv.getSoCT(), gv.Xetthuong());
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhập đơn vị công tác: ");
        String donVi = sc.nextLine();
        System.out.print("Nhập số công trình tối thiểu: ");
        int soCT = sc.nextInt();

        dogetGV(donVi, soCT);
        sc.close();
    }
}