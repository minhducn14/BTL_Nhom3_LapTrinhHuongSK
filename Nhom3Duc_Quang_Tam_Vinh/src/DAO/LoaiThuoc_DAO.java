package DAO;

import connectDB.ConnectDB;
import entity.Loaithuoc;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LoaiThuoc_DAO {

	public LoaiThuoc_DAO() {

	}
	public boolean isTenLoaiThuocUnique(String tenLoaiThuoc) {
	    ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
	    PreparedStatement stmt = null;
	    ResultSet rs = null;
	    try {
	        stmt = con.prepareStatement("SELECT COUNT(*) FROM LoaiThuoc WHERE TENLOAI = ?");
	        stmt.setString(1, tenLoaiThuoc);
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

	public List<Loaithuoc> getLS() {
		List<Loaithuoc> ds = new ArrayList<>();
		Connection con = null;
		Statement statement = null;
		try {
			ConnectDB.getInstance();
			con = ConnectDB.getConnection();
			String sql = "Select * from LoaiThuoc";
			statement = con.createStatement();

			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				Loaithuoc lt = new Loaithuoc(rs.getString(1), rs.getString(2));
				ds.add(lt);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return ds;
	}

	public boolean addLoaiThuoc(Loaithuoc lt) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stmt = null;
		try {
			stmt = con
					.prepareStatement("INSERT INTO LOAITHUOC ([TENLOAI]) VALUES(?)");
			stmt.setString(1, lt.getTenThuoc());
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

	public boolean deleteLT(String maLoai) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stmt = null;
		try {
			stmt = con
					.prepareStatement("delete from LoaiThuoc where MALOAI = ?");
			stmt.setString(1, maLoai);
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

	public boolean updateLoaiThuoc(String maLoai, Loaithuoc lt) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stmt = null;
		String sql = "update LoaiThuoc set TENLOAI = ?  WHERE  MaLoai = ?";
		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(2, lt.getMaLoai());
			stmt.setString(1, lt.getTenThuoc());
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

	public Loaithuoc TimKiemMa(String ma) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stmt = null;
		Loaithuoc lt = null;
		try {
			stmt = con
					.prepareStatement("select * from LOAITHUOC where MALOAI = ?");
			stmt.setString(1, ma);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				lt = new Loaithuoc(rs.getString(1), rs.getString(2));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lt;
	}

	public Loaithuoc TimKiemTen(String ten) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stmt = null;
		Loaithuoc lt = null;
		try {
			stmt = con
					.prepareStatement("select * from LOAITHUOC where TENLOAI = ?");
			stmt.setString(1, ten);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				lt = new Loaithuoc(rs.getString(1), rs.getString(2));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lt;
	}
	
}
