package DAO;

import connectDB.ConnectDB;
import entity.ChucVu;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ChucVu_DAO {
    public ChucVu_DAO() {
    }

    public List<ChucVu> getLS() {
    	Connection con = null;
		Statement statement = null;
        List<ChucVu> ds = new ArrayList<>();
        try {
        	ConnectDB.getInstance();
			con = ConnectDB.getConnection();
			String sql = "Select * from ChucVu";
			statement = con.createStatement();

			ResultSet rs = statement.executeQuery(sql);
            while(rs.next()){
                ChucVu cv = new ChucVu(rs.getString(1),rs.getString(2),rs.getDouble(3));
                ds.add(cv);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ds;
    }
    
    
    public ChucVu TimKiemTen(String ten){
        ChucVu cv = null;
        Connection con = null;
		PreparedStatement stmt = null;
        try{
        	ConnectDB.getInstance();
			con = ConnectDB.getConnection();
            stmt = con.prepareStatement("select * from ChucVu where TENCV = ?");
            stmt.setString(1,ten);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                cv = new ChucVu(rs.getString(1), rs.getString(2), rs.getDouble(3));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return cv;
    }
    
    public ChucVu TimKiemMa(String ma){
        ChucVu cv = null;
        Connection con = null;
		PreparedStatement stmt = null;
        try{
        	ConnectDB.getInstance();
			con = ConnectDB.getConnection();
            stmt = con.prepareStatement("select * from ChucVu where MACV = ?");
            stmt.setString(1,ma);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                cv = new ChucVu(rs.getString(1), rs.getString(2), rs.getDouble(3));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return cv;
    }
}
