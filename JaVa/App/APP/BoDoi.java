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
import java.io.Serializable;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

@SuppressWarnings("unused")
public class BoDoi implements Serializable{
    // Các biến thành viên
    protected String ten;
    protected int tuoi;
    
    // Hàm khởi tạo không tham số
    public BoDoi() {}

    // Hàm khởi tạo có tham số
    public BoDoi(String ten, int tuoi) {
        this.ten = ten;
        this.tuoi = tuoi;
    }

    // Getters và Setters
    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public int getTuoi() {
        return tuoi;
    }

    public void setTuoi(int tuoi) {
        this.tuoi = tuoi;
    }

    // Hàm nhập thông tin
    @SuppressWarnings("resource")
    public void nhapThongTin() {
        Scanner sc = new Scanner(System.in);
        try {
        	System.out.print("Nhập tên: ");
            this.ten = sc.nextLine();
            System.out.print("Nhập tuổi: ");
            this.tuoi = sc.nextInt();
            if(this.tuoi <= 0)
            {
            	throw new IllegalArgumentException("Số tuổi phải lớn hơn 0, vui lòng nhập lại!");
            }
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
			nhapThongTin();
		} catch(InputMismatchException e)
        {
			System.out.println("Lỗi định dạng dữ liệu, vui lòng nhập lại!");
			nhapThongTin();
        }
    }

    // Hàm xuất thông tin
    public void xuatThongTin() {
        System.out.println("Tên: " + ten);
        System.out.println("Tuổi: " + tuoi);
    }
    

    
    public void hanhquan()
    {
    	System.out.println("Đang hành quân");
    }
    
    public void chiendau()
    {
    	System.out.println("Đang chiến đấu");
    }
}



