package gui;

import entity.Loaithuoc;
import entity.Thuoc;
import tableModel.Thuoc_TableModel;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import DAO.LoaiThuoc_DAO;
import DAO.ThongKe_DAO;
import DAO.Thuoc_DAO;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class ThongKe_Form extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -343354033986428930L;
	JTable table;
	DefaultTableModel model;
	JPanel pnNorth, pnCenter;
	JRadioButton rdThuocBanTrongNgay, rdThuocConLai, rdDoanhThu;
	ButtonGroup btnGR;
	JButton btnThongKe, btnThoat;
	JComboBox cbcTKDoanhThu;
	ThongKe_DAO thongKe = new ThongKe_DAO();

	public ThongKe_Form() {
		doShow();
	}

	public void doShow() {
		// pnNorth
		pnNorth = new JPanel();
		JPanel pnNorth_N = new JPanel();
		JLabel lblTieuDe = new JLabel("THỐNG KÊ");
		lblTieuDe.setFont(new Font("arial", Font.BOLD, 20));
		lblTieuDe.setForeground(Color.RED);
		pnNorth_N.add(lblTieuDe);

		JPanel pnNorth_C = new JPanel();
		Box b, b1, b2;
		b = Box.createVerticalBox();
		b.setPreferredSize(new Dimension(700, 130));
		b.add(Box.createVerticalStrut(20));
		b.add(b1 = Box.createHorizontalBox());
		b1.add(rdThuocBanTrongNgay = new JRadioButton("Thuốc Bán Trong Ngày"));
		b1.add(Box.createHorizontalStrut(30));
		b1.add(rdThuocConLai = new JRadioButton("Thuốc Còn Lại"));
		b1.add(Box.createHorizontalStrut(30));
		b1.add(rdDoanhThu = new JRadioButton("Thống Kê Doanh Thu"));
		b1.add(Box.createHorizontalStrut(30));
		b1.add(cbcTKDoanhThu = new JComboBox<>());
		cbcTKDoanhThu.addItem("Ngày");
		cbcTKDoanhThu.addItem("Tháng");
		cbcTKDoanhThu.addItem("Quý");
		cbcTKDoanhThu.addItem("Năm");

		b.add(Box.createVerticalStrut(20));

		b.add(b2 = Box.createHorizontalBox());
		b2.add(btnThongKe = new JButton("Thống Kê"));
		b2.add(Box.createHorizontalStrut(100));
		b2.add(btnThoat = new JButton("Thoát"));
		b.add(Box.createVerticalStrut(60));

		btnGR = new ButtonGroup();
		btnGR.add(rdThuocBanTrongNgay);
		btnGR.add(rdThuocConLai);
		btnGR.add(rdDoanhThu);
		pnNorth_C.add(b);

		pnNorth.setLayout(new BorderLayout());
		pnNorth.add(pnNorth_N, BorderLayout.NORTH);
		pnNorth.add(pnNorth_C, BorderLayout.CENTER);

		this.setLayout(new BorderLayout());
		pnNorth_C.setBorder(new TitledBorder("Các loại thống kê"));

		// pnCenter
		pnCenter = new JPanel();

		pnCenter.setBorder(new TitledBorder("Kết Quả Thống Kê"));

		btnThongKe.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (rdThuocBanTrongNgay.isSelected()) {
					pnCenter.removeAll();
					model = thongKe.getThuocBanTrongNgay();
					table = new JTable(model);
					JScrollPane sc = new JScrollPane(table,
							JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
							JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
					sc.setPreferredSize(new Dimension(850, 300));
					pnCenter.add(sc);
					pnCenter.revalidate();
					pnCenter.repaint();
					JOptionPane.showMessageDialog(null, "Da Thong Ke Xong");
				} else if (rdThuocConLai.isSelected()) {
					pnCenter.removeAll();
					model = thongKe.getThuocConLai();
					table = new JTable(model);
					JScrollPane sc = new JScrollPane(table,
							JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
							JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
					sc.setPreferredSize(new Dimension(850, 300));
					pnCenter.add(sc);
					pnCenter.revalidate();
					pnCenter.repaint();
					JOptionPane.showMessageDialog(null, "Da Thong Ke Xong");
				} else if (rdDoanhThu.isSelected()) {
					if (cbcTKDoanhThu.getSelectedIndex() == 0) {
						pnCenter.removeAll();
						model = thongKe.thongKeDoanhThuTheoNgay();
						table = new JTable(model);
						JScrollPane sc = new JScrollPane(table,
								JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
								JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
						sc.setPreferredSize(new Dimension(850, 300));
						pnCenter.add(sc);
						pnCenter.revalidate();
						pnCenter.repaint();
						JOptionPane.showMessageDialog(null, "Da Thong Ke Xong");
					} else if (cbcTKDoanhThu.getSelectedIndex() == 1) {
						pnCenter.removeAll();
						model = thongKe.thongKeDoanhThuTheoThang();
						table = new JTable(model);
						JScrollPane sc = new JScrollPane(table,
								JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
								JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
						sc.setPreferredSize(new Dimension(850, 300));
						pnCenter.add(sc);
						pnCenter.revalidate();
						pnCenter.repaint();
						JOptionPane.showMessageDialog(null, "Da Thong Ke Xong");
					} else if (cbcTKDoanhThu.getSelectedIndex() == 2) {
						pnCenter.removeAll();
						model = thongKe.thongKeDoanhThuTheoQuy();
						table = new JTable(model);
						JScrollPane sc = new JScrollPane(table,
								JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
								JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
						sc.setPreferredSize(new Dimension(850, 300));
						pnCenter.add(sc);
						pnCenter.revalidate();
						pnCenter.repaint();
						JOptionPane.showMessageDialog(null, "Da Thong Ke Xong");
					} else if (cbcTKDoanhThu.getSelectedIndex() == 3) {
						pnCenter.removeAll();
						model = thongKe.thongKeDoanhThuTheoNam();
						table = new JTable(model);
						JScrollPane sc = new JScrollPane(table,
								JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
								JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
						sc.setPreferredSize(new Dimension(850, 300));
						pnCenter.add(sc);
						pnCenter.revalidate();
						pnCenter.repaint();
						JOptionPane.showMessageDialog(null, "Da Thong Ke Xong");
					}
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
		this.add(pnNorth, BorderLayout.NORTH);
		this.add(pnCenter, BorderLayout.CENTER);
	}
}
