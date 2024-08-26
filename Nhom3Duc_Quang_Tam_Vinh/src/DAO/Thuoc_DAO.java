package DAO;

import entity.Loaithuoc;
import entity.Thuoc;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import connectDB.ConnectDB;

public class Thuoc_DAO {

	public Thuoc_DAO() {

	}

	public List<Thuoc> getAllThuoc() {

		List<Thuoc> ds = new ArrayList<>();
		Connection con = null;
		Statement statement = null;
		try {
			ConnectDB.getInstance();
			con = ConnectDB.getConnection();
			String sql = "Select * from Thuoc";
			statement = con.createStatement();

			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				Thuoc thuoc = new Thuoc(rs.getString(1), rs.getString(3),
						rs.getString(4), rs.getString(5), rs.getString(6),
						rs.getString(7), rs.getInt(8), rs.getDouble(9),
						rs.getDate(10));
				LoaiThuoc_DAO ltDao = new LoaiThuoc_DAO();
				Loaithuoc lt = ltDao.TimKiemMa(rs.getString(2));
				thuoc.setLoaiThuoc(lt);
				ds.add(thuoc);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ds;
	}


	public boolean addThuoc(Thuoc thuoc) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stmt = null;
		try {
			stmt = con
					.prepareStatement("INSERT INTO Thuoc (MALOAI, TENTHUOC, THANHPHAN, CONGDUNG, DONVITINH, XUATXU, SOLUONG, GIABAN, NGAYHETHAN) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");
			stmt.setString(1, thuoc.getLoaiThuoc().getMaLoai());
			stmt.setString(2, thuoc.getTenThuoc());
			stmt.setString(3, thuoc.getThanhPhan());
			stmt.setString(4, thuoc.getCongDung());
			stmt.setString(5, thuoc.getDonViTinh());
			stmt.setString(6, thuoc.getXuatXu());
			stmt.setInt(7, thuoc.getSoLuong());
			stmt.setDouble(8, thuoc.getGiaBan());
			Date date = new Date(thuoc.getNgayHetHan().getTime());
			stmt.setDate(9, date);
			int n = stmt.executeUpdate();
			if (n > 0)
				return true;
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

	public boolean deleteThuoc(String maThuoc) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stmt = null;
		try {
			stmt = con.prepareStatement("delete from THuoc where MAT = ?");
			stmt.setString(1, maThuoc);
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

	public boolean updateThuoc(String maThuoc, Thuoc thuoc) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stmt = null;
		String sql = "update Thuoc set [MALOAI] = ?,[TENTHUOC] = ?,[THANHPHAN] = ?,[CONGDUNG] = ?,[DONVITINH] = ?,[XUATXU] = ?,[SOLUONG] = ?,[GIABAN] = ?,[NGAYHETHAN] =?  where MAT = ?";
		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, thuoc.getLoaiThuoc().getMaLoai());
			stmt.setString(2, thuoc.getTenThuoc());
			stmt.setString(3, thuoc.getThanhPhan());
			stmt.setString(4, thuoc.getCongDung());
			stmt.setString(5, thuoc.getDonViTinh());
			stmt.setString(6, thuoc.getXuatXu());
			stmt.setInt(7, thuoc.getSoLuong());
			stmt.setDouble(8, thuoc.getGiaBan());
			Date date = new Date(thuoc.getNgayHetHan().getTime());
			stmt.setDate(9, date);
			stmt.setString(10, maThuoc);

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

	public Thuoc TimKiemMa(String ma) {
		Thuoc thuoc = null;
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stmt = null;
		try {
			stmt = con.prepareStatement("select * from Thuoc where MAT = ?");
			stmt.setString(1, ma);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				thuoc = new Thuoc(rs.getString(1), rs.getString(3),
						rs.getString(4), rs.getString(5), rs.getString(6),
						rs.getString(7), rs.getInt(8), rs.getDouble(9),
						rs.getDate(10));
				LoaiThuoc_DAO ltDao = new LoaiThuoc_DAO();
				Loaithuoc lt = ltDao.TimKiemMa(rs.getString(2));
				thuoc.setLoaiThuoc(lt);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return thuoc;
	}

	public Thuoc TimKiemTen(String ten) {
		Thuoc thuoc = null;
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stmt = null;
		try {
			stmt = con
					.prepareStatement("select * from Thuoc where TENTHUOC = ?");
			stmt.setString(1, ten);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				thuoc = new Thuoc(rs.getString(1), rs.getString(3),
						rs.getString(4), rs.getString(5), rs.getString(6),
						rs.getString(7), rs.getInt(8), rs.getDouble(9),
						rs.getDate(10));
				LoaiThuoc_DAO ltDao = new LoaiThuoc_DAO();
				Loaithuoc lt = ltDao.TimKiemMa(rs.getString(2));
				thuoc.setLoaiThuoc(lt);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return thuoc;
	}

	public List<Thuoc> TimKiemMaLoai(String maL) {
		List<Thuoc> ls = new ArrayList<>();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stmt = null;
		try {
			stmt = con.prepareStatement("select * from Thuoc where MALOAI = ?");
			stmt.setString(1, maL);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Thuoc thuoc = new Thuoc(rs.getString(1), rs.getString(3),
						rs.getString(4), rs.getString(5), rs.getString(6),
						rs.getString(7), rs.getInt(8), rs.getDouble(9),
						rs.getDate(10));
				LoaiThuoc_DAO ltDao = new LoaiThuoc_DAO();
				Loaithuoc lt = ltDao.TimKiemMa(rs.getString(2));
				thuoc.setLoaiThuoc(lt);
				ls.add(thuoc);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ls;
	}
	public boolean updateSoLuong(String maT,int soLuong) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stmt = null;
        String sql = "update THUOC set SOLUONG = ? where MAT = ?";
        try {
            stmt = con.prepareStatement(sql);
            stmt.setInt(1,soLuong);
            stmt.setString(2,maT);

            int n = stmt.executeUpdate();
            if(n > 0){
                return true;

            }
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
			stmt = con.prepareStatement("SELECT MAX([MAT]) FROM [dbo].[Thuoc]");
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}

		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
			ma = rs.getString(1);
		}
		return ma;
	}

}
