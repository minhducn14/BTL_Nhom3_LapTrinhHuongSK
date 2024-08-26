package DAO;

import connectDB.ConnectDB;
import entity.ChucVu;
import entity.NhanVien;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class NhanVien_DAO {
	private Connection con;

	public NhanVien_DAO() {
	}

	public List<NhanVien> getLS() {
		List<NhanVien> ds = new ArrayList<>();
		ChucVu_DAO cvDao;
		Connection con = null;
		Statement statement = null;
		try {
			ConnectDB.getInstance();
			con = ConnectDB.getConnection();
			String sql = "select * from NHANVIEN";
			statement = con.createStatement();

			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				NhanVien nv = new NhanVien(rs.getString(1), rs.getString(3),
						rs.getString(4), rs.getDate(5), rs.getString(6),
						rs.getString(7), rs.getInt(8), rs.getString(9),
						rs.getDate(10));
				cvDao = new ChucVu_DAO();
				ChucVu cv = null;
				cv = cvDao.TimKiemMa(rs.getString(2));
				nv.setChucVu(cv);
				ds.add(nv);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return ds;
	}

	public boolean isNhanVienUnique(String tenNhanVien) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = con
					.prepareStatement("SELECT COUNT(*) FROM NhanVien WHERE TENNV = ?");
			stmt.setString(1, tenNhanVien);
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

	public boolean addNhanVien(NhanVien nv) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stmt = null;
		try {
			stmt = con
					.prepareStatement("INSERT INTO NhanVien ([MACV],[TENNV],[GIOITINH],[NGAYSINH],[EMAIL],"
							+ "[DIENTHOAI],[CMND],[DIACHI],[NGAYVAOLAM]) VALUES(?,?,?,?,?,?,?,?,?)");
			stmt.setString(1, nv.getChucVu().getMaChucVu());
			stmt.setString(2, nv.getTenNV());
			stmt.setString(3, nv.getGioiTinh());
			Date NgaySinh = new Date(nv.getNgaySinh().getTime());
			stmt.setDate(4, NgaySinh);
			stmt.setString(5, nv.getEmail());
			stmt.setString(6, nv.getDienThoai());
			stmt.setInt(7, nv.getCMND());
			stmt.setString(8, nv.getDiaChi());
			Date NgayVao = new Date(nv.getNgayVaoLam().getTime());
			stmt.setDate(9, NgayVao);

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

	public boolean deleteNV(String maNV) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stmt = null;
		try {
			stmt = con.prepareStatement("delete from NhanVien where MANV = ?");
			stmt.setString(1, maNV);
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

	public boolean updateNhanVien(String maNV, NhanVien nv) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stmt = null;
		String sql = "update NhanVien set MACV = ? ,TENNV = ?, "
				+ "GIOITINH = ?,NGAYSINH = ? ,EMAIL = ?,DIENTHOAI = ? ,CMND = ? ,DIACHI = ?,NGAYVAOLAM = ?  where MANV = ?";
		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, nv.getChucVu().getMaChucVu());
			stmt.setString(2, nv.getTenNV());
			stmt.setString(3, nv.getGioiTinh());
			Date NgaySinh = new Date(nv.getNgaySinh().getTime());
			stmt.setDate(4, NgaySinh);
			stmt.setString(5, nv.getEmail());
			stmt.setString(6, nv.getDienThoai());
			stmt.setInt(7, nv.getCMND());
			stmt.setString(8, nv.getDiaChi());
			Date NgayVao = new Date(nv.getNgayVaoLam().getTime());
			stmt.setDate(9, NgayVao);
			stmt.setString(10, maNV);

			int n = stmt.executeUpdate();
			if (n > 0)
				return true;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}

	public NhanVien TimKiemMa(String ma) {
		NhanVien nv = null;
		ChucVu cv = null;
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stmt = null;
		try {
			stmt = con
					.prepareStatement("select * from NHANVIEN where MANV = ?");
			stmt.setString(1, ma);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				nv = new NhanVien(rs.getString(1), rs.getString(3),
						rs.getString(4), rs.getDate(5), rs.getString(6),
						rs.getString(7), rs.getInt(8), rs.getString(9),
						rs.getDate(10));
				ChucVu_DAO cvDao;
				cvDao = new ChucVu_DAO();
				cv = cvDao.TimKiemMa(rs.getString(2));
				nv.setChucVu(cv);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return nv;
	}

	public NhanVien TimKiemTen(String ten) {
		NhanVien nv = null;
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stmt = null;
		try {
			stmt = con
					.prepareStatement("select * from NHANVIEN where TENNV = ?");
			stmt.setString(1, ten);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				nv = new NhanVien(rs.getString(1), rs.getString(3),
						rs.getString(4), rs.getDate(5), rs.getString(6),
						rs.getString(7), rs.getInt(8), rs.getString(9),
						rs.getDate(10));
				ChucVu_DAO cvDao = new ChucVu_DAO();
				ChucVu cv = cvDao.TimKiemMa(rs.getString(2));
				nv.setChucVu(cv);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return nv;
	}

	public NhanVien TimKiemCM(int cm) {
		NhanVien nv = null;
		ChucVu_DAO cvDao;
		try {
			PreparedStatement stmt = con
					.prepareStatement("select * from NHANVIEN where CMND = ?");
			stmt.setInt(1, cm);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				nv = new NhanVien(rs.getString(1), rs.getString(3),
						rs.getString(4), rs.getDate(5), rs.getString(6),
						rs.getString(7), rs.getInt(8), rs.getString(9),
						rs.getDate(10));
				cvDao = new ChucVu_DAO();
				System.out.println(rs.getString(2));
				ChucVu cv = null;
				cv = cvDao.TimKiemMa(rs.getString(2));
				System.out.println(cv);
				nv.setChucVu(cv);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return nv;
	}

	public NhanVien TimKiemSDT1(String soDT) {
		String so = "%".concat(soDT).concat("%");
		System.out.println(so);
		NhanVien nv = null;
		try {
			PreparedStatement stmt = con
					.prepareStatement("select * from NHANVIEN where DIENTHOAI LIKE ?");
			stmt.setString(1, so);
			ResultSet rs = stmt.executeQuery();
			ChucVu_DAO cvDao;
			while (rs.next()) {
				nv = new NhanVien(rs.getString(1), rs.getString(3),
						rs.getString(4), rs.getDate(5), rs.getString(6),
						rs.getString(7), rs.getInt(8), rs.getString(9),
						rs.getDate(10));
				cvDao = new ChucVu_DAO();
				System.out.println(rs.getString(2));
				ChucVu cv = null;
				cv = cvDao.TimKiemMa(rs.getString(2));
				System.out.println(cv);
				nv.setChucVu(cv);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return nv;
	}
}
