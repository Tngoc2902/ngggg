package ThoXay;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

@SuppressWarnings("unused")
public final class ConnectDataBase {
    private Connection cn;
    
    public ConnectDataBase() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url = "jdbc:sqlserver://LAPTOP-2GT0TEME:1433;databaseName=ThoXay;encrypt=true;trustServerCertificate=true;";
            cn = DriverManager.getConnection(url, "sa", "12345");
            System.out.println("Kết nối thành công!");

                // Only proceed if the connection is not null
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Connection failed: " + e.getMessage());
        }
    }
    public void addThoHo(String maNhanVien, int namKinhNghiem, int soChiNhanh, String chinhSachQuanLy, String DiaChiStr){
        try {
		String sql = "INSERT INTO LINHBO(maNhanVien, namKinhNghiem,soChiNhanh , DiaChiStr , SoGioLam, HeSoLuong) VALUES (?,?,?,?,?,?);";
                    var ps = cn.prepareStatement(sql);
                    ps.setString(1, maNhanVien);
                    ps.setInt(2, namKinhNghiem);
                    ps.setInt(3, soChiNhanh);
                    ps.setString(4, chinhSachQuanLy);
                    ps.setString(5, DiaChiStr);
                    int rs = ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
	
	public void addThoMoc(String maNhanVien, int namKinhNghiem, int soChiNhanh, String chinhSachQuanLy, String diaChi)
	{
		try {
                        String sql = "INSERT INTO ThoMoc(maNhanVien,namKinhNghiem,soChiNhanh,chinhSachQuanLy,diaChi) VALUES (?,?,?,?,?);";
			var ps = cn.prepareStatement(sql);
			ps.setString(1, maNhanVien);
			ps.setInt(2, namKinhNghiem);
			ps.setInt(3, soChiNhanh);
                        ps.setString(4, chinhSachQuanLy);
                        ps.setString(5, diaChi);
			int rs = ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void xoaThoHo(String maNhanVien)
	{
		try {
			String sql = "DELETE FROM ThoHo WHERE maNhanVien = ?";
			var ps = cn.prepareStatement(sql);
			ps.setString(1, maNhanVien);
			int rs = ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void xoaThoMoc(String name)
	{
		try {
			String sql = "DELETE FROM ThoMoc WHERE maNhanVien = ?";
			var ps = cn.prepareStatement(sql);
			ps.setString(1, name);
			int rs = ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	public ArrayList<ThoXay> getAllNhanVien()
	{
		ArrayList<ThoXay> ThoXayList = new ArrayList<>();
		try {
			String sql = "SELECT * FROM ThoHo";
			Statement st = cn.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while(rs.next())
			{
				String maNhanVien = rs.getString("Ma");
				int namKinhNghiem = rs.getInt("Kinh Nghiem");
				int soChiNhanh = rs.getInt("So Chi Nhanh");
                                String chinhSachQuanLy = rs.getString("Chinh Sach Quan Ly");
                                String diaChi = rs.getString("Dia Chi");
				ThoHo thoHo = new ThoHo(maNhanVien, namKinhNghiem, soChiNhanh ,diaChi, chinhSachQuanLy);
				ThoXayList.add(thoHo);
			}
			sql = "SELECT * FROM ThoMoc";
			rs = st.executeQuery(sql); 
			while(rs.next())
			{
				String maNhanVien = rs.getString("Ma");
				int namKinhNghiem = rs.getInt("Kinh Nghiem");
				int soChiNhanh = rs.getInt("So Chi Nhanh");
                                String diaChi = rs.getString("Dia Chi");
                                String chinhSachQuanLy = rs.getString("Chinh Sach Quan Ly");
				ThoMoc thoMoc = new ThoMoc(maNhanVien, namKinhNghiem, soChiNhanh ,diaChi, chinhSachQuanLy);
				ThoXayList.add(thoMoc);
			}
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return ThoXayList;
	}
	public void capnhat(String name, int tuoi, int optional, String type, String newname, Double HeSoLuong)
	{
		try {
			String sql;
			if(type.equals("ThoXay"))
			{
                            sql = "UPDATE ThoXay SET TEN = ?, TUOI = ? , SKM = ? WHERE TEN = ?";
			}else
                        {
                            sql = "UPDATE LINHBO SET TEN = ?, TUOI = ? , SLTL = ? WHERE TEN = ?";
			}
			var ps = cn.prepareStatement(sql);
			ps.setString(1, newname);
			ps.setInt(2, tuoi);
			ps.setInt(3, optional);
			ps.setString(4, name);
			int rs = ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}	
	}
    
    void capnhat(String maNhanVien, int namKinhNghiem, int soChiNhanh, String diaChi,String chinhSachQuanLy) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    public static void main(String[] args) {
        ConnectDataBase cn = new ConnectDataBase();
        // Remove this line since it doesn't apply to a database connection class
        // cn.setVisible(true);
    }

}