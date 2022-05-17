package View.model;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import Dao.TraNoDao;
import model.KhachHang;
import model.NhaBanHang;
import model.Observer;
import model.ThuNo;
import model.TraNo;
import model.TraNoModelInterface;

public class PayDebtData implements TraNoModelInterface {
	private String[] columnNames = { "", "Tên nhà cung cấp", "Mã Nhà CC", "Số tiền", "Ngày trả", "Ghi chú" };
	private Object[][] rowData;

	public PayDebtData() {

	}

	ArrayList<Observer> observers = new ArrayList<Observer>();

	@Override
	public void setData() {
		// TODO Auto-generated method stub
		List<TraNo> list = getAllDebt();

		rowData = new Object[list.size()][columnNames.length];
		for (int i = 0; i < rowData.length; i++) {
			TraNo traNo = list.get(i);
			NhaBanHang nbh = traNo.getNbh();
			String trangThai = (traNo.getNgayTra() == null) ? "Còn Nợ" : "Đã Trả";
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
			String dateString;
			if (traNo.getNgayTra() == null)
				dateString = "";
			else {

				dateString = sdf.format(traNo.getNgayTra());
			}
			Object[] element = { false, nbh.getTen(), nbh.getId(), traNo.getSoTienNo(), dateString, nbh.getGhiChu() };
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
	public List<TraNo> getAllDebt() {
		// TODO Auto-generated method stub
		return TraNoDao.getInstance().getAllDebt();
	}

	@Override
	public NhaBanHang getNBH(String id) {
		// TODO Auto-generated method stub
		return TraNoDao.getInstance().getNBH(id);
	}

	@Override
	public boolean addDebt(String id, Date ngayTra, double soTienNo) {
		// TODO Auto-generated method stub
		boolean r = TraNoDao.getInstance().addDebt(id, ngayTra, soTienNo);
		if (r)
			notifyObservers();
		return r;
	}

	@Override
	public boolean deleteDebt(String idNBH, Date ngayTra) {
		// TODO Auto-generated method stub
		boolean r = TraNoDao.getInstance().deleteDebt(idNBH, ngayTra);
		if (r)
			notifyObservers();
		return r;
	}

	@Override
	public boolean setDebt(String idNbh, Date ngayTra, double soTienNo, String idOld) {
		// TODO Auto-generated method stub
		boolean r = TraNoDao.getInstance().setDebt(idNbh, ngayTra, soTienNo, idOld);
		if (r)
			notifyObservers();
		return r;
	}

}
