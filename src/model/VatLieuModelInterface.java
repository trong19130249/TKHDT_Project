package model;

import java.util.List;

public interface VatLieuModelInterface {
	public void registerObserver(Observer o);
	public void removeObserver(Observer o);
	public void notifyObservers();
	public ASanPham getVatLieu(String id);
	public List<ASanPham> getAll();
	public boolean setProperty(String id,String ten,String donViTinh,double donGia,double soLuong,String loai,double giaNhap,String idnew);
	public boolean addVatLieu(String id,String ten,String donViTinh,double donGia,double soLuong,String loai,double giaNhap);
	public boolean delete(String id);
	// Thiết lập lại dữ liệu, được tải lên từ database
	public void setData();
	// Nhận về mảng các trường sẽ hiển thị ra trong jtable ở view
	public String[] getColumnNames();
	// Nhận về mảng các dòng dữ liệu sẽ hiển thị ra trong jtable ở view
	public Object[][] getRowData();
}
