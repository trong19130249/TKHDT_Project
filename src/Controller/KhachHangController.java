package Controller;

import java.util.List;

import Dao.KhachHangDao;
import model.KhachHang;
import model.KhachHangModelInterface;
import model.Observer;

public class KhachHangController {
	KhachHangModelInterface model;

	public KhachHangController(KhachHangModelInterface model) {
		this.model = model;
	}

	public boolean addKhachHang(String id, String ten, String email, String diaChi, double soTienNo, String ghiChu) {
		return model.addKhachHang(id, ten, email, diaChi, soTienNo, ghiChu);
	}

	public boolean setProperty(String id, String ten, String email, String diaChi, double soTienNo, String ghiChu) {
		return model.setProperty(id, ten, email, diaChi, soTienNo, ghiChu);
	}

	public boolean delete(String id) {
		return model.delete(id);
	}
}
