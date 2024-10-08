package gui;

import entity.HoaDonNhapHang;
import entity.NhaCungCap;
import entity.NhanVien;
import tableModel.NCC_TableModel;

import javax.swing.*;

import DAO.HoaDonNhapHang_DAO;
import DAO.NhaCungCap_DAO;
import DAO.NhanVien_DAO;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DS_NhaCungCap_Form extends JFrame {
    NhapHang_Form nhapHang;
    NhaCungCap ncc;
    public DS_NhaCungCap_Form(){
        doShow();
    }
    public void doShow(){
    	setSize(700,300);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setTitle("Danh Sách Nhà Cung Cấp");
        Container cp =getContentPane();

        //pnNorth
        JPanel pnNorth = new JPanel();
        JLabel lbltieuDe = new JLabel("DANH SÁCH NHÀ CUNG CẤP");
        pnNorth.add(lbltieuDe);

        //pnCenter
        JPanel pnCenter = new JPanel();
        List<NhaCungCap> ls = new ArrayList<>();
        NhaCungCap_DAO nccDao = new NhaCungCap_DAO();
        NCC_TableModel model = new NCC_TableModel(nccDao.getLS());
        JTable table = new JTable();
        table.setModel(model);
        table.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int r = table.getSelectedRow();
                if(r != -1){

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
        JScrollPane sc = new JScrollPane(table,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        sc.setPreferredSize(new Dimension(650,170));
        pnCenter.add(sc);

        //South
        JPanel pnSouth = new JPanel();
        JButton btnLapHD = new JButton("Lập Hóa Đơn");
        pnSouth.add(btnLapHD);


        btnLapHD.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int r = table.getSelectedRow();
                if(r != -1){
                	HoaDonNhapHang hd = new HoaDonNhapHang();
                    ncc = nccDao.TimKiemMa(table.getValueAt(r,0).toString());
                    nhapHang.ncc = ncc;
                    nhapHang.txtNhaCC.setText(ncc.getTenNhaCC());
                    nhapHang.txtSDT.setText(ncc.getSoDT());
                    nhapHang.txtDiaChi.setText(ncc.getDiaChi());
                    nhapHang.txtMail.setText(ncc.geteMail());
                    NhanVien_DAO nhanVienDAO = new NhanVien_DAO();
					NhanVien nv = nhanVienDAO.TimKiemTen((String) nhapHang.cbcNV
							.getSelectedItem());
                    hd.setNhaCungCap(ncc);
                    hd.setNhanVien(nv);
                    hd.setNgayLapHD(nhapHang.NgayLap.getDate());
                    nhapHang.hd=hd;
                    HoaDonNhapHang_DAO hdDao = new HoaDonNhapHang_DAO();
                    if (hdDao.addHDNH(hd)) {
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
        new DS_NhaCungCap_Form().setVisible(true);
    }
}
