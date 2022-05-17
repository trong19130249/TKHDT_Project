package model;

import java.util.List;

public interface CTNhapHangModelInterface {
	public void registerObserver(Observer o);

	public void removeObserver(Observer o);

	public void notifyObservers();

	public List<ChiTietLichSuMuaHang> getChiTiet(String idLichSu);

	public void setData(String idLichSu);

	public boolean deleteChiTiet(String id, String idvl);

	public boolean editChiTiet(String id, String idVatLieuCu, String idVatLieu, double soLuong);

	public boolean addChiTiet(String idLichSu, String idVatLieu, double soLuong);

	public String[] getColumnNames();

	public Object[][] getRowData();
}