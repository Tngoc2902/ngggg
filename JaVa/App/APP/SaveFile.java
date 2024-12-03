package App.APP;

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
import java.util.List;

public class SaveFile {
	// Hàm lưu danh sách vào tệp nhị phân
	public static void luuDanhSachNhiPhan(List<BoDoi> danhSach) {
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("bodoi.dat"))) {
			oos.writeObject(danhSach);
			System.out.println("Lưu danh sách thành công!");
		} catch (IOException e) {
			System.out.println("Lỗi khi lưu danh sách: " + e.getMessage());
		}
	}

	// Hàm tải danh sách từ tệp nhị phân
	@SuppressWarnings("unchecked")
	public List<BoDoi> taiDanhSachNhiPhan() {
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("bodoi.dat"))) {
			return (List<BoDoi>) ois.readObject();
		} catch (IOException | ClassNotFoundException e) {
			System.out.println("Lỗi khi tải danh sách: " + e.getMessage());
			return new ArrayList<>();
		}
	}

	// Hàm lưu danh sách vào tệp văn bản
	public static void luuDanhSachVanBan(List<BoDoi> danhSach) {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter("bodoi.txt"))) {
			for (BoDoi boDoi : danhSach) {
				if (boDoi instanceof LinhThuy) {
					LinhThuy x = (LinhThuy) boDoi;
					writer.write("Đây là thông tin của linh thủy");
					writer.newLine();
					writer.write("Tên: " + x.getTen());
					writer.newLine();
					writer.write("Tuổi: " + x.getTuoi());
					writer.newLine();
					writer.write("Số km hành quân: " + x.getSokmhanhquan());
					writer.newLine();
				} else if (boDoi instanceof LinhBo) {
					LinhBo x = (LinhBo) boDoi;
					writer.write("Đây là thông tin của linh bộ");
					writer.newLine();
					writer.write("Tên: " + x.getTen());
					writer.newLine();
					writer.write("Tuổi: " + x.getTuoi());
					writer.newLine();
					writer.write("Số lần luyện tập: " + x.getSolanluyentap());
					writer.newLine();
				} else {
					writer.write("Đây là thông tin của bộ đội");
					writer.newLine();
					writer.write("Tên: " + boDoi.getTen());
					writer.newLine();
					writer.write("Tuổi: " + boDoi.getTuoi());
					writer.newLine();

				}
				// writer.newLine();
			}
			System.out.println("Lưu danh sách văn bản thành công!");
		} catch (IOException e) {
			System.out.println("Lỗi khi lưu danh sách: " + e.getMessage());
		}
	}

// Hàm tải danh sách từ tệp văn bản
	public static List<BoDoi> taiDanhSachVanBan() {
		List<BoDoi> danhSach = new ArrayList<>();

		try (BufferedReader reader = new BufferedReader(new FileReader("bodoi.txt"))) {
			String line;
			BoDoi boDoi = null;

			while ((line = reader.readLine()) != null) {
				// Bỏ qua dòng trống
				if (line.trim().isEmpty()) {
					continue;
				}

				// Kiểm tra loại lính và đọc thông tin
				if (line.startsWith("Đây là thông tin của linh bộ")) {
					// Đọc thông tin lính bộ
					line = reader.readLine(); // Tên
					if (line == null)
						break;
					String ten = line.substring(5); // Lấy tên

					line = reader.readLine(); // Tuổi
					if (line == null)
						break;
					int tuoi = Integer.parseInt(line.substring(6)); // Lấy tuổi

					line = reader.readLine(); // Số lần luyện tập
					if (line == null)
						break;
					int solanluyentap = Integer.parseInt(line.substring(18)); // Lấy số lần luyện tập

					boDoi = new LinhBo(ten, tuoi, solanluyentap);
					danhSach.add(boDoi);

				} else if (line.startsWith("Đây là thông tin của linh thủy")) {
					// Đọc thông tin lính thủy
					line = reader.readLine(); // Tên
					if (line == null)
						break;
					String ten = line.substring(5); // Lấy tên

					line = reader.readLine(); // Tuổi
					if (line == null)
						break;
					int tuoi = Integer.parseInt(line.substring(6)); // Lấy tuổi

					line = reader.readLine(); // Số km hành quân
					if (line == null)
						break;
					int sokmhanhquan = Integer.parseInt(line.substring(17)); // Lấy số km hành quân

					boDoi = new LinhThuy(ten, tuoi, sokmhanhquan);
					danhSach.add(boDoi);

				} else if (line.startsWith("Đây là thông tin của bộ đội")) {
					// Đọc thông tin bộ đội cơ bản
					line = reader.readLine(); // Tên
					if (line == null)
						break;
					String ten = line.substring(5); // Lấy tên

					line = reader.readLine(); // Tuổi
					if (line == null)
						break;
					int tuoi = Integer.parseInt(line.substring(6)); // Lấy tuổi

					boDoi = new BoDoi(ten, tuoi);
					danhSach.add(boDoi);
				}
			}
			System.out.println("Tải danh sách văn bản thành công!");
		} catch (IOException e) {
			System.out.println("Lỗi khi tải danh sách: " + e.getMessage());
		} catch (NumberFormatException e) {
			System.out.println("Lỗi định dạng số: " + e.getMessage());
		}
		return danhSach;
	}
}
