package Controller;

import java.sql.Date;

import model.NhapHangModelInterface;

public class LSNHController {
	private NhapHangModelInterface model;

	public LSNHController(NhapHangModelInterface model) {
		this.model = model;
	}

	public boolean setLichSu(String idNbh, Date ngayNhap, String id, String oldNbh) {
		return model.setLichSu(idNbh, ngayNhap, id, oldNbh);
	}

	public boolean addLichSu(String idls, String idNbh, Date ngayNhap) {
		return model.addLichSu(idls, idNbh, ngayNhap);
	}

	public boolean deleteLichSu(String idLichSu) {
		return model.deleteLichSu(idLichSu);
	}
}
