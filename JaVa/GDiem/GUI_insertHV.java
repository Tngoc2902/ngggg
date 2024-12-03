package GDiem;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class GUI_insertHV extends JFrame {
    private JTextField txtMaHV, txtHoten, txtDiem;
    private JComboBox<String> cboLop;
    private JTable tblHocvien;
    private DefaultTableModel tableModel;
    private final XLDiem xlDiem;
    private JPanel panelInput;
    
    public GUI_insertHV() {
        xlDiem = new XLDiem();
        setupGUI();
        loadData();
    }
    
    private void setupGUI() {
        setTitle("Quản lý điểm sinh viên");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        
        // Panel nhập liệu
        panelInput = new JPanel(new GridLayout(6, 2));
//        inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        txtMaHV = new JTextField();
        txtHoten = new JTextField();
        txtDiem = new JTextField();
        cboLop = new JComboBox<>(new String[]{"62TH1", "62PM1", "62PM2"});
        
        panelInput.add(new JLabel("Mã học viên:"));
        panelInput.add(txtMaHV);
        panelInput.add(new JLabel("Họ tên:"));
        panelInput.add(txtHoten);
        panelInput.add(new JLabel("Lớp:"));
        panelInput.add(cboLop);
        panelInput.add(new JLabel("Điểm:"));
        panelInput.add(txtDiem);
        
        // Button thêm
        JButton btnThem = new JButton("Thêm học viên");
        btnThem.addActionListener(e -> themHocVien());
        
        // Table
        String[] columnNames = {"Mã HV", "Họ tên", "Lớp", "Điểm", "Kết quả"};
        tableModel = new DefaultTableModel(columnNames, 0);
        tblHocvien = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(tblHocvien);
        
        // Layout
        add(panelInput, BorderLayout.NORTH);
        add(btnThem, BorderLayout.CENTER);
        add(scrollPane, BorderLayout.SOUTH);
        
        pack();
        setLocationRelativeTo(null);
    }
    
    private void loadData() {
        tableModel.setRowCount(0);
        List<Hocvien> list = xlDiem.getHV();
        for (Hocvien hv : list) {
            Object[] row = {
                hv.getMaHV(),
                hv.getHoten(),
                hv.getLop(),
                hv.getDiem(),
                hv.Ketqua()
            };
            tableModel.addRow(row);
        }
    }
    
    private void themHocVien() {
        try {
            String maHV = txtMaHV.getText();
            String hoTen = txtHoten.getText();
            String lop = (String) cboLop.getSelectedItem();
            float diem = Float.parseFloat(txtDiem.getText());
            
            Hocvien hv = new Hocvien(maHV, hoTen, lop, diem);
            if (xlDiem.insertHV(hv)) {
                loadData();
                JOptionPane.showMessageDialog(this, "Thêm học viên thành công!");
                // Clear input fields
                txtMaHV.setText("");
                txtHoten.setText("");
                txtDiem.setText("");
            } else {
                JOptionPane.showMessageDialog(this, "Thêm học viên thất bại!");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập thông tin");
        }
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new GUI_insertHV().setVisible(true);
        });
    }
}