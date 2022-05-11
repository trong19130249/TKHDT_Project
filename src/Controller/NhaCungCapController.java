package Controller;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import Dao.ConnectionDao;
import model.NhaCungCapModelInterface;

public class NhaCungCapController {
private NhaCungCapModelInterface model;
public NhaCungCapController(NhaCungCapModelInterface model) {
	this.model=model;
}
public boolean deleteNCC(String id) {
	return model.deleteNCC(id);
}
public boolean addNCC(String id,String ten,int sdt,String diaChi,double noCCC,String ghiChu) {
	return model.addNCC(id, ten, sdt, diaChi, noCCC, ghiChu);
}
public boolean editNCC(String id,String ten,int sdt,String diaChi,double noCCC,String ghiChu) {
	return model.editNCC(id, ten, sdt, diaChi, noCCC, ghiChu);
}
}
