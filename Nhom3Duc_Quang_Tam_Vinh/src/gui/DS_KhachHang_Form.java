package gui;

import entity.HoaDonBanHang;
import entity.KhachHang;
import entity.NhanVien;
import tableModel.KH_TableModel;

import javax.swing.*;

import DAO.HoaDonBanHang_DAO;
import DAO.KhachHang_DAO;
import DAO.NhanVien_DAO;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DS_KhachHang_Form extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	BanHang_Form banhang;
	KhachHang kh;

	public DS_KhachHang_Form() {
		doShow();
	}

	public void doShow() {
		setSize(700, 300);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setTitle("Danh Sách Khách Hàng");
		Container cp = getContentPane();

		// pnNorth
		JPanel pnNorth = new JPanel();
		JLabel lbltieuDe = new JLabel("DANH SÁCH KHÁCH HÀNG");
		pnNorth.add(lbltieuDe);

		// pnCenter
		JPanel pnCenter = new JPanel();
		KhachHang_DAO khDao = new KhachHang_DAO();
		KH_TableModel model = new KH_TableModel(khDao.getLS());
		JTable table = new JTable();
		table.setModel(model);
		JScrollPane sc = new JScrollPane(table,
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		sc.setPreferredSize(new Dimension(650, 170));
		pnCenter.add(sc);

		// South
		JPanel pnSouth = new JPanel();
		JButton btnLapHD = new JButton("Lập Hóa Đơn");
		pnSouth.add(btnLapHD);

		btnLapHD.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int r = table.getSelectedRow();
				if (r != -1) {
					kh = khDao.TimKiemMa(table.getValueAt(r, 0).toString());
					banhang.kh = kh;
					banhang.txtKhachHang.setText(kh.getTenKH());
					banhang.txtSDT.setText(kh.getDienThoai());
					banhang.txtDiaChi.setText(kh.getDiaChi());
					NhanVien_DAO nhanVienDAO = new NhanVien_DAO();
					NhanVien nv = nhanVienDAO.TimKiemTen((String) banhang.cbcNV
							.getSelectedItem());
					HoaDonBanHang hd = new HoaDonBanHang();
					hd.setKhachHang(kh);
					hd.setNhanVien(nv);
					hd.setNgayLapHD(banhang.NgayLap.getDate());
					banhang.hd = hd;
					HoaDonBanHang_DAO hdDao = new HoaDonBanHang_DAO();
					
					if (hdDao.addHDBH(hd)) {
						setVisible(false);
					}
				}
			}
		});

		cp.setLayout(new BorderLayout());
		cp.add(pnNorth, BorderLayout.NORTH);
		cp.add(pnCenter, BorderLayout.CENTER);
		cp.add(pnSouth, BorderLayout.SOUTH);
	}

	public static void main(String[] args) {
		new DS_KhachHang_Form().setVisible(true);
	}
}
