package View.model;

import java.util.ArrayList;
import java.util.List;

import Dao.NhaCungCapDao;
import model.KhachHang;
import model.NhaBanHang;
import model.NhaCungCapModelInterface;
import model.Observer;

public class SupplierData implements NhaCungCapModelInterface{
	private String[] columnNames = { "", "Mã NCC", "Tên nhà cung cấp", "Điện thoại", "Địa chỉ", "Nợ NCC", "Ghi chú" };

	private Object[][] rowData;
	public SupplierData() {
		
	}
	ArrayList<Observer> observers=new ArrayList<Observer>();
	public void setData() {
		List<NhaBanHang> list=getAll();
		rowData=new Object[list.size()][columnNames.length];
		for(int i=0;i<rowData.length;i++) {
			NhaBanHang nbh=list.get(i);
			Object[] element= {false,nbh.getId(),nbh.getTen(),nbh.getSdt(),nbh.getDiaChi(),nbh.getNoCCC(),nbh.getGhiChu()};
			rowData[i]=element;
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
		int index=observers.indexOf(o);
		if(index>=0) observers.remove(index);
		
	}

	@Override
	public void notifyObservers() {
		// TODO Auto-generated method stub
		for(Observer view:observers) {
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
	public List<NhaBanHang> getAll() {
		// TODO Auto-generated method stub
		return NhaCungCapDao.getInstance().getAll();
	}
	@Override
	public boolean deleteNCC(String id) {
		// TODO Auto-generated method stub
		boolean r= NhaCungCapDao.getInstance().deleteNCC(id);
		notifyObservers();
		return r;
	}
	@Override
	public boolean addNCC(String id, String ten, int sdt, String diaChi, double noCCC, String ghiChu) {
		// TODO Auto-generated method stub
		boolean r=NhaCungCapDao.getInstance().addNCC(id, ten, sdt, diaChi, noCCC, ghiChu);
		if(r)notifyObservers();
		return r;
		
	}
	@Override
	public boolean editNCC(String id, String ten, int sdt, String diaChi, double noCCC, String ghiChu) {
		// TODO Auto-generated method stub
		boolean r=NhaCungCapDao.getInstance().editNCC(id, ten, sdt, diaChi, noCCC, ghiChu);
		if(r)notifyObservers();
	return r;
	}
	@Override
	public NhaBanHang getNBH(String idNBH) {
		// TODO Auto-generated method stub
		return NhaCungCapDao.getInstance().getNBH(idNBH);
	}
	

}
