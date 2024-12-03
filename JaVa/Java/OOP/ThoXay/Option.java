package Java.OOP.ThoXay;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;


public class Option extends ThoXay {
        private static ArrayList<ThoXay> danhSachNhanVienThoXay = new ArrayList<>();
            @SuppressWarnings("resource")
            public void OptionEmployee () throws IOException, ClassNotFoundException{
                Scanner scanner = new Scanner(System.in);
                int n ;
    
                do{
                    System.out.println("\n--- QUAN LY NHAN VIEN THO XAY ---");
                    System.out.println("1. Them nhan vien tho ho");
                    System.out.println("2. Them them nhan vien tho moc");
                    System.out.println("3. Hien thi danh sach Nhan vien");
                    System.out.println("4. Xoa Nhan vien");
                    System.out.println("5. Tim kiem nhan vien");
                    System.out.println("6. Luu danh sach vao tep nhi phan");
                    System.out.println("7. Doc danh sach tu tep nhi phan");
                    System.out.println("8. Luu danh sach vao tep van ban");
                    System.out.println("9. Doc danh sach tu tep van ban");
                    System.out.println("0. Thoat");
                    System.out.print("Nhap lua chon: ");
    
                    while ((!scanner.hasNextInt()) || scanner.hashCode() < 10) {
                        System.out.println("Vui long nhap so nguyen. Nhap lai!");
                        System.out.print("Nhap lua chon cua ban: ");
                        scanner.nextLine();
                    }
                    n = scanner.nextInt();
                    switch (n) {
                        case 1:
                            ThoHo thoHo = new ThoHo();
                            thoHo.NhapthongTin();
                            danhSachNhanVienThoXay.add(thoHo);
                            System.out.println("Da them nhan vien Tho Ho vao danh sach.");
                            break;
    
                        case 2:
                            ThoMoc thoMoc = new ThoMoc();
                            thoMoc.NhapthongTin();
                            danhSachNhanVienThoXay.add(thoMoc);
                            System.out.println("Da them nhan vien Tho Moc vao danh sach.");
                            break;
    
                        case 3:
                            System.out.println("\n--- DANH SACH NHAN VIEN---");
                            for (int i = 0; i < danhSachNhanVienThoXay.size(); i++) {
                                System.out.println("\nNhan Vien " + (i + 1) + ":");
                                danhSachNhanVienThoXay.get(i).hienThiThongTin();
                            }
                            break;
    
                        case 4:
                            System.out.print("Nhap so thu tu Nhan Vien can xoa: ");
                            int index = scanner.nextInt();
                            if (index > 0 && index <= danhSachNhanVienThoXay.size()) {
                                danhSachNhanVienThoXay.remove(index - 1);
                                System.out.println("Da xoa nhan vien thu " + index + " khoi danh sach.");
                            } else {
                                System.out.println("So thu tu khong hop le.");
                            }
                        break;
    
                        case 5:
                            scanner.nextLine(); // Xóa bộ đệm
                            System.out.print("Nhap ten Nhan Vien can tim: ");
                            String tenNhanVienTim = scanner.nextLine();
                            boolean found = false;
                            for (ThoXay thoXay : danhSachNhanVienThoXay) {
                                if (thoXay.gethoTen().equalsIgnoreCase(tenNhanVienTim)) {
                                    thoXay.hienThiThongTin();
                                    found = true;
                                }
                            }
                            if (!found) System.out.println("Khong tim thay Nhan Vien voi ten: " + tenNhanVienTim);
                            break;
    
                        case 6:
                            ghiFileNhiPhan("danhSachThoXay.dat");
                            break;
    
                        case 7:
                            docFileNhiPhan("danhSachThoXay.dat");
                            break;
        
                        case 8:
                            luuDanhSachVaoFileVanBan("danhSachThoXay.txt", null);
                            break;
    
                        case 9:
                            taiDanhSachTuFileVanBan("danhSachThoXay.txt");
                            break;
    
                        case 0:
                            System.out.println("Thoat chuong trinh.");
                            break;
                        default:
                            System.out.println("");
                            System.out.println("Nhap lai.");
                    }
                }while(n != 0);
            }
    
        // Ghi danh sách ThoXay vào tệp nhị phân
        public static void ghiFileNhiPhan(String tenFile) throws IOException {
            try(ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(tenFile));) {
                outputStream.writeObject(danhSachNhanVienThoXay);
                System.out.println("Da luu danh sach vao tep nhi phan.");
            } catch (IOException e) {
                System.out.println("Loi khi luu vao file nhi phan: " + e.getMessage());
            }
        }
    
        // Đọc danh sách ThoXay từ tệp nhị phân
        @SuppressWarnings("unchecked")
        public static void docFileNhiPhan(String tenFile) throws IOException, ClassNotFoundException {
            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(tenFile));
            danhSachNhanVienThoXay = (ArrayList<ThoXay>) objectInputStream.readObject();
            objectInputStream.close();
        }
    
        // Ghi danh sách tho xay vào tệp văn bản
        public static void luuDanhSachVaoFileVanBan(String filePath, Object object) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
                for (ThoXay thoXay : danhSachNhanVienThoXay) {
                    writer.write(thoXay.toString());
                    writer.newLine();
                }
                System.out.println("Da luu danh sach vao file van ban.");
            } catch (IOException e) {
                System.out.println("Loi khi luu vao file van ban: " + e.getMessage());
            }
        }
        // Đọc danh sách tho xay từ tệp văn bản
        public static ArrayList<ThoXay> taiDanhSachTuFileVanBan(String filePath) {
            ArrayList<ThoXay> danhSachNhanVienThoXay = new ArrayList<>();
            try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] data = line.split(", ");
                    if (data.length == 8) {
                        ThoHo thoHo = new ThoHo(data[0], Integer.parseInt(data[1]), data[2], Double.parseDouble(data[3]));
                        danhSachNhanVienThoXay.add(thoHo);
                    } else if (data.length == 9) {
                        ThoMoc thoMoc = new ThoMoc(data[0], Integer.parseInt(data[1]), data[2], data[3], Double.parseDouble(data[4]));
                        danhSachNhanVienThoXay.add(thoMoc);
                    } else  System.out.println("Du lieu khong hop le: " + line);
            }
        } catch (IOException e) {
            System.out.println("Loi khi tai file tu van ban: " + e.getMessage());
        }
        return danhSachNhanVienThoXay;
    }
}
