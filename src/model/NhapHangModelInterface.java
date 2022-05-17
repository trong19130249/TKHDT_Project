package model;

import java.sql.Date;
import java.util.List;

public interface NhapHangModelInterface {
	public void registerObserver(Observer o);

	public void removeObserver(Observer o);

	public void notifyObservers();

	public List<LichSuMuaHang> getAll();

	public List<LichSuMuaHang> getAll(String idNhaBanHang);

	public LichSuMuaHang getLichSu(String idLichSu);

	public boolean setLichSu(String idNbh, Date ngayNhap, String id, String oldNbh);

	public boolean addLichSu(String idls, String idNbh, Date ngayNhap);

	public boolean deleteLichSu(String idLichSu);

	public void setData();

	public String[] getColumnNames();

	public Object[][] getRowData();
}