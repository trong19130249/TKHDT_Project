package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import model.ASanPham;
import model.MaterialFactory;

public class VatLieuDao {
	Connection connection;
	private static VatLieuDao instance;

	private VatLieuDao() {

	}

	// tạo ra thể hiện duy nhất cho kết nối
	public static VatLieuDao getInstance() {
		if (instance == null)
			instance = new VatLieuDao();
		return instance;
	}

	// có nhiệm vụ lấy ra vật liệu có mã id là tham số truyền vào
	public ASanPham getVatLieu(String id) {
		// TODO Auto-generated method stub
		ArrayList<String> exits = new ArrayList<String>();
		ASanPham result = null;
		try {

			connection = ConnectionDao.getConnection();
			PreparedStatement ps = connection.prepareStatement(
					"select * from vat_lieu m inner join hinh_anh h on m.id=h.idvl where m.id=? and m.hd=1");
			ps.setString(1, id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				if (!exits.contains(id)) {
					String type = rs.getString("loai");
					String tenSanPham = rs.getString("ten_vl");
					String mota = rs.getString("mo_ta");
					String donViTinh = rs.getString("don_vi");
					double donGia = rs.getDouble("don_gia");
					double soLuong = rs.getDouble("so_luong_kho");
					ArrayList<String> list = new ArrayList<String>();
					list.add(rs.getString("url"));
					double giaNhap = rs.getDouble("gia_nhap");
					result = MaterialFactory.getInstance().createMaterial(type, id, tenSanPham, mota, donViTinh, donGia,
							soLuong, list, giaNhap);
					exits.add(id);
				}
				result.getListUrl().add(rs.getString("url"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	// lấy tất cả các vật liệu trong kho (chưa bị vô hiệu hóa)

	public List<ASanPham> getAll() {
		// TODO Auto-generated method stub
		Map<String, ASanPham> exits = new HashMap<String, ASanPham>();
		ArrayList<ASanPham> result = new ArrayList<ASanPham>();
		try {
			connection = ConnectionDao.getConnection();
			PreparedStatement ps = connection
					.prepareStatement("select * from vat_lieu m inner join hinh_anh h on m.id=h.idvl where m.hd=1");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				String maVL = rs.getString("id");
				if (!exits.containsKey(maVL)) {
					String type = rs.getString("loai");
					String tenSanPham = rs.getString("ten_vl");
					String mota = rs.getString("mo_ta");
					String donViTinh = rs.getString("don_vi");
					double donGia = rs.getDouble("don_gia");
					double soLuong = rs.getDouble("so_luong_kho");
					ArrayList<String> listUrl = new ArrayList<String>();
					listUrl.add(rs.getString("url"));
					double giaNhap = rs.getDouble("gia_nhap");
					ASanPham vl = MaterialFactory.getInstance().createMaterial(type, maVL, tenSanPham, mota, donViTinh,
							donGia, soLuong, listUrl, giaNhap);
					exits.put(maVL, vl);

				} else {
					exits.get(maVL).getListUrl().add(rs.getString("url"));
				}
			}
			for (Entry<String, ASanPham> e : exits.entrySet()) {
				result.add(e.getValue());
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

// Chỉnh sửa thông tin của vật liệu chỉ định dựa vào id truyền vào
	public boolean setProperty(String id, String ten, String donViTinh, double donGia, double soLuong, String loai,
			double giaNhap, String idnew) {
		try {
			connection = ConnectionDao.getConnection();
			PreparedStatement ps = connection
					.prepareStatement("update vat_lieu m set m.don_gia=?,m.id=?,m.ten_vl=?,m.don_vi=?,m.don_gia=?,"
							+ "m.so_luong_kho=?,m.gia_nhap=?,m.loai=? where m.id=?");
			ps.setString(2, idnew);
			ps.setDouble(1, donGia);
			ps.setString(3, ten);
			;
			ps.setString(4, donViTinh);
			ps.setDouble(5, donGia);
			ps.setDouble(6, soLuong);
			ps.setDouble(7, giaNhap);
			ps.setString(8, loai);
			ps.setString(9, id);
			int affect = ps.executeUpdate();
			if (affect > 0)
				return true;
			else
				return false;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	// Thêm mới vật liệu
	public boolean addVatLieu(String id, String ten, String donViTinh, double donGia, double soLuong, String loai,
			double giaNhap) {
		try {
			connection = ConnectionDao.getConnection();
			PreparedStatement ps = connection.prepareStatement(
					"insert into vat_lieu(id,ten_vl,mo_ta,don_vi,don_gia,so_luong_kho,loai,gia_nhap,hd)"
							+ " values(?,?,?,?,?,?,?,?,?)");
			ps.setString(1, id);
			ps.setString(2, ten);
			ps.setString(3, "");
			ps.setString(4, donViTinh);
			ps.setDouble(5, donGia);
			ps.setDouble(6, soLuong);
			ps.setString(7, loai);
			ps.setDouble(8, giaNhap);
			ps.setInt(9, 1);
			int affect = ps.executeUpdate();
			if (affect > 0) {
				PreparedStatement psi = connection.prepareStatement("insert into hinh_anh(idvl,url) values(?,?)");
				psi.setString(1, id);
				psi.setString(2, id + "1.jpg");
				psi.executeUpdate();
				return true;
			} else
				return false;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	// Xóa - Vô hiệu hóa vật liệu, việc này sẽ không xóa hoàn toàn vật
	// liệu ra khỏi database mà chỉ vô hiệu hóa nó để có thể dễ dàng
	// tham chiếu các dữ liệu liên quan đến lịch sử, ...
	public boolean deleteVatLieu(String id) {
		try {
			connection = ConnectionDao.getConnection();

			PreparedStatement ps = connection.prepareStatement("update vat_lieu set vat_lieu.hd=0 where vat_lieu.id=?");
			ps.setString(1, id);
			int affect = ps.executeUpdate();
			if (affect > 0)
				return true;
		} catch (SQLException e) {

			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
}
