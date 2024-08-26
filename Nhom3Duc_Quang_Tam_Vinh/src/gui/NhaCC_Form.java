package gui;

import DAO.NhaCungCap_DAO;
import entity.NhaCungCap;
import tableModel.NCC_TableModel;

import javax.swing.*;
import javax.swing.border.TitledBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class NhaCC_Form extends JPanel {
	/**
     *
     */
	private static final long serialVersionUID = 1L;
	JPanel pnNorth, pnCenter, pnSouth;
	JLabel lblMa, lblNhacungCap, lblNamSinh, lblEmail, lblSDT, lblCM,
			lbldiaChi;
	JTextField txtMa, txtNhacungCap, txtEmail, txtSDT, txtdiaChi;

	public NhaCC_Form() {
		doShow();
	}

	public void doShow() {
		// pnNorth
		pnNorth = new JPanel();
		JPanel pnTieuDe = new JPanel();
		pnNorth.setLayout(new BorderLayout());
		JLabel lblTieuDe = new JLabel("QUẢN LÝ NHÀ CUNG CẤP");
		lblTieuDe.setFont(new Font("arial", Font.BOLD, 20));
		lblTieuDe.setForeground(Color.RED);
		pnTieuDe.add(lblTieuDe);
		pnNorth.add(pnTieuDe);

		// pnCenter
		pnCenter = new JPanel();
		pnCenter.setLayout(new BorderLayout());
		JPanel pnCenN = new JPanel();
		JPanel pnCenC = new JPanel();
		Box b, b1, b3, b4;
		b = Box.createVerticalBox();
		b.setPreferredSize(new Dimension(840, 100));

		b.add(b1 = Box.createHorizontalBox());
		b1.add(lblMa = new JLabel("Mã Nhà Cung Cấp: "));
		b1.add(txtMa = new JTextField(30));
		b1.add(Box.createHorizontalStrut(20));
		b1.add(lblNhacungCap = new JLabel("Tên Nhà Cung Cấp:    "));
		b1.add(txtNhacungCap = new JTextField(30));
		b.add(Box.createVerticalStrut(10));

		b.add(b3 = Box.createHorizontalBox());
		b3.add(lblEmail = new JLabel("Email:      "));
		b3.add(txtEmail = new JTextField(30));
		b3.add(Box.createHorizontalStrut(20));
		b3.add(lblSDT = new JLabel("Điện Thoại:     "));
		b3.add(txtSDT = new JTextField(30));
		b.add(Box.createVerticalStrut(10));

		b.add(b4 = Box.createHorizontalBox());
		b4.add(lbldiaChi = new JLabel("Địa Chỉ:    "));
		b4.add(txtdiaChi = new JTextField());
		b.add(Box.createVerticalStrut(10));

		lblMa.setPreferredSize(lblNhacungCap.getPreferredSize());
		lblEmail.setPreferredSize(lblNhacungCap.getPreferredSize());
		lblSDT.setPreferredSize(lblNhacungCap.getPreferredSize());
		lbldiaChi.setPreferredSize(lblNhacungCap.getPreferredSize());

		JPanel pnCenS = new JPanel();
		JButton btnThem, btnXoa, btnSua, btnThoat, btnXoaTrang;
		pnCenS.add(btnThem = new JButton("Thêm Nhà CC"));
		pnCenS.add(btnXoa = new JButton("Xóa Nhà CC"));
		pnCenS.add(btnSua = new JButton("Sửa Thông Tin"));
		pnCenS.add(btnXoaTrang = new JButton("Xóa Trắng"));
		pnCenS.add(btnThoat = new JButton("Thoát"));

		pnCenN.add(b);
		pnCenter.add(pnCenN, BorderLayout.NORTH);
		pnCenter.add(pnCenC, BorderLayout.CENTER);
		pnCenter.add(pnCenS, BorderLayout.SOUTH);

		// pnSouth
		pnSouth = new JPanel();
		NhaCungCap_DAO nccDao = new NhaCungCap_DAO();
		NCC_TableModel model = new NCC_TableModel(nccDao.getLS());
		JTable table = new JTable();
		table.setModel(model);
		table.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int r = table.getSelectedRow();
				if (r != -1) {
					txtMa.setText(table.getValueAt(r, 0).toString());
					txtNhacungCap.setText(table.getValueAt(r, 1).toString());
					txtSDT.setText(table.getValueAt(r, 2).toString());
					txtEmail.setText(table.getValueAt(r, 3).toString());
					txtdiaChi.setText(table.getValueAt(r, 4).toString());
					txtMa.setEditable(false);
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
		pnSouth.setBorder(new TitledBorder("Danh Sách Nhà Cung Cấp"));

		// Su kien Them
		btnThem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				if (txtNhacungCap.getText().equals("")
						|| txtEmail.getText().equals("")
						|| txtSDT.getText().equals("")
						|| txtdiaChi.getText().equals("")) {
					JOptionPane.showMessageDialog(null,
							"Vui lòng điền đầy đủ thông tin khách hàng!");
				} else {
					String tenNhaCungCap = txtNhacungCap.getText();
					String email = txtEmail.getText();
					String sdt = txtSDT.getText();
					String diaChi = txtdiaChi.getText();
					NhaCungCap nhaCungCap = new NhaCungCap();
					nhaCungCap.setTenNhaCC(tenNhaCungCap);
					nhaCungCap.setDiaChi(diaChi);
					nhaCungCap.seteMail(email);
					nhaCungCap.setSoDT(sdt);

					if (nccDao.isTenNhaCungCapUnique(tenNhaCungCap)) {
						if (nccDao.addNhaCungCap(nhaCungCap)) {
							NhaCungCap ncc = nccDao.TimKiemTen(tenNhaCungCap);
							JOptionPane.showMessageDialog(null,
									"Thêm Nhà cung cấp thành công");
							model.addNCC(ncc);
							clearText();
						} else {
							JOptionPane.showMessageDialog(null,
									"Thêm Nhà cung cấp thất bại");
						}

					} else {
						JOptionPane.showMessageDialog(null,
								"Nhà cung cấp Đã Có");
					}
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
						Object maNCC = model.getValueAt(r, 0);
						model.xoaNCC(r);
						nccDao.deleteNCC(maNCC.toString());
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
						String maNCC = txtMa.getText();
						String tenNhaCungCap = txtNhacungCap.getText();
						String email = txtEmail.getText();
						String sdt = txtSDT.getText();
						String diaChi = txtdiaChi.getText();
						NhaCungCap nhaCungCap = new NhaCungCap();
						nhaCungCap.setTenNhaCC(tenNhaCungCap);
						nhaCungCap.setDiaChi(diaChi);
						nhaCungCap.seteMail(email);
						nhaCungCap.setSoDT(sdt);
						if (nccDao.updateNhaCC(maNCC, nhaCungCap)) {
							model.update(nccDao.getLS());
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
		txtNhacungCap.setText("");
		txtdiaChi.setText("");
		txtEmail.setText("");
		txtSDT.setText("");

	}
}
