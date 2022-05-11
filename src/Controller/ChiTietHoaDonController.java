package Controller;

import View.model.PurchasesData;
import model.CTDonHangModelInterface;
import model.NhapHangModelInterface;

public class ChiTietHoaDonController {
	
private CTDonHangModelInterface model;
public ChiTietHoaDonController(CTDonHangModelInterface model) {
	this.model=model;
}
public boolean deleteChiTiet(String id,String idvl) {
	return model.deleteChiTiet(id,idvl);
}
public boolean editChiTiet(String id,String idVatLieuCu,String idVatLieu,double soLuong) {
	return model.editChiTiet(id,idVatLieuCu, idVatLieu,soLuong);
}
public boolean addChiTiet(String id,String idVatLieu,double soLuong) {
	return model.addChiTiet(id,idVatLieu, soLuong);
}
}
