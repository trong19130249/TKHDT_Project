package model;

import java.util.List;;

public interface NhaCungCapModelInterface {
	public void registerObserver(Observer o);

	public void removeObserver(Observer o);

	public void notifyObservers();

	public List<NhaBanHang> getAll();

	public boolean deleteNCC(String id);

	public boolean addNCC(String id, String ten, int sdt, String diaChi, double noCCC, String ghiChu);

	public boolean editNCC(String id, String ten, int sdt, String diaChi, double noCCC, String ghiChu);

	public NhaBanHang getNBH(String idNBH);

	public void setData();

	public String[] getColumnNames();

	public Object[][] getRowData();
}
