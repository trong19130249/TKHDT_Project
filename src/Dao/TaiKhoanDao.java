package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.ChucVu;
import model.NhanVien;
import model.TaiKhoan;

public class TaiKhoanDao {
	private static TaiKhoanDao instance;
	Connection connection;

	private TaiKhoanDao() {

	}

	public static TaiKhoanDao getInstance() {
		if (instance == null)
			instance = new TaiKhoanDao();
		return instance;
	}

	public List<ChucVu> getListChucVu() {
		List<ChucVu> result = new ArrayList<ChucVu>();
		try {
			connection = ConnectionDao.getConnection();
			PreparedStatement ps = connection.prepareStatement("select * from chuc_vu");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				String idChucVu = rs.getString("id");
				String ten = rs.getString("ten_cv");
				double luong = rs.getDouble("luong");
				ChucVu cv = new ChucVu(idChucVu, ten, luong);
				result.add(cv);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public TaiKhoan getAccountWithUser(String userName) {
		try {
			connection = ConnectionDao.getConnection();
			PreparedStatement ps = connection.prepareStatement(
					"select * from nhan_vien nv inner join tai_khoan tk on tk.id_nv=nv.id inner join chuc_vu cv on cv.id=nv.id_cv where tk.user_name=?");
			ps.setString(1, userName);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				String id = rs.getString("id_nv");
				String ten = rs.getString("ten");
				int sdt = rs.getInt("sdt");
				int trangThai = rs.getInt("trang_thai");
				String ghiChu = rs.getString("ghi_chu");
				int gioiTinh = rs.getInt("gioi_tinh");
				String email = rs.getString("dia_chi");
				String diaChi = rs.getString("dia_chi");
				String tenChucVu = rs.getString("cv.ten_cv");
				double luong = rs.getDouble("luong");
				NhanVien nv = new NhanVien(id, ten, sdt, trangThai, ghiChu, gioiTinh, email, diaChi, tenChucVu, luong);
				String pass = rs.getString("pass");
				int quyen = rs.getInt("admin");
				TaiKhoan tk = new TaiKhoan(userName, pass, nv, quyen);
				return tk;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public TaiKhoan getAccount(String id) {
		try {
			connection = ConnectionDao.getConnection();
			PreparedStatement ps = connection.prepareStatement(
					"select * from nhan_vien nv inner join tai_khoan tk on tk.id_nv=nv.id inner join chuc_vu cv on cv.id=nv.id_cv where tk.id_nv=?");
			ps.setString(1, id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				String ten = rs.getString("ten");
				int sdt = rs.getInt("sdt");
				int trangThai = rs.getInt("trang_thai");
				String ghiChu = rs.getString("ghi_chu");
				int gioiTinh = rs.getInt("gioi_tinh");
				String email = rs.getString("dia_chi");
				String diaChi = rs.getString("dia_chi");
				String tenChucVu = rs.getString("cv.ten_cv");
				double luong = rs.getDouble("luong");
				NhanVien nv = new NhanVien(id, ten, sdt, trangThai, ghiChu, gioiTinh, email, diaChi, tenChucVu, luong);
				String userName = rs.getString("user_name");
				String pass = rs.getString("pass");
				int quyen = rs.getInt("admin");
				TaiKhoan tk = new TaiKhoan(userName, pass, nv, quyen);
				return tk;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public List<TaiKhoan> getAll() {
		List<TaiKhoan> result = new ArrayList<TaiKhoan>();
		try {
			connection = ConnectionDao.getConnection();
			PreparedStatement ps = connection.prepareStatement(
					"select * from nhan_vien nv inner join tai_khoan tk on tk.id_nv=nv.id inner join chuc_vu cv on cv.id=nv.id_cv");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				String id = rs.getString("id");
				String ten = rs.getString("ten");
				int sdt = rs.getInt("sdt");
				int trangThai = rs.getInt("trang_thai");
				String ghiChu = rs.getString("ghi_chu");
				int gioiTinh = rs.getInt("gioi_tinh");
				String email = rs.getString("dia_chi");
				String diaChi = rs.getString("dia_chi");
				String tenChucVu = rs.getString("cv.ten_cv");
				double luong = rs.getDouble("luong");
				NhanVien nv = new NhanVien(id, ten, sdt, trangThai, ghiChu, gioiTinh, email, diaChi, tenChucVu, luong);
				String userName = rs.getString("user_name");
				String pass = rs.getString("pass");
				int quyen = rs.getInt("admin");
				TaiKhoan tk = new TaiKhoan(userName, pass, nv, quyen);
				result.add(tk);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public boolean addAccount(String idNhanVien, String userName, String pass, int admin) {
		try {
			TaiKhoan tk = getAccount(idNhanVien);
			if (tk != null) {
				return editAccount(idNhanVien, idNhanVien, userName, pass, admin);
			}
			connection = ConnectionDao.getConnection();
			PreparedStatement ps = connection
					.prepareStatement("insert into tai_khoan(id_nv,user_name,pass,admin) values(?,?,?,?)");
			ps.setString(1, idNhanVien);
			ps.setString(2, userName);
			ps.setString(3, pass);
			ps.setInt(4, admin);
			if (ps.executeUpdate() > 0)
				return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public boolean deleteAccount(String userName) {
		try {
			connection = ConnectionDao.getConnection();
			PreparedStatement ps = connection.prepareStatement("delete from tai_khoan where tai_khoan.user_name=?");
			ps.setString(1, userName);
			if (ps.executeUpdate() > 0)
				return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public boolean editAccount(String idNew, String id, String userName, String pass, int admin) {
		try {
			connection = ConnectionDao.getConnection();
			PreparedStatement ps = connection.prepareStatement(
					"update tai_khoan tk set tk.id_nv=?,tk.user_name=?,tk.pass=?,tk.admin=? where tk.id_nv=?");
			ps.setString(1, idNew);
			ps.setString(2, userName);
			ps.setString(3, pass);
			ps.setInt(4, admin);
			ps.setString(5, id);
			if (ps.executeUpdate() > 0)
				return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

}
