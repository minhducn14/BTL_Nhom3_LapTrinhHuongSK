package gui;

import DAO.ChucVu_DAO;
import DAO.LoaiThuoc_DAO;
import DAO.NhanVien_DAO;

import com.toedter.calendar.JDateChooser;

import entity.ChucVu;
import entity.KhachHang;
import entity.Loaithuoc;
import entity.NhanVien;
import entity.Thuoc;
import tableModel.NV_TableModel;

import javax.swing.*;
import javax.swing.border.TitledBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Date;
import java.time.LocalDate;
import java.util.regex.Pattern;

public class NhanVien_Form extends JPanel {
	/**
     *
     */
	private static final long serialVersionUID = 1L;
	JPanel pnNorth, pnCenter, pnSouth;
	JLabel lblMa, lblGioiTinh, lblNamSinh, lblEmail, lblSDT, lblCM, lbldiaChi,
			lblTen, lblChucVu, lblNgayVao;
	JTextField txtMa, txtEmail, txtSDT, txtCM, txtdiaChi, txtTen;
	JComboBox<String> cbcGT, cbcChucVu;
	JDateChooser namSinh, NgayVao;

	public NhanVien_Form() {
		doShow();
	}

	public void doShow() {
		// pnNorth
		pnNorth = new JPanel();
		JPanel pnTieuDe = new JPanel();
		pnNorth.setLayout(new BorderLayout());
		JLabel lblTieuDe = new JLabel("QUẢN LÝ NHÂN VIÊN");
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
		b1.add(lblMa = new JLabel("Mã Nhân Viên: "));
		b1.add(txtMa = new JTextField(30));
		b1.add(Box.createHorizontalStrut(20));
		b1.add(lblTen = new JLabel("Họ Tên Nhân Viên:    "));
		b1.add(txtTen = new JTextField(30));
		b.add(Box.createVerticalStrut(10));

		b.add(b2 = Box.createHorizontalBox());
		b2.add(lblGioiTinh = new JLabel("Giới Tính: "));
		cbcGT = new JComboBox<>();
		cbcGT.addItem("Nam");
		cbcGT.addItem("Nữ");
		cbcGT.setPreferredSize(new Dimension(313, 20));
		b2.add(cbcGT);
		b2.add(Box.createHorizontalStrut(20));
		b2.add(lblNamSinh = new JLabel("Ngày Sinh:    "));
		namSinh = new JDateChooser();
		namSinh.setSize(new Dimension(30, 20));
		namSinh.setDateFormatString("yyyy-MM-dd");
		try {
			java.sql.Date date = java.sql.Date.valueOf(LocalDate.now());
			namSinh.setDate(date);
		} catch (Exception e) {
			e.printStackTrace();
		}

		b2.add(namSinh);
		b.add(Box.createVerticalStrut(10));

		b.add(b3 = Box.createHorizontalBox());
		b3.add(lblEmail = new JLabel("Email: "));
		b3.add(txtEmail = new JTextField(30));
		b3.add(Box.createHorizontalStrut(20));
		b3.add(lblSDT = new JLabel("Điện Thoại:    "));
		b3.add(txtSDT = new JTextField(30));
		b.add(Box.createVerticalStrut(10));

		b.add(b4 = Box.createHorizontalBox());
		b4.add(lblCM = new JLabel("SỐ CMND: "));
		b4.add(txtCM = new JTextField(30));
		b4.add(Box.createHorizontalStrut(20));
		b4.add(lbldiaChi = new JLabel("Địa Chỉ:    "));
		b4.add(txtdiaChi = new JTextField(30));
		b.add(Box.createVerticalStrut(10));

		b.add(b5 = Box.createHorizontalBox());
		b5.add(lblChucVu = new JLabel("Chức Vụ: "));
		cbcChucVu = new JComboBox<>();
		cbcChucVu.addItem("Nhân Viên Kinh Doanh");
		cbcChucVu.addItem("Nhân viên thu ngân");
		cbcChucVu.setPreferredSize(new Dimension(313, 20));
		b5.add(cbcChucVu);
		b5.add(Box.createHorizontalStrut(20));
		b5.add(lblNgayVao = new JLabel("Ngày Vào Làm:    "));
		NgayVao = new JDateChooser();
		lblNgayVao.setSize(new Dimension(30, 20));
		NgayVao.setDateFormatString("yyyy-MM-dd");
		try {
			Date date = Date.valueOf(LocalDate.now());
			NgayVao.setDate(date);
		} catch (Exception e) {
			e.printStackTrace();
		}

		b5.add(NgayVao);
		b.add(Box.createVerticalStrut(10));

		lblMa.setPreferredSize(lblTen.getPreferredSize());
		lblEmail.setPreferredSize(lblTen.getPreferredSize());
		lblCM.setPreferredSize(lblTen.getPreferredSize());
		lblGioiTinh.setPreferredSize(lblTen.getPreferredSize());
		lblNamSinh.setPreferredSize(lblTen.getPreferredSize());
		lblSDT.setPreferredSize(lblTen.getPreferredSize());
		lbldiaChi.setPreferredSize(lblTen.getPreferredSize());
		lblChucVu.setPreferredSize(lblTen.getPreferredSize());
		lblNgayVao.setPreferredSize(lblTen.getPreferredSize());

		JPanel pnCenS = new JPanel();
		JButton btnThem, btnXoa, btnSua, btnThoat, btnXoaTrang;
		pnCenS.add(btnThem = new JButton("Thêm Nhân Viên"));
		pnCenS.add(btnXoa = new JButton("Xóa Nhân Viên"));
		pnCenS.add(btnSua = new JButton("Sửa Thông Tin"));
		pnCenS.add(btnXoaTrang = new JButton("Xóa Trắng"));
		pnCenS.add(btnThoat = new JButton("Thoát"));

		pnCenN.add(b);
		pnCenter.add(pnCenN, BorderLayout.NORTH);
		pnCenter.add(pnCenC, BorderLayout.CENTER);
		pnCenter.add(pnCenS, BorderLayout.SOUTH);

		// pnSouth
		pnSouth = new JPanel();
		NhanVien_DAO nvDao = new NhanVien_DAO();
		NV_TableModel model = new NV_TableModel(nvDao.getLS());
		JTable table = new JTable();
		table.setModel(model);
		table.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int r = table.getSelectedRow();
				if (r != -1) {
					txtMa.setEditable(false);
					txtMa.setText(table.getValueAt(r, 0).toString());
					txtTen.setText(table.getValueAt(r, 1).toString());
					if (table.getValueAt(r, 2).toString()
							.equalsIgnoreCase("Nam"))
						cbcGT.setSelectedItem("Nam");
					else
						cbcGT.setSelectedItem("Nữ");

					namSinh.setDate(java.sql.Date.valueOf(table
							.getValueAt(r, 3).toString()));
					txtSDT.setText(table.getValueAt(r, 4).toString());
					txtEmail.setText(table.getValueAt(r, 5).toString());
					txtCM.setText(table.getValueAt(r, 6).toString());
					txtdiaChi.setText(table.getValueAt(r, 7).toString());
					if (table.getValueAt(r, 8).toString()
							.equalsIgnoreCase("Nhân Viên Kinh Doanh"))
						cbcChucVu.setSelectedItem("Nhân Viên Kinh Doanh");
					else
						cbcChucVu.setSelectedItem("Nhân viên thu ngân");
					NgayVao.setDate(java.sql.Date.valueOf(table
							.getValueAt(r, 9).toString()));
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

		txtMa.setEditable(false);
		pnSouth.add(sc);
		pnSouth.setBorder(new TitledBorder("Danh Sách Nhân Viên"));

		// Su kien Them
		btnThem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				try {
					if (!txtTen.getText().equals("")
							&& !txtCM.getText().equals("")
							&& !txtdiaChi.getText().equals("")
							&& !txtEmail.getText().equals("")
							&& !txtSDT.getText().equals("")) {
						String ten = txtTen.getText();
						String sdt = txtSDT.getText();
						int sCM = Integer.parseInt(txtCM.getText());
						String email = txtEmail.getText();
						String diaChi = txtdiaChi.getText();
						ChucVu chucVu = new ChucVu();
						ChucVu_DAO chucVu_DAo = new ChucVu_DAO();
						chucVu = chucVu_DAo.TimKiemTen(cbcChucVu
								.getSelectedItem().toString());
						NhanVien nhanVien = new NhanVien();
						nhanVien.setChucVu(chucVu);
						nhanVien.setCMND(sCM);
						nhanVien.setDiaChi(diaChi);
						nhanVien.setDienThoai(sdt);
						nhanVien.setEmail(email);
						nhanVien.setGioiTinh(cbcGT.getSelectedItem().toString());
						nhanVien.setNgaySinh(namSinh.getDate());
						nhanVien.setNgayVaoLam(NgayVao.getDate());
						nhanVien.setTenNV(ten);
						if (nvDao.isNhanVienUnique(ten)) {
							if (nvDao.addNhanVien(nhanVien)) {
								NhanVien nv = nvDao.TimKiemTen(ten);
								JOptionPane.showMessageDialog(null,
										"Thêm nhân viên thành công");
								model.addNhanVien(nv);
								clearText();
							} else {
								JOptionPane.showMessageDialog(null,
										"Thêm nhân viên thất bại");
							}

						} else {
							JOptionPane.showMessageDialog(null,
									"Nhân Viên Đã Có");
						}
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
						Object maNV = model.getValueAt(r, 0);
						model.xoaNV(r);
						nvDao.deleteNV(maNV.toString());
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
						String maNV = txtMa.getText();
						String ten = txtTen.getText();
						String sdt = txtSDT.getText();
						int sCM = Integer.parseInt(txtCM.getText());
						String email = txtEmail.getText();
						String diaChi = txtdiaChi.getText();
						ChucVu chucVu = new ChucVu();
						ChucVu_DAO chucVu_DAo = new ChucVu_DAO();
						chucVu = chucVu_DAo.TimKiemTen(cbcChucVu
								.getSelectedItem().toString());

						NhanVien nhanVien = new NhanVien();
						nhanVien.setChucVu(chucVu);
						nhanVien.setCMND(sCM);
						nhanVien.setDiaChi(diaChi);
						nhanVien.setDienThoai(sdt);
						nhanVien.setEmail(email);
						nhanVien.setGioiTinh(cbcGT.getSelectedItem().toString());
						nhanVien.setNgaySinh(namSinh.getDate());
						nhanVien.setNgayVaoLam(NgayVao.getDate());
						nhanVien.setTenNV(ten);
						if (nvDao.updateNhanVien(maNV, nhanVien)) {
							model.update(nvDao.getLS());
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
		txtCM.setText("");
		txtdiaChi.setText("");
		txtEmail.setText("");
		txtSDT.setText("");
		cbcChucVu.setSelectedIndex(0);
		cbcGT.setSelectedIndex(0);
		txtTen.requestFocus();
	}
}
