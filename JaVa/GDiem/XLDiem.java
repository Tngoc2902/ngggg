package GDiem;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class XLDiem {
    
    
    public Connection getCon() throws SQLException {
        return DriverManager.getConnection(
            "jdbc:sqlserver://LAPTOP-2GT0TEME:1433;databaseName=qlghe3;encrypt=false",
            "sa",
            "12345"
        );
    }

    public List<Hocvien> getHV(){
        List<Hocvien> list = new ArrayList<>();
        try (Connection con = getCon();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM tbHocvien")) {
            
            while (rs.next()) {
                Hocvien Hv = new Hocvien(
                    rs.getString("MaHV"),
                    rs.getString("Hoten"),
                    rs.getString("Lop"),
                    rs.getFloat("Diem")
                );
                list.add(Hv);
            }
        } catch (SQLException e) {
        }
        return list;
    }
    
    public boolean insertHV(Hocvien hv) {
        String sql = "INSERT INTO tbHocvien VALUES (?, ?, ?, ?)";
        try (Connection con = getCon();
             PreparedStatement pstmt = con.prepareStatement(sql)) {
            
            pstmt.setString(1, hv.getMaHV());
            pstmt.setString(2, hv.getHoten());
            pstmt.setString(3, hv.getLop());
            pstmt.setFloat(4, hv.getDiem());
            
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
