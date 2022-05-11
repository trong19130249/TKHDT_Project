package Dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.KhachHang;
import model.NhaBanHang;
import model.ThuNo;
public class ThuNoDao {
	Connection connection;
private static ThuNoDao instance;
private ThuNoDao() {
	
}
public static ThuNoDao getInstance() {
	if(instance==null) instance=new ThuNoDao();
	return instance;
}
public List<ThuNo> getAllDebt(){
	List<ThuNo> result=new ArrayList<ThuNo>();
	try {
		connection=ConnectionDao.getConnection();
		PreparedStatement ps=connection.prepareStatement("select * from khach_hang kh inner join ls_thu_no ls on ls.id_kh=kh.id_kh"
				,
				ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
		ResultSet rs=ps.executeQuery();
		while(rs.next()) {
			String idkH=rs.getString("kh.id_kh");
			String ten=rs.getString("ten");
			String email=rs.getString("email");
			String diaChi=rs.getString("dia_chi");
			Date ngayThu=rs.getDate("ngay_thu");
			String ghiChu=rs.getString("ghi_chu");
			double soTienNo=rs.getDouble("kh.so_tien_no");
			double soTienNoLs=rs.getDouble("ls.so_tien_no");
			KhachHang kh=new KhachHang(idkH, ten, diaChi, email, soTienNo, ghiChu);
			ThuNo thuNo=new ThuNo(kh,ngayThu,soTienNoLs);
			result.add(thuNo);
		}
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return result;
	
}
public ThuNo getDebt(String idKh){
	try {
		connection=ConnectionDao.getConnection();
		PreparedStatement ps=connection.prepareStatement("select * from khach_hang kh inner join ls_thu_no ls on ls.id_kh=kh.id_kh where ls.id_kh=?",
				ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
		ps.setString(1, idKh);
		ResultSet rs=ps.executeQuery();
		while(rs.next()) {
			String id=rs.getString("kh.id_kh");
			String ten=rs.getString("ten");
			String email=rs.getString("email");
			String diaChi=rs.getString("dia_chi");
			double soTienNo=rs.getDouble("so_tien_no");
			String ghiChu=rs.getString("ghi_chu");
			Date ngayThu=rs.getDate("ngay_thu");
			double soTienNoLs=rs.getDouble("ls.so_tien_no");
			KhachHang kh=new KhachHang(id, ten, diaChi, email, soTienNo, ghiChu);
			ThuNo thuNo=new ThuNo(kh,ngayThu,soTienNoLs);
			return thuNo;
		}
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return null;
	
}
public KhachHang getKhachHang(String id) {
	try {
		connection=ConnectionDao.getConnection();
		PreparedStatement ps=connection.prepareStatement("select * from khach_hang");
	ResultSet rs=ps.executeQuery();
	while(rs.next()) {
		String ten=rs.getString("ten");
		String email=rs.getString("email");
		String diaChi=rs.getString("dia_chi");
		double soTienNo=rs.getDouble("so_tien_no");
		String ghiChu=rs.getString("ghi_chu");
		Date ngayThu=rs.getDate("ngay_thu");
		KhachHang kh=new KhachHang(id, ten, diaChi, email, soTienNo, ghiChu);
		return kh;
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return null;
}
public boolean addDebt(String id,Date ngayThu,double soTienNo) {
	try {
		connection=ConnectionDao.getConnection();
		PreparedStatement ps=connection.prepareStatement("insert into ls_thu_no(id_kh,ngay_thu,so_tien_no) values(?,?,?)");
		ps.setString(1, id);
		ps.setDate(2, ngayThu);
		ps.setDouble(3, soTienNo);
		int affect=ps.executeUpdate();
		if(affect>0) return true;
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return false;
}
// tra no cho nha cc
public boolean deleteDebt(String idKh,Date ngayThu) {
	try {
		connection=ConnectionDao.getConnection();
		PreparedStatement ps=connection.prepareStatement("DELETE from ls_thu_no where ls_thu_no.ID_KH=? and ls_thu_no.ngay_thu=?");
	ps.setString(1, idKh);
	ps.setDate(2, ngayThu);
	int affect=ps.executeUpdate();
	if(affect>0) return true;
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return true;
}
public boolean setDebt(String idKh,Date ngayThu,double soTienNo,String idOld) {
	try {
		connection=ConnectionDao.getConnection();
		PreparedStatement ps=connection.prepareStatement("update ls_thu_no ls set ls.id_kh=? ,ls.so_tien_no=?,ls.ngay_thu=? where ls.id_kh=?");
		ps.setString(1, idKh);
		ps.setDate(3, ngayThu);
		ps.setDouble(2, soTienNo);
		ps.setString(4, idOld);
		int affect=ps.executeUpdate();
		if(affect>0) return true;
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return false;
}
}
