package Java.OOP.ThoXay;

import java.io.*;
import java.util.ArrayList;

public class Selection {

    // Lưu danh sách kỹ sư vào tệp nhị phân
    public static void luuDanhSachVaoFileNhiPhan(String filePath, ArrayList<ThoXay> danhSachKySu) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
            oos.writeObject(danhSachKySu);
            System.out.println("Da luu danh sach vao file nhi phan.");
        } catch (IOException e) {
            System.out.println("Loi khi luu vao file nhi phan: " + e.getMessage());
        }
    }

    // Tải danh sách kỹ sư từ tệp nhị phân
    @SuppressWarnings("unchecked")
    public static ArrayList<ThoXay> taiDanhSachTuFileNhiPhan(String filePath) {
        ArrayList<ThoXay> danhSachKySu = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
            danhSachKySu = (ArrayList<ThoXay>) ois.readObject();
            System.out.println("Da tai danh sach tu file nhi phan.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Loi khi tai tu file nhi phan: " + e.getMessage());
        }
        return danhSachKySu;
    }

    // Lưu danh sách kỹ sư vào tệp văn bản
    public static void luuDanhSachVaoFileVanBan(String filePath, ArrayList<ThoXay> danhSachKySu) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (ThoXay thoXay : danhSachKySu) {
                writer.write(thoXay.toString());
                writer.newLine();
            }
            System.out.println("Da luu danh sach vao file van ban.");
        } catch (IOException e) {
            System.out.println("Loi khi luu vao file van ban: " + e.getMessage());
        }
    }

    // Tải danh sách kỹ sư từ tệp văn bản
    public static ArrayList<ThoXay> taiDanhSachTuFileVanBan(String filePath) {
        ArrayList<ThoXay> danhSachKySu = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(", ");
                if (data.length == 4) {
                    ThoHo thoHo = new ThoHo();
                    danhSachKySu.add(thoHo);
                } else if (data.length == 5) {
                    ThoMoc thoMoc = new ThoMoc();
                    danhSachKySu.add(thoMoc);
                }
            }
            System.out.println("Da tai danh sach tu file van ban.");
        } catch (IOException | NumberFormatException e) {
            System.out.println("Loi khi tai tu file van ban: " + e.getMessage());
        }
        return danhSachKySu;
    }
}
