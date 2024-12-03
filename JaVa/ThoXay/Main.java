package ThoXay;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static List<ThoXay> ThoXayList = new ArrayList<>();

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                System.out.println("\nChọn một tùy chọn:");
                System.out.println("1. Thêm Thợ Hồ(Thương Mại)");
                System.out.println("2. Thêm Thợ Mộc(Ngân Hàng Nhà Nước)");
                System.out.println("3. Hiển thị danh sách");
                System.out.println("4. Xóa ");
                System.out.println("5. Tìm kiếm ");
                System.out.println("6. Lưu danh sách vào tệp văn bản");
                System.out.println("7. Tải danh sách từ tệp văn bản");
                System.out.println("8. Lưu danh sách vào tệp nhị phân");
                System.out.println("9. Tải danh sách từ tệp nhị phân");
                System.out.println("10. Thoát");
                System.out.print("Lựa chọn của bạn: ");

                int choice = scanner.nextInt();
                scanner.nextLine(); // Clear the newline

                switch (choice) {
                    case 1 -> {
                        ThoHo thoHo = new ThoHo();
                        thoHo.nhapThongTin(scanner);
                        if (!isMaNhanVienExists(ThoXayList, thoHo.getmaNhanVien())) {
                            ThoXayList.add(thoHo);
                            System.out.println("Thợ hồ đã được thêm thành công.");
                        } else {
                            System.out.println("Mã đã tồn tại. Vui lòng nhập mã khác.");
                        }
                    }

                    case 2 -> {
                        ThoMoc thoMoc = new ThoMoc();
                        thoMoc.nhapThongTin(scanner);
                        if (!isMaNhanVienExists(ThoXayList, thoMoc.getmaNhanVien())) {
                            ThoXayList.add(thoMoc);
                            System.out.println("Thợ Mộc đã được thêm thành công.");
                        } else {
                            System.out.println("Mã đã tồn tại. Vui lòng nhập mã khác.");
                        }
                    }

                    case 3 -> hienThiDanhSach();

                    case 4 -> {
                        System.out.print("Nhập mã cần xóa: ");
                        String maXoa = scanner.nextLine();
                        ThoXayList.removeIf(thoXay -> thoXay.getmaNhanVien().equalsIgnoreCase(maXoa));
                        System.out.println("Nhân viên đã được xóa (nếu tồn tại).");
                    }

                    case 5 -> {
                        System.out.print("Nhập mã cần tìm: ");
                        String maTim = scanner.nextLine();
                        boolean found = false;
                        for (ThoXay thoXay : ThoXayList) {
                            if (thoXay.getmaNhanVien().equalsIgnoreCase(maTim)) {
                                thoXay.xuatThongTin();
                                found = true;
                            }
                        }
                        if (!found) {
                            System.out.println("Nhân viên không tồn tại.");
                        }
                    }

                    case 6 -> {
                        try {
                            luuDanhSachText("nhanVienData.txt", ThoXayList);
                        } catch (IOException e) {
                            System.out.println("Lỗi khi lưu dữ liệu vào tệp văn bản: " + e.getMessage());
                        }
                    }
                    case 7 -> {
                        try {
                            ThoXayList = taiDanhSachText("nhanVienData.txt");
                        } catch (FileNotFoundException e) {
                            System.out.println("Không tìm thấy tệp: " + e.getMessage());
                        } catch (IOException e) {
                            System.out.println("Lỗi khi đọc dữ liệu từ tệp văn bản: " + e.getMessage());
                        }
                    }

                    case 8 -> {
                        try {
                            luuDanhSachBinary("nhanVienData.bin", ThoXayList);
                        } catch (IOException e) {
                            System.out.println("Lỗi khi lưu dữ liệu vào tệp nhị phân: " + e.getMessage());
                        }
                    }
                    case 9 -> {
                        try {
                            ThoXayList = taiDanhSachBinary("nhanVienData.bin");
                            hienThiDanhSach(); // Hiển thị danh sách sau khi tải từ file nhị phân
                        } catch (IOException e) {
                            System.out.println("Lỗi khi tải dữ liệu từ tệp nhị phân: " + e.getMessage());
                        }
                    }

                    case 10 -> {
                        System.out.println("Thoát chương trình.");
                        return;
                    }

                    default -> System.out.println("Lựa chọn không hợp lệ. Vui lòng thử lại.");
                }
            }
        } catch (Exception e) {
            System.out.println("Có lỗi xảy ra: " + e.getMessage());
        }
    }

    // Phương thức hiển thị danh sách ngân hàng
    private static void hienThiDanhSach() {
        if (ThoXayList.isEmpty()) {
            System.out.println("Không có ngân hàng nào trong danh sách.");
        } else {
            System.out.println("\nDanh sách ngân hàng:");
            for (ThoXay thoXay : ThoXayList) {
                thoXay.xuatThongTin();
                System.out.println("-------------------");
            }
        }
    }

    // Lưu danh sách vào tệp văn bản
    static void luuDanhSachText(String fileName, List<ThoXay> list) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (ThoXay thoXay : list) {
                writer.write(thoXay.toTextFormat());
                writer.newLine();
            }
        }
    }

    static List<ThoXay> taiDanhSachText(String fileName) throws IOException {
        List<ThoXay> loadedList = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                ThoXay thoXay = parseBank(line);
                if (thoXay != null) {
                    loadedList.add(thoXay);
                }
            }
        }
        return loadedList;
    }

    static void luuDanhSachBinary(String fileName, List<ThoXay> list) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(list);
        }
    }

    @SuppressWarnings("unchecked")
    static List<ThoXay> taiDanhSachBinary(String fileName) throws IOException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            return (List<ThoXay>) ois.readObject();
        } catch (ClassNotFoundException e) {
            throw new IOException("Lỗi khi đọc dữ liệu: " + e.getMessage());
        }
    }

    // Phân tích dòng từ file văn bản thành đối tượng ngân hàng
    static ThoXay parseBank(String line) {
        try {
          
            String[] parts = line.split(",");

            if (parts.length < 6) {
                System.out.println("Dòng không hợp lệ: " + line);
                return null;
            }

            // Kiểm tra loại ngân hàng trước tiên
            String loaiNhanVien = parts[0].trim();
            String maNhanVien = parts[1].trim();
            int namKinhNghiem = Integer.parseInt(parts[2].trim());

            if (loaiNhanVien.equalsIgnoreCase("Thợ Hồ")) {
                int soChiNhanh = Integer.parseInt(parts[3].trim());
                String chinhSachQuanLy = parts[4].trim();
                String diaChi = parts[5].trim();

                return new ThoHo(maNhanVien, namKinhNghiem, soChiNhanh, chinhSachQuanLy, diaChi);
            } else if (loaiNhanVien.equalsIgnoreCase("Thợ Mộc")) {
                int soChiNhanh = Integer.parseInt(parts[3].trim());
                String chinhSachQuanLy = parts[4].trim();
                String diaChi = parts[5].trim();
                return new ThoMoc(maNhanVien, namKinhNghiem, soChiNhanh, chinhSachQuanLy, diaChi);
            }

            System.out.println("Loại nhân viên không hợp lệ: " + loaiNhanVien);
            return null;

        } catch (NumberFormatException e) {
            System.out.println("Lỗi định dạng số trong dòng: " + line + " - " + e.getMessage());
            return null;
        }
    }

    // Kiểm tra mã ngân hàng đã tồn tại hay chưa
    private static boolean isMaNhanVienExists(List<ThoXay> list, String maNhanVien) {
        return list.stream().anyMatch(thoXay -> thoXay.getmaNhanVien().equalsIgnoreCase(maNhanVien));
    }

}
