package ThoXay;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class SaveFile {
    // Phương thức để lưu danh sách vào tệp văn bản
    public static void luuDanhSachVaoFileVanBan(String fileName, List<ThoXay> danhSachThoXay) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (ThoXay tho : danhSachThoXay) {
                writer.write(tho.toString());
                writer.newLine();
            }
        } catch (IOException e) {
        }
    }

     // Phương thức để đọc danh sách từ tệp văn bản
    public static List<ThoXay> taiDanhSachTuFileVanBan(String fileName) {
        List<ThoXay> danhSachNhanVienThoXay = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Giả sử bạn có phương thức parseToThoXay để phân tích chuỗi thành đối tượng ThoXay
                ThoXay tho = parseToThoXay(line);
                danhSachNhanVienThoXay.add(tho);
            }
        } catch (IOException e) {
            System.err.println("Lỗi khi tải dữ liệu từ tệp: " + e.getMessage());
        }
        return danhSachNhanVienThoXay;
    }

    // Cài đặt phương thức để ghi dữ liệu vào tệp nhị phân
    public static void ghiFileNhiPhan(String fileName, List<ThoXay> danhSach) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(danhSach);
        } catch (IOException e) {
            System.err.println("Lỗi khi ghi dữ liệu vào tệp nhị phân: " + e.getMessage());
        }
    }

    // Cài đặt phương thức để đọc dữ liệu từ tệp nhị phân
    public static List<ThoXay> docFileNhiPhan(String fileName) throws IOException, ClassNotFoundException {
        List<ThoXay> danhSachNhanVienThoXay;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            danhSachNhanVienThoXay = (List<ThoXay>) ois.readObject();
        }
        return danhSachNhanVienThoXay;
    }

    // Phương thức để chuyển đổi chuỗi thành đối tượng ThoXay (cần cài đặt thêm)
//    private static ThoXay parseToThoXay(String line) {
//        // Cài đặt logic để phân tích chuỗi thành đối tượng ThoXay
//        // Ví dụ: sử dụng dòng được phân tách bằng dấu phẩy
//        String[] parts = line.split(",");
//        // Giả sử ThoXay có constructor tương ứng
////        return new ThoXay(parts[0], parts[1], parts[2], Integer.parseInt(parts[3]), Integer.parseInt(parts[4]), Double.parseDouble(parts[5]), parts[6]);
//    }

    static void luuDanhSachVaoFileVanBan(String fileName) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private static ThoXay parseToThoXay(String line) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
