package DAO;

import connectDB.ConnectDB;
import entity.NhaCungCap;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class NhaCungCap_DAO {

	public NhaCungCap_DAO() {
		
	}

	public List<NhaCungCap> getLS() {
		Connection con = null;
		Statement statement = null;
		List<NhaCungCap> ds = new ArrayList<>();
		try {

			ConnectDB.getInstance();
			con = ConnectDB.getConnection();
			String sql = "Select * from NhaCungCap";
			statement = con.createStatement();

			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				NhaCungCap ncc = new NhaCungCap(rs.getString(1),
						rs.getString(2), rs.getString(4), rs.getString(5),
						rs.getString(3));
				ds.add(ncc);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return ds;
	}
	public boolean isTenNhaCungCapUnique(String tenNhaCungCap) {
	    ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
	    PreparedStatement stmt = null;
	    ResultSet rs = null;
	    try {
	        stmt = con.prepareStatement("SELECT COUNT(*) FROM NhaCungCap WHERE TENNHACC = ?");
	        stmt.setString(1, tenNhaCungCap);
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
	public boolean addNhaCungCap(NhaCungCap ncc) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stmt = null;
		try {
			stmt = con
					.prepareStatement("INSERT INTO NhaCungCap ([TENNHACC],[DIACHI],[SODT],"
							+ "[EMAIL]) VALUES(?,?,?,?)");
			stmt.setString(1, ncc.getTenNhaCC());
			stmt.setString(2, ncc.getDiaChi());
			stmt.setString(3, ncc.getSoDT());
			stmt.setString(4, ncc.geteMail());

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

	public boolean deleteNCC(String maNCC) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
        PreparedStatement stmt = null;
		try {
			stmt = con
					.prepareStatement("delete from NhaCungCap where MANHACC = ?");
			stmt.setString(1, maNCC);
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

	public boolean updateNhaCC(String maNCC, NhaCungCap ncc) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
        PreparedStatement stmt = null;
		String sql = "update NhaCungCap set TENNHACC = ?, "
				+ "DIACHI = ?,SODT = ? ,EMAIL = ? where MANHACC = ?";
		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, ncc.getTenNhaCC());
			stmt.setString(2, ncc.getDiaChi());
			stmt.setString(3, ncc.getSoDT());
			stmt.setString(4, ncc.geteMail());
			stmt.setString(5, maNCC);

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

	public NhaCungCap TimKiemMa(String ma) {
		NhaCungCap ncc = null;
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
        PreparedStatement stmt = null;
		try {
			stmt = con
					.prepareStatement("select * from NhaCungCap where MANHACC = ?");
			stmt.setString(1, ma);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				ncc = new NhaCungCap(rs.getString(1), rs.getString(2),
						rs.getString(4), rs.getString(5), rs.getString(3));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ncc;
	}

	public NhaCungCap TimKiemTen(String ten) {
		NhaCungCap ncc = null;
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
        PreparedStatement stmt = null;
		try {
			stmt = con
					.prepareStatement("select * from NHACUNGCAP where TENNHACC = ?");
			stmt.setString(1, ten);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				ncc = new NhaCungCap(rs.getString(1), rs.getString(2),
						rs.getString(4), rs.getString(5), rs.getString(3));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ncc;
	}

	public List<NhaCungCap> TimKiemTen1(String ten) {
		List<NhaCungCap> ls = new ArrayList<>();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
        PreparedStatement stmt = null;
		try {
			stmt = con
					.prepareStatement("select * from NHACUNGCAP where CONTAINS(TENNHACC, ?)");
			stmt.setString(1, ten);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				NhaCungCap ncc = new NhaCungCap(rs.getString(1),
						rs.getString(2), rs.getString(4), rs.getString(5),
						rs.getString(3));
				ls.add(ncc);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(stmt);
		}
		return ls;
	}

	public NhaCungCap TimKiemSDT1(String soDT) {
		String so = "%".concat(soDT).concat("%");
		NhaCungCap ncc = null;
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
        PreparedStatement stmt = null;
		try {
			stmt = con
					.prepareStatement("select * from NHACUNGCAP where SODT LIKE ?");
			stmt.setString(1, so);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				ncc = new NhaCungCap(rs.getString(1), rs.getString(2),
						rs.getString(4), rs.getString(5), rs.getString(3));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ncc;
	}
}
