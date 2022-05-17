package Controller;

import java.sql.Date;

import model.TraNoModelInterface;

public class TraNoController {
	private TraNoModelInterface model;

	public TraNoController(TraNoModelInterface model) {
		this.model = model;
	}

	public boolean addDebt(String id, Date ngayTra, double soTienNo) {
		return model.addDebt(id, ngayTra, soTienNo);
	}

	public boolean deleteDebt(String idNBH, Date ngayTra) {
		return model.deleteDebt(idNBH, ngayTra);
	}

	public boolean setDebt(String idNbh, Date ngayTra, double soTienNo, String idOld) {
		return model.setDebt(idNbh, ngayTra, soTienNo, idOld);
	}
}
