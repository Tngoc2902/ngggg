package ThoXay;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("serial")
public class WorkerManagament extends JFrame {
    private JTextField txtMaNhanVien;
    private JTextField txtNamKinhNghiem;

    private JTextField txtSoChiNhanh;

    private JTextField txtChinhSachQuanLy;
    private JTextField txtDiaChi;
    private JComboBox<String> comboBoxLoaiNhanVien;
    private JTable table;
    private DefaultTableModel model;
    private List<ThoXay> ThoXayList;
    private JPanel panelInput;
    private ConnectDataBase cndb;
    public WorkerManagament() {
        ThoXayList = new ArrayList<>();
        this.cndb = new ConnectDataBase(); // Khởi tạo đối tượng cndb

        // Khởi tạo giao diện
        setTitle("Quản Lý Nhân Viên");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        
        // Create menu bar
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        // Create File menu
        JMenu fileMenu = new JMenu("File");
        menuBar.add(fileMenu);
       

        // Add menu items
        JMenuItem saveTextItem = new JMenuItem("Save as Text");
        JMenuItem loadTextItem = new JMenuItem("Load from Text");
        JMenuItem saveBinaryItem = new JMenuItem("Save as Binary");
        JMenuItem loadBinaryItem = new JMenuItem("Load from Binary");

        fileMenu.add(saveTextItem);
        fileMenu.add(loadTextItem);
        fileMenu.addSeparator();
        fileMenu.add(saveBinaryItem);
        fileMenu.add(loadBinaryItem);

        // Add action listeners to menu items
        saveTextItem.addActionListener(e -> saveToTextFile("nhanVienData.txt"));
        loadTextItem.addActionListener(e -> loadFromTextFile("nhanVienData.txt"));
        saveBinaryItem.addActionListener(e -> saveToBinaryFile("nhanVienData.bin"));
        loadBinaryItem.addActionListener(e -> loadFromBinaryFile("nhanVienData.bin"));

        panelInput = new JPanel(new GridLayout(7, 2));

        panelInput.add(new JLabel("Loại Nhân Viên:"));
        comboBoxLoaiNhanVien = new JComboBox<>(new String[]{"Thợ Mộc", "Thợ Hồ"});
        panelInput.add(comboBoxLoaiNhanVien);

        panelInput.add(new JLabel("Mã Nhân Viên:"));
        txtMaNhanVien = new JTextField();
        panelInput.add(txtMaNhanVien);

        panelInput.add(new JLabel("Năm Kinh Nghiệm:"));
        txtNamKinhNghiem = new JTextField();
        panelInput.add(txtNamKinhNghiem);

        panelInput.add(new JLabel("Số Chi Nhánh:"));
        txtSoChiNhanh = new JTextField();
        panelInput.add(txtSoChiNhanh);
        
        txtDiaChi = new JTextField();
        txtChinhSachQuanLy = new JTextField();
        txtDiaChi = new JTextField();

        add(panelInput, BorderLayout.NORTH);

        // Bảng hiển thị ngân hàng
        model = new DefaultTableModel(new String[]{"Loại Nhân Viên", "Mã Nhân Viên", "Năm Kinh Nghiệm","Số Chi Nhánh","Chính Sách Quản Lý","Địa Chỉ"}, 0);
        table = new JTable(model);
        add(new JScrollPane(table), BorderLayout.CENTER);

        // Panel nút
        JPanel panelButtons = new JPanel();
        JButton btnAdd = new JButton("Thêm");
        JButton btnDelete = new JButton("Xóa");
        JButton btnSearch = new JButton("Tìm kiếm");
        JButton btnLoad = new JButton("Load");
        JButton btnThemData = new JButton("Them Data");
        JButton btnSuaData = new JButton("Sua Data");
        JButton btnTimData = new JButton("Tim Data");
        panelButtons.add(btnAdd);
        panelButtons.add(btnDelete);
        panelButtons.add(btnSearch);
        panelButtons.add(btnLoad);
        panelButtons.add(btnThemData);
        panelButtons.add(btnSuaData);
        panelButtons.add(btnTimData);
        add(panelButtons, BorderLayout.SOUTH);

        comboBoxLoaiNhanVien.addActionListener(e -> updateInputFieldsVisibility());
            
        


        // Sự kiện cho nút thêm
	btnAdd.addActionListener((ActionEvent e) -> {
                    addWorker();
        });

        // Sự kiện cho nút xóa
        btnDelete.addActionListener((ActionEvent e) -> {
            deleteWork();
        });
        
        // Lắng nghe sự kiện tìm kiếm
        btnSearch.addActionListener((ActionEvent e) -> {
            searchWorkerByCode();
        });
        btnLoad.addActionListener((ActionEvent e) -> {
            capnhat();
        }); 
        btnThemData.addActionListener((ActionEvent e) -> {
            capnhat();
        }); 
        
        btnSuaData.addActionListener((ActionEvent e) -> {
            loaddata();
        }); 
        btnTimData.addActionListener((ActionEvent e) -> {
            capnhat();
        }); 

        // Cập nhật trạng thái trường nhập liệu ban đầu
        updateInputFieldsVisibility();
    }



//    public void capnhat(String name, int age, int id, String position, String address) {
//        if (cndb != null) {
//            cndb.capnhat(name, age, id, position);
//        } else {
//            System.out.println("Database connection is not initialized!");
//        }
//    }
    
    private void updateInputFieldsVisibility() {

        // Xóa tất cả các thành phần hiện tại
        panelInput.removeAll();

        // Thêm lại các trường cố định
        panelInput.add(new JLabel("Loại Nhân Viên:"));
        panelInput.add(comboBoxLoaiNhanVien);
        panelInput.add(new JLabel("Mã Nhân Viên:"));
        panelInput.add(txtMaNhanVien);
        panelInput.add(new JLabel("Năm Kinh Nghiệm:"));
        panelInput.add(txtNamKinhNghiem);
        panelInput.add(new JLabel("Số Chi Nhánh:"));
        panelInput.add(txtSoChiNhanh);
        panelInput.add(new JLabel("Chính Sách Quản Lý:"));
        panelInput.add(txtChinhSachQuanLy);
        panelInput.add(new JLabel("Địa Chỉ:"));
        panelInput.add(txtDiaChi);

        // Cập nhật lại giao diện
        panelInput.revalidate();
        panelInput.repaint();
    }

    
private void capnhat() {
    int selectRow = table.getSelectedRow();

    // Check if a row is selected
    if (selectRow == -1) {
        JOptionPane.showMessageDialog(this, "Vui lòng chọn một hàng để cập nhật.");
        return;
    }

    String maNhanVien = (String) table.getValueAt(selectRow, 1); // Column 1 for Mã Nhân Viên

    // Validate input fields
    if (txtNamKinhNghiem.getText().isEmpty() || txtSoChiNhanh.getText().isEmpty()) {
        JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin.");
        return;
    }

    int namKinhNghiem;
    int soChiNhanh;
    try {
        namKinhNghiem = Integer.parseInt(txtNamKinhNghiem.getText());
        soChiNhanh = Integer.parseInt(txtSoChiNhanh.getText());
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(this, "Năm kinh nghiệm và số chi nhánh phải là số hợp lệ.");
        return;
    }

    String diaChi = (String) table.getValueAt(selectRow, 4); // Column 4 for Địa Chỉ

    if (ThoXayList.get(selectRow) instanceof ThoMoc) {
        String chinhSachQuanLy = (String) table.getValueAt(selectRow, 5); // Column 5 for Chính Sách Quản Lý
        cndb.capnhat(maNhanVien, namKinhNghiem, soChiNhanh, chinhSachQuanLy,diaChi);

    } else if (ThoXayList.get(selectRow) instanceof ThoHo) {
        // Validate input for ThoHo
        if (this.cndb != null) {
            String chinhSachQuanLy = null;
            this.cndb.capnhat(maNhanVien, namKinhNghiem, soChiNhanh,diaChi,chinhSachQuanLy);
        } else {
            System.out.println("Lỗi: Đối tượng kết nối cơ sở dữ liệu chưa được khởi tạo.");
        }
    }


    model.setRowCount(0);
    loaddata(); // Load updated data into the table
}


    public void loaddata(){
        ThoXayList = cndb.getAllNhanVien();
        for (ThoXay thoXay :ThoXayList){
            switch (thoXay) {
                case ThoHo thoHo -> model.addRow(thoHo.toRowData());
                case ThoMoc thoMoc -> model.addRow(thoMoc.toRowData());
                default -> {
                }
            }
        }
    }

    private void addWorker() {
        String loaiNhanVien = (String) comboBoxLoaiNhanVien.getSelectedItem();
        String maNhanVien = txtMaNhanVien.getText();
        String namKinhNghiemStr = txtNamKinhNghiem.getText();

        // Kiểm tra trường mã ngân hàng và năm thành lập không rỗng
        if (maNhanVien.isEmpty() || namKinhNghiemStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập.");
            return;
        }

        int namKinhNghiem;
        try {
            namKinhNghiem = Integer.parseInt(namKinhNghiemStr);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Năm kinh nghiệm phải là một số nguyên hợp lệ.");
            return;
        }

        if ("Thợ Hồ".equals(loaiNhanVien)) {
            String soChiNhanhStr = txtSoChiNhanh.getText();
            String DiaChiStr = txtDiaChi.getText();
            String chinhSachQuanLy = txtChinhSachQuanLy.getText();

            if ( soChiNhanhStr.isEmpty() || DiaChiStr.isEmpty()|| chinhSachQuanLy.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin cho Thợ Hồ");
                return;
            }

            int soChiNhanh;

            try {
                soChiNhanh = Integer.parseInt(soChiNhanhStr);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Thông tin phải là số hợp lệ.");
                return;
            }

            ThoHo thoHo = new ThoHo(maNhanVien, namKinhNghiem, soChiNhanh ,DiaChiStr, chinhSachQuanLy);
            ThoXayList.add(thoHo);
            model.addRow(thoHo.toRowData());
        } else {
            String chinhSachQuanLy = txtChinhSachQuanLy.getText();
            String diaChi = txtDiaChi.getText();
            String soChiNhanhStr = txtSoChiNhanh.getText();

            if ( soChiNhanhStr.isEmpty() || chinhSachQuanLy.isEmpty() || diaChi.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin cho Thợ Mộc.");
                return;
            }

            int soChiNhanhInt;

            try {
                soChiNhanhInt = Integer.parseInt(soChiNhanhStr);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Thông tin phải là số hợp lệ.");
                return;
            }

            ThoMoc thoMoc = new ThoMoc(maNhanVien, namKinhNghiem, soChiNhanhInt ,diaChi, chinhSachQuanLy);
            ThoXayList.add(thoMoc);
            model.addRow(thoMoc.toRowData());
        }

        // Xóa nội dung các trường nhập liệu
        clearInputFields();
    }
    

    private void clearInputFields() {
        txtMaNhanVien.setText("");
        txtNamKinhNghiem.setText("");
        txtSoChiNhanh.setText("");
        txtChinhSachQuanLy.setText("");
        txtDiaChi.setText("");
    }

    private void deleteWork() {
        String maNhanVien = JOptionPane.showInputDialog(this, "Nhập mã cần xóa:");

        if (maNhanVien == null || maNhanVien.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập mã cần xóa.");
            return;
        }

        boolean found = false;
        for (int i = 0; i < ThoXayList.size(); i++) {
            if (ThoXayList.get(i).getmaNhanVien().equals(maNhanVien)) {
                ThoXayList.remove(i);
                model.removeRow(i);
                found = true;
                break;
            }
        }

        if (!found) {
            JOptionPane.showMessageDialog(this, "Không tìm thấy với mã: " + maNhanVien);
        } else {
            JOptionPane.showMessageDialog(this, "Đã xóa với mã: " + maNhanVien);
        }
    }
    
    private void searchWorkerByCode() {
        String maNhanVien = JOptionPane.showInputDialog(this, "Nhập mã cần tìm:");
        
        if (maNhanVien == null || maNhanVien.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập mã để tìm kiếm.");
            return;
        }

        boolean found = false;
        for (ThoXay thoXay : ThoXayList) {
            if (thoXay.getmaNhanVien().equals(maNhanVien)) {
                found = true;
                // Tạo một cửa sổ hiển thị thông tin ngân hàng tìm thấy
                JOptionPane.showMessageDialog(this, 
                    """
                    Nh\u00e2n Vi\u00ean t\u00ecm th\u1ea5y:
                    M\u00e3 nh\u00e2n vi\u00ean: """ + thoXay.getmaNhanVien() + "\n" +
                    "Năm kinh nghiệm: " + thoXay.getnamKinhNghiem() + "\n" +
                    "Thông tin chi tiết: " + thoXay.toTextFormat());
                break;
            }
        }

        if (!found) {
            JOptionPane.showMessageDialog(this, "Không tìm thấy với mã: " + maNhanVien);
        }
    }
    
    private void saveToTextFile(String fileName) {
        if (ThoXayList.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Danh sách rỗng, không có gì để lưu.");
            return;
        }
        try {
            Main.luuDanhSachText(fileName, ThoXayList);
            JOptionPane.showMessageDialog(this, "Đã lưu dữ liệu vào file văn bản thành công.");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Lỗi khi lưu file: " + e.getMessage());
        }
    }

    private void loadFromTextFile(String fileName) {
        try {
            List<ThoXay> loadedList = Main.taiDanhSachText(fileName);
            if (loadedList.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Không có nhân viên nào trong tệp văn bản hoặc tệp rỗng.");
            } else {
                ThoXayList = loadedList;
                updateTableFromList();
                JOptionPane.showMessageDialog(this, "Đã tải dữ liệu từ file văn bản thành công.");
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Lỗi khi đọc file: " + e.getMessage());
        }
    }

    private void saveToBinaryFile(String fileName) {
        if (ThoXayList.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Danh sách rỗng, không có gì để lưu.");
            return;
        }
        try {
            Main.luuDanhSachBinary(fileName, ThoXayList);
            JOptionPane.showMessageDialog(this, "Đã lưu dữ liệu vào file nhị phân thành công.");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Lỗi khi lưu file: " + e.getMessage());
        }
    }

    private void loadFromBinaryFile(String fileName) {
        try {
            List<ThoXay> loadedList = Main.taiDanhSachBinary(fileName);
            if (loadedList.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Không có nhân viên nào trong tệp nhị phân hoặc tệp rỗng.");
            } else {
                ThoXayList = loadedList;
                updateTableFromList();
                JOptionPane.showMessageDialog(this, "Đã tải dữ liệu từ file nhị phân thành công.");
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Lỗi khi đọc file: " + e.getMessage());
        }
    }

    private void updateTableFromList() {
        model.setRowCount(0);
        for (ThoXay thoXay : ThoXayList) {
            model.addRow(thoXay.toRowData());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            WorkerManagament gui = new WorkerManagament();
            gui.setVisible(true);
        });
    }
}
