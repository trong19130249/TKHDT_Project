package Controller;

import View.model.APurchaseData;
import model.CTNhapHangModelInterface;

public class ChiTietLSNHController {
private CTNhapHangModelInterface model;
public ChiTietLSNHController(CTNhapHangModelInterface model) {
	this.model=model;
}
public boolean deleteChiTiet(String id,String idvl) {
	return model.deleteChiTiet(id, idvl);
}
public boolean editChiTiet(String id,String idVatLieuCu,String idVatLieu,double soLuong) {
	return model.editChiTiet(id, idVatLieuCu,idVatLieu, soLuong);
}
public boolean addChiTiet(String idLichSu,String idVatLieu,double soLuong) {
	return model.addChiTiet(idLichSu, idVatLieu, soLuong);
	
}
}
