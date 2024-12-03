package App.APP;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;

@SuppressWarnings("unused")
public class NewWinDow extends JFrame {
    private JTable table;
    private DefaultTableModel model;
    private JTextField txtTen, txtTuoi, txtSolanluyentap, txtSokmhanhquan,timkiem;
    private JButton btnThem,btnClear,btnload,btnCapnhat, btnXoa, btnTimKiem, btnLuuFileVanBan, btnLuuFileNhiPhan, btnMoFileVanBan, btnMoFileNhiPhan;
    private ArrayList<BoDoi> boDoiList;
    private SaveFile file;
    private ConnectDataBase cndb;

    public NewWinDow() {
        // Khởi tạo giao diện
        setTitle("Quản lý Binh Lính");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);
        
        cndb = new ConnectDataBase();
        boDoiList = new ArrayList<BoDoi>();
        file = new SaveFile();

        JLabel lblTen = new JLabel("Tên:");
        lblTen.setBounds(10, 10, 100, 30);
        add(lblTen);
        
        txtTen = new JTextField();
        txtTen.setBounds(120, 10, 150, 30);
        add(txtTen);
        
        JLabel lblTuoi = new JLabel("Tuổi:");
        lblTuoi.setBounds(10, 50, 100, 30);
        add(lblTuoi);
        
        txtTuoi = new JTextField();
        txtTuoi.setBounds(120, 50, 150, 30);
        add(txtTuoi);
        
        JLabel lblSolanluyentap = new JLabel("Số lần luyện tập:");
        lblSolanluyentap.setBounds(10, 90, 100, 30);
        add(lblSolanluyentap);
        
        txtSolanluyentap = new JTextField();
        txtSolanluyentap.setBounds(120, 90, 150, 30);
        add(txtSolanluyentap);
        
        JLabel lblSokmhanhquan = new JLabel("Số km hành quân:");
        lblSokmhanhquan.setBounds(10, 130, 150, 30);
        add(lblSokmhanhquan);
        
        txtSokmhanhquan = new JTextField();
        txtSokmhanhquan.setBounds(120, 130, 150, 30);
        add(txtSokmhanhquan);

        // Bảng hiển thị danh sách đối tượng
        model = new DefaultTableModel();
        model.addColumn("Tên");
        model.addColumn("Tuổi");
        model.addColumn("Số lần luyện tập / Số km hành quân");
        table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(10, 200, 760, 300);
        add(scrollPane);
        
        // Nút thêm
        btnThem = new JButton("Thêm");
        btnThem.setBounds(300, 10, 150, 30);
        add(btnThem);
        
        // Nút xóa
        btnXoa = new JButton("Xóa");
        btnXoa.setBounds(300, 50, 150, 30);
        add(btnXoa);
        
        // Nút lưu file văn bản
        btnLuuFileVanBan = new JButton("Lưu file văn bản");
        btnLuuFileVanBan.setBounds(300, 90, 150, 30);
        add(btnLuuFileVanBan);

        // Nút lưu file nhị phân
        btnLuuFileNhiPhan = new JButton("Lưu file nhị phân");
        btnLuuFileNhiPhan.setBounds(300, 130, 150, 30);
        add(btnLuuFileNhiPhan);
        
        // Nút mở file văn bản
        btnMoFileVanBan = new JButton("Mở file văn bản");
        btnMoFileVanBan.setBounds(470, 90, 150, 30);
        add(btnMoFileVanBan);

        // Nút mở file nhị phân
        btnMoFileNhiPhan = new JButton("Mở file nhị phân");
        btnMoFileNhiPhan.setBounds(470, 130, 150, 30);
        add(btnMoFileNhiPhan);
        
        // nút tìm kiếm
        btnTimKiem = new JButton("Tìm kiếm");
        btnTimKiem.setBounds(470,10,150,30);
        add(btnTimKiem);
        // jtext tìm kiếm
        
        btnCapnhat = new JButton("Cập nhật");
        btnCapnhat.setBounds(470,50,150,30);
        add(btnCapnhat);
        
        timkiem = new JTextField();
        timkiem.setBounds(650, 10,130,30);
        add(timkiem);
        
        btnload = new JButton("Load data");
        btnload.setBounds(650,50,130,30);
        add(btnload);
        
        btnClear = new JButton("Clear");
        btnClear.setBounds(650,90,130,30);
        add(btnClear);
        
        // Danh sách lưu đối tượng
        boDoiList = new ArrayList<>();
        
        // Thêm sự kiện cho nút thêm
        btnThem.addActionListener(e -> Them());
        btnXoa.addActionListener(e->Xoa());
        btnLuuFileNhiPhan.addActionListener(e->savebdt());
        btnLuuFileVanBan.addActionListener(e->savetxt());
        btnMoFileNhiPhan.addActionListener(e->openbdt());
        btnMoFileVanBan.addActionListener(e->opentxt());
        btnTimKiem.addActionListener(e->timkiem());
        btnCapnhat.addActionListener(e->capnhat());
        btnload.addActionListener(e->load());
        btnClear.addActionListener(e->clear());
        
        loaddata();
        this.setVisible(true);
    }
    private void clear() {
		model.setRowCount(0);
	}
	private void load() {
    	model.setRowCount(0);
		loaddata();
	}
	private void capnhat() {
    	int selectRow = table.getSelectedRow();
    	String name = (String) table.getValueAt(selectRow, 0);
    	String newname = txtTen.getText();
    	int tuoi = Integer.parseInt(txtTuoi.getText());
    	String type;
    	int optional ;
    	if(boDoiList.get(selectRow) instanceof LinhBo)
    	{
    		type = "LINHBO";
    		optional = Integer.parseInt(txtSolanluyentap.getText());
    		cndb.capnhat(name, tuoi, optional, type, newname);
    		
    	}else if(boDoiList.get(selectRow) instanceof LinhThuy)
    	{
    		type = "LINHTHUY";
    		optional = Integer.parseInt(txtSokmhanhquan.getText());
    		cndb.capnhat(name, tuoi, optional, type, newname);
    	}
    	model.setRowCount(0);
    	loaddata();
			
	}
	public void loaddata()
    {
    	boDoiList = cndb.getAllBoDoi();
    	for (BoDoi boDoi : boDoiList) {
    		if(boDoi instanceof LinhThuy)
    		{
    			LinhThuy linhThuy = (LinhThuy) boDoi;
    			model.addRow(new Object[] {linhThuy.getTen(),linhThuy.getTuoi(),linhThuy.getSokmhanhquan()});
    		}else if(boDoi instanceof LinhBo)
    		{
    			LinhBo linhBo = (LinhBo) boDoi;
    			model.addRow(new Object[] {linhBo.getTen(),linhBo.getTuoi(),linhBo.getSolanluyentap()});
    		}
		}
    }

	private void timkiem() {
		model.setRowCount(0);
		String name = timkiem.getText();
		for (BoDoi boDoi : boDoiList) {
			if(boDoi.getTen().contains(name))
			{
				if(boDoi instanceof LinhBo)
				{
					LinhBo x = (LinhBo) boDoi;
					model.addRow(new Object[] {x.getTen(),x.getTuoi(),x.getSolanluyentap()});
				}else if(boDoi instanceof LinhThuy)
				{
					LinhThuy x = (LinhThuy) boDoi;
					model.addRow(new Object[] {x.getTen(),x.getTuoi(),x.getSokmhanhquan()});
				}
			}
		}
	}

	@SuppressWarnings("static-access")
	private void opentxt() {
    	ArrayList<BoDoi> x = (ArrayList<BoDoi>) file.taiDanhSachVanBan();
    	model.setRowCount(0);
    	for (BoDoi boDoi : x) {
			if(boDoi instanceof LinhThuy)
			{
				LinhThuy y = (LinhThuy) boDoi;
				model.addRow(new Object[] {y.getTen(),y.getTuoi(),y.getSokmhanhquan()});
			}else if(boDoi instanceof LinhBo)
			{
				LinhBo y = (LinhBo) boDoi;
				model.addRow(new Object[] {y.getTen(),y.getTuoi(),y.getSolanluyentap()});
			}
		}
	}

	private void openbdt() {
		ArrayList<BoDoi> x = (ArrayList<BoDoi>) file.taiDanhSachNhiPhan();
    	model.setRowCount(0);
    	for (BoDoi boDoi : x) {
			if(boDoi instanceof LinhThuy)
			{
				LinhThuy y = (LinhThuy) boDoi;
				model.addRow(new Object[] {y.getTen(),y.getTuoi(),y.getSokmhanhquan()});
			}else if(boDoi instanceof LinhBo)
			{
				LinhBo y = (LinhBo) boDoi;
				model.addRow(new Object[] {y.getTen(),y.getTuoi(),y.getSolanluyentap()});
			}
		}
	}

	@SuppressWarnings("static-access")
	private void savetxt() {
		file.luuDanhSachVanBan(boDoiList);
		JOptionPane.showMessageDialog(null, "Lưu file văn bản thành công!");
	}

	@SuppressWarnings("static-access")
	private void savebdt() {
		file.luuDanhSachNhiPhan(boDoiList);
		JOptionPane.showMessageDialog(null, "Lưu file nhị phân thành công!");
	}

	private void Xoa() {
	    int selectedRow = table.getSelectedRow();
	    if (selectedRow >= 0) {
	        String name = (String) table.getValueAt(selectedRow, 0);
	        
	        // Sử dụng Iterator để xóa an toàn
	        Iterator<BoDoi> iterator = boDoiList.iterator();
	        while (iterator.hasNext()) {
	            BoDoi x = iterator.next();
	            if (x.getTen().equals(name)) {
	                iterator.remove(); // Xóa phần tử hiện tại

	                // Xóa dữ liệu khỏi cơ sở dữ liệu
	                if (x instanceof LinhThuy) {
	                    cndb.xoaLinhThuy(x.getTen());
	                } else if (x instanceof LinhBo) {
	                    cndb.xoaLinhBo(x.getTen());
	                }
	                
	                // Xóa hàng trong bảng
	                model.removeRow(selectedRow);
	                break;
	            }
	        }
	    } else {
	        JOptionPane.showMessageDialog(null, "Vui lòng chọn để xóa!");
	    }
	}


	private void Them() {
		String name = txtTen.getText();
		int age = Integer.parseInt(txtTuoi.getText());
		if(!txtSolanluyentap.getText().isEmpty())
		{
			int sollt = Integer.parseInt(txtSolanluyentap.getText());
			LinhBo x = new LinhBo(name, age, sollt);
			boDoiList.add(x);
			cndb.addLinhBo(name, age, sollt);
			model.addRow(new Object[] {x.getTen(),x.getTuoi(),x.getSolanluyentap()});
		}else if(!txtSokmhanhquan.getText().isEmpty())
		{
			int skm = Integer.parseInt(txtSokmhanhquan.getText());
			LinhThuy x = new LinhThuy(name, age, skm);
			boDoiList.add(x);
			cndb.addLinhThuy(name, age, skm);
			model.addRow(new Object[] {x.getTen(),x.getTuoi(),x.getSokmhanhquan()});
		}
	}
}
