package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.KhachHang;
import model.KhachHangModelInterface;
import model.Observer;
import model.ThuNo;

public class KhachHangDao{
	Connection connection;
	private static KhachHangDao instance;
private KhachHangDao() {
	
}
public static KhachHangDao getInstance() {
	if(instance==null) instance=new KhachHangDao();
	return instance;
}



public KhachHang getKhachHang(String id) {
	// TODO Auto-generated method stub
	try {
		connection=ConnectionDao.getConnection();
		
		PreparedStatement ps=connection.prepareStatement("select * from khach_hang k where k.id_kh=? and k.hd=1");
		ps.setString(1, id);
		ResultSet rs=ps.executeQuery();
		if(rs.next()) {
			KhachHang kh=new KhachHang(rs.getString("id_kh"), rs.getString("ten"), rs.getString("dia_chi"), rs.getString("email"),rs.getDouble("so_tien_no"),rs.getString("ghi_chu"));
			return kh;
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return null;
}
public boolean setProperty(String id,String ten,String email,String diaChi,double soTienNo,String ghiChu) {
	try {
		connection=ConnectionDao.getConnection();
		PreparedStatement ps=connection.prepareStatement("update khach_hang k set k.ten=?,k.email=?,k.dia_chi=?,k.so_tien_no=?,k.ghi_chu=? where k.id_kh=?");
		ps.setString(1, ten);
		ps.setString(2, email);
		ps.setString(3, diaChi);
		ps.setDouble(4, soTienNo);
		ps.setString(5, ghiChu);
		ps.setString(6, id);
		int affect=ps.executeUpdate();
		if(affect>0) {
			ThuNo thuNo=ThuNoDao.getInstance().getDebt(id);
			if(thuNo==null) {	
				return	ThuNoDao.getInstance().addDebt(id, null, soTienNo);
			}
			else
			return	ThuNoDao.getInstance().setDebt(id,thuNo.getNgayThu(), soTienNo, id);
		}
		else return false;
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return false;
}
public boolean addKhachHang(String id,String ten,String email,String diaChi,double soTienNo,String ghiChu) {
	try {
		connection=ConnectionDao.getConnection();
		PreparedStatement ps=connection.prepareStatement("insert khach_hang(id_kh,email,ten,dia_chi,so_tien_no,ghi_chu,hd) values(?,?,?,?,?,?,?)");
		ps.setString(1, id);
		ps.setString(3, ten);
		ps.setString(2, email);
		ps.setString(4, diaChi);
		ps.setDouble(5, soTienNo);
		ps.setString(6, ghiChu);
		ps.setInt(7, 1);
		int affect=ps.executeUpdate();
		if(affect>0) {
			ThuNo thuNo=ThuNoDao.getInstance().getDebt(id);
			if(thuNo==null) {
			return	ThuNoDao.getInstance().addDebt(id, null, soTienNo);
			}
		}
		else return false;
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return false;
}
public List<KhachHang> getDanhSachKhachHang() {
	try {
		connection=ConnectionDao.getConnection();
		ArrayList<KhachHang> result=new ArrayList<KhachHang>();
		PreparedStatement ps=connection.prepareStatement("select * from khach_hang kh where kh.hd=1");
		ResultSet rs=ps.executeQuery();
		while(rs.next()) {
			result.add(new KhachHang(rs.getString("id_kh"), rs.getString("ten"), rs.getString("dia_chi"), rs.getString("email"),rs.getDouble("so_tien_no"),rs.getString("ghi_chu")));
		}
		return result;
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return null;
}
public boolean deleteKhachHang(String id) {
	try {
		connection=ConnectionDao.getConnection();
		PreparedStatement ps=connection.prepareStatement("update khach_hang set khach_hang.hd=0 where khach_hang.id_kh=?");
		ps.setString(1, id);
		int affect=ps.executeUpdate();
		if(affect>0) return true;
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return false;
}

}
