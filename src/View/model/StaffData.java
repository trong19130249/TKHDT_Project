package View.model;

import java.util.ArrayList;
import java.util.List;

import Dao.NhanVienDao;
import Dao.VatLieuDao;
import model.NhanVien;
import model.NhanVienModelInterface;
import model.Observer;

public class StaffData implements NhanVienModelInterface{
	private String[] columnNames = { "", "Mã nhân viên", "Tên nhân viên", "Điện thoại", "Lương", "Chức vụ", "Email", "Giới tính", "Hoạt động", "Ghi chú", "Chi tiết" };
	private Object[][] rowData;
	public StaffData() {
		
	}
	ArrayList<Observer> observers=new ArrayList<Observer>();
	@Override
	public void setData() {
		// TODO Auto-generated method stub
		List<NhanVien> list=getAll();
		rowData=new Object[list.size()][columnNames.length];
		for(int i=0;i<rowData.length;i++) {
			NhanVien nv=list.get(i);
			String statusString=nv.getTrangThai()==0?"Chưa hoạt động":"Đang hoạt động";
			String sexString=nv.getGioiTinh()==0?"Nữ":"Nam";
			Object[] element= {false,nv.getId(),nv.getTen(),"0"+nv.getSdt(),nv.getLuong(),nv.getChucVu(),nv.getEmail(),sexString,statusString,nv.getGhiChu(),"Xem"};
			rowData[i]=element;
		}
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
	public String[] getColumnNames() {
		return columnNames;
	}
	
	public Object[][] getRowData() {
		return rowData;
	}

	
	@Override
	public List<NhanVien> getAll() {
		// TODO Auto-generated method stub
		return NhanVienDao.getInstance().getAll();
	}

	@Override
	public boolean deleteNhanVien(String id) {
		// TODO Auto-generated method stub
		boolean r= NhanVienDao.getInstance().deleteNhanVien(id);
		if(r) notifyObservers();
		return r;
	}
	@Override
	public boolean addNhanVien(String id, String ten, int sdt, int trangThai, String ghiChu, int gioiTinh, String email,
			String diaChi, String idChucVu) {
		// TODO Auto-generated method stub
		boolean r=NhanVienDao.getInstance().addNhanVien(id, ten, sdt, trangThai, ghiChu, gioiTinh, email, diaChi, idChucVu);
		if(r)notifyObservers(); return r;
	}
	@Override
	public boolean setNhanVien(String id, String ten, int sdt, int trangThai, String ghiChu, int gioiTinh, String email,
			String diaChi, String idChucVu) {
		// TODO Auto-generated method stub
		boolean r=NhanVienDao.getInstance().setNhanVien(id, ten, sdt, trangThai, ghiChu, gioiTinh, email, diaChi, idChucVu);
		if(r)notifyObservers(); return r;
	}
	@Override
	public NhanVien getNhanVien(String id) {
		// TODO Auto-generated method stub
		return NhanVienDao.getInstance().getNhanVien(id);
	}


	
	

}
