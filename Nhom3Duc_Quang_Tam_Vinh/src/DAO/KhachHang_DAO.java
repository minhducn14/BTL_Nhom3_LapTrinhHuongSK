package DAO;

import connectDB.ConnectDB;
import entity.KhachHang;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class KhachHang_DAO {

	public KhachHang_DAO() {

	}

	public List<KhachHang> getLS() {
		List<KhachHang> ds = new ArrayList<>();
		Statement stmt = null;
		Connection con = null;
		try {
			ConnectDB.getInstance();
			con = ConnectDB.getConnection();
			String sql = "select * from KhachHang";
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				KhachHang kh = new KhachHang(rs.getString(1), rs.getString(2),
						rs.getString(3), rs.getDate(4), rs.getString(5),
						rs.getString(6), rs.getInt(7), rs.getString(8));
				ds.add(kh);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return ds;
	}

	public boolean isKhacHangUnique(String tenKhanhHang) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = con
					.prepareStatement("SELECT COUNT(*) FROM KhachHang WHERE TENKH = ?");
			stmt.setString(1, tenKhanhHang);
			rs = stmt.executeQuery();
			if (rs.next()) {
				int count = rs.getInt(1);
				if (count == 0) {
					return true;
				}
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
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

	public boolean addKhachHang(KhachHang kh) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stmt = null;
		try {
			stmt = con
					.prepareStatement("INSERT INTO KhachHang ([TENKH],[GIOITINH],[NGAYSINH],"
							+ "[EMAIL],[DIENTHOAI],[CMND],[DIACHI]) VALUES(?,?,?,?,?,?,?)");
			stmt.setString(1, kh.getTenKH());
			stmt.setString(2, kh.getGioiTinh());
			Date date = new Date(kh.getNgaySinh().getTime());
			stmt.setDate(3, date);
			stmt.setString(4, kh.getEmail());
			stmt.setString(5, kh.getDienThoai());
			stmt.setInt(6, kh.getCMND());
			stmt.setString(7, kh.getDiaChi());

			int n = stmt.executeUpdate();
			if (n > 0)
				return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}

	public boolean deleteKH(String maKH) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stmt = null;
		try {
			stmt = con.prepareStatement("delete from KhachHang where MAKH = ?");
			stmt.setString(1, maKH);
			int n = stmt.executeUpdate();
			if (n > 0)
				return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
        	close(stmt);
        }

        return false;
    }

	public boolean updateKhachHang(String maKH, KhachHang kh) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stmt = null;
		String sql = "update KhachHang set TENKH = ?, "
				+ "GIOITINH = ?,NGAYSINH = ? ,EMAIL = ?,DIENTHOAI = ? ,CMND = ? ,DIACHI = ? where MAKH = ?";
		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, kh.getTenKH());
			stmt.setString(2, kh.getGioiTinh());
			Date date = new Date(kh.getNgaySinh().getTime());
			stmt.setDate(3, date);
			stmt.setString(4, kh.getEmail());
			stmt.setString(5, kh.getDienThoai());
			stmt.setInt(6, kh.getCMND());
			stmt.setString(7, kh.getDiaChi());
			stmt.setString(8, maKH);

			int n = stmt.executeUpdate();
			if (n > 0)
				return true;

		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
        	close(stmt);
        }

        return false;
    }
	public KhachHang TimKiemMa(String ma) {
		KhachHang kh = null;
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stmt = null;
		try {
			stmt = con
					.prepareStatement("select * from KhachHang where MAKH = ?");
			stmt.setString(1, ma);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				kh = new KhachHang(rs.getString(1), rs.getString(2),
						rs.getString(3), rs.getDate(4), rs.getString(5),
						rs.getString(6), rs.getInt(7), rs.getString(8));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return kh;
	}

	public KhachHang TimKiemTen(String ten) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stmt = null;
		KhachHang kh = null;
		try {
			stmt = con
					.prepareStatement("select * from KhachHang where TENKH = ?");
			stmt.setString(1, ten);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				kh = new KhachHang(rs.getString(1), rs.getString(2),
						rs.getString(3), rs.getDate(4), rs.getString(5),
						rs.getString(6), rs.getInt(7), rs.getString(8));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return kh;
	}

	public KhachHang TimKiemCM(int cm) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stmt = null;
		KhachHang kh = null;
		try {
			stmt = con
					.prepareStatement("select * from KhachHang where CMND = ?");
			stmt.setInt(1, cm);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				kh = new KhachHang(rs.getString(1), rs.getString(2),
						rs.getString(3), rs.getDate(4), rs.getString(5),
						rs.getString(6), rs.getInt(7), rs.getString(8));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return kh;
	}

	public KhachHang TimKiemSDT1(String soDT) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stmt = null;
		String so = "%".concat(soDT).concat("%");
		KhachHang kh = null;
		try {
			stmt = con
					.prepareStatement("select * from KHACHHANG where DIENTHOAI LIKE ?");
			stmt.setString(1, so);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				kh = new KhachHang(rs.getString(1), rs.getString(2),
						rs.getString(3), rs.getDate(4), rs.getString(5),
						rs.getString(6), rs.getInt(7), rs.getString(8));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return kh;
	}
}