package Controller;

import java.sql.Date;

import model.ThuNoModelInterface;

public class ThuNoController {
	private ThuNoModelInterface model;

	public ThuNoController(ThuNoModelInterface model) {
		this.model = model;
	}

	public boolean addDebt(String id, Date ngayThu, double soTienNo) {
		return model.addDebt(id, ngayThu, soTienNo);
	}

	public boolean deleteDebt(String idKh, Date ngayThu) {
		return model.deleteDebt(idKh, ngayThu);
	}

	public boolean setDebt(String idKh, Date ngayThu, double soTienNo, String idOld) {
		return model.setDebt(idKh, ngayThu, soTienNo, idOld);
	}
}
