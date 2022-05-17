package View.model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import Dao.NhapHangDao;
import model.CTNhapHangModelInterface;
import model.KhachHang;
import model.LichSuMuaHang;
import model.NhapHangModelInterface;
import model.Observer;

public class PurchasesData implements NhapHangModelInterface {
	private String[] columnNames = { "", "Chi tiết", "Mã nhập hàng", "Mã NCC", "Tên nhà cung cấp", "Số ĐT",
			"Tổng tiền" };

	private Object[][] rowData;

	public PurchasesData() {

	}

	public String[] getColumnNames() {
		return columnNames;
	}

	public Object[][] getRowData() {
		return rowData;
	}

	ArrayList<Observer> observers = new ArrayList<Observer>();

	public void setData() {
		List<LichSuMuaHang> list = getAll();
		rowData = new Object[list.size()][columnNames.length];
		for (int i = 0; i < rowData.length; i++) {
			LichSuMuaHang ls = list.get(i);
			Object[] element = { false, "Xem", ls.getId(), ls.getCoSoNhap().getId(), ls.getCoSoNhap().getTen(),
					"0" + ls.getCoSoNhap().getSdt(), ls.thanhTien() };
			rowData[i] = element;
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
	public List<LichSuMuaHang> getAll() {
		// TODO Auto-generated method stub
		return NhapHangDao.getInstance().getAll();
	}

	@Override
	public List<LichSuMuaHang> getAll(String idNhaBanHang) {
		// TODO Auto-generated method stub
		return NhapHangDao.getInstance().getAll(idNhaBanHang);
	}

	@Override
	public LichSuMuaHang getLichSu(String idLichSu) {
		// TODO Auto-generated method stub

		return NhapHangDao.getInstance().getLichSu(idLichSu);
	}

	@Override
	public boolean setLichSu(String idNbh, Date ngayNhap, String id, String oldNbh) {
		// TODO Auto-generated method stub
		boolean r = NhapHangDao.getInstance().setLichSu(idNbh, ngayNhap, id, oldNbh);
		if (r)
			notifyObservers();
		return r;
	}

	@Override
	public boolean addLichSu(String idls, String idNbh, Date ngayNhap) {
		// TODO Auto-generated method stub
		boolean r = NhapHangDao.getInstance().addLichSu(idls, idNbh, ngayNhap);
		if (r)
			notifyObservers();
		return r;
	}

	@Override
	public boolean deleteLichSu(String idLichSu) {
		// TODO Auto-generated method stub
		boolean r = NhapHangDao.getInstance().deleteLichSu(idLichSu);
		if (r)
			notifyObservers();
		return r;
	}

}
