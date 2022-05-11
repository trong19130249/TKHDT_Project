package model;

import java.util.List;

public interface KhachHangModelInterface {
public void registerObserver(Observer o);
public void removeObserver(Observer o);
public void notifyObservers();
public KhachHang getKhachHang(String id);
public List<KhachHang> getDanhSachKhachHang();
public boolean addKhachHang(String id,String ten,String email,String diaChi,double soTienNo,String ghiChu);
public boolean setProperty(String id,String ten,String email,String diaChi,double soTienNo,String ghiChu);
public boolean delete(String id);
public void setData();
public String[] getColumnNames();
public Object[][] getRowData();
}
