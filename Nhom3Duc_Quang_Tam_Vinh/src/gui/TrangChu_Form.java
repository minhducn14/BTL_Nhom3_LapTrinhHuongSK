package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class TrangChu_Form extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JPanel pnCenter;
	JLabel lblGiaoVien, lblTenGV, lblThanhVien, lblSV1, lbl1, lblSV2, lbl2,
			lblSV3, lbl3, lblSV4;

	public TrangChu_Form() {
		doShow();
	}

	public void doShow() {
		// PnCneter
		pnCenter = new JPanel();
		JPanel pnCenterN = new JPanel();
		JPanel pnCenterC = new JPanel();
		JPanel pnCenterS = new JPanel();
		pnCenter.setLayout(new BorderLayout());
		JLabel lblTieuDe = new JLabel("CHƯƠNG TRÌNH QUẢN LÝ QUẦY THUỐC");
		lblTieuDe.setFont(new Font("arial", Font.BOLD, 24));
		lblTieuDe.setForeground(Color.RED);
		pnCenterN.setPreferredSize(new Dimension(1000, 60));

		JLabel lbImage = new JLabel();
		lbImage.setIcon(new ImageIcon(getClass().getResource(
				"/images/banner2.png")));

		pnCenterN.add(lblTieuDe);
		pnCenterC.add(lbImage);
		pnCenter.add(pnCenterN, BorderLayout.NORTH);
		pnCenter.add(pnCenterC, BorderLayout.CENTER);

		// pnSouth
		JPanel pnSouth = new JPanel();

		Box b, b1, b2, b3, b4, b5;
		b = Box.createVerticalBox();
		b.add(b1 = Box.createHorizontalBox());
		b1.add(Box.createHorizontalStrut(200));
		b1.add(lblGiaoVien = new JLabel("Giáo Viên Hướng Dẫn: "));
		b1.add(Box.createHorizontalStrut(30));
		b1.add(lblTenGV = new JLabel("Tôn Long Phước"));
		b.add(Box.createVerticalStrut(30));

		b.add(b2 = Box.createHorizontalBox());
		b2.add(Box.createHorizontalStrut(200));
		b2.add(lblThanhVien = new JLabel("Thành Viên Nhóm: "));
		b2.add(Box.createHorizontalStrut(30));
		b2.add(lblSV1 = new JLabel("Vũ Nguyễn Minh Đức"));
		b.add(Box.createVerticalStrut(20));

		b.add(b3 = Box.createHorizontalBox());
		b3.add(Box.createHorizontalStrut(200));
		b3.add(lbl1 = new JLabel());
		b3.add(Box.createHorizontalStrut(30));
		b3.add(lblSV2 = new JLabel("Hoàng Công Khánh Quang"));
		b.add(Box.createVerticalStrut(20));

		b.add(b4 = Box.createHorizontalBox());
		b4.add(Box.createHorizontalStrut(200));
		b4.add(lbl2 = new JLabel());
		b4.add(Box.createHorizontalStrut(30));
		b4.add(lblSV3 = new JLabel("Trần Thanh Tâm"));
		b.add(Box.createVerticalStrut(20));

		b.add(b5 = Box.createHorizontalBox());
		b5.add(Box.createHorizontalStrut(200));
		b5.add(lbl3 = new JLabel());
		b5.add(Box.createHorizontalStrut(30));
		b5.add(lblSV4 = new JLabel("Mai Lưu Hữu Vinh"));
		b.add(Box.createVerticalStrut(30));

		lblThanhVien.setPreferredSize(lblGiaoVien.getPreferredSize());

		Font ft = new Font("arial", Font.BOLD, 17);
		lblGiaoVien.setFont(ft);
		lblTenGV.setFont(ft);
		lblThanhVien.setFont(ft);
		lbl1.setFont(ft);
		lbl2.setFont(ft);
		lbl3.setFont(ft);
		lblSV1.setFont(ft);
		lblSV2.setFont(ft);
		lblSV3.setFont(ft);
		lblSV4.setFont(ft);

		pnCenterS.add(b);
		pnCenter.add(pnCenterS, BorderLayout.SOUTH);
		add(pnCenter, BorderLayout.CENTER);
	}

}
