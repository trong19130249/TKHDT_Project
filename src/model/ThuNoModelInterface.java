package model;

import java.sql.Date;
import java.util.List;

public interface ThuNoModelInterface {
	public void registerObserver(Observer o);
	public void removeObserver(Observer o);
	public void notifyObservers();
	public List<ThuNo> getAllDebt();
	public KhachHang getKhachHang(String id);
	public boolean setDebt(String idKh,Date ngayThu,double soTienNo,String idOld);
	public boolean addDebt(String id,Date ngayThu,double soTienNo) ;
	public boolean deleteDebt(String idKh,Date ngayThu);
	public void setData();
	public String[] getColumnNames();
	public Object[][] getRowData();
}
