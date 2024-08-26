package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.DefaultTableModel;

public class ThongKe_DAO {
	public DefaultTableModel getThuocBanTrongNgay() {
		Connection con = null;
		DefaultTableModel tableModel = new DefaultTableModel();
		try {
			Class.forName("net.sourceforge.jtds.jdbc.Driver");
			con = DriverManager
					.getConnection("jdbc:jtds:sqlserver://localhost:1433/QuanLyQuayThuoc;instance=SQLEXPRESS;user=sa;password=sapassword");
			String sql = "SELECT *\r\n"
					+ "FROM [dbo].[Thuoc] t WHERE t.MAT IN (SELECT ct.MAT FROM \r\n"
					+ "[dbo].[CT_HoaDonBanHang] ct JOIN [dbo].[HoaDonBanHang] \r\n"
					+ "hd ON ct.MaHDBH = hd.MaHDBH WHERE DAY(hd.NGAYLAPHD) = DAY(GETDATE()))";
			PreparedStatement pstmt = con.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			int columnCount = rsmd.getColumnCount();
			String[] columnNames = new String[columnCount];
			for (int i = 1; i <= columnCount; i++) {
				columnNames[i - 1] = rsmd.getColumnName(i);
			}
			List<Object[]> dataList = new ArrayList<>();
			while (rs.next()) {
				Object[] row = new Object[columnCount];
				for (int i = 1; i <= columnCount; i++) {
					row[i - 1] = rs.getObject(i);
				}
				dataList.add(row);
			}
			Object[][] data = new Object[dataList.size()][columnCount];
			for (int i = 0; i < dataList.size(); i++) {
				data[i] = dataList.get(i);
			}

			tableModel.setDataVector(data, columnNames);

			rs.close();
			pstmt.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return tableModel;
	}

	public DefaultTableModel getThuocConLai() {
		Connection con = null;
		DefaultTableModel tableModel = new DefaultTableModel();
		try {
			Class.forName("net.sourceforge.jtds.jdbc.Driver");
			con = DriverManager
					.getConnection("jdbc:jtds:sqlserver://localhost:1433/QuanLyQuayThuoc;instance=SQLEXPRESS;user=sa;password=sapassword");
			String sql = "SELECT t.MAT,t.TENTHUOC,lt.TENLOAI,t.CONGDUNG,t.DONVITINH,t.SOLUONG,t.GIABAN,t.GIABAN*t.SOLUONG AS THANHTIEN\r\n"
					+ "FROM [dbo].[Thuoc] t JOIN [dbo].[LoaiThuoc] lt ON t.MALOAI = lt.MALOAI";
			PreparedStatement pstmt = con.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			int columnCount = rsmd.getColumnCount();
			String[] columnNames = new String[columnCount];
			for (int i = 1; i <= columnCount; i++) {
				columnNames[i - 1] = rsmd.getColumnName(i);
			}
			List<Object[]> dataList = new ArrayList<>();
			while (rs.next()) {
				Object[] row = new Object[columnCount];
				for (int i = 1; i <= columnCount; i++) {
					row[i - 1] = rs.getObject(i);
				}
				dataList.add(row);
			}
			Object[][] data = new Object[dataList.size()][columnCount];
			for (int i = 0; i < dataList.size(); i++) {
				data[i] = dataList.get(i);
			}

			tableModel.setDataVector(data, columnNames);

			rs.close();
			pstmt.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return tableModel;
	}

	public DefaultTableModel thongKeDoanhThuTheoNgay() {
		Connection con = null;
		DefaultTableModel tableModel = new DefaultTableModel();
		try {
			Class.forName("net.sourceforge.jtds.jdbc.Driver");
			con = DriverManager
					.getConnection("jdbc:jtds:sqlserver://localhost:1433/QuanLyQuayThuoc;instance=SQLEXPRESS;user=sa;password=sapassword");
			String sql = "SELECT CONVERT(date, hd.NGAYLAPHD) AS NGAYLAPHD, SUM(t.GIABAN * cthd.SOLUONG) AS TONGDOANHTHU\r\n"
					+ "FROM [dbo].[Thuoc] t\r\n"
					+ "JOIN [dbo].[CT_HoaDonBanHang] cthd ON t.MAT = cthd.MAT\r\n"
					+ "JOIN [dbo].[HoaDonBanHang] hd ON cthd.MaHDBH = hd.MaHDBH\r\n"
					+ "GROUP BY CONVERT(date, hd.NGAYLAPHD) ";
			PreparedStatement pstmt = con.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			int columnCount = rsmd.getColumnCount();
			String[] columnNames = new String[columnCount];
			for (int i = 1; i <= columnCount; i++) {
				columnNames[i - 1] = rsmd.getColumnName(i);
			}
			List<Object[]> dataList = new ArrayList<>();
			while (rs.next()) {
				Object[] row = new Object[columnCount];
				for (int i = 1; i <= columnCount; i++) {
					row[i - 1] = rs.getObject(i);
				}
				dataList.add(row);
			}
			Object[][] data = new Object[dataList.size()][columnCount];
			for (int i = 0; i < dataList.size(); i++) {
				data[i] = dataList.get(i);
			}

			tableModel.setDataVector(data, columnNames);

			rs.close();
			pstmt.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return tableModel;
	}
	
	public DefaultTableModel thongKeDoanhThuTheoThang() {
		Connection con = null;
		DefaultTableModel tableModel = new DefaultTableModel();
		try {
			Class.forName("net.sourceforge.jtds.jdbc.Driver");
			con = DriverManager
					.getConnection("jdbc:jtds:sqlserver://localhost:1433/QuanLyQuayThuoc;instance=SQLEXPRESS;user=sa;password=sapassword");
			String sql = "SELECT YEAR(hd.NGAYLAPHD) AS NAM, MONTH(hd.NGAYLAPHD) AS THANG, SUM(t.GIABAN * cthd.SOLUONG) AS TONGDOANHTHU\r\n"
					+ "FROM [dbo].[Thuoc] t\r\n"
					+ "JOIN [dbo].[CT_HoaDonBanHang] cthd ON t.MAT = cthd.MAT\r\n"
					+ "JOIN [dbo].[HoaDonBanHang] hd ON cthd.MaHDBH = hd.MaHDBH\r\n"
					+ "GROUP BY YEAR(hd.NGAYLAPHD), MONTH(hd.NGAYLAPHD)";
			PreparedStatement pstmt = con.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			int columnCount = rsmd.getColumnCount();
			String[] columnNames = new String[columnCount];
			for (int i = 1; i <= columnCount; i++) {
				columnNames[i - 1] = rsmd.getColumnName(i);
			}
			List<Object[]> dataList = new ArrayList<>();
			while (rs.next()) {
				Object[] row = new Object[columnCount];
				for (int i = 1; i <= columnCount; i++) {
					row[i - 1] = rs.getObject(i);
				}
				dataList.add(row);
			}
			Object[][] data = new Object[dataList.size()][columnCount];
			for (int i = 0; i < dataList.size(); i++) {
				data[i] = dataList.get(i);
			}

			tableModel.setDataVector(data, columnNames);

			rs.close();
			pstmt.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return tableModel;
	}
	
	public DefaultTableModel thongKeDoanhThuTheoNam() {
		Connection con = null;
		DefaultTableModel tableModel = new DefaultTableModel();
		try {
			Class.forName("net.sourceforge.jtds.jdbc.Driver");
			con = DriverManager
					.getConnection("jdbc:jtds:sqlserver://localhost:1433/QuanLyQuayThuoc;instance=SQLEXPRESS;user=sa;password=sapassword");
			String sql = "SELECT YEAR(hd.NGAYLAPHD) AS NAM, SUM(t.GIABAN * cthd.SOLUONG) AS TONGDOANHTHU\r\n"
					+ "FROM [dbo].[Thuoc] t\r\n"
					+ "JOIN [dbo].[CT_HoaDonBanHang] cthd ON t.MAT = cthd.MAT\r\n"
					+ "JOIN [dbo].[HoaDonBanHang] hd ON cthd.MaHDBH = hd.MaHDBH\r\n"
					+ "GROUP BY YEAR(hd.NGAYLAPHD)";
			PreparedStatement pstmt = con.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			int columnCount = rsmd.getColumnCount();
			String[] columnNames = new String[columnCount];
			for (int i = 1; i <= columnCount; i++) {
				columnNames[i - 1] = rsmd.getColumnName(i);
			}
			List<Object[]> dataList = new ArrayList<>();
			while (rs.next()) {
				Object[] row = new Object[columnCount];
				for (int i = 1; i <= columnCount; i++) {
					row[i - 1] = rs.getObject(i);
				}
				dataList.add(row);
			}
			Object[][] data = new Object[dataList.size()][columnCount];
			for (int i = 0; i < dataList.size(); i++) {
				data[i] = dataList.get(i);
			}

			tableModel.setDataVector(data, columnNames);

			rs.close();
			pstmt.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return tableModel;
	}
	
	public DefaultTableModel thongKeDoanhThuTheoQuy() {
		Connection con = null;
		DefaultTableModel tableModel = new DefaultTableModel();
		try {
			Class.forName("net.sourceforge.jtds.jdbc.Driver");
			con = DriverManager
					.getConnection("jdbc:jtds:sqlserver://localhost:1433/QuanLyQuayThuoc;instance=SQLEXPRESS;user=sa;password=sapassword");
			String sql = "SELECT YEAR(hd.NGAYLAPHD) AS NAM,CEILING(MONTH(hd.NGAYLAPHD) / 3.0) AS QUY,SUM(t.GIABAN * cthd.SOLUONG) AS TONGDOANHTHU\r\n"
					+ "FROM [dbo].[Thuoc] t\r\n"
					+ "JOIN [dbo].[CT_HoaDonBanHang] cthd ON t.MAT = cthd.MAT\r\n"
					+ "JOIN [dbo].[HoaDonBanHang] hd ON cthd.MaHDBH = hd.MaHDBH\r\n"
					+ "GROUP BY YEAR(hd.NGAYLAPHD), CEILING(MONTH(hd.NGAYLAPHD) / 3.0)";
			PreparedStatement pstmt = con.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			int columnCount = rsmd.getColumnCount();
			String[] columnNames = new String[columnCount];
			for (int i = 1; i <= columnCount; i++) {
				columnNames[i - 1] = rsmd.getColumnName(i);
			}
			List<Object[]> dataList = new ArrayList<>();
			while (rs.next()) {
				Object[] row = new Object[columnCount];
				for (int i = 1; i <= columnCount; i++) {
					row[i - 1] = rs.getObject(i);
				}
				dataList.add(row);
			}
			Object[][] data = new Object[dataList.size()][columnCount];
			for (int i = 0; i < dataList.size(); i++) {
				data[i] = dataList.get(i);
			}

			tableModel.setDataVector(data, columnNames);

			rs.close();
			pstmt.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return tableModel;
	}

}
