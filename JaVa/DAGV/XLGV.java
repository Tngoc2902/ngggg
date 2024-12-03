package DAGV;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class XLGV {
    public Connection getCon() throws SQLException {
        return DriverManager.getConnection(
            "jdbc:sqlserver://LAPTOP-2GT0TEME:1433;databaseName=QLCB;encrypt=false",
            "sa",
            "12345"
        );
    }
    public List<Giangvien> getGV(String donVi, int soCT) {
        List<Giangvien> dsGV = new ArrayList<>();
        try {
            Connection con = getCon();
            String sql = "SELECT * FROM tbGiangvien WHERE Donvi = ? AND Soct >= ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, donVi);
            stmt.setInt(2, soCT);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Giangvien gv = new Giangvien(
                        rs.getString("MaDD"),
                        rs.getString("Hoten"),
                        rs.getString("GT"),
                        rs.getString("Donvi"),
                        rs.getInt("Soct")
                );
                dsGV.add(gv);
            }
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dsGV;
    }
}