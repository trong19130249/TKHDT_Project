package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Dao.ConnectionDao;

public interface NhanVienModelInterface {
	public void registerObserver(Observer o);
	public void removeObserver(Observer o);
	public void notifyObservers();
	public List<NhanVien> getAll();
	public boolean deleteNhanVien(String id) ;
	public boolean addNhanVien(String id,String ten,int sdt,int trangThai,String ghiChu,int gioiTinh,String email,String diaChi,String idChucVu) ;
	public boolean setNhanVien(String id,String ten,int sdt,int trangThai,String ghiChu,int gioiTinh,String email,String diaChi,String idChucVu) ;
	public NhanVien getNhanVien(String id) ;
	public void setData();
	public String[] getColumnNames();
	public Object[][] getRowData();
}
