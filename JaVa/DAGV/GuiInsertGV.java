package DAGV;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.Vector;

public class GuiInsertGV extends JFrame {
    private JTextField txtMaDD, txtHoTen, txtDonVi, txtSoCT;
    private JComboBox<String> cboGT;
    private JButton btnThem, btnXoa, btnSua, btnThoat;
    private JTable tblGiangVien;
    private DefaultTableModel tableModel;
    private XLGV xlgv;

    public GuiInsertGV() {
        xlgv = new XLGV(); // Khởi tạo đối tượng XLGV

        setTitle("Quản lý Giảng Viên");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setLocationRelativeTo(null);
        // Khởi tạo các components
        JLabel lblTitle = new JLabel("THÔNG TIN GIẢNG VIÊN", JLabel.CENTER);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 20));
        lblTitle.setBounds(200, 20, 400, 30);
        add(lblTitle);

        // Panel nhập liệu
        JPanel pnlInput = new JPanel(null);
        pnlInput.setBounds(20, 60, 750, 200);
        pnlInput.setBorder(BorderFactory.createTitledBorder("Nhập thông tin"));

        // Labels
        JLabel lblMaDD = new JLabel("Mã định danh:");
        lblMaDD.setBounds(20, 30, 100, 25);
        JLabel lblHoTen = new JLabel("Họ tên:");
        lblHoTen.setBounds(20, 65, 100, 25);
        JLabel lblGioiTinh = new JLabel("Giới tính:");
        lblGioiTinh.setBounds(20, 100, 100, 25);
        JLabel lblDonVi = new JLabel("Đơn vị:");
        lblDonVi.setBounds(400, 30, 100, 25);
        JLabel lblSoCT = new JLabel("Số công trình:");
        lblSoCT.setBounds(400, 65, 100, 25);

        // Text fields
        txtMaDD = new JTextField();
        txtMaDD.setBounds(120, 30, 200, 25);
        txtHoTen = new JTextField();
        txtHoTen.setBounds(120, 65, 200, 25);
        cboGT = new JComboBox<>(new String[]{"Nam", "Nữ"});
        cboGT.setBounds(120, 100, 200, 25);
        txtDonVi = new JTextField();
        txtDonVi.setBounds(500, 30, 200, 25);
        txtSoCT = new JTextField();
        txtSoCT.setBounds(500, 65, 200, 25);

        // Buttons
        btnThem = new JButton("Thêm");
        btnThem.setBounds(150, 150, 100, 30);
        btnSua = new JButton("Sửa");
        btnSua.setBounds(270, 150, 100, 30);
        btnXoa = new JButton("Xóa");
        btnXoa.setBounds(390, 150, 100, 30);
        btnThoat = new JButton("Thoát");
        btnThoat.setBounds(510, 150, 100, 30);

        // Add components to input panel
        pnlInput.add(lblMaDD);
        pnlInput.add(lblHoTen);
        pnlInput.add(lblGioiTinh);
        pnlInput.add(lblDonVi);
        pnlInput.add(lblSoCT);
        pnlInput.add(txtMaDD);
        pnlInput.add(txtHoTen);
        pnlInput.add(cboGT);
        pnlInput.add(txtDonVi);
        pnlInput.add(txtSoCT);
        pnlInput.add(btnThem);
        pnlInput.add(btnSua);
        pnlInput.add(btnXoa);
        pnlInput.add(btnThoat);

        add(pnlInput);

        // Table
        String[] columnNames = {"Mã ĐD", "Họ tên", "Giới tính", "Đơn vị", "Số CT", "Xét thưởng"};
        tableModel = new DefaultTableModel(columnNames, 0);
        tblGiangVien = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(tblGiangVien);
        scrollPane.setBounds(20, 280, 750, 250);
        add(scrollPane);

        // Thêm sự kiện cho các nút
        btnThem.addActionListener(e -> themGiangVien());
        btnSua.addActionListener(e -> suaGiangVien());
        btnXoa.addActionListener(e -> xoaGiangVien());
        btnThoat.addActionListener(e -> System.exit(0));

        // Sự kiện khi click vào table
        tblGiangVien.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = tblGiangVien.getSelectedRow();
                if (row >= 0) {
                    txtMaDD.setText(tblGiangVien.getValueAt(row, 0).toString());
                    txtHoTen.setText(tblGiangVien.getValueAt(row, 1).toString());
                    cboGT.setSelectedItem(tblGiangVien.getValueAt(row, 2).toString());
                    txtDonVi.setText(tblGiangVien.getValueAt(row, 3).toString());
                    txtSoCT.setText(tblGiangVien.getValueAt(row, 4).toString());
                }
            }
        });

        // Load dữ liệu ban đầu
        loadData();
    }

    private void loadData() {
        tableModel.setRowCount(0);
        try {
            Connection conn = xlgv.getCon();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM tbGiangvien");

            while (rs.next()) {
                String xetThuong = rs.getInt("Soct") > 10 ? "Khen thưởng" : "Không khen thưởng";
                Vector<String> row = new Vector<>();
                row.add(rs.getString("MaDD"));
                row.add(rs.getString("Hoten"));
                row.add(rs.getString("GioiTinh"));
                row.add(rs.getString("Donvi"));
                row.add(rs.getString("Soct"));
                row.add(xetThuong);
                tableModel.addRow(row);
            }
            conn.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi khi tải dữ liệu: " + e.getMessage());
        }
    }

    private void themGiangVien() {
        try {
            if (txtMaDD.getText().trim().isEmpty() ||
                    txtHoTen.getText().trim().isEmpty() ||
                    txtDonVi.getText().trim().isEmpty() ||
                    txtSoCT.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin!");
                return;
            }

            Connection conn = xlgv.getCon();
            String sql = "INSERT INTO tbGiangvien (MaDD, Hoten, GioiTinh, Donvi, Soct) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, txtMaDD.getText().trim());
            pstmt.setString(2, txtHoTen.getText().trim());
            pstmt.setString(3, cboGT.getSelectedItem().toString());
            pstmt.setString(4, txtDonVi.getText().trim());
            pstmt.setInt(5, Integer.parseInt(txtSoCT.getText().trim()));

            pstmt.executeUpdate();
            conn.close();

            JOptionPane.showMessageDialog(this, "Thêm giảng viên thành công!");
            loadData();
            clearFields();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi khi thêm: " + e.getMessage());
        }
    }

    private void suaGiangVien() {
        try {
            int row = tblGiangVien.getSelectedRow();
            if (row < 0) {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn giảng viên cần sửa!");
                return;
            }

            Connection conn = xlgv.getCon();
            String sql = "UPDATE tbGiangvien SET Hoten=?, GioiTinh=?, Donvi=?, Soct=? WHERE MaDD=?";
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, txtHoTen.getText().trim());
            pstmt.setString(2, cboGT.getSelectedItem().toString());
            pstmt.setString(3, txtDonVi.getText().trim());
            pstmt.setInt(4, Integer.parseInt(txtSoCT.getText().trim()));
            pstmt.setString(5, txtMaDD.getText().trim());

            pstmt.executeUpdate();
            conn.close();

            JOptionPane.showMessageDialog(this, "Cập nhật thành công!");
            loadData();
            clearFields();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi khi sửa: " + e.getMessage());
        }
    }

    private void xoaGiangVien() {
        try {
            int row = tblGiangVien.getSelectedRow();
            if (row < 0) {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn giảng viên cần xóa!");
                return;
            }

            int confirm = JOptionPane.showConfirmDialog(this,
                    "Bạn có chắc muốn xóa giảng viên này?",
                    "Xác nhận xóa",
                    JOptionPane.YES_NO_OPTION);

            if (confirm == JOptionPane.YES_OPTION) {
                Connection conn = xlgv.getCon();
                String sql = "DELETE FROM tbGiangvien WHERE MaDD=?";
                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, txtMaDD.getText().trim());

                pstmt.executeUpdate();
                conn.close();

                JOptionPane.showMessageDialog(this, "Xóa thành công!");
                loadData();
                clearFields();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi khi xóa: " + e.getMessage());
        }
    }
    private void clearFields() {
        txtMaDD.setText("");
        txtHoTen.setText("");
        cboGT.setSelectedIndex(0);
        txtDonVi.setText("");
        txtSoCT.setText("");
        txtMaDD.requestFocus();
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        SwingUtilities.invokeLater(() -> {
            new GuiInsertGV().setVisible(true);
        });
    }
}
