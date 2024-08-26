package DAO;

import entity.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connectDB.ConnectDB;

public class CT_HDNH_DAO {
	private Connection con;

	public CT_HDNH_DAO() {

	}

	public boolean addCTHDNH(CT_HoaDonNhapHang cthd) {
		Connection con = ConnectDB.getInstance().getConnection();
		PreparedStatement stmt = null;
		try {
			stmt = con
					.prepareStatement("INSERT INTO CT_HoaDonNhapHang ([MAHDNH],[MAT],[SOLUONG],[DONGIA]) "
							+ "VALUES(?,?,?,?)");
			stmt.setString(1, cthd.getHoaDonNhapHang().getMaHDNH());
			stmt.setString(2, cthd.getThuoc().getMaThuoc());
			stmt.setInt(3, cthd.getSoLuong());
			stmt.setDouble(4, cthd.getDonGia());
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

	public CT_HoaDonNhapHang TimKiemMaT(String ma) {
		CT_HoaDonNhapHang ct = null;
		Connection con = ConnectDB.getInstance().getConnection();
		PreparedStatement stmt = null;
		try {
			stmt = con
					.prepareStatement("select * from CT_HOADONNHAPHANG where MAT = ?");
			stmt.setString(1, ma);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				ct = new CT_HoaDonNhapHang(rs.getInt(3), rs.getDouble(4));
				HoaDonNhapHang_DAO hdDao = new HoaDonNhapHang_DAO();
				Thuoc_DAO thuocDao = new Thuoc_DAO();
				HoaDonNhapHang hd = hdDao.TimKiemMa(rs.getString(1));
				Thuoc thuoc = thuocDao.TimKiemMa(rs.getString(2));
				ct.setHoaDonNhapHang(hd);
				ct.setThuoc(thuoc);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ct;
	}

	public List<CT_HoaDonNhapHang> TimKiemHD(String ma) {
		List<CT_HoaDonNhapHang> ls = new ArrayList<>();
		Connection con = ConnectDB.getInstance().getConnection();
		PreparedStatement stmt = null;
		try {
			stmt = con
					.prepareStatement("select * from CT_HOADONNHAPHANG where MAHDNH = ?");
			stmt.setString(1, ma);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				CT_HoaDonNhapHang ct = new CT_HoaDonNhapHang(rs.getInt(3),
						rs.getDouble(4));
				HoaDonNhapHang_DAO hdDao = new HoaDonNhapHang_DAO();
				Thuoc_DAO thuocDao = new Thuoc_DAO();
				HoaDonNhapHang hd = hdDao.TimKiemMa(rs.getString(1));
				Thuoc thuoc = thuocDao.TimKiemMa(rs.getString(2));
				ct.setHoaDonNhapHang(hd);
				ct.setThuoc(thuoc);
				ls.add(ct);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ls;
	}

	public CT_HoaDonNhapHang TimKiemMaHD(String ma, String maT) {
		CT_HoaDonNhapHang ct = null;
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		try {
			PreparedStatement stmt = con
					.prepareStatement("select * from CT_HoaDonNhapHang where MAHDNH = ? AND maT = ?");
			stmt.setString(1, ma);
			stmt.setString(2, maT);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				ct = new CT_HoaDonNhapHang(rs.getInt(3), rs.getDouble(4));
				HoaDonNhapHang_DAO hdDao = new HoaDonNhapHang_DAO();
				Thuoc_DAO thuocDao = new Thuoc_DAO();
				HoaDonNhapHang hd = hdDao.TimKiemMa(rs.getString(1));
				Thuoc thuoc = thuocDao.TimKiemMa(rs.getString(2));
				ct.setHoaDonNhapHang(hd);
				ct.setThuoc(thuoc);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ct;
	}

	public boolean updateSoLuong(String maHD, String maT, int sl) {
		Connection con = ConnectDB.getInstance().getConnection();
		PreparedStatement stmt = null;
		String sql = "update CT_HOADONNHAPHANG set SOLUONG = ? where MAHDNH = ? and MAT = ?";
		try {
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, sl);
			stmt.setString(2, maHD);
			stmt.setString(3, maT);

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
