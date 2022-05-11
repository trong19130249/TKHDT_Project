package View.model;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import Dao.VatLieuDao;
import model.ASanPham;
import model.Observer;
import model.VatLieuModelInterface;

public class ProductsData implements VatLieuModelInterface{
	// Mảng chứa tên các trường (table header) của bảng. Dữ liệu sẽ được hiển thị ở dạng bảng
	private String[] columnNames = {"", "Mã hàng hóa", "Tên hàng hóa", "DVT", "Giá nhập", "Giá bán", "Tồn kho",
			"Loại hàng hóa" };
	// Mảng chưa các dòng dữ liệu
	private Object[][] rowData;
	// Danh sách các observer đã đăng ký với modelView
	ArrayList<Observer> observers=new ArrayList<Observer>();
	public ProductsData() {

	}
	// Thiết lập các dòng dữ liệu cho bảng
	public void setData() {
		// Lấy ra tất cả các vật liệu có trong kho (trừ trường hợp bị vô hiệu hóa)
		List<ASanPham> list=this.getAll();
		rowData=new Object[list.size()][columnNames.length];
		
		for(int i=0;i<rowData.length;i++) {
			ASanPham vl=list.get(i);
			Object[] element= {false,vl.getId(),vl.getTenSanPham(),vl.getDonViTinh(),vl.getGiaNhap(),vl.getDonGia(),vl.getSoLuong(),vl.getType()};
			rowData[i]=element;
		}
	}
	public String[] getColumnNames() {
		return columnNames;
	}
 
	public Object[][] getRowData() {
		return rowData;
	}

	@Override
	public void registerObserver(Observer o) {
		// TODO Auto-generated method stub
		observers.add(o);
	}

	@Override
	public void removeObserver(Observer o) {
		// TODO Auto-generated method stub
		int index=observers.indexOf(o);
		if(index>=0) observers.remove(index);
		
	}

	@Override
	public void notifyObservers() {
		// TODO Auto-generated method stub
		for(Observer view:observers) {
			view.update();
		}
	}
	// Lấy vật liệu tương ứng với id truyền vào
	@Override
	public ASanPham getVatLieu(String id) {
		// TODO Auto-generated method stub
		return VatLieuDao.getInstance().getVatLieu(id);
	}
	// Lấy ra tất cả các vật liệu có trong kho (trừ trường hợp bị vô hiệu hóa)
	@Override
	public List<ASanPham> getAll() {
		// TODO Auto-generated method stub
		return VatLieuDao.getInstance().getAll();
	}
	// Cập nhật lại dữ liệu 
	@Override
	public boolean setProperty(String id,String ten,String donViTinh,double donGia,double soLuong,String loai,double giaNhap,String idnew) {
		// TODO Auto-generated method stub
		boolean result=VatLieuDao.getInstance().setProperty(id, ten, donViTinh, donGia, soLuong,loai, giaNhap, idnew);
		// thông báo, thực hiện cập nhật lại view khi dữ liệu có sự thay đổi
		if(result) notifyObservers();
		return result;
	}
	 // Thêm vật liệu
	@Override
	public boolean addVatLieu(String id, String ten, String donViTinh, double donGia, double soLuong, String loai,
			double giaNhap) {
		// TODO Auto-generated method stub
		boolean result= VatLieuDao.getInstance().addVatLieu(id, ten, donViTinh, donGia, soLuong, loai, giaNhap);
		if(result)notifyObservers();
		return result;
	}
	// Xóa Vật Liệu
	@Override
	public boolean delete(String id) {
		// TODO Auto-generated method stub
		boolean re= VatLieuDao.getInstance().deleteVatLieu(id);
		if(re)notifyObservers();
		return re;
	}

}
