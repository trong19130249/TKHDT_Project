package Controller;

import java.sql.Date;

import model.DonHangModelInterface;

public class HoaDonController {
private DonHangModelInterface model;
public HoaDonController(DonHangModelInterface model) {
	this.model=model;
}
public boolean delete(String id) {
	return model.delete(id);
}
public boolean setDonHang(String idKh,Date ngayLap,String id,String oldKh) {
	return model.setDonHang(idKh, ngayLap,id,oldKh);
}
public boolean addDonHang(String idDh,String idKh,Date ngayLap) {
	return model.addDonHang(idDh, idKh, ngayLap);
}
}
