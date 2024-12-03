package App.APP;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unused")
public class main1 {
	@SuppressWarnings("static-access")
	public static void main(String[] args) {
		List<BoDoi> danhSachBoDoi = new ArrayList<>();
		SaveFile saveFile = new SaveFile();
        // Thêm các đối tượng vào danh sách
        LinhBo boDoi1 = new LinhBo("Nguyen Van A", 25,12);
        LinhThuy boDoi2 = new LinhThuy("Tran Van B", 30,14);
        BoDoi boDoi3 = new BoDoi("Nguyễn Văn c",25);
        LinhThuy boDoi4 = new LinhThuy("Tran Van D", 30,14);
        danhSachBoDoi.add(boDoi1);
        danhSachBoDoi.add(boDoi2);
        danhSachBoDoi.add(boDoi3);
        danhSachBoDoi.add(boDoi4);

        // Lưu danh sách vào tệp nhị phân
//        try {
//            saveFile.luuDanhSachNhiPhan(danhSachBoDoi);
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }
        saveFile.luuDanhSachNhiPhan(danhSachBoDoi);
        saveFile.luuDanhSachVanBan(danhSachBoDoi);
        
        
        try {
        	ArrayList<BoDoi> x = (ArrayList<BoDoi>) saveFile.taiDanhSachNhiPhan();;
        	for (BoDoi boDoi : x) {
        		if(boDoi instanceof LinhThuy)
        		{
        			LinhThuy x1 = (LinhThuy) boDoi;
        			x1.xuatThongTin();
        		}else if(boDoi instanceof LinhBo)
        		{
        			LinhBo x1 = (LinhBo) boDoi;
        			x1.xuatThongTin();
        		}else 
        		{
        			boDoi.xuatThongTin();
        		}
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
        saveFile.taiDanhSachVanBan();
//        // tải danh sách từ tệp văn bản
//        try {
//        	ArrayList<BoDoi> x = (ArrayList<BoDoi>) saveFile.taiDanhSachVanBan();
//        	for (BoDoi boDoi : x) {
//        		if(boDoi instanceof LinhThuy)
//        		{
//        			LinhThuy x1 = (LinhThuy) boDoi;
//        			x1.xuatThongTin();
//        		}else if(boDoi instanceof LinhBo)
//        		{
//        			LinhBo x1 = (LinhBo) boDoi;
//        			x1.xuatThongTin();
//        		}else 
//        		{
//        			boDoi.xuatThongTin();
//        		}
//			}
//		} catch (Exception e) {
//			System.out.println(e.getMessage());
//		}
	}
}
