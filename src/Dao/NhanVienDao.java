package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.NhanVien;

public class NhanVienDao {
private static NhanVienDao instance;
Connection connection;
private NhanVienDao() {
	
}
public static NhanVienDao getInstance() {
	if(instance==null) instance=new NhanVienDao();
	return instance;
}
public NhanVien getNhanVien(String id) {
	try {
		connection=ConnectionDao.getConnection();
		PreparedStatement ps=connection.prepareStatement("select * from nhan_vien nv inner join chuc_vu cv on cv.id=nv.id_cv where nv.id=?");
		ps.setString(1, id);
		ResultSet rs=ps.executeQuery();
		if(rs.next()) {
			String ten=rs.getString("ten");
			int sdt=rs.getInt("sdt");
			int trangThai=rs.getInt("trang_thai");
			String ghiChu=rs.getString("ghi_chu");
			int gioiTinh=rs.getInt("gioi_tinh");
			String email=rs.getString("dia_chi");
			String diaChi=rs.getString("dia_chi");
			String tenChucVu=rs.getString("cv.ten_cv");
			double luong=rs.getDouble("luong");
			NhanVien nv=new NhanVien(id, ten, sdt, trangThai, ghiChu, gioiTinh, email, diaChi, tenChucVu, luong);
			return nv;
			
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return null;
}
public List<NhanVien> getAll(){
	List<NhanVien> result=new ArrayList<NhanVien>();
	try {
		connection=ConnectionDao.getConnection();
		PreparedStatement ps=connection.prepareStatement("select * from nhan_vien nv inner join chuc_vu cv on cv.id=nv.id_cv");
		ResultSet rs=ps.executeQuery();
		while(rs.next()) {
			String id=rs.getString("id");
			String ten=rs.getString("ten");
			int sdt=rs.getInt("sdt");
			int trangThai=rs.getInt("trang_thai");
			String ghiChu=rs.getString("ghi_chu");
			int gioiTinh=rs.getInt("gioi_tinh");
			String email=rs.getString("dia_chi");
			String diaChi=rs.getString("dia_chi");
			String tenChucVu=rs.getString("cv.ten_cv");
			double luong=rs.getDouble("luong");
			NhanVien nv=new NhanVien(id, ten, sdt, trangThai, ghiChu, gioiTinh, email, diaChi, tenChucVu, luong);
			result.add(nv);
			
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return result;
}
public boolean deleteNhanVien(String id) {
	try {
		connection=ConnectionDao.getConnection();
		PreparedStatement ps=connection.prepareStatement("delete from nhan_vien where nhan_vien.id=?");
		ps.setString(1, id);
		int affect=ps.executeUpdate();
		if(affect>0) return true;
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return false;
}
public boolean addNhanVien(String id,String ten,int sdt,int trangThai,String ghiChu,int gioiTinh,String email,String diaChi,String idChucVu) {
	try {
		connection=ConnectionDao.getConnection();
		PreparedStatement ps=connection.prepareStatement("insert into nhan_vien(id,ten,sdt,trang_thai,ghi_chu,gioi_tinh,email,dia_chi,id_cv) values(?,?,?,?,?,?,?,?,?)");
		ps.setString(1, id);
		ps.setString(2, ten);
		ps.setInt(3, sdt);
		ps.setInt(4, trangThai);
		ps.setString(5, ghiChu);
		ps.setInt(6, gioiTinh);
		ps.setString(7, email);
		ps.setString(8, diaChi);
		ps.setString(9, idChucVu);
		int affect=ps.executeUpdate();
		if(affect>0) return true;
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return false;
}

public boolean setNhanVien(String id,String ten,int sdt,int trangThai,String ghiChu,int gioiTinh,String email,String diaChi,String idChucVu) {
	try {
		connection=ConnectionDao.getConnection();
		PreparedStatement ps=connection.prepareStatement("update nhan_vien e set e.ten=?,e.sdt=?,e.trang_thai=?,e.ghi_chu=?,e.gioi_tinh=?,e.email=?,e.dia_chi=?,e.id_cv=? where e.id=?");
		ps.setString(1, ten);
		ps.setInt(2, sdt);
		ps.setInt(3,trangThai);
		ps.setString(4, ghiChu);
		ps.setInt(5, gioiTinh);
		ps.setString(6, email);
		ps.setString(7, diaChi);
		ps.setString(8, idChucVu);
		ps.setString(9, id);
		int affect=ps.executeUpdate();
		if(affect>0) return true;
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return false;
}

}
