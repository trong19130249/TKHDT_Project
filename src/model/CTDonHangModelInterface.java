package model;

import java.util.List;

public interface CTDonHangModelInterface {
	public void registerObserver(Observer o);

	public void removeObserver(Observer o);

	public void notifyObservers();

	public List<ChiTietDonHang> getChiTiet(String idDonHang);

	public void setData(String idDonHang);

	public boolean deleteChiTiet(String id, String idvl);

	public boolean editChiTiet(String id, String idVatLieuCu, String idVatLieu, double soLuong);

	public boolean addChiTiet(String id, String idVatLieu, double soLuong);

	public String[] getColumnNames();

	public Object[][] getRowData();
}