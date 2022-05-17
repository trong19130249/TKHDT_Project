package Controller;

import java.util.List;

import Dao.NhanVienDao;
import model.NhanVien;
import model.NhanVienModelInterface;

public class NhanVienController {
	private NhanVienModelInterface model;

	public NhanVienController(NhanVienModelInterface model) {
		this.model = model;
	}

	public boolean deleteNhanVien(String id) {
		return model.deleteNhanVien(id);
	}

	public boolean addNhanVien(String id, String ten, int sdt, int trangThai, String ghiChu, int gioiTinh, String email,
			String diaChi, String idChucVu) {
		return model.addNhanVien(id, ten, sdt, trangThai, ghiChu, gioiTinh, email, diaChi, idChucVu);
	}

	public boolean setNhanVien(String id, String ten, int sdt, int trangThai, String ghiChu, int gioiTinh, String email,
			String diaChi, String idChucVu) {
		return model.setNhanVien(id, ten, sdt, trangThai, ghiChu, gioiTinh, email, diaChi, idChucVu);
	}
}
