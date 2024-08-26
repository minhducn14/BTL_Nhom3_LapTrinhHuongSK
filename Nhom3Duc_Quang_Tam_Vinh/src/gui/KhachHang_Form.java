package gui;

import DAO.KhachHang_DAO;

import com.toedter.calendar.JDateChooser;

import entity.KhachHang;
import tableModel.KH_TableModel;

import javax.swing.*;
import javax.swing.border.TitledBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Date;
import java.time.LocalDate;

public class KhachHang_Form extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JPanel pnNorth, pnCenter, pnSouth;
	JLabel lblMa, lblKhachHang, lblGioiTinh, lblNamSinh, lblEmail, lblSDT,
			lblCM, lbldiaChi;
	JTextField txtMa, txtKhachHang, txtEmail, txtSDT, txtCM, txtdiaChi;
	JComboBox<String> cbcGT;
	JDateChooser namSinh;

	public KhachHang_Form() {
		doShow();
	}

	public void doShow() {
		// pnNorth
		pnNorth = new JPanel();
		JPanel pnTieuDe = new JPanel();
		pnNorth.setLayout(new BorderLayout());
		JLabel lblTieuDe = new JLabel("QUẢN LÝ KHÁCH HÀNG");
		lblTieuDe.setFont(new Font("arial", Font.BOLD, 20));
		lblTieuDe.setForeground(Color.RED);
		pnTieuDe.add(lblTieuDe);
		pnNorth.add(pnTieuDe);

		// pnCenter
		pnCenter = new JPanel();
		pnCenter.setLayout(new BorderLayout());
		Box b, b1, b2, b3, b4;
		JPanel pnCenN = new JPanel();
		JPanel pnCenC = new JPanel();
		b = Box.createVerticalBox();
		b.setPreferredSize(new Dimension(840, 150));

		b.add(b1 = Box.createHorizontalBox());
		b1.add(lblMa = new JLabel("Mã Khách Hàng: "));
		b1.add(txtMa = new JTextField(30));
		b1.add(Box.createHorizontalStrut(20));
		b1.add(lblKhachHang = new JLabel("Họ Tên Khách Hàng:    "));
		b1.add(txtKhachHang = new JTextField(30));
		b.add(Box.createVerticalStrut(10));

		b.add(b2 = Box.createHorizontalBox());
		b2.add(lblGioiTinh = new JLabel("Giới Tính: "));
		cbcGT = new JComboBox<>();
		cbcGT.addItem("Nam");
		cbcGT.addItem("Nữ");
		cbcGT.setPreferredSize(new Dimension(300, 20));
		b2.add(cbcGT);
		b2.add(Box.createHorizontalStrut(20));
		b2.add(lblNamSinh = new JLabel("Ngày Sinh:    "));
		namSinh = new JDateChooser();
		namSinh.setSize(new Dimension(45, 20));
		namSinh.setDateFormatString("yyyy-MM-dd");
		try {
			Date date = Date.valueOf(LocalDate.now());
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

		lblMa.setPreferredSize(lblKhachHang.getPreferredSize());
		lblEmail.setPreferredSize(lblKhachHang.getPreferredSize());
		lblCM.setPreferredSize(lblKhachHang.getPreferredSize());
		lblGioiTinh.setPreferredSize(lblKhachHang.getPreferredSize());
		lblNamSinh.setPreferredSize(lblKhachHang.getPreferredSize());
		lblSDT.setPreferredSize(lblKhachHang.getPreferredSize());
		lbldiaChi.setPreferredSize(lblKhachHang.getPreferredSize());

		JPanel pnCenS = new JPanel();
		JButton btnThem, btnXoa, btnSua, btnThoat, btnXoaTrang;
		pnCenS.add(btnThem = new JButton("Thêm Khách Hàng"));
		pnCenS.add(btnXoa = new JButton("Xóa Khách Hàng"));
		pnCenS.add(btnSua = new JButton("Sửa Thông Tin"));
		pnCenS.add(btnXoaTrang = new JButton("Xóa Trắng"));
		pnCenS.add(btnThoat = new JButton("Thoát"));

		pnCenN.add(b);
		pnCenter.add(pnCenN, BorderLayout.NORTH);
		pnCenter.add(pnCenC, BorderLayout.CENTER);
		pnCenter.add(pnCenS, BorderLayout.SOUTH);

		// pnSouth
		pnSouth = new JPanel();
		KhachHang_DAO khDao = new KhachHang_DAO();
		KH_TableModel model = new KH_TableModel(khDao.getLS());
		JTable table = new JTable();
		table.setModel(model);
		table.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int r = table.getSelectedRow();
				if (r != -1) {
					txtMa.setEditable(false);
					txtMa.setText(table.getValueAt(r, 0).toString());
					txtKhachHang.setText(table.getValueAt(r, 1).toString());
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
		sc.setPreferredSize(new Dimension(850, 300));

		txtMa.setEditable(false);
		pnSouth.add(sc);
		pnSouth.setBorder(new TitledBorder("Danh Sách Khách Hàng"));

		btnThem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					if (txtKhachHang.getText().equals("")
							|| txtSDT.getText().equals("")
							|| txtCM.getText().equals("")
							|| txtdiaChi.getText().equals("")
							|| txtEmail.getText().equals("")) {
						JOptionPane.showMessageDialog(null,
								"Vui lòng nhập đầy đủ thông tin");
					} else {
						String tenKH = txtKhachHang.getText();
						String email = txtEmail.getText();
						String sdt = txtSDT.getText();
						int cmnd = Integer.parseInt(txtCM.getText());
						String diaChi = txtdiaChi.getText();
						String gioiTinh = cbcGT.getSelectedItem().toString();

						KhachHang khachHang = new KhachHang();
						khachHang.setTenKH(tenKH);
						khachHang.setEmail(email);
						khachHang.setDienThoai(sdt);
						khachHang.setCMND(cmnd);
						khachHang.setDiaChi(diaChi);
						khachHang.setGioiTinh(gioiTinh);
						khachHang.setNgaySinh(namSinh.getDate());
						if (khDao.isKhacHangUnique(tenKH)) {
							if (khDao.addKhachHang(khachHang)) {
								KhachHang kh = khDao.TimKiemTen(tenKH);
								JOptionPane.showMessageDialog(null,
										"Thêm khách hàng thành công");
								model.addKhachHang(kh);
								;
								cleartext();
							} else {
								JOptionPane.showMessageDialog(null,
										"Thêm khách hàng thất bại");
							}

						} else {
							JOptionPane.showMessageDialog(null,
									"Thêm loại khách hàng thất bại");
						}
					}

				} catch (Exception e2) {
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
						Object maKH = model.getValueAt(r, 0);
						model.xoaKhachHang(r);
						khDao.deleteKH(maKH.toString());
						cleartext();
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
		        if(r != -1)
		        {
                    int lc = JOptionPane.showConfirmDialog(null,"Bạn có chắc chắn muốn sửa!","update",JOptionPane.YES_NO_OPTION);
                    if(lc == JOptionPane.YES_OPTION) {
                    	String maKH= txtMa.getText();
                    	String tenKH = txtKhachHang.getText();
						String email = txtEmail.getText();
						String sdt = txtSDT.getText();
						int cmnd = Integer.parseInt(txtCM.getText());
						String diaChi = txtdiaChi.getText();
						String gioiTinh = cbcGT.getSelectedItem().toString();

						KhachHang khachHang = new KhachHang();
						khachHang.setTenKH(tenKH);
						khachHang.setEmail(email);
						khachHang.setDienThoai(sdt);
						khachHang.setCMND(cmnd);
						khachHang.setDiaChi(diaChi);
						khachHang.setGioiTinh(gioiTinh);
						khachHang.setNgaySinh(namSinh.getDate());
                        if (khDao.updateKhachHang(maKH, khachHang)) {
                            model.update(khDao.getLS());
                            cleartext();
                            JOptionPane.showMessageDialog(null,"Sửa thành công!");
                        }
                    }
		        }
		        else
		        {
		        	JOptionPane.showMessageDialog(null, "Bạn chưa chọn dữ liệu để sửa.");
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
				cleartext();

			}
		});
		this.setLayout(new BorderLayout());
		this.add(pnNorth, BorderLayout.NORTH);
		this.add(pnCenter, BorderLayout.CENTER);
		this.add(pnSouth, BorderLayout.SOUTH);

	}

	public void cleartext() {
		txtMa.setText("");
		txtKhachHang.setText("");
		txtCM.setText("");
		txtdiaChi.setText("");
		txtEmail.setText("");
		txtSDT.setText("");
		cbcGT.setSelectedIndex(0);
	}
}
