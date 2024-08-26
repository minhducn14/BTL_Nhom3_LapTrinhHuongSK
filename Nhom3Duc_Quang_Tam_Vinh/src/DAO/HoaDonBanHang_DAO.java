package DAO;

import entity.HoaDonBanHang;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import connectDB.ConnectDB;

public class HoaDonBanHang_DAO {
	private Connection con;

	public HoaDonBanHang_DAO() {
		ConnectDB.getInstance();
		con = ConnectDB.getConnection();
	}

	public boolean addHDBH(HoaDonBanHang hd) {
		PreparedStatement stmt = null;
		try {
			stmt = con
					.prepareStatement("INSERT INTO HoaDonBanHang (MAKH, MANV, NGAYLAPHD, TONGTIEN)VALUES (?,?, ?, ?)");
			stmt.setString(1, hd.getKhachHang().getMaKH());
			stmt.setString(2, hd.getNhanVien().getMaNV());
			Date ngayLap = new Date(hd.getNgayLapHD().getTime());
			stmt.setDate(3, ngayLap);
			stmt.setDouble(4, hd.getTongTien());
			int n = stmt.executeUpdate();
			if (n > 0)
				return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}

	public String getMa() throws SQLException {
		String ma = "";
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stmt = null;
		try {
			stmt = con
					.prepareStatement("SELECT MAX([MAHDBH]) FROM [dbo].[HoaDonBanHang]");
		} catch (SQLException e) {
			e.printStackTrace();
		}

		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
			ma = rs.getString(1);
		}
		return ma;
	}

	public HoaDonBanHang TimKiemMa(String ma) {
		HoaDonBanHang hdbh = null;
		KhachHang_DAO khDao;
		NhanVien_DAO nvDao;
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stmt = null;
		try {
			stmt = con
					.prepareStatement("select * from HoaDonBanHang where MAHDBH = ?");
			stmt.setString(1, ma);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				khDao = new KhachHang_DAO();
				nvDao = new NhanVien_DAO();
				hdbh = new HoaDonBanHang(rs.getString(1), khDao.TimKiemMa(rs
						.getString(2)), nvDao.TimKiemMa(rs.getString(3)),
						rs.getDate(4));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return hdbh;
	}

	public boolean updateTongtien(String maHD, double tongTien) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stmt = null;
		String sql = "update HoaDonBanHang set TONGTIEN = ? where MAHDBH = ?";
		try {
			stmt = con.prepareStatement(sql);
			stmt.setDouble(1, tongTien);
			stmt.setString(2, maHD);
			int n = stmt.executeUpdate();
			if (n > 0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
}
