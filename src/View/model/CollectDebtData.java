package View.model;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import Dao.ThuNoDao;
import model.ASanPham;
import model.ChiTietLichSuMuaHang;
import model.KhachHang;
import model.LichSuMuaHang;
import model.Observer;
import model.ThuNo;
import model.ThuNoModelInterface;

public class CollectDebtData implements ThuNoModelInterface {
	private String[] columnNames = { "", "Tên khách hàng", "Mã Khách Hàng", "Số tiền", "Ngày thu", "Email",
			"Địa chỉ", "Ghi chú", "Trạng Thái" };
	private Object[][] rowData;

	public CollectDebtData() {

	}

	ArrayList<Observer> observers = new ArrayList<Observer>();

	@Override
	public void setData() {
		// TODO Auto-generated method stub
		List<ThuNo> list = getAllDebt();
		rowData = new Object[list.size()][columnNames.length];
		for (int i = 0; i < rowData.length; i++) {
			ThuNo thuNo = list.get(i);
			KhachHang kh = thuNo.getKhachHang();
			String trangThai = (thuNo.getNgayThu() == null) ? "Chưa Thanh Toán" : "Đã Thu";
			String dateString;
			if (thuNo.getNgayThu() == null)
				dateString = "";
			else {
				SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
				dateString = sdf.format(thuNo.getNgayThu());
			}
			Object[] element = { false, kh.getHoVaTen(), kh.getMaSo(), thuNo.getSoTienNo(), dateString, kh.getEmail(),
					kh.getDiachi(), kh.getGhiChu(), trangThai };
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

	public String[] getColumnNames() {
		return columnNames;
	}

	public Object[][] getRowData() {
		return rowData;
	}

	@Override
	public KhachHang getKhachHang(String id) {
		// TODO Auto-generated method stub
		return ThuNoDao.getInstance().getKhachHang(id);
	}

	@Override
	public boolean addDebt(String id, Date ngayThu, double soTienNo) {
		// TODO Auto-generated method stub
		boolean r = ThuNoDao.getInstance().addDebt(id, ngayThu, soTienNo);
		if (r)
			notifyObservers();
		return r;
	}

	@Override
	public boolean deleteDebt(String idKh, Date ngayThu) {
		// TODO Auto-generated method stub
		boolean r = ThuNoDao.getInstance().deleteDebt(idKh, ngayThu);
		if (r)
			notifyObservers();
		return r;
	}

	@Override
	public List<ThuNo> getAllDebt() {
		// TODO Auto-generated method stub
		return ThuNoDao.getInstance().getAllDebt();
	}

	@Override
	public boolean setDebt(String idKh, Date ngayThu, double soTienNo, String idOld) {
		// TODO Auto-generated method stub
		boolean r = ThuNoDao.getInstance().setDebt(idKh, ngayThu, soTienNo, idOld);
		if (r)
			notifyObservers();
		return r;
	}

}
