package App.APP;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

@SuppressWarnings("unused")
public class ConnectDataBase {
	private Connection cn;
	public ConnectDataBase()
	{
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			String url = "jdbc:sqlserver://DESKTOP-MB701U4:1433;databaseName=BODOI;trustServerCertificate=true;";
			cn = DriverManager.getConnection(url, "sa", "123456");
			System.out.println("Kết nối thành công!");
			String sql = "";
			Statement st = cn.createStatement();
			int rs = st.executeUpdate(sql);

	
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		xoaLinhBo("a");
		xoaLinhThuy("a");
	}
	public void addLinhBo(String name, int tuoi, int sltl)
	{
		try {
			String sql = "INSERT INTO LINHBO(TEN,TUOI,SLTL) VALUES (?,?,?);";
			var ps = cn.prepareStatement(sql);
			ps.setString(1, name);
			ps.setInt(2, tuoi);
			ps.setInt(3, sltl);
			int rs = ps.executeUpdate();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void addLinhThuy(String name, int tuoi, int skm)
	{
		try {
			String sql = "INSERT INTO LINHTHUY(TEN,TUOI,SKM) VALUES (?,?,?);";
			var ps = cn.prepareStatement(sql);
			ps.setString(1, name);
			ps.setInt(2, tuoi);
			ps.setInt(3, skm);
			int rs = ps.executeUpdate();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void xoaLinhBo(String name)
	{
		try {
			String sql = "DELETE FROM LINHBO WHERE TEN = ?";
			var ps = cn.prepareStatement(sql);
			ps.setString(1, name);
			int rs = ps.executeUpdate();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void xoaLinhThuy(String name)
	{
		try {
			String sql = "DELETE FROM LINHTHUY WHERE TEN = ?";
			var ps = cn.prepareStatement(sql);
			ps.setString(1, name);
			int rs = ps.executeUpdate();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	public ArrayList<BoDoi> getAllBoDoi()
	{
		ArrayList<BoDoi> danhsach = new ArrayList<BoDoi>();
		try {
			String sql = "SELECT * FROM LINHBO";
			Statement st = cn.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while(rs.next())
			{
				String ten = rs.getString("TEN");
				int tuoi = rs.getInt("TUOI");
				int sllt = rs.getInt("SLTL");
				LinhBo x = new LinhBo(ten,tuoi,sllt);
				danhsach.add(x);
			}
			sql = "SELECT * FROM LINHTHUY";
			rs = st.executeQuery(sql); 
			while(rs.next())
			{
				String ten = rs.getString("TEN");
				int tuoi = rs.getInt("TUOI");
				int skm = rs.getInt("SKM");
				LinhThuy x = new LinhThuy(ten,tuoi,skm);
				danhsach.add(x);
			}
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return danhsach;
	}
	public void capnhat(String name,int tuoi, int optional,String type,String newname)
	{
		try {
			String sql;
			if(type.equals("LINHTHUY"))
			{
				sql = "UPDATE LINHTHUY SET TEN = ?, TUOI = ? , SKM = ? WHERE TEN = ?";
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
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}	
	}
}


