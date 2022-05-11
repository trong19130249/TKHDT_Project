package model;

import java.sql.Date;
import java.util.List;
public interface TraNoModelInterface {
	public void registerObserver(Observer o);
	public void removeObserver(Observer o);
	public void notifyObservers();
	public List<TraNo> getAllDebt();
	public NhaBanHang getNBH(String id);
	public boolean addDebt(String id,Date ngayTra,double soTienNo) ;
	public boolean deleteDebt(String idNBH,Date ngayTra);
	public boolean setDebt(String idNbh,Date ngayTra,double soTienNo,String idOld) ;
	public void setData();
	public String[] getColumnNames();
	public Object[][] getRowData();
}
