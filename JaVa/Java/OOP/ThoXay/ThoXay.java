package Java.OOP.ThoXay;

import java.util.Scanner;

public class ThoXay {
    protected String hoTen;
    private int Tuoi;
    private int kinhNghiem;
    private String DiaChi;
    

    public ThoXay(){
        this.hoTen = "";
        this.Tuoi = 0;
        this.DiaChi = "";
        this.kinhNghiem = 0;
    }

    // Constructor
    public ThoXay(String hoTen,int Tuoi, String DiaChi, int kinhNghiem) {
        this.hoTen = hoTen;
        this.Tuoi = Tuoi;
        this.kinhNghiem = kinhNghiem;
        this.DiaChi = DiaChi;
    }

    // Getter và setter
    public String gethoTen() {
        return hoTen;
    }
    public void sethoTen(String hoTen){
        this.hoTen = hoTen;
    }
    

    public int getTuoi(){
        return Tuoi;
    }
    public void setTuoi(int Tuoi){
        this.Tuoi = Tuoi;
    }
    public String getDiaChi(){
        return DiaChi;
    }
    public void setDiaChi(String DiaChi){
        this.DiaChi = DiaChi;
    }

    public int getKinhNghiem() {
        return kinhNghiem;
    }
    public void setKinhNghiem(int kinhNghiem) throws IllegalArgumentException {
        if (kinhNghiem < 0) {
                throw new IllegalArgumentException("Nam kinh nghiem khong hop le!.");
            }
            this.kinhNghiem = kinhNghiem;
    }


    @SuppressWarnings("resource")
    public void NhapthongTin(){
        Scanner scanner = new Scanner(System.in);

        // xử lý ngoại lệ khi nhập tên
        do{
            try{
                System.out.print("Nhap ten: ");
                hoTen = scanner.nextLine();
                // Kiểm tra thêm các điều kiện khác nếu cần
                if (hoTen.isEmpty()){
                    System.out.println("Ten khong duoc de trong.");
                    continue;
                }
                // Kiểm tra tên chỉ chứa chữ cái và khoảng trắng
                if (!hoTen.matches("[a-zA-Z\\s]+")){
                    System.out.println("Ten chi duoc chu cai va khoang trang.");
                    continue;
                }
                break; // Thoát vòng lặp nếu nhập đúng
            }
            catch (Exception e) {
                System.out.println("Loi: " + e.getMessage());
            }
        }while(true);


        //xu ly ngoai le tuoi
        System.out.print("Nhap tuoi: ");
        while(!scanner.hasNextInt()){
            System.out.println("Vui long nhap so nguyen. Nhap lai.");
            System.out.print("Nhap tuoi: ");
            scanner.next();
        }
        Tuoi = scanner.nextInt();
        scanner.nextLine();

        do{
            try{
                System.out.print("Nhap dia chi: ");
                DiaChi = scanner.nextLine();
                // Kiểm tra thêm các điều kiện khác nếu cần
                if (DiaChi.isEmpty()){
                    System.out.println("Dia chi khong duoc de trong.");
                    continue;
                }
                // Kiểm tra tên chỉ chứa chữ cái và khoảng trắng
                if (!DiaChi.matches("[a-zA-Z\\s]+")) {
                    System.out.println("Dia chi chi duoc chu cai va khoang trang.");
                    continue;
                }
                break; // Thoát vòng lặp nếu nhập đúng
            }
            catch (Exception e) {
                System.out.println("Loi: " + e.getMessage());
            }
        }while(true);

        // xử lý ngoại lê kinh nghiệm
        while (true) {
            try {
                System.out.print("Nhap nam kinh nghiem: ");
                int nam = scanner.nextInt();
                setKinhNghiem(nam);// Gọi phương thức để kiểm tra năm
                break;  // Thoát vòng lặp nếu nhập đúng
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage() + " Vui long nhap lai.");
                scanner.nextLine();  // Đọc bỏ giá trị lỗi để tiếp tục nhập lại
            }
        }
        scanner.nextLine();  // Đọc bỏ dòng còn lại
    }
    
    // Phương thức hiển thị thông tin chung
    
    public void hienThiThongTin() {
        System.out.println("________________________");
        System.out.println(" ");
        
        // System.out.println("Ten nghe nghiep: " + tenNgheNghiep);
        System.out.println("Ten: "+ hoTen);
        System.out.println("Tuoi: " + Tuoi);
        System.out.println("Dia chi: "+ DiaChi);
        System.out.println("Kinh nghiem: " + kinhNghiem + " nam");
    }

    public String toString(){
        return  "Ten: " + hoTen + ", Tuoi: " + Tuoi + ", Dia chi: " + DiaChi + ", Kinh nghiem: "+  kinhNghiem;
    }
}
