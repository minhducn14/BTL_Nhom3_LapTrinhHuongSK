package DAO;

import entity.HoaDonNhapHang;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import connectDB.ConnectDB;

public class HoaDonNhapHang_DAO {
	private Connection con;

	public HoaDonNhapHang_DAO() {
		ConnectDB.getInstance();
		con = ConnectDB.getConnection();
	}

	public boolean addHDNH(HoaDonNhapHang hd) {

		PreparedStatement stmt = null;
		try {
			stmt = con
					.prepareStatement("INSERT INTO HoaDonNhapHang (MANHACC, MANV, NGAYLAPHD, TONGTIEN)VALUES (?,?, ?, ?)");
			stmt.setString(1, hd.getNhaCungCap().getMaNhaCC());
			stmt.setString(2, hd.getNhanVien().getMaNV());
			Date ngayLap = new Date(hd.getNgayLapHD().getTime());
			stmt.setDate(3, ngayLap);
			stmt.setDouble(4, hd.getTongTien());
			int n = stmt.executeUpdate();
			if (n > 0)
				return true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(stmt);
		}
		return false;
	}

	public void close(PreparedStatement stmt) {
		if (stmt != null) {
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public String getMa() throws SQLException {
		String ma = "";
		Connection con = ConnectDB.getInstance().getConnection();
		PreparedStatement stmt = null;
		try {
			stmt = con
					.prepareStatement("SELECT MAX([MAHDNH]) FROM [dbo].[HoaDonNhapHang]");
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}

		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
			ma = rs.getString(1);
		}
		return ma;
	}

	public HoaDonNhapHang TimKiemMa(String ma) {
		HoaDonNhapHang hdnh = null;
		NhaCungCap_DAO nccDao;
		NhanVien_DAO nvDao;
		Connection con = ConnectDB.getInstance().getConnection();
		PreparedStatement stmt = null;
		try {
			stmt = con
					.prepareStatement("select * from HoaDonNhapHang where MAHDNH = ?");
			stmt.setString(1, ma);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				nccDao = new NhaCungCap_DAO();
				nvDao = new NhanVien_DAO();
				hdnh = new HoaDonNhapHang(rs.getString(1), nccDao.TimKiemMa(rs
						.getString(2)), nvDao.TimKiemMa(rs.getString(3)),
						rs.getDate(4));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return hdnh;
	}

	public boolean updateTongtien(String maHD, double tongTien) {
		Connection con = ConnectDB.getInstance().getConnection();
		PreparedStatement stmt = null;
		String sql = "update HoaDonNhapHang set TONGTIEN = ? where MAHDNH = ?";
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
