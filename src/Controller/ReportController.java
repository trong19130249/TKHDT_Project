package Controller;

import java.util.ArrayList;
import java.util.List;

import model.KhachHang;
import model.Observer;
import model.ReportModelInterface;
import model.TaiKhoan;

public class ReportController {
private ReportModelInterface model;
public ReportController(ReportModelInterface model) {
	this.model=model;
}
public List<TaiKhoan> getAll(){
	return model.getAll();
}
public boolean addAccount(String idNhanVien,String userName,String pass,int admin) {
	return model.addAccount(idNhanVien, userName, pass, admin);
}
public boolean deleteAccount(String idNhanVien) {
	return model.deleteAccount(idNhanVien);
}
public boolean editAccount(String idNew,String id, String userName,String pass,int admin) {
	return model.editAccount(idNew, id, userName, pass, admin);
}
}
