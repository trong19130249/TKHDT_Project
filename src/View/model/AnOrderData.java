package View.model;

import java.util.ArrayList;
import java.util.List;

import Dao.DonHangDao;
import model.ASanPham;
import model.CTDonHangModelInterface;
import model.ChiTietDonHang;
import model.Observer;

public class AnOrderData implements CTDonHangModelInterface {
	private String[] columnNames = { "", "Mã hàng hóa", "Tên hàng hóa", "DVT", "Giá bán", "Số lượng", "Loại hàng hóa",
			"Thành tiền" };
	private Object[][] rowData;
	ArrayList<Observer> observers = new ArrayList<Observer>();

	public AnOrderData() {

	}

	public void setData(String idDonHang) {
		List<ChiTietDonHang> list = getChiTiet(idDonHang);
		rowData = new Object[list.size()][columnNames.length];
		for (int i = 0; i < rowData.length; i++) {
			ChiTietDonHang ct = list.get(i);
			ASanPham sp = ct.getSanPham();
			Object[] element = { false, sp.getId(), sp.getTenSanPham(), sp.getDonViTinh(), sp.getDonGia(),
					ct.getSoluong(), sp.getType(), ct.thanhTien() };
			rowData[i] = element;
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
		int index = observers.indexOf(o);
		if (index >= 0)
			observers.remove(index);

	}

	@Override
	public void notifyObservers() {
		// TODO Auto-generated method stub
		for (Observer view : observers) {
			view.update();
		}
	}

	@Override
	public List<ChiTietDonHang> getChiTiet(String idDonHang) {
		// TODO Auto-generated method stub
		return DonHangDao.getInstance().getChiTiet(idDonHang);
	}

	@Override
	public boolean deleteChiTiet(String id, String idvl) {
		// TODO Auto-generated method stub
		boolean r = DonHangDao.getInstance().deleteChiTiet(id, idvl);
		if (r)
			notifyObservers();
		return r;
	}

	@Override
	public boolean editChiTiet(String id, String idVatLieuCu, String idVatLieu, double soLuong) {
		// TODO Auto-generated method stub
		boolean r = DonHangDao.getInstance().editChiTiet(id, idVatLieuCu, idVatLieu, soLuong);
		if (r)
			notifyObservers();
		return r;
	}

	@Override
	public boolean addChiTiet(String id, String idVatLieu, double soLuong) {
		// TODO Auto-generated method stub
		boolean r = DonHangDao.getInstance().addChiTiet(id, idVatLieu, soLuong);
		if (r)
			notifyObservers();
		return r;
	}

}
