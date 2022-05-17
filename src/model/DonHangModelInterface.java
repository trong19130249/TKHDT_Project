package model;

import java.sql.Date;
import java.util.List;
import java.util.Map;

public interface DonHangModelInterface {
	public void registerObserver(Observer o);

	public void removeObserver(Observer o);

	public void notifyObservers();

	public List<DonHang> getAll();

	public List<DonHang> getAllOrder();

	public DonHang getDonHang(String idDonHang);

	public boolean delete(String id);

	public boolean setDonHang(String idKh, Date ngayLap, String id, String oldKh);

	public List<DonHang> getAll(String idKhachHang);

	public boolean addDonHang(String idDh, String idKh, Date ngayLap);

	public boolean addDonHang(String idKh, double payMoneyDate, Date ngayLap, Map<String, Double> data);

	public void setData();

	public String[] getColumnNames();

	public Object[][] getRowData();

	public Object[][] getDataTable();

	public double getRatioTotalOrderMonth(int service);

	public int countOrderMonth();

	public double totalProfitMonth();

	public int totalReceivedMonth();
}
