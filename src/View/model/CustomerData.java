package View.model;

import java.util.ArrayList;
import java.util.List;

import Dao.KhachHangDao;
import Dao.VatLieuDao;
import model.KhachHang;
import model.KhachHangModelInterface;
import model.Observer;

public class CustomerData implements KhachHangModelInterface{
	private String[] columnNames = { "", "Mã khách hàng", "Tên khách hàng", "Email", "Địa chỉ", "Số tiền nợ",
			"Ghi chú" };
	private Object[][] rowData;
	
	public CustomerData() {
		
	}
	ArrayList<Observer> observers=new ArrayList<Observer>();
	public void setData() {
		List<KhachHang> list=getDanhSachKhachHang();
		rowData=new Object[list.size()][columnNames.length];
		for(int i=0;i<rowData.length;i++) {
			KhachHang kh=list.get(i);
			Object[] element= {false,kh.getMaSo(),kh.getHoVaTen(),kh.getEmail(),kh.getDiachi(),kh.getSoTienNo(),kh.getGhiChu()};
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
	public KhachHang getKhachHang(String id) {
		// TODO Auto-generated method stub
		return KhachHangDao.getInstance().getKhachHang(id);
	}
	@Override
	public List<KhachHang> getDanhSachKhachHang() {
		// TODO Auto-generated method stub
		return KhachHangDao.getInstance().getDanhSachKhachHang();
	}
	@Override
	public boolean setProperty(String id,String ten,String email,String diaChi,double soTienNo,String ghiChu) {
		// TODO Auto-generated method stub
		boolean result= KhachHangDao.getInstance().setProperty(id, ten, email, diaChi, soTienNo, ghiChu);
		if(result)notifyObservers();
	return result;
	}
	@Override
	public boolean addKhachHang(String id, String ten, String email, String diaChi, double soTienNo, String ghiChu) {
		// TODO Auto-generated method stub
		boolean result= KhachHangDao.getInstance().addKhachHang(id, ten, email, diaChi, soTienNo, ghiChu);
		if(result)notifyObservers();
	return result;
	}
	@Override
	public boolean delete(String id) {
		// TODO Auto-generated method stub
		boolean re=KhachHangDao.getInstance().deleteKhachHang(id);
		if(re) notifyObservers();
		return re;
	}


	

}
