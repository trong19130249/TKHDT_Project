package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.NhaBanHang;
import model.ThuNo;
import model.TraNo;

public class NhaCungCapDao {
	Connection connection;
	private static NhaCungCapDao instance;
private NhaCungCapDao() {
	
}
public static NhaCungCapDao getInstance() {
	if(instance==null) instance=new NhaCungCapDao();
	return instance;
}
public List<NhaBanHang> getAll(){
	List<NhaBanHang> result=new ArrayList<NhaBanHang>();
	try {
		connection=ConnectionDao.getConnection();
		PreparedStatement ps=connection.prepareStatement("select * from nha_ban_hang nbh where nbh.hd=1 ");
	ResultSet rs=ps.executeQuery();
	while(rs.next()) {
		String id=rs.getString("id");
		String ten=rs.getString("ten");
		int sdt=rs.getInt("so_dien_thoai");
		String diaChi=rs.getString("dia_chi");
		double noCCC=rs.getDouble("no_ccc");
		String ghiChu=rs.getString("ghi_chu");
		NhaBanHang nbh=new NhaBanHang(id, ten, sdt, diaChi, noCCC, ghiChu);
		result.add(nbh);
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return result;
}
public boolean editNCC(String id,String ten,int sdt,String diaChi,double noCCC,String ghiChu) {
	try {
		connection=ConnectionDao.getConnection();
		PreparedStatement ps=connection.prepareStatement("update nha_ban_hang nbh set nbh.id=?,nbh.ten=?,nbh.so_dien_thoai=?,nbh.dia_chi=?,nbh.no_ccc=?,nbh.ghi_chu=? where nbh.id=?");
		ps.setString(1, id);
	ps.setString(2, ten);
	ps.setInt(3, sdt);
	ps.setString(4, diaChi);
	ps.setDouble(5, noCCC);
	ps.setString(6, ghiChu);
	ps.setString(7, id);
	if(ps.executeUpdate()>0) {
			TraNo TraNo=TraNoDao.getInstance().getDebt(id);
			if(TraNo==null) {
				return TraNoDao.getInstance().addDebt(id, null,noCCC);
			}
			else
			return TraNoDao.getInstance().setDebt(id, TraNo.getNgayTra(), noCCC, id);
	}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return false;
}
public boolean deleteNCC(String id) {
	try {
		connection=ConnectionDao.getConnection();
		PreparedStatement ps=connection.prepareStatement("update nha_ban_hang nbh set nbh.hd=0 where nbh.id=? ");
		ps.setString(1, id);
		int affect=ps.executeUpdate();
		if(affect>0) return true;
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return false;
}
public NhaBanHang getNBH(String idNBH) {
	try {
		connection=ConnectionDao.getConnection();
		PreparedStatement ps=connection.prepareStatement("select * from nha_ban_hang nbh where nbh.id=? and nbh.hd=1"); 
	ps.setString(1, idNBH);
	
		ResultSet rs=ps.executeQuery();
		if(rs.next()) {
			String id=rs.getString("id");
			String ten=rs.getString("ten");
			int sdt=rs.getInt("so_dien_thoai");
			String diaChi=rs.getString("dia_chi");
			double noCCC=rs.getDouble("no_ccc");
			String ghiChu=rs.getString("ghi_chu");
			NhaBanHang nbh=new NhaBanHang(id, ten, sdt, diaChi, noCCC, ghiChu);
			return nbh;
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return null;
}
public boolean addNCC(String id,String ten,int sdt,String diaChi,double noCCC,String ghiChu) {
	try {
		connection=ConnectionDao.getConnection();
		PreparedStatement ps=connection.prepareStatement("insert into nha_ban_hang(id,ten,so_dien_thoai,dia_chi,no_ccc,ghi_chu,hd) values(?,?,?,?,?,?,?)"); 
	ps.setString(1, id);
	ps.setString(2, ten);
	ps.setInt(3, sdt);
	ps.setString(4, diaChi);
	ps.setDouble(5, noCCC);
	ps.setString(6, ghiChu);
	ps.setInt(7, 1);
		int affect=ps.executeUpdate();
	if(affect>0) {
		TraNo TraNo=TraNoDao.getInstance().getDebt(id);
	if(TraNo==null)	return TraNoDao.getInstance().addDebt(id, null, noCCC);
	}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return false;
}

}
