package View.model;

import java.util.ArrayList;
import java.util.List;

import Dao.NhanVienDao;
import Dao.TaiKhoanDao;
import model.KhachHang;
import model.NhanVien;
import model.Observer;
import model.ReportModelInterface;
import model.TaiKhoan;

public class AccountData implements ReportModelInterface {
	private String[] columnNames = { "", "Tên đăng nhập", "Mật khẩu", "Hoạt động", "Chi tiết" };
	private Object[][] rowData;
	private boolean admin=false;
	public AccountData() {

	}

	public String[] getColumnNames() {
		return columnNames;
	}

	public Object[][] getRowData() {
		return rowData;
	}
	ArrayList<Observer> observers=new ArrayList<Observer>();
	public void setAdmin(boolean admin) {
		this.admin=admin;
	}
	public void setData() {
		List<TaiKhoan> list=getAll();
		rowData=new Object[list.size()][columnNames.length];
		for(int i=0;i<rowData.length;i++) {
			TaiKhoan tk=list.get(i);
			String statusString=tk.getNhanVien().getTrangThai()==0?"Chưa hoạt động":"Đang hoạt động";
			String passString="";
			if(admin) {
				passString=tk.getPass();
			}
			else {
				for(int j=0;j<tk.getPass().length();j++) {
					passString+="*";
				}
			}
			Object[] element= {false,tk.getUserName(),passString,statusString,"Xem"};
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
	public List<TaiKhoan> getAll() {
		// TODO Auto-generated method stub
		return TaiKhoanDao.getInstance().getAll();
	}

	@Override
	public boolean addAccount(String idNhanVien, String userName, String pass, int admin) {
		// TODO Auto-generated method stub
		boolean r=TaiKhoanDao.getInstance().addAccount(idNhanVien, userName, pass, admin);
		notifyObservers();
		return r;
	}

	@Override
	public boolean deleteAccount(String idNhanVien) {
		// TODO Auto-generatesd method stub
		boolean r=TaiKhoanDao.getInstance().deleteAccount(idNhanVien);
		if(r) notifyObservers();
		return r;
	}

	@Override
	public boolean editAccount(String idNew, String id, String userName, String pass, int admin) {
		// TODO Auto-generated method stub
		boolean r=TaiKhoanDao.getInstance().editAccount(idNew, id, userName, pass, admin);
		if(r) notifyObservers();
		return r;
	}
	



}
