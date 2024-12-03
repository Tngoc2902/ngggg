package App.APP;
import java.util.InputMismatchException;
import java.util.Scanner;
public class LinhThuy extends BoDoi {
	private int sokmhanhquan;
    // Hàm khởi tạo không tham số
    public LinhThuy() {
        super();
    }

    // Hàm khởi tạo có tham số
    public LinhThuy(String ten, int tuoi,int sokmhanhquan) {
        super(ten, tuoi);
        this.sokmhanhquan = sokmhanhquan;
    }

    public int getSokmhanhquan() {
		return sokmhanhquan;
	}
    

	public void setSokmhanhquan(int sokmhanhquan) {
		this.sokmhanhquan = sokmhanhquan;
	}

	// Hàm nhập thông tin (ghi đè)
    @SuppressWarnings("resource")
    @Override
    public void nhapThongTin() {
        Scanner scanner = new Scanner(System.in);
		try {
			super.nhapThongTin();
	        System.out.print("Nhập số lần km hành quân: ");
	        this.sokmhanhquan = scanner.nextInt(); 
			if (this.sokmhanhquan < 0 || this.tuoi <= 0) {
				throw new IllegalArgumentException("Số km hành quân phải lớn hơn 0 hoặc số tuổi phải lớn 0, vui lòng nhập lại!");
			}
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
			nhapThongTin();
		} catch (InputMismatchException e) {
			System.out.println("Sai định dạng dữ liệu, vui lòng nhập lại.");
			System.out.println("\n");
			nhapThongTin(); //
		}
    }

    // Hàm xuất thông tin (ghi đè)
    @Override
    public void xuatThongTin() {
        super.xuatThongTin();
        System.out.println("Số km hành quân: " + getSokmhanhquan());
        System.out.println("Đây là thông tin của một lính thủy.");
    }
    
    
    // interface
    public void hanhquan()
    {
    	System.out.println("Đang hành quân trên đường thủy");
    }
    
    public void chiendau()
    {
    	System.out.println("Đang chiến đấu trên đường thủy");
    }
}
