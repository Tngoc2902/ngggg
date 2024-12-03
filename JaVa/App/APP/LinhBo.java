package App.APP;
import java.util.InputMismatchException;
import java.util.Scanner;

import javax.accessibility.AccessibleRelationSet;

@SuppressWarnings("unused")
public class LinhBo extends BoDoi {
	private int solanluyentap;

	// Hàm khởi tạo không tham số
	public LinhBo() {
		super();
	}

	// Hàm khởi tạo có tham số
	public LinhBo(String ten, int tuoi, int solanluyentap) {
		super(ten, tuoi);
		this.solanluyentap = solanluyentap;
	}

	public int getSolanluyentap() {
		return solanluyentap;
	}

	public void setSolanluyentap(int solanluyentap) {
		this.solanluyentap = solanluyentap;
	}

	// Hàm nhập thông tin (ghi đè)
	@SuppressWarnings("resource")
	@Override
	public void nhapThongTin() {
		Scanner scanner = new Scanner(System.in);
		try {
			super.nhapThongTin();
			System.out.print("Nhập số lần luyện tập: ");
			this.solanluyentap = scanner.nextInt();
			if (this.solanluyentap < 0) {
				throw new IllegalArgumentException("Số lần luyện tập phải lớn hơn 0, vui lòng nhập lại!");
			}
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
			nhapThongTin();
		} catch (InputMismatchException e) {
			System.out.println("Sai định dạng dữ liệu, vui lòng nhập lại.");
			System.out.println("\n");
			nhapThongTin(); 
		}
	}

	// Hàm xuất thông tin (ghi đè)
	@Override
	public void xuatThongTin() {
		super.xuatThongTin();
		System.out.println("Số lần luyện tập: " + getSolanluyentap());
		System.out.println("Đây là thông tin của một lính bộ.");
	}

	public void hanhquan() {
		System.out.println("Đang hành quân trên đường bộ");
	}

	public void chiendau() {
		System.out.println("Đang chiến đấu trên đường bộ");
	}
}
