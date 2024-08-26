package gui;

import DAO.LoaiThuoc_DAO;
import DAO.Thuoc_DAO;

import com.toedter.calendar.JDateChooser;

import entity.Loaithuoc;
import entity.Thuoc;
import tableModel.LoaiThuoc_TableModel;
import tableModel.Thuoc_TableModel;

import javax.swing.*;
import javax.swing.border.TitledBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Date;
import java.time.LocalDate;

public class Thuoc_Form extends JPanel {
	/**
     *
     */
	private static final long serialVersionUID = 1L;
	JPanel pnNorth, pnCenter, pnSouth;
	JLabel lblMa, lblTen, lblLoaiThuoc, lblCongDung, lblThanhPhan, lblXuatXu,
			lblSoLuong, lblGiaBan, lblHanDung, lblDonViTinh;
	JTextField txtMa, txtTen, txtCongDung, txtThanhPhan, txtXuatXu, txtSoLuong,
			txtGia;
	JComboBox<String> cbcLoai, cbcDonVi;
	JDateChooser NgayHetHan;

	public Thuoc_Form() {
		doShow();
	}

	public void doShow() {
		// pnNorth
		pnNorth = new JPanel();
		JPanel pnTieuDe = new JPanel();
		pnNorth.setLayout(new BorderLayout());
		JLabel lblTieuDe = new JLabel("QUẢN LÝ THUỐC");
		lblTieuDe.setFont(new Font("arial", Font.BOLD, 20));
		lblTieuDe.setForeground(Color.RED);
		pnTieuDe.add(lblTieuDe);
		pnNorth.add(pnTieuDe);

		// pnCenter
		pnCenter = new JPanel();
		pnCenter.setLayout(new BorderLayout());
		Box b, b1, b2, b3, b4, b5;
		JPanel pnCenN = new JPanel();
		JPanel pnCenC = new JPanel();
		b = Box.createVerticalBox();
		b.setPreferredSize(new Dimension(840, 170));

		b.add(b1 = Box.createHorizontalBox());
		b1.add(lblMa = new JLabel("Mã Thuốc: "));
		b1.add(txtMa = new JTextField(30));
		b1.add(Box.createHorizontalStrut(20));
		b1.add(lblTen = new JLabel("Tên Thuốc:    "));
		b1.add(txtTen = new JTextField(30));
		b.add(Box.createVerticalStrut(10));

		b.add(b2 = Box.createHorizontalBox());
		b2.add(lblLoaiThuoc = new JLabel("Loại Thuốc: "));
		cbcLoai = new JComboBox<>();
		LoaiThuoc_DAO ltDao = new LoaiThuoc_DAO();
		for (Loaithuoc lt : ltDao.getLS()) {
			cbcLoai.addItem(lt.getTenThuoc());
		}
		cbcLoai.setPreferredSize(new Dimension(325, 20));
		b2.add(cbcLoai);
		b2.add(Box.createHorizontalStrut(20));
		b2.add(lblCongDung = new JLabel("Công Dụng:    "));
		b2.add(txtCongDung = new JTextField(30));
		b.add(Box.createVerticalStrut(10));

		b.add(b3 = Box.createHorizontalBox());
		b3.add(lblThanhPhan = new JLabel("Thành Phần: "));
		b3.add(txtThanhPhan = new JTextField(30));
		b3.add(Box.createHorizontalStrut(20));
		b3.add(lblXuatXu = new JLabel("Xuất Xứ:    "));
		b3.add(txtXuatXu = new JTextField(30));
		b.add(Box.createVerticalStrut(10));

		b.add(b4 = Box.createHorizontalBox());
		b4.add(lblSoLuong = new JLabel("Số Lượng: "));
		b4.add(txtSoLuong = new JTextField(30));
		b4.add(Box.createHorizontalStrut(20));
		b4.add(lblGiaBan = new JLabel("Giá Bán:    "));
		b4.add(txtGia = new JTextField(30));
		b.add(Box.createVerticalStrut(10));

		b.add(b5 = Box.createHorizontalBox());
		b5.add(lblDonViTinh = new JLabel("Đơn Vị Tính: "));
		cbcDonVi = new JComboBox<>();
		cbcDonVi.addItem("Viên");
		cbcDonVi.addItem("Vỉ");
		cbcDonVi.addItem("Tuýp");
		cbcDonVi.addItem("Hộp");
		cbcDonVi.setPreferredSize(new Dimension(325, 20));
		b5.add(cbcDonVi);
		b5.add(Box.createHorizontalStrut(20));
		b5.add(lblHanDung = new JLabel("Hạn Sử Dụng:    "));
		NgayHetHan = new JDateChooser();
		NgayHetHan.setDateFormatString("yyyy-MM-dd");
		try {
			Date date = Date.valueOf(LocalDate.now());
			NgayHetHan.setDate(date);
		} catch (Exception e) {
			e.printStackTrace();
		}

		b5.add(NgayHetHan);
		b.add(Box.createVerticalStrut(10));

		lblMa.setPreferredSize(lblHanDung.getPreferredSize());
		lblTen.setPreferredSize(lblHanDung.getPreferredSize());
		lblCongDung.setPreferredSize(lblHanDung.getPreferredSize());
		lblDonViTinh.setPreferredSize(lblHanDung.getPreferredSize());
		lblXuatXu.setPreferredSize(lblHanDung.getPreferredSize());
		lblSoLuong.setPreferredSize(lblHanDung.getPreferredSize());
		lblLoaiThuoc.setPreferredSize(lblHanDung.getPreferredSize());
		lblThanhPhan.setPreferredSize(lblHanDung.getPreferredSize());
		lblGiaBan.setPreferredSize(lblHanDung.getPreferredSize());

		JPanel pnCenS = new JPanel();
		JButton btnThem, btnXoa, btnSua, btnThoat, btnXoaTrang;
		pnCenS.add(btnThem = new JButton("Thêm Thuốc"));
		pnCenS.add(btnXoa = new JButton("Xóa Thuốc"));
		pnCenS.add(btnSua = new JButton("Sửa Thông Tin"));
		pnCenS.add(btnXoaTrang = new JButton("Xóa Trắng"));
		pnCenS.add(btnThoat = new JButton("Thoát"));

		pnCenN.add(b);
		pnCenter.add(pnCenN, BorderLayout.NORTH);
		pnCenter.add(pnCenC, BorderLayout.CENTER);
		pnCenter.add(pnCenS, BorderLayout.SOUTH);

		txtMa.setEditable(false);
		// pnSouth
		pnSouth = new JPanel();
		Thuoc_DAO thuocDao = new Thuoc_DAO();
		Thuoc_TableModel model = new Thuoc_TableModel(thuocDao.getAllThuoc());
		JTable table = new JTable();
		table.setModel(model);
		table.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int r = table.getSelectedRow();
				if (r != -1) {
					txtMa.setText(table.getValueAt(r, 0).toString());
					txtTen.setText(table.getValueAt(r, 1).toString());
					cbcLoai.setSelectedItem(table.getValueAt(r, 2).toString());
					txtCongDung.setText(table.getValueAt(r, 3).toString());
					txtThanhPhan.setText(table.getValueAt(r, 4).toString());
					txtXuatXu.setText(table.getValueAt(r, 5).toString());
					txtSoLuong.setText(table.getValueAt(r, 6).toString());
					txtGia.setText(table.getValueAt(r, 8).toString());
					cbcDonVi.setSelectedItem(table.getValueAt(r, 7).toString());
					NgayHetHan.setDate(Date.valueOf(table.getValueAt(r, 9)
							.toString()));
				}
			}

			@Override
			public void mousePressed(MouseEvent e) {

			}

			@Override
			public void mouseReleased(MouseEvent e) {

			}

			@Override
			public void mouseEntered(MouseEvent e) {

			}

			@Override
			public void mouseExited(MouseEvent e) {

			}
		});
		JScrollPane sc = new JScrollPane(table,
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		sc.setPreferredSize(new Dimension(850, 280));

		pnSouth.add(sc);
		pnSouth.setBorder(new TitledBorder("Danh Sách Thuốc"));

		btnThem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

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
						if (thuocDao.addThuoc(thuoc)) {
							Thuoc thuoc1 = thuocDao.TimKiemTen(thuoc
									.getTenThuoc());
							JOptionPane.showMessageDialog(null,
									"Thêm thuốc thành công");
							model.addThuoc(thuoc1);
							clearText();
						} else {
							JOptionPane.showMessageDialog(null,
									"Thêm thuốc thất bại");
						}

					} else {
						JOptionPane.showMessageDialog(null,
								"Vui lòng nhập đầy đủ thông tin");
					}

				} catch (NumberFormatException e2) {
					// TODO: handle exception
					e2.printStackTrace();
					JOptionPane.showMessageDialog(null, "Sai định dạng");
				}

			}
		});

		btnXoa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int r = table.getSelectedRow();
				if (r != -1) {
					int tb = JOptionPane.showConfirmDialog(null,
							"Bạn có chắc chắn muốn xóa dòng này không",
							"Delete", JOptionPane.YES_NO_OPTION);
					if (tb == JOptionPane.YES_OPTION) {
						Object maThuoc = model.getValueAt(r, 0);
						model.xoaThuoc(r);
						thuocDao.deleteThuoc(maThuoc.toString());
						clearText();
						JOptionPane.showMessageDialog(null, "Xóa thành công!");
					}
				} else {
					JOptionPane.showMessageDialog(null,
							"Bạn chưa chọn dòng cần xóa!");
				}
			}
		});

		btnSua.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int r = table.getSelectedRow();
				if (r != -1) {
					int lc = JOptionPane.showConfirmDialog(null,
							"Bạn có chắc chắn muốn sửa!", "update",
							JOptionPane.YES_NO_OPTION);
					if (lc == JOptionPane.YES_OPTION) {
						String maThuoc = txtMa.getText().trim();
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
						if (thuocDao.updateThuoc(maThuoc, thuoc)) {
							model.update(thuocDao.getAllThuoc());
							clearText();
							JOptionPane.showMessageDialog(null,
									"Sửa thành công!");
						}
					}
				} else {
					JOptionPane.showMessageDialog(null,
							"Bạn chưa chọn dữ liệu để sửa.");
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
		
		btnXoaTrang.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				clearText();

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
		cbcLoai.setSelectedIndex(0);
		txtTen.requestFocus();
	}
}
