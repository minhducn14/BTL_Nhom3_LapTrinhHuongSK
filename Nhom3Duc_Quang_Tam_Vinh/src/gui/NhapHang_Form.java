package gui;

import DAO.CT_HDNH_DAO;
import DAO.HoaDonNhapHang_DAO;
import DAO.LoaiThuoc_DAO;
import DAO.NhanVien_DAO;
import DAO.Thuoc_DAO;

import com.toedter.calendar.JDateChooser;

import entity.*;
import tableModel.CT_HDBH_TableModel;
import tableModel.CT_HDNH_TableModel;
import tableModel.Thuoc_TableModel;

import javax.swing.*;
import javax.swing.border.TitledBorder;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NhapHang_Form extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JPanel pnNorth, pnCenter, pnSouth;
	JLabel lblTienThuoc, lblTienNhan, lblTienThue, lblTienThoi, lblTongTien,
			lblMaHD, lblST, lblNgayLap, lblNhanVien, lblDiaChi, lblNhaCC,
			lblEmail, lblMa, lblTen, lblLoaiThuoc, lblCongDung, lblThanhPhan,
			lblXuatXu, lblSoLuong, lblGiaBan, lblHanDung, lblDonViTinh;
	JTextField txttienThuoc, txtTienNhan, txtThue, txtTienThoi, txtTong,
			txtSDT, txtMaHD, txtNhanVien, txtDiaChi, txtNhaCC, txtMail,
			txtCongDung, txtThanhPhan, txtXuatXu, txtSoLuong, txtGia, txtMa,
			txtTen;
	JButton btnHoaDonMoi, btnIn, btnThoat, btnNhaCC, btnNhapthuoc, btnThem,
			btnChinhSua;
	JComboBox cbcLoai, cbcDonVi, cbcNV, cbcTenThuoc;
	JDateChooser NgayLap, NgayHetHan;
	String mahd = "";
	NhanVien nv;
	NhaCungCap ncc;
	NhapHang_Form nh;
	HoaDonNhapHang hd;
	JRadioButton rdDaCo, rdChuaCo;
	ButtonGroup btnGR;

	public NhapHang_Form() {

	}

	public void doShow() {
		// pnNorth
		pnNorth = new JPanel();
		JPanel pnNorth_C = new JPanel();
		JPanel pnTieuDe = new JPanel();
		pnNorth.setLayout(new BorderLayout());
		JLabel lblTieuDe = new JLabel("LẬP HÓA ĐƠN NHẬP HÀNG");
		lblTieuDe.setFont(new Font("arial", Font.BOLD, 18));
		lblTieuDe.setForeground(Color.RED);
		pnTieuDe.add(lblTieuDe);
		pnNorth.add(pnTieuDe, BorderLayout.NORTH);

		JPanel pnThongTin = new JPanel();
		pnThongTin.setBorder(new TitledBorder("Thông Tin Hóa Đơn"));
		Box b, b1, b2, b3, b4;
		b = Box.createVerticalBox();
		b.setPreferredSize(new Dimension(500, 210));
		b.add(Box.createVerticalStrut(30));
		b.add(b1 = Box.createHorizontalBox());
		b1.add(lblMaHD = new JLabel("Mã HD:"));
		b1.add(txtMaHD = new JTextField());
		txtMaHD.setEditable(false);
		b1.add(Box.createHorizontalStrut(20));
		b1.add(lblNgayLap = new JLabel("Ngày Lập HD:"));
		NgayLap = new JDateChooser();
		NgayLap.setDateFormatString("yyyy-MM-dd");
		try {
			Date date = Date.valueOf(LocalDate.now());
			NgayLap.setDate(date);
		} catch (Exception e) {
			e.printStackTrace();
		}

		b1.add(NgayLap);
		b.add(Box.createVerticalStrut(10));

		Box b11;
		b.add(b11 = Box.createHorizontalBox());
		b11.add(lblNhanVien = new JLabel("Nhân Viên Lập HD: "));
		cbcNV = new JComboBox();
		NhanVien_DAO nvDao = new NhanVien_DAO();
		for (NhanVien nv : nvDao.getLS()) {
			cbcNV.addItem(nv.getTenNV());
		}
		cbcNV.setPreferredSize(new Dimension(325, 20));
		b11.add(cbcNV);
		b.add(Box.createVerticalStrut(10));
		b.add(b2 = Box.createHorizontalBox());
		b2.add(btnNhaCC = new JButton("Nhà Cung Cấp"));
		b2.add(Box.createHorizontalStrut(10));
		b2.add(btnThem = new JButton("..."));
		b.add(Box.createVerticalStrut(10));

		b.add(b3 = Box.createHorizontalBox());
		b3.add(lblNhaCC = new JLabel("Nhà Cung Cấp: "));
		b3.add(txtNhaCC = new JTextField());
		txtNhaCC.setEditable(false);
		b3.add(Box.createHorizontalStrut(20));
		b3.add(lblEmail = new JLabel("Email: "));
		b3.add(txtMail = new JTextField());
		txtMail.setEditable(false);

		b.add(Box.createVerticalStrut(10));

		b.add(b4 = Box.createHorizontalBox());
		b4.add(lblST = new JLabel("Điện Thoại: "));
		b4.add(txtSDT = new JTextField());
		txtSDT.setEditable(false);
		b4.add(Box.createHorizontalStrut(20));
		b4.add(lblDiaChi = new JLabel("Địa Chỉ: "));
		b4.add(txtDiaChi = new JTextField());
		txtDiaChi.setEditable(false);
		b.add(Box.createVerticalStrut(10));

		lblMaHD.setPreferredSize(lblNhanVien.getPreferredSize());
		lblNgayLap.setPreferredSize(lblNhanVien.getPreferredSize());
		lblNhaCC.setPreferredSize(lblNhanVien.getPreferredSize());
		lblST.setPreferredSize(lblNhanVien.getPreferredSize());

		JPanel pnThuoc = new JPanel();
		pnThuoc.setBorder(new TitledBorder("Nhập Thuốc"));
		pnThuoc.setLayout(new BorderLayout());
		Box bb, bb1, bb2, bb3, bb4, bb5, bb6;
		bb = Box.createVerticalBox();
		bb.setPreferredSize(new Dimension(650, 170));

		bb.add(bb1 = Box.createHorizontalBox());
		bb1.add(lblMa = new JLabel("Mã Thuốc: "));
		bb1.add(txtMa = new JTextField(30));
		txtMa.setEditable(false);
		bb1.add(Box.createHorizontalStrut(20));
		bb1.add(lblTen = new JLabel("Tên Thuốc:    "));
		bb1.add(txtTen = new JTextField(30));
		bb.add(Box.createVerticalStrut(10));
		btnGR = new ButtonGroup();
		btnGR.add(rdDaCo);
		btnGR.add(rdChuaCo);
		bb.add(bb2 = Box.createHorizontalBox());
		bb2.add(lblLoaiThuoc = new JLabel("Loại Thuốc: "));
		cbcLoai = new JComboBox<>();
		LoaiThuoc_DAO lDao = new LoaiThuoc_DAO();
		for (Loaithuoc l : lDao.getLS()) {
			cbcLoai.addItem(l.getTenThuoc());
		}
		cbcLoai.setPreferredSize(new Dimension(300, 20));
		bb2.add(cbcLoai);
		bb2.add(Box.createHorizontalStrut(20));
		bb2.add(lblCongDung = new JLabel("Công Dụng:    "));
		bb2.add(txtCongDung = new JTextField(30));
		bb.add(Box.createVerticalStrut(10));

		bb.add(bb3 = Box.createHorizontalBox());
		bb3.add(lblThanhPhan = new JLabel("Thành Phần: "));
		bb3.add(txtThanhPhan = new JTextField(30));
		bb3.add(Box.createHorizontalStrut(20));
		bb3.add(lblXuatXu = new JLabel("Xuất Xứ:    "));
		bb3.add(txtXuatXu = new JTextField(30));
		bb.add(Box.createVerticalStrut(10));

		bb.add(bb4 = Box.createHorizontalBox());
		bb4.add(lblSoLuong = new JLabel("Số Lượng: "));
		bb4.add(txtSoLuong = new JTextField(30));
		bb4.add(Box.createHorizontalStrut(20));
		bb4.add(lblGiaBan = new JLabel("Giá Bán:    "));
		bb4.add(txtGia = new JTextField(30));
		bb.add(Box.createVerticalStrut(10));

		bb.add(bb5 = Box.createHorizontalBox());
		bb5.add(lblDonViTinh = new JLabel("Đơn Vị Tính: "));
		cbcDonVi = new JComboBox<>();
		cbcDonVi.addItem("Viên");
		cbcDonVi.addItem("Vỉ");
		cbcDonVi.addItem("Tuýp");
		cbcDonVi.addItem("Hộp");
		cbcDonVi.setPreferredSize(new Dimension(230, 20));
		bb5.add(cbcDonVi);
		bb5.add(Box.createHorizontalStrut(20));
		bb5.add(lblHanDung = new JLabel("Hạn Sử Dụng:    "));
		NgayHetHan = new JDateChooser();
		NgayHetHan.setDateFormatString("yyyy-MM-dd");
		try {
			Date date = Date.valueOf(LocalDate.now());
			NgayHetHan.setDate(date);
		} catch (Exception e) {
			e.printStackTrace();
		}

		bb5.add(NgayHetHan);
		bb.add(Box.createVerticalStrut(10));

		bb.add(bb6 = Box.createHorizontalBox());
		bb6.add(btnNhapthuoc = new JButton("Nhập Thuốc"));

		lblMa.setPreferredSize(lblHanDung.getPreferredSize());
		lblTen.setPreferredSize(lblHanDung.getPreferredSize());
		lblCongDung.setPreferredSize(lblHanDung.getPreferredSize());
		lblDonViTinh.setPreferredSize(lblHanDung.getPreferredSize());
		lblXuatXu.setPreferredSize(lblHanDung.getPreferredSize());
		lblSoLuong.setPreferredSize(lblHanDung.getPreferredSize());
		lblLoaiThuoc.setPreferredSize(lblHanDung.getPreferredSize());
		lblThanhPhan.setPreferredSize(lblHanDung.getPreferredSize());
		lblGiaBan.setPreferredSize(lblHanDung.getPreferredSize());

		pnThuoc.add(bb);
		pnThongTin.add(b);

		pnNorth.add(pnThuoc, BorderLayout.EAST);
		pnNorth.add(pnThongTin, BorderLayout.CENTER);

		// pnCenter
		pnCenter = new JPanel();
		pnCenter.setBorder(new TitledBorder("Danh Sách Thuốc Đã Nhập"));
		List<CT_HoaDonNhapHang> ls1 = new ArrayList<>();
		CT_HDNH_TableModel model1 = new CT_HDNH_TableModel(ls1);
		JTable table1 = new JTable();
		table1.setModel(model1);
		JScrollPane sc1 = new JScrollPane(table1,
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		sc1.setPreferredSize(new Dimension(850, 140));
		pnCenter.add(sc1);

		// pnSouth
		pnSouth = new JPanel();
		pnSouth.setLayout(new BorderLayout());
		Box s, s1, s2, s3;
		s = Box.createVerticalBox();
		s.setPreferredSize(new Dimension(1000, 100));
		s.add(s1 = Box.createHorizontalBox());
		s1.add(lblTienThuoc = new JLabel("Tổng Tiền Thuốc: "));
		s1.add(txttienThuoc = new JTextField());
		txttienThuoc.setEditable(false);
		s1.add(Box.createHorizontalStrut(50));
		s1.add(lblTienNhan = new JLabel("          "));
		s1.add(txtTienNhan = new JTextField());
		txtTienNhan.setEditable(false);
		s.add(Box.createVerticalStrut(7));

		s.add(s2 = Box.createHorizontalBox());
		s2.add(lblTienThue = new JLabel("Thuế GTGT: "));
		s2.add(txtThue = new JTextField());
		txtThue.setEditable(false);
		s2.add(Box.createHorizontalStrut(50));
		s2.add(lblTienThoi = new JLabel("           "));
		s2.add(txtTienThoi = new JTextField());
		txtTienThoi.setEditable(false);
		s.add(Box.createVerticalStrut(10));

		s.add(s3 = Box.createHorizontalBox());
		s3.add(lblTongTien = new JLabel("Tổng Tiền HD: "));
		s3.add(txtTong = new JTextField());
		txtTong.setEditable(false);
		s3.add(Box.createHorizontalStrut(50));
		s3.add(btnHoaDonMoi = new JButton("Tạo Hóa Đơn Mới"));
		s.add(Box.createVerticalStrut(7));

		Box d;
		d = Box.createVerticalBox();
		d.setPreferredSize(new Dimension(100, 30));
		d.add(btnIn = new JButton("In HD"));
		d.add(Box.createVerticalStrut(15));
		d.add(btnThoat = new JButton("Thoát"));

		pnSouth.add(s, BorderLayout.WEST);
		pnSouth.add(d, BorderLayout.EAST);
		pnSouth.setBorder(new TitledBorder("Chi tiết hóa đơn"));

		lblTienNhan.setPreferredSize(lblTienThuoc.getPreferredSize());
		lblTienThoi.setPreferredSize(lblTienThuoc.getPreferredSize());
		lblTienThue.setPreferredSize(lblTienThuoc.getPreferredSize());
		lblTongTien.setPreferredSize(lblTienThuoc.getPreferredSize());

		cbcLoai.setPreferredSize(new Dimension(260, 20));
		cbcDonVi.setPreferredSize(new Dimension(225, 20));

		if (nv != null) {
			txtNhanVien.setEditable(false);
			txtNhanVien.setText(nv.getTenNV());
		}
		btnNhaCC.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				DS_NhaCungCap_Form ds = new DS_NhaCungCap_Form();
				ds.setVisible(true);
				ds.nhapHang = nh;
			}
		});

		Thuoc_DAO thuocDao = new Thuoc_DAO();
		btnNhapthuoc.addActionListener(new ActionListener() {
			double tongTien = 0;

			@Override
			public void actionPerformed(ActionEvent e) {
				if (ncc != null) {

					try {
						if (!txtTen.getText().equals("")
								&& !txtCongDung.getText().equals("")
								&& !txtThanhPhan.getText().equals("")
								&& !txtXuatXu.getText().equals("")
								&& !txtSoLuong.getText().equals("")
								&& !txtGia.getText().equals("")) {
							String ten = txtTen.getText();
							String congDung = txtCongDung.getText();
							String thanhPhan = txtThanhPhan.getText();
							String xuatXu = txtXuatXu.getText();
							int soLuong = Integer.parseInt(txtSoLuong.getText());
							double gia = Double.parseDouble(txtGia.getText());
							Loaithuoc loaiThuoc = new Loaithuoc();
							LoaiThuoc_DAO loaiThuoc_DAO = new LoaiThuoc_DAO();
							loaiThuoc = loaiThuoc_DAO.TimKiemTen(cbcLoai
									.getSelectedItem().toString());
							Thuoc thuoc = new Thuoc();
							thuoc.setLoaiThuoc(loaiThuoc);
							thuoc.setTenThuoc(ten);
							thuoc.setCongDung(congDung);
							thuoc.setThanhPhan(thanhPhan);
							thuoc.setXuatXu(xuatXu);
							thuoc.setDonViTinh(cbcDonVi.getSelectedItem()
									.toString());
							thuoc.setSoLuong(soLuong);
							thuoc.setGiaBan(gia);
							thuoc.setNgayHetHan(NgayHetHan.getDate());
							thuocDao.addThuoc(thuoc);
							try {
								HoaDonNhapHang_DAO hdDao = new HoaDonNhapHang_DAO();
								mahd = hdDao.getMa();
								Thuoc t = thuocDao.TimKiemTen(ten);
								CT_HoaDonNhapHang ct = new CT_HoaDonNhapHang(
										soLuong, gia);
								hd.setMaHDNH(mahd);
								ct.setHoaDonNhapHang(hd);
								ct.setThuoc(t);
								CT_HDNH_DAO ctDao = new CT_HDNH_DAO();
								if (ctDao.TimKiemMaHD(mahd, t.getMaThuoc()) != null) {
									ctDao.updateSoLuong(mahd, t.getMaThuoc(),
											ctDao.TimKiemMaT(t.getMaThuoc())
													.getSoLuong() + soLuong);
									model1.update(ctDao.TimKiemHD(mahd));
									table1.setModel(model1);
									tongTien += (double) ctDao.TimKiemMaT(
											t.getMaThuoc()).getSoLuong()
											* ct.getDonGia();
									txttienThuoc.setText(String
											.valueOf(tongTien));
									txtThue.setText("5%");
									txtTong.setText(String.valueOf(tongTien
											+ tongTien * 0.05));
									hdDao.updateTongtien(mahd, tongTien
											+ tongTien * 0.05);
								} else {
									if (ctDao.addCTHDNH(ct)) {
										ls1.add(ct);
										model1.update(ls1);
										tongTien += (double) ct.getSoLuong()
												* ct.getDonGia();
										txttienThuoc.setText(String
												.valueOf(tongTien));
										txtThue.setText("5%");
										txtTong.setText(String.valueOf(tongTien
												+ tongTien * 0.05));
										hdDao.updateTongtien(mahd, tongTien
												+ tongTien * 0.05);
									}
								}

							} catch (SQLException throwables) {
								throwables.printStackTrace();
							}
						} else {
							JOptionPane.showMessageDialog(null,
									"Vui lòng nhập đầy đủ thông tin");
						}
					}

					catch (NumberFormatException e2) {
						// TODO: handle exception
						e2.printStackTrace();
						JOptionPane.showMessageDialog(null, "Sai định dạng");
					}
				} else {
					JOptionPane.showMessageDialog(null,
							"Bạn chưa chọn nhà cung cấp!");
				}

			}
		});

		btnThem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Them_Form them = new Them_Form();
				them.setVisible(true);
				NhaCC_Form ncc = new NhaCC_Form();
				them.add(ncc);
				validate();
			}
		});
		btnIn.addActionListener(new ActionListener() {
			private JasperPrint jprint;

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					Class.forName("net.sourceforge.jtds.jdbc.Driver");
					Connection con = DriverManager
							.getConnection("jdbc:jtds:sqlserver://localhost:1433/QuanLyQuayThuoc;instance=SQLEXPRESS;user=sa;password=sapassword");
					String sql = "SELECT hd.MaHDNH,hd.NGAYLAPHD,nv.TENNV,ncc.TENNHACC,ncc.SODT,ncc.EMAIL,ncc.DIACHI,t.TENTHUOC,t.CONGDUNG,t.DONVITINH,t.GIABAN,t.NGAYHETHAN,\r\n"
							+ "ct.SOLUONG,t.THANHPHAN,lt.TENLOAI,ct.SOLUONG*t.GIABAN AS ThanhTien\r\n"
							+ "FROM [dbo].[HoaDonNhapHang] hd JOIN [dbo].[CT_HoaDonNhapHang] ct  ON hd.MaHDNH = ct.MaHDNH\r\n"
							+ "JOIN [dbo].[NhanVien] nv ON hd.MANV = nv.MANV\r\n"
							+ "JOIN [dbo].[NhaCungCap] ncc ON hd.MANHACC = ncc.MANHACC\r\n"
							+ "JOIN [dbo].[Thuoc] t ON ct.MAT = t.MAT\r\n"
							+ "JOIN [dbo].[LoaiThuoc] lt ON t.MALOAI = lt.MALOAI\r\n"
							+ "WHERE hd.MaHDNH = " + "'" + mahd + "'";
					JasperDesign jdesign = JRXmlLoader
							.load("src//Report//HoaDonNhapHang.jrxml");
					JRDesignQuery updateQuery = new JRDesignQuery();
					updateQuery.setText(sql);
					jdesign.setQuery(updateQuery);

					Map<String, Object> parameters = new HashMap<String, Object>();
					JasperReport jreport = JasperCompileManager
							.compileReport(jdesign);
					JasperPrint jpasperPrint = JasperFillManager.fillReport(
							jreport, parameters, con);

					JasperViewer.viewReport(jpasperPrint, false);
				} catch (Exception e2) {
					// TODO: handle exception
					e2.printStackTrace();
					JOptionPane.showMessageDialog(null, e2);
				}

			}
		});
		btnThoat.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int lc = JOptionPane.showConfirmDialog(null,
						"Bạn có chắc chắn muốn thoát chương trình không?",
						"Xác nhận", JOptionPane.YES_NO_OPTION);
				if (lc == JOptionPane.YES_OPTION)
					setVisible(false);

			}
		});
		this.setLayout(new BorderLayout());
		this.add(pnNorth, BorderLayout.NORTH);
		this.add(pnCenter, BorderLayout.CENTER);
		this.add(pnSouth, BorderLayout.SOUTH);
	}

	public void clearText() {
		txtMa.setText("");
		txtTen.setText("");
		txtCongDung.setText("");
		txtGia.setText("");
		txtSoLuong.setText("");
		txtThanhPhan.setText("");
		txtXuatXu.setText("");
		cbcDonVi.setSelectedIndex(0);
		txtTen.requestFocus();
	}
}
