package View.model;

import java.util.ArrayList;
import java.util.List;

import Dao.NhapHangDao;
import model.ASanPham;
import model.CTNhapHangModelInterface;
import model.ChiTietLichSuMuaHang;
import model.LichSuMuaHang;
import model.Observer;

public class APurchaseData implements CTNhapHangModelInterface{
	private String[] columnNames = { "", "Mã hàng hóa", "Tên hàng hóa", "DVT", "Giá nhập", "Số lượng", "Loại hàng hóa",
			 "Tổng tiền" };
	
	private Object[][] rowData;
	public APurchaseData() {
		
	}
	 
	public String[] getColumnNames() {
		return columnNames;
	}
	 
	public Object[][] getRowData() {
		return rowData;
	}
	ArrayList<Observer> observers=new ArrayList<Observer>();
	public void setData(String idLichSu) {
		List<ChiTietLichSuMuaHang> list=getChiTiet(idLichSu);
		rowData=new Object[list.size()][columnNames.length];
		for(int i=0;i<rowData.length;i++) {
			ChiTietLichSuMuaHang ct=list.get(i);
			ASanPham sp=ct.getVatLieu();
			Object[] element= {false,sp.getId(),sp.getTenSanPham(),sp.getDonViTinh(),sp.getGiaNhap(),ct.getSoLuong(),ct.getVatLieu().getType(),ct.thanhTien()};
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

	@Override
	public List<ChiTietLichSuMuaHang> getChiTiet(String idLichSu) {
		// TODO Auto-generated method stub
		return NhapHangDao.getInstance().getChiTiet(idLichSu);
	}

	@Override
	public boolean deleteChiTiet(String id, String idvl) {
		// TODO Auto-generated method stub
		boolean r=NhapHangDao.getInstance().deleteChiTiet(id, idvl);
		if(r) notifyObservers();
		return r;
	}

	@Override
	public boolean editChiTiet(String id,String idVatLieuCu, String idVatLieu, double soLuong) {
		// TODO Auto-generated method stub
		boolean r=NhapHangDao.getInstance().editChiTiet(id, idVatLieuCu,idVatLieu, soLuong);
		if(r)notifyObservers();
		return r;
	}

	@Override
	public boolean addChiTiet(String idLichSu, String idVatLieu, double soLuong) {
		// TODO Auto-generated method stub
		boolean r=NhapHangDao.getInstance().addChiTiet(idLichSu, idVatLieu, soLuong);
		if(r)notifyObservers();
		return r;
	}
	

}
