package gui;

import entity.Loaithuoc;
import tableModel.LoaiThuoc_TableModel;

import javax.swing.*;
import javax.swing.border.TitledBorder;

import DAO.LoaiThuoc_DAO;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.print.PrinterJob;

public class LoaiThuoc_Form extends JPanel {
	/**
     *
     */
	private static final long serialVersionUID = 1L;
	JPanel pnNorth, pnCenter, pnSouth;
	JLabel lblMa, lblTen;
	JTextField txtMa, txtTen;

	public LoaiThuoc_Form() {
		doShow();
	}

	public void doShow() {
		// pnNorth
		pnNorth = new JPanel();
		JPanel pnTieuDe = new JPanel();
		pnNorth.setLayout(new BorderLayout());
		JLabel lblTieuDe = new JLabel("QUẢN LÝ LOẠI THUỐC");
		lblTieuDe.setFont(new Font("arial", Font.BOLD, 20));
		lblTieuDe.setForeground(Color.RED);
		pnTieuDe.add(lblTieuDe);
		pnNorth.add(pnTieuDe);

		// pnCenter
		pnCenter = new JPanel();
		pnCenter.setLayout(new BorderLayout());
		JPanel pnCenN = new JPanel();
		JPanel pnCenC = new JPanel();
		Box b, b1;
		b = Box.createVerticalBox();
		b.setPreferredSize(new Dimension(840, 70));

		b.add(Box.createVerticalStrut(30));
		b.add(b1 = Box.createHorizontalBox());

		b1.add(lblMa = new JLabel("Mã Loại Thuốc: "));
		b1.add(txtMa = new JTextField());
		b1.add(Box.createHorizontalStrut(20));
		b1.add(lblTen = new JLabel("Tên Loại Thuốc:    "));
		b1.add(txtTen = new JTextField());
		b.add(Box.createVerticalStrut(10));

		lblMa.setPreferredSize(lblTen.getPreferredSize());

		JPanel pnCenS = new JPanel();
		JButton btnThem, btnXoa, btnSua, btnThoat, btnXoaTrang;
		pnCenS.add(btnThem = new JButton("Thêm Loại Thuốc"));
		pnCenS.add(btnXoa = new JButton("Xóa Loại Thuốc"));
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
		LoaiThuoc_DAO ltDao = new LoaiThuoc_DAO();
		LoaiThuoc_TableModel model = new LoaiThuoc_TableModel(ltDao.getLS());
		JTable table = new JTable();
		table.setModel(model);
		table.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int r = table.getSelectedRow();
				if (r != -1) {
					txtMa.setText(table.getValueAt(r, 1).toString());
					txtTen.setText(table.getValueAt(r, 2).toString());
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

		pnSouth.add(sc);
		pnSouth.setBorder(new TitledBorder("Danh Sách Loại Thuốc"));

		btnThem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				String tenLt = txtTen.getText().trim();
				Loaithuoc lt = new Loaithuoc();
				lt.setTenThuoc(tenLt);
				if (!tenLt.isEmpty()) {
					if (ltDao.isTenLoaiThuocUnique(tenLt)) {
						if (ltDao.addLoaiThuoc(lt)) {
							Loaithuoc lt1 = ltDao.TimKiemTen(tenLt);
							JOptionPane.showMessageDialog(null,
									"Thêm loại thuốc thành công");
							model.addLoaiThuoc(lt1);
							clearText();
						} else {
							JOptionPane.showMessageDialog(null,
									"Thêm loại thuốc thất bại");
						}

					} else {
						JOptionPane.showMessageDialog(null,
								"Thêm loại thuốc thất bại");
					}
				} else {
					JOptionPane.showMessageDialog(null,
							"Vui lòng nhập tên loại thuốc");
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
						Object maLoai = model.getValueAt(r, 1);
						model.xoaLoaiThuoc(r);
						ltDao.deleteLT(maLoai.toString());
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
						String maLoaiThuoc = txtMa.getText().trim();
						Loaithuoc lt = new Loaithuoc(txtMa.getText(), txtTen
								.getText());
						if (ltDao.updateLoaiThuoc(maLoaiThuoc, lt)) {
							model.update(ltDao.getLS());
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
		txtTen.requestFocus();
	}
}
