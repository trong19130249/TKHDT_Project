package View.model;

import java.util.ArrayList;
import java.util.List;

import Dao.NhanVienDao;
import Dao.TaiKhoanDao;
import model.DangNhapDangXuatModelInterface;
import model.LichSuMuaHang;
import model.NhanVien;
import model.Observer;
import model.TaiKhoan;

public class SignInSignUpData implements DangNhapDangXuatModelInterface {
	public SignInSignUpData() {

	}

	ArrayList<Observer> observers = new ArrayList<Observer>();

	@Override
	public void registerObserver(Observer o) {
		// TODO Auto-generated method stub
		observers.add(o);
	}

	@Override
	public void removeObserver(Observer o) {
		// TODO Auto-generated method stub
		int index = observers.indexOf(o);
		if (index >= 0)
			observers.remove(index);

	}

	@Override
	public void notifyObservers() {
		// TODO Auto-generated method stub
		for (Observer view : observers) {
			view.update();
		}
	}

	@Override
	public boolean signup(TaiKhoan cus) {
		// TODO Auto-generated method stub
		NhanVien nv = cus.getNhanVien();
		boolean addTk = false;
		boolean addNv = NhanVienDao.getInstance().addNhanVien(nv.getId(), nv.getTen(), nv.getSdt(), nv.getTrangThai(),
				nv.getGhiChu(), nv.getGioiTinh(), nv.getEmail(), nv.getDiaChi(), nv.getChucVu());
		if (addNv) {
			addTk = TaiKhoanDao.getInstance().addAccount(nv.getId(), cus.getUserName(), cus.getPass(), 0);
		}
		return addTk && addNv;
	}

	@Override
	public boolean signin(String userName, String password) {
		// TODO Auto-generated method stub
		List<TaiKhoan> danhSachTaiKhoan = TaiKhoanDao.getInstance().getAll();
		for (TaiKhoan tk : danhSachTaiKhoan) {
			if (tk.getUserName().equals(userName) && tk.getPass().equals(password)) {
				return true;
			}

		}
		return false;
	}

	@Override
	public boolean containUserName(String userName) {
		// TODO Auto-generated method stub
		List<TaiKhoan> danhSachTaiKhoan = TaiKhoanDao.getInstance().getAll();
		for (TaiKhoan tk : danhSachTaiKhoan) {
			if (tk.getUserName().equals(userName))
				;
			return true;
		}
		return false;
	}

}
