package Dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;

import model.ASanPham;
import model.ChiTietDonHang;
import model.DonHang;
import model.DonHangModelInterface;
import model.KhachHang;
import model.MaterialFactory;
import model.Observer;
import model.ThuNo;

public class DonHangDao {

	ArrayList<Observer> observers = new ArrayList<Observer>();
	Connection connection;
	private static DonHangDao instance;

	private DonHangDao() {

	}

	public static DonHangDao getInstance() {
		if (instance == null)
			instance = new DonHangDao();
		return instance;
	}

	public boolean delete(String id) {
		try {
			DonHang dh = getDonHang(id);
			connection = ConnectionDao.getConnection();
			PreparedStatement ps = connection.prepareStatement("update don_hang set don_hang.hd=0 where don_hang.id=?");
			ps.setString(1, id);
			if (ps.executeUpdate() > 0) {
				KhachHang kh = dh.getKhachHang();
				return KhachHangDao.getInstance().setProperty(kh.getMaSo(), kh.getHoVaTen(), kh.getEmail(),
						kh.getDiachi(), kh.getSoTienNo() - dh.thanhTien(), kh.getGhiChu());
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public List<DonHang> getAll() {
		// TODO Auto-generated method stub
		Map<String, DonHang> exits = new HashMap<String, DonHang>();
		ArrayList<DonHang> result = new ArrayList<DonHang>();
		try {
			connection = ConnectionDao.getConnection();
			PreparedStatement ps = connection
					.prepareStatement("select * from don_hang dh inner join chi_tiet_don_hang ct on ct.id_dh=dh.id"
							+ " inner join khach_hang k on k.id_kh=dh.id_kh inner join vat_lieu m on m.id=ct.id_vl inner join hinh_anh i on i.idvl=m.id where dh.hd=1");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				String maDH = rs.getString("dh.id");
				if (!exits.containsKey(maDH)) {
					KhachHang kh = new KhachHang(rs.getString("id_kh"), rs.getString("ten"), rs.getString("dia_chi"),
							rs.getString("email"), rs.getDouble("so_tien_no"), rs.getString("ghi_chu"));
					ArrayList<ChiTietDonHang> danhSachThanhPhan = new ArrayList<ChiTietDonHang>();
					String type = rs.getString("loai");
					String id = rs.getString("m.id");
					String tenSanPham = rs.getString("ten_vl");
					String mota = rs.getString("mo_ta");
					String donViTinh = rs.getString("don_vi");
					double donGia = rs.getDouble("don_gia");
					double soLuong = rs.getDouble("so_luong_kho");
					double giaNhap = rs.getDouble("gia_nhap");
					ArrayList<String> list = new ArrayList<String>();
					list.add(rs.getString("url"));
					ASanPham vl = MaterialFactory.getInstance().createMaterial(type, id, tenSanPham, mota, donViTinh,
							donGia, soLuong, list, giaNhap);
					danhSachThanhPhan.add(new ChiTietDonHang(vl, rs.getDouble("ct.so_luong")));
					DonHang dh = new DonHang(rs.getString("dh.id"), rs.getDate("ngay_lap"), kh,
							rs.getInt("dh.trang_thai"), danhSachThanhPhan);
					exits.put(maDH, dh);
				} else {
					String idvl = rs.getString("m.id");
					ChiTietDonHang ct = exits.get(maDH).getChiTietHoaDon(idvl);
					if (ct != null) {
						ct.getSanPham().addImage(rs.getString("url"));
					} else {
						String type = rs.getString("loai");
						String tenSanPham = rs.getString("ten_vl");
						String mota = rs.getString("mo_ta");
						String donViTinh = rs.getString("don_vi");
						double donGia = rs.getDouble("don_gia");
						double soLuong = rs.getDouble("so_luong_kho");
						double giaNhap = rs.getDouble("gia_nhap");
						List<String> list = new ArrayList<String>();
						list.add(rs.getString("url"));
						ASanPham vl = MaterialFactory.getInstance().createMaterial(type, idvl, tenSanPham, mota,
								donViTinh, donGia, soLuong, list, giaNhap);
						ChiTietDonHang ctdh = new ChiTietDonHang(vl, rs.getDouble("so_luong"));
						exits.get(maDH).getDanhSachThanhPhan().add(ctdh);
					}
				}

			}
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		for (Entry<String, DonHang> e : exits.entrySet()) {
			result.add(e.getValue());
		}
		return result;
	}

	public List<DonHang> getAllOrder() {
		// TODO Auto-generated method stub
		Map<String, DonHang> exits = new LinkedHashMap<String, DonHang>();
		ArrayList<DonHang> result = new ArrayList<DonHang>();
		try {
			connection = ConnectionDao.getConnection();
			PreparedStatement ps = connection
					.prepareStatement("select * from don_hang dh inner join chi_tiet_don_hang ct on ct.id_dh=dh.id "
							+ " inner join khach_hang k on k.id_kh=dh.id_kh inner join vat_lieu m on m.id=ct.id_vl inner join hinh_anh i on i.idvl=m.id where dh.hd=1 ORDER BY dh.NGAY_LAP DESC");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				String maDH = rs.getString("dh.id");
				if (!exits.containsKey(maDH)) {
					KhachHang kh = new KhachHang(rs.getString("id_kh"), rs.getString("ten"), rs.getString("dia_chi"),
							rs.getString("email"), rs.getDouble("so_tien_no"), rs.getString("ghi_chu"));
					ArrayList<ChiTietDonHang> danhSachThanhPhan = new ArrayList<ChiTietDonHang>();
					String type = rs.getString("loai");
					String id = rs.getString("m.id");
					String tenSanPham = rs.getString("ten_vl");
					String mota = rs.getString("mo_ta");
					String donViTinh = rs.getString("don_vi");
					double donGia = rs.getDouble("don_gia");
					double soLuong = rs.getDouble("so_luong_kho");
					double giaNhap = rs.getDouble("gia_nhap");
					ArrayList<String> list = new ArrayList<String>();
					list.add(rs.getString("url"));
					ASanPham vl = MaterialFactory.getInstance().createMaterial(type, id, tenSanPham, mota, donViTinh,
							donGia, soLuong, list, giaNhap);
					danhSachThanhPhan.add(new ChiTietDonHang(vl, rs.getDouble("ct.so_luong")));
					DonHang dh = new DonHang(rs.getString("dh.id"), rs.getDate("ngay_lap"), kh,
							rs.getInt("dh.trang_thai"), danhSachThanhPhan);
					exits.put(maDH, dh);
				} else {
					String idvl = rs.getString("m.id");
					ChiTietDonHang ct = exits.get(maDH).getChiTietHoaDon(idvl);
					if (ct != null) {
						ct.getSanPham().addImage(rs.getString("url"));
					} else {
						String type = rs.getString("loai");
						String tenSanPham = rs.getString("ten_vl");
						String mota = rs.getString("mo_ta");
						String donViTinh = rs.getString("don_vi");
						double donGia = rs.getDouble("don_gia");
						double soLuong = rs.getDouble("so_luong_kho");
						double giaNhap = rs.getDouble("gia_nhap");
						List<String> list = new ArrayList<String>();
						list.add(rs.getString("url"));
						ASanPham vl = MaterialFactory.getInstance().createMaterial(type, idvl, tenSanPham, mota,
								donViTinh, donGia, soLuong, list, giaNhap);
						ChiTietDonHang ctdh = new ChiTietDonHang(vl, rs.getDouble("so_luong"));
						exits.get(maDH).getDanhSachThanhPhan().add(ctdh);
					}
				}

			}
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		for (Entry<String, DonHang> e : exits.entrySet()) {
			result.add(e.getValue());
		}
		return result;
	}

	public boolean deleteChiTiet(String idDonHang, String idVl) {
		try {
			DonHang dh = getDonHang(idDonHang);
			connection = ConnectionDao.getConnection();
			PreparedStatement ps = connection.prepareStatement(
					"delete from chi_tiet_don_hang where chi_tiet_don_hang.id_dh=? and chi_tiet_don_hang.id_vl=?");
			ps.setString(1, idDonHang);
			ps.setString(2, idVl);
			int affect = ps.executeUpdate();
			if (affect > 0) {
				ChiTietDonHang ct = dh.getChiTietHoaDon(idVl);
				KhachHang kh = dh.getKhachHang();
				return KhachHangDao.getInstance().setProperty(kh.getMaSo(), kh.getHoVaTen(), kh.getEmail(),
						kh.getDiachi(), kh.getSoTienNo() - ct.thanhTien(), kh.getGhiChu());
			}
		} catch (SQLException e) {

			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public boolean addChiTiet(String idDonHang, String idVatLieu, double soLuong) {
		try {
			DonHang dhDonHang = getDonHang(idDonHang);
			ChiTietDonHang ctOld = dhDonHang.getChiTietHoaDon(idVatLieu);
			if (ctOld != null) {
				connection = ConnectionDao.getConnection();
				PreparedStatement ps = connection.prepareStatement(
						"update chi_tiet_don_hang ct set ct.so_luong=? where ct.id_dh=? and ct.id_vl=?");
				ps.setString(2, idDonHang);
				ps.setString(3, idVatLieu);
				ps.setDouble(1, soLuong + ctOld.getSoluong());
				int affect = ps.executeUpdate();
				if (affect > 0) {

					ASanPham vl = VatLieuDao.getInstance().getVatLieu(idVatLieu);
					KhachHang kh = dhDonHang.getKhachHang();
					return KhachHangDao.getInstance().setProperty(kh.getMaSo(), kh.getHoVaTen(), kh.getEmail(),
							kh.getDiachi(), kh.getSoTienNo() + vl.getDonGia() * soLuong, kh.getGhiChu());
				}
			} else {
				connection = ConnectionDao.getConnection();
				PreparedStatement ps = connection
						.prepareStatement("insert into chi_tiet_don_hang(id_dh,id_vl,so_luong) values (?,?,?)");
				ps.setString(1, idDonHang);
				ps.setString(2, idVatLieu);
				ps.setDouble(3, soLuong);
				int affect = ps.executeUpdate();
				if (affect > 0) {
					dhDonHang = getDonHang(idDonHang);
					ASanPham vl = VatLieuDao.getInstance().getVatLieu(idVatLieu);
					VatLieuDao.getInstance().setProperty(vl.getId(), vl.getTenSanPham(), vl.getDonViTinh(),
							vl.getDonGia(), vl.getSoLuong() - soLuong, vl.getType(), vl.getGiaNhap(), vl.getId());
					KhachHang kh = dhDonHang.getKhachHang();
					return KhachHangDao.getInstance().setProperty(kh.getMaSo(), kh.getHoVaTen(), kh.getEmail(),
							kh.getDiachi(), kh.getSoTienNo() + vl.getDonGia() * soLuong, kh.getGhiChu());

				}

			}
		} catch (SQLException e) {

			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public boolean editChiTiet(String id, String idVatLieuCu, String idVatLieu, double soLuong) {
		try {
			DonHang dh = getDonHang(id);
			connection = ConnectionDao.getConnection();
			if (idVatLieuCu.equals(idVatLieu)) {
				PreparedStatement ps = connection.prepareStatement(
						"update chi_tiet_don_hang ct set ct.so_luong=? where ct.id_dh=? and ct.id_vl=?");
				ps.setString(3, idVatLieu);
				ps.setString(2, id);
				ps.setDouble(1, soLuong);
				if (ps.executeUpdate() > 0) {
					ChiTietDonHang ct1 = dh.getChiTietHoaDon(idVatLieuCu);
					ASanPham vl = VatLieuDao.getInstance().getVatLieu(idVatLieu);
					KhachHang kh = dh.getKhachHang();
					return KhachHangDao.getInstance().setProperty(kh.getMaSo(), kh.getHoVaTen(), kh.getEmail(),
							kh.getDiachi(), kh.getSoTienNo() + vl.getDonGia() * soLuong - ct1.thanhTien(),
							kh.getGhiChu());
				} else
					return false;
			}
			ChiTietDonHang ctnew = dh.getChiTietHoaDon(idVatLieu);
			PreparedStatement ps = connection.prepareStatement(
					"update chi_tiet_don_hang ct set ct.id_vl=?,ct.so_luong=? where ct.id_dh=? and ct.id_vl=?");
			ps.setString(3, id);
			ps.setString(1, idVatLieu);
			if (ctnew != null) {
				deleteChiTiet(id, idVatLieuCu);
				ps.setString(4, idVatLieu);
				ps.setDouble(2, ctnew.getSoluong() + soLuong);
			} else {
				ps.setDouble(2, soLuong);
				ps.setString(4, idVatLieuCu);
			}

			if (ps.executeUpdate() > 0) {
				ChiTietDonHang ct1 = dh.getChiTietHoaDon(idVatLieuCu);
				ASanPham vl = VatLieuDao.getInstance().getVatLieu(idVatLieu);
				KhachHang kh = dh.getKhachHang();
				return KhachHangDao.getInstance().setProperty(kh.getMaSo(), kh.getHoVaTen(), kh.getEmail(),
						kh.getDiachi(), kh.getSoTienNo() + vl.getDonGia() * soLuong - ct1.thanhTien(), kh.getGhiChu());
			}
		} catch (SQLException e) {

			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public DonHang getDonHang(String idDonHang) {
		try {
			connection = ConnectionDao.getConnection();
			PreparedStatement ps = connection.prepareStatement(
					"select * from don_hang dh inner join chi_tiet_don_hang ct on dh.id=ct.id_dh inner join "
							+ " khach_hang k on k.id_kh=dh.id_kh inner join vat_lieu m on m.id=ct.id_vl inner join hinh_anh i on i.idvl=m.id where dh.hd=1 and dh.id=?");
			ps.setString(1, idDonHang);
			ResultSet rs = ps.executeQuery();
			DonHang dh = new DonHang();
			ArrayList<ChiTietDonHang> danhSachThanhPhan = new ArrayList<ChiTietDonHang>();
			while (rs.next()) {
				KhachHang kh = null;
				if (danhSachThanhPhan.size() == 0) {
					kh = new KhachHang(rs.getString("id_kh"), rs.getString("ten"), rs.getString("dia_chi"),
							rs.getString("email"), rs.getDouble("so_tien_no"), rs.getString("ghi_chu"));
					String type = rs.getString("loai");
					String id = rs.getString("m.id");
					String tenSanPham = rs.getString("ten_vl");
					String mota = rs.getString("mo_ta");
					String donViTinh = rs.getString("don_vi");
					double donGia = rs.getDouble("don_gia");
					double soLuong = rs.getDouble("so_luong_kho");
					double giaNhap = rs.getDouble("gia_nhap");
					ArrayList<String> list = new ArrayList<String>();
					list.add(rs.getString("url"));
					ASanPham vl = MaterialFactory.getInstance().createMaterial(type, id, tenSanPham, mota, donViTinh,
							donGia, soLuong, list, giaNhap);
					danhSachThanhPhan.add(new ChiTietDonHang(vl, rs.getDouble("so_luong")));
					dh = new DonHang(rs.getString("dh.id"), rs.getDate("ngay_lap"), kh, rs.getInt("trang_thai"),
							danhSachThanhPhan);

				} else {
					String type = rs.getString("loai");
					String id = rs.getString("m.id");
					String tenSanPham = rs.getString("ten_vl");
					String mota = rs.getString("mo_ta");
					String donViTinh = rs.getString("don_vi");
					double donGia = rs.getDouble("don_gia");
					double soLuong = rs.getDouble("so_luong_kho");
					double giaNhap = rs.getDouble("gia_nhap");
					ArrayList<String> list = new ArrayList<String>();
					list.add(rs.getString("url"));
					ASanPham vl = MaterialFactory.getInstance().createMaterial(type, id, tenSanPham, mota, donViTinh,
							donGia, soLuong, list, giaNhap);
					danhSachThanhPhan.add(new ChiTietDonHang(vl, rs.getDouble("so_luong")));
//					System.out.println(kh);
				}

			}

			return dh;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	public List<ChiTietDonHang> getChiTiet(String idDonHang) {
		ArrayList<ChiTietDonHang> result = new ArrayList<ChiTietDonHang>();
		try {
			connection = ConnectionDao.getConnection();
			PreparedStatement ps = connection.prepareStatement(
					"select * from chi_tiet_don_hang ct inner join vat_lieu m on m.id=ct.id_vl where ct.id_dh=? and ct.so_luong<>0");
			ps.setString(1, idDonHang);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				String idVL = rs.getString("id");
				String type = rs.getString("loai");
				String tenSanPham = rs.getString("ten_vl");
				String mota = rs.getString("mo_ta");
				String donViTinh = rs.getString("don_vi");
				double donGia = rs.getDouble("don_gia");
				double soLuong = rs.getDouble("so_luong_kho");
				double giaNhap = rs.getDouble("gia_nhap");
				ASanPham vatLieu = new ASanPham(idVL, tenSanPham, mota, donViTinh, donGia, soLuong, type,
						new ArrayList<String>(), giaNhap);
				result.add(new ChiTietDonHang(vatLieu, rs.getDouble("so_luong")));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public List<DonHang> getAll(String idKhachHang) {
		// TODO Auto-generated method stub
		try {
			connection = ConnectionDao.getConnection();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public boolean setDonHang(String idKh, Date ngayLap, String id, String oldKh) {
		try {
			connection = ConnectionDao.getConnection();
			PreparedStatement ps = connection
					.prepareStatement("update don_hang dh set dh.id_kh=?,dh.ngay_lap=? where dh.id=?");
			ps.setString(1, idKh);
			ps.setDate(2, ngayLap);
			ps.setString(3, id);
			int affect = ps.executeUpdate();
			if (affect > 0) {
				DonHang dh = DonHangDao.getInstance().getDonHang(id);

				KhachHang kh1 = KhachHangDao.getInstance().getKhachHang(idKh);
				KhachHang kh2 = KhachHangDao.getInstance().getKhachHang(oldKh);
				boolean a = KhachHangDao.getInstance().setProperty(idKh, kh1.getHoVaTen(), kh1.getEmail(),
						kh1.getDiachi(), kh1.getSoTienNo() + dh.thanhTien(), kh1.getDiachi());
				boolean b = KhachHangDao.getInstance().setProperty(oldKh, kh2.getHoVaTen(), kh2.getEmail(),
						kh2.getDiachi(), kh2.getSoTienNo() - dh.thanhTien(), kh2.getDiachi());
				return a && b;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public boolean addDonHang(String idKh, double payMoney, Date ngayLap, Map<String, Double> data) {
		try {
			String idDh = UUID.randomUUID().toString();
			connection = ConnectionDao.getConnection();
			connection.setAutoCommit(false);
			PreparedStatement ps = connection
					.prepareStatement("insert into don_hang(id,id_kh,trang_thai,ngay_lap,hd) values(?,?,?,?,?)");
			ps.setString(1, idDh);
			ps.setString(2, idKh);
			ps.setInt(3, 0);
			ps.setDate(4, ngayLap);
			ps.setInt(5, 1);
			int affect = ps.executeUpdate();
			if (affect > 0) {
				boolean isAddAll = true;
				for (Map.Entry<String, Double> entry : data.entrySet()) {
					isAddAll = isAddAll && addChiTiet(idDh, entry.getKey(), entry.getValue());
					if (!isAddAll) {
						connection.rollback();
						return false;
					}
				}
				KhachHang kh = KhachHangDao.getInstance().getKhachHang(idKh);
				if (KhachHangDao.getInstance().setProperty(idKh, kh.getHoVaTen(), kh.getEmail(), kh.getDiachi(),
						kh.getSoTienNo() - payMoney, kh.getGhiChu())) {
					connection.commit();
					return true;
				} else {
					connection.rollback();
				}
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			try {
				connection.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		return false;
	}

	public boolean addDonHang(String idDh, String idKh, Date ngayLap) {
		try {
			connection = ConnectionDao.getConnection();
			PreparedStatement ps = connection
					.prepareStatement("insert into don_hang(id,id_kh,trang_thai,ngay_lap,hd) values(?,?,?,?,?)");
			ps.setString(1, idDh);
			ps.setString(2, idKh);
			ps.setInt(3, 1);
			ps.setDate(4, ngayLap);
			ps.setInt(5, 1);
			int affect = ps.executeUpdate();
			if (affect > 0) {
				boolean addChiTiet = addChiTiet(idDh, "vl01", 0);
				KhachHang kh = KhachHangDao.getInstance().getKhachHang(idKh);
				if (kh != null) {

					return addChiTiet && KhachHangDao.getInstance().setProperty(idKh, kh.getHoVaTen(), kh.getEmail(),
							kh.getDiachi(), kh.getSoTienNo(), kh.getGhiChu());
				}
				return false;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public int totalOrderByMonth(int month, int year) {
		try {
			connection = ConnectionDao.getConnection();
			PreparedStatement ps = connection.prepareStatement(
					"SELECT COUNT(*) as total from  don_hang as d WHERE d.hd=1 AND YEAR(d.NGAY_LAP) = ? AND MONTH(d.NGAY_LAP) = ?");
			ps.setInt(1, year);
			ps.setInt(2, month);
			ResultSet resultSet = ps.executeQuery();
			resultSet.next();
			return resultSet.getInt(1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
	}

	public double totalSales(int month, int year) {
		try {
			connection = ConnectionDao.getConnection();
			PreparedStatement ps = connection.prepareStatement(
					"SELECT SUM(C.SO_LUONG* V.DON_GIA) FROM don_hang as d INNER JOIN chi_tiet_don_hang as c on d.ID = c.ID_DH INNER JOIN vat_lieu AS V ON C.ID_VL=V.ID "
							+ "WHERE d.HD = 1 AND D.TRANG_THAI = 2 AND YEAR(d.NGAY_LAP) = ? AND MONTH(d.NGAY_LAP) = ?");
			ps.setInt(1, year);
			ps.setInt(2, month);
			ResultSet resultSet = ps.executeQuery();
			resultSet.next();
			return resultSet.getDouble(1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;

	}

}
