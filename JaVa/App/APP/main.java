package App.APP;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class main {
	@SuppressWarnings("resource")
	protected static void main(String[] args) {
		List<BoDoi> DanhSach = new ArrayList<BoDoi>();
		int luachon;
		Scanner scaner = new Scanner(System.in);
		do
		{
			System.out.println("1. Thêm lính bộ vào danh sách.");
			System.out.println("2. Thêm lính thủy vào danh sách.");
			System.out.println("3. Hiển thị danh sách lính bộ.");
			System.out.println("4. Hiển thị danh sách lính thủy.");
			System.out.println("5. Hiển thị danh sách bộ đội.");
			System.out.println("6. Xóa thông tin theo tên.");
			System.out.println("7. Tìm kiếm thông tin theo tên.");
			System.out.println("0. Thoát.");
			System.out.println("Vui lòng nhập lựa chọn của bạn: ");
			luachon = scaner.nextInt();
			switch (luachon) {
				case 1:
					LinhBo linhBo = new LinhBo();
					linhBo.nhapThongTin();
					DanhSach.add(linhBo);
					break;
				case 2: 
					LinhThuy linhThuy = new LinhThuy();
					linhThuy.nhapThongTin();
					DanhSach.add(linhThuy);
					break;
				case 3:
					int ok = 0;
					for (BoDoi boDoi : DanhSach) {
						if(boDoi instanceof LinhBo)
						{
							boDoi.xuatThongTin();
							ok = 1;
						}
					}
					System.out.println("Danh sách lính bộ trống!");
					break;
				case 4: 
					ok = 0;
					for(BoDoi boDoi : DanhSach)
					{
						if(boDoi instanceof LinhThuy)
						{
							boDoi.xuatThongTin();
							ok = 1;
						}
					}
					System.out.println("Danh sách lính thủy trống!");
					break;
				case 5: 
					if(DanhSach.size()==0)
					{
						System.out.println("Danh sách trống!");
					}
					else
					{
						System.out.println("Danh sách bộ đội: ");
						for(BoDoi boDoi : DanhSach)
						{
							boDoi.xuatThongTin();
						}
					}
					break;
				case 6: 
					String ten;
					System.out.println("Nhập tên bộ đội muốn xóa: ");
					ten = scaner.next();
					ok = 0;
					Iterator<BoDoi> iterator = DanhSach.iterator();
					while (iterator.hasNext()) {
					    BoDoi boDoi = iterator.next();
					    if (ten.equals(boDoi.getTen())) {
					        ok = 1;
					        iterator.remove(); // Xóa phần tử an toàn thông qua Iterator
					    }
					}
					if(ok == 0)
					{
						System.out.println("Không tìm thấy thông tin của bộ đội này!");
					}else
					{
						System.out.println("Xóa thành công!");
					}
					break;
				case 7: 
					System.out.println("Nhập tên bộ đội muốn tìm: ");
					String tentk = scaner.next();
					ok = 0;
					for (BoDoi boDoi : DanhSach) {
						if(tentk.equals(boDoi.getTen()))
						{
							ok = 1;
							boDoi.xuatThongTin();
						}
					}
					if(ok == 0)
					{
						System.out.println("Không tìm thấy thông tin của bộ đội này!");
					}
					break;
			}
		}while(luachon!=0);
	}
}
