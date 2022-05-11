package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Dao.ConnectionDao;

public interface ReportModelInterface {
	public void registerObserver(Observer o);
	public void removeObserver(Observer o);
	public void notifyObservers();
	public List<TaiKhoan> getAll();
	public boolean addAccount(String idNhanVien,String userName,String pass,int admin) ;
	public boolean deleteAccount(String idNhanVien) ;
	public boolean editAccount(String idNew,String id, String userName,String pass,int admin) ;
	public String[] getColumnNames();
	public Object[][] getRowData();
	public void setAdmin(boolean admin);
	public void setData() ;
}
