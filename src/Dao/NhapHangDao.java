package Dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import model.ASanPham;
import model.ChiTietDonHang;
import model.ChiTietLichSuMuaHang;
import model.DonHang;
import model.KhachHang;
import model.LichSuMuaHang;
import model.MaterialFactory;
import model.NhaBanHang;

public class NhapHangDao {
	private static NhapHangDao instance;
	Connection connection;

	private NhapHangDao() {

	}

	public static NhapHangDao getInstance() {
		if (instance == null)
			instance = new NhapHangDao();
		return instance;
	}

	public List<LichSuMuaHang> getAll() {
		Map<String, LichSuMuaHang> exits = new HashMap<String, LichSuMuaHang>();
		ArrayList<LichSuMuaHang> result = new ArrayList<LichSuMuaHang>();
		try {
			connection = ConnectionDao.getConnection();
			PreparedStatement ps = connection
					.prepareStatement("select * from lich_su_nhap_hang ls inner join ct_lsnh ct on ct.id_ls=ls.id "
							+ "inner join vat_lieu m on m.id=ct.id_vl inner join nha_ban_hang nbh on nbh.id=ls.id_nbh where ls.hd=1"
							+ "");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				String maDH = rs.getString("ls.id");
				if (!exits.containsKey(maDH)) {
					NhaBanHang nbh = new NhaBanHang(rs.getString("nbh.id"), rs.getString("nbh.ten"),
							rs.getInt("nbh.so_dien_thoai"), rs.getString("nbh.dia_chi"), rs.getDouble("no_ccc"),
							rs.getString("ghi_chu"));
					ArrayList<ChiTietLichSuMuaHang> listCT = new ArrayList<ChiTietLichSuMuaHang>();
					String maVL = rs.getString("m.id");
					String type = rs.getString("loai");
					String tenSanPham = rs.getString("ten_vl");
					String mota = rs.getString("mo_ta");
					String donViTinh = rs.getString("don_vi");
					double donGia = rs.getDouble("don_gia");
					double soLuong = rs.getDouble("so_luong_kho");
					double giaNhap = rs.getDouble("gia_nhap");
					ASanPham vatLieu = new ASanPham(maVL, tenSanPham, mota, donViTinh, donGia, soLuong, type,
							new ArrayList<String>(), giaNhap);
					ChiTietLichSuMuaHang ct = new ChiTietLichSuMuaHang(vatLieu, rs.getDouble("ct.so_luong"));
					listCT.add(ct);
					LichSuMuaHang ls = new LichSuMuaHang(maDH, listCT, rs.getDate("ls.ngay_nhap"), nbh);
					exits.put(maDH, ls);
				} else {
					String maVL = rs.getString("m.id");
					String type = rs.getString("loai");
					String tenSanPham = rs.getString("ten_vl");
					String mota = rs.getString("mo_ta");
					String donViTinh = rs.getString("don_vi");
					double donGia = rs.getDouble("don_gia");
					double soLuong = rs.getDouble("so_luong_kho");
					double giaNhap = rs.getDouble("gia_nhap");
					ASanPham vatLieu = new ASanPham(maVL, tenSanPham, mota, donViTinh, donGia, soLuong, type,
							new ArrayList<String>(), giaNhap);
					ChiTietLichSuMuaHang ct = new ChiTietLichSuMuaHang(vatLieu, rs.getDouble("ct.so_luong"));
					exits.get(maDH).addChiTiet(ct);
				}

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (Entry<String, LichSuMuaHang> e : exits.entrySet()) {
			result.add(e.getValue());
		}
		return result;
	}

	public List<LichSuMuaHang> getAll(String nhaBanHang) {
		Map<String, LichSuMuaHang> exits = new HashMap<String, LichSuMuaHang>();
		ArrayList<LichSuMuaHang> result = new ArrayList<LichSuMuaHang>();
		try {
			connection = ConnectionDao.getConnection();
			PreparedStatement ps = connection
					.prepareStatement("select * from lich_su_nhap_hang ls inner join ct_lsnh ct on ct.id_ls=ls.id "
							+ "inner join vat_lieu m on m.id=ct.id_vl inner join nha_ban_hang nbh on nbh.id=ls.id_nbh where ls.hd=1"
							+ "where ls.id_nbh=?");
			ps.setString(1, nhaBanHang);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				String maDH = rs.getString("ls.id");
				if (!exits.containsKey(maDH)) {
					NhaBanHang nbh = new NhaBanHang(rs.getString("nbh.id"), rs.getString("nbh.ten"),
							rs.getInt("nbh.so_dien_thoai"), rs.getString("nbh.dia_chi"), rs.getDouble("no_ccc"),
							rs.getString("ghi_chu"));
					ArrayList<ChiTietLichSuMuaHang> listCT = new ArrayList<ChiTietLichSuMuaHang>();
					String maVL = rs.getString("m.id");
					String type = rs.getString("loai");
					String tenSanPham = rs.getString("ten_vl");
					String mota = rs.getString("mo_ta");
					String donViTinh = rs.getString("don_vi");
					double donGia = rs.getDouble("don_gia");
					double soLuong = rs.getDouble("so_luong_kho");
					double giaNhap = rs.getDouble("gia_nhap");
					ASanPham vatLieu = new ASanPham(maVL, tenSanPham, mota, donViTinh, donGia, soLuong, type,
							new ArrayList<String>(), giaNhap);
					ChiTietLichSuMuaHang ct = new ChiTietLichSuMuaHang(vatLieu, rs.getDouble("ct.so_luong"));
					listCT.add(ct);
					LichSuMuaHang ls = new LichSuMuaHang(maDH, listCT, rs.getDate("ls.ngay_nhap"), nbh);
					exits.put(maDH, ls);
				} else {
					String maVL = rs.getString("m.id");
					String type = rs.getString("loai");
					String tenSanPham = rs.getString("ten_vl");
					String mota = rs.getString("mo_ta");
					String donViTinh = rs.getString("don_vi");
					double donGia = rs.getDouble("don_gia");
					double soLuong = rs.getDouble("so_luong_kho");
					double giaNhap = rs.getDouble("gia_nhap");
					ASanPham vatLieu = new ASanPham(maVL, tenSanPham, mota, donViTinh, donGia, soLuong, type,
							new ArrayList<String>(), giaNhap);
					ChiTietLichSuMuaHang ct = new ChiTietLichSuMuaHang(vatLieu, rs.getDouble("ct.so_luong"));
					exits.get(maDH).addChiTiet(ct);
				}

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (Entry<String, LichSuMuaHang> e : exits.entrySet()) {
			result.add(e.getValue());
		}
		return result;
	}

	public List<ChiTietLichSuMuaHang> getChiTiet(String idLichSu) {
		ArrayList<ChiTietLichSuMuaHang> result = new ArrayList<ChiTietLichSuMuaHang>();
		try {
			connection = ConnectionDao.getConnection();
			PreparedStatement ps = connection.prepareStatement(
					"select * from ct_lsnh ct inner join vat_lieu m on m.id=ct.id_vl where ct.id_ls=? and ct.so_luong <>0");
			ps.setString(1, idLichSu);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				String maVL = rs.getString("m.id");
				String type = rs.getString("loai");
				String tenSanPham = rs.getString("ten_vl");
				String mota = rs.getString("mo_ta");
				String donViTinh = rs.getString("don_vi");
				double donGia = rs.getDouble("don_gia");
				double soLuong = rs.getDouble("so_luong_kho");
				double giaNhap = rs.getDouble("gia_nhap");
				ASanPham vatLieu = new ASanPham(maVL, tenSanPham, mota, donViTinh, donGia, soLuong, type,
						new ArrayList<String>(), giaNhap);
				ChiTietLichSuMuaHang ct = new ChiTietLichSuMuaHang(vatLieu, rs.getDouble("ct.so_luong"));
				result.add(ct);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

//
	public boolean addChiTiet(String idLichSu, String idVatLieu, double soLuong) {
		try {
			LichSuMuaHang ls = getLichSu(idLichSu);
			ChiTietLichSuMuaHang ctOld = ls.getChiTietLichSu(idVatLieu);
			if (ctOld != null) {
				connection = ConnectionDao.getConnection();
				PreparedStatement ps = connection
						.prepareStatement("update ct_lsnh ct set ct.so_luong=? where ct.id_ls=? and ct.id_vl=?");
				ps.setString(2, idLichSu);
				ps.setString(3, idVatLieu);
				ps.setDouble(1, soLuong + ctOld.getSoLuong());
				int affect = ps.executeUpdate();
				if (affect > 0) {
					ASanPham vl = VatLieuDao.getInstance().getVatLieu(idVatLieu);
					NhaBanHang nbh = ls.getCoSoNhap();
					return NhaCungCapDao.getInstance().editNCC(nbh.getId(), nbh.getTen(), nbh.getSdt(), nbh.getDiaChi(),
							nbh.getNoCCC() + vl.getDonGia() * soLuong - ctOld.thanhTien(), nbh.getGhiChu());

				}
			} else {
				connection = ConnectionDao.getConnection();
				PreparedStatement ps = connection
						.prepareStatement("insert into ct_lsnh(id_ls,id_vl,so_luong) values (?,?,?)");
				ps.setString(1, idLichSu);
				ps.setString(2, idVatLieu);
				ps.setDouble(3, soLuong);
				int affect = ps.executeUpdate();
				if (affect > 0) {
					ASanPham vl = VatLieuDao.getInstance().getVatLieu(idVatLieu);
					NhaBanHang nbh = ls.getCoSoNhap();
					return NhaCungCapDao.getInstance().editNCC(nbh.getId(), nbh.getTen(), nbh.getSdt(), nbh.getDiaChi(),
							nbh.getNoCCC() + vl.getDonGia() * soLuong, nbh.getGhiChu());
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
			LichSuMuaHang ls = getLichSu(id);
			connection = ConnectionDao.getConnection();
			if (idVatLieuCu.equals(idVatLieu)) {
				PreparedStatement ps = connection
						.prepareStatement("update ct_lsnh ct set ct.so_luong=? where ct.id_ls=? and ct.id_vl=?");
				ps.setString(3, idVatLieu);
				ps.setString(2, id);
				ps.setDouble(1, soLuong);
				if (ps.executeUpdate() > 0) {
					ChiTietLichSuMuaHang ct = ls.getChiTietLichSu(idVatLieuCu);
					ASanPham vl = VatLieuDao.getInstance().getVatLieu(idVatLieu);
					NhaBanHang nbh = ls.getCoSoNhap();
					return NhaCungCapDao.getInstance().editNCC(nbh.getId(), nbh.getTen(), nbh.getSdt(), nbh.getDiaChi(),
							nbh.getNoCCC() - ct.thanhTien() + vl.getDonGia() * soLuong, nbh.getGhiChu());
				} else
					return false;
			}
			ChiTietLichSuMuaHang ct = ls.getChiTietLichSu(idVatLieu);
			PreparedStatement ps = connection
					.prepareStatement("update ct_lsnh ct set ct.id_vl=?,ct.so_luong=? where ct.id_ls=? and ct.id_vl=?");
			ps.setString(3, id);
			ps.setString(1, idVatLieu);
			if (ct != null) {
				deleteChiTiet(id, idVatLieuCu);
				ps.setString(4, idVatLieu);
				ps.setDouble(2, ct.getSoLuong() + soLuong);
			} else {
				ps.setDouble(2, soLuong);
				ps.setString(4, idVatLieuCu);
			}

			if (ps.executeUpdate() > 0) {
				ChiTietLichSuMuaHang ct1 = ls.getChiTietLichSu(idVatLieuCu);
				ASanPham vl = VatLieuDao.getInstance().getVatLieu(idVatLieu);
				NhaBanHang nbh = ls.getCoSoNhap();
				return NhaCungCapDao.getInstance().editNCC(nbh.getId(), nbh.getTen(), nbh.getSdt(), nbh.getDiaChi(),
						nbh.getNoCCC() - ct.thanhTien() + vl.getDonGia() * soLuong, nbh.getGhiChu());
			}
		} catch (SQLException e) {

			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;

	}

	public LichSuMuaHang getLichSu(String idLichSu) {
		try {
			connection = ConnectionDao.getConnection();
			PreparedStatement ps = connection.prepareStatement(
					"select * from lich_su_nhap_hang ls inner join ct_lsnh ct on ls.id=ct.id_ls inner join "
							+ " nha_ban_hang nbh on nbh.id=ls.id_nbh inner join vat_lieu m on m.id=ct.id_vl inner join hinh_anh i on i.idvl=m.id where ls.hd=1 and ls.id=?");
			ps.setString(1, idLichSu);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				String maLs = rs.getString("ls.id");
				NhaBanHang nbh = new NhaBanHang(rs.getString("nbh.id"), rs.getString("nbh.ten"),
						rs.getInt("so_dien_thoai"), rs.getString("dia_chi"), rs.getDouble("no_ccc"),
						rs.getString("ghi_chu"));
				ArrayList<ChiTietLichSuMuaHang> danhSachThanhPhan = new ArrayList<ChiTietLichSuMuaHang>();
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
				danhSachThanhPhan.add(new ChiTietLichSuMuaHang(vl, rs.getDouble("ct.so_luong")));
				LichSuMuaHang ls = new LichSuMuaHang(maLs, danhSachThanhPhan, rs.getDate("ngay_nhap"), nbh);
				return ls;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	public boolean setLichSu(String idNbh, Date ngayNhap, String id, String oldNbh) {
		try {
			connection = ConnectionDao.getConnection();
			PreparedStatement ps = connection
					.prepareStatement("update lich_su_nhap_hang ls set ls.id_nbh=?,ls.ngay_nhap=? where ls.id=?");
			ps.setString(1, idNbh);
			ps.setDate(2, ngayNhap);
			ps.setString(3, id);
			int affect = ps.executeUpdate();
			if (affect > 0) {
				LichSuMuaHang ls = getLichSu(id);
				NhaBanHang nbh1 = NhaCungCapDao.getInstance().getNBH(idNbh);
				NhaBanHang nbh2 = NhaCungCapDao.getInstance().getNBH(oldNbh);
				boolean a = NhaCungCapDao.getInstance().editNCC(nbh1.getId(), nbh1.getTen(), nbh1.getSdt(),
						nbh1.getDiaChi(), nbh1.getNoCCC() + ls.thanhTien(), nbh1.getGhiChu());
				boolean b = NhaCungCapDao.getInstance().editNCC(nbh2.getId(), nbh2.getTen(), nbh2.getSdt(),
						nbh2.getDiaChi(), nbh2.getNoCCC() - ls.thanhTien(), nbh2.getGhiChu());
				return a && b;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public boolean addLichSu(String idls, String idNbh, Date ngayNhap) {
		try {
			connection = ConnectionDao.getConnection();
			PreparedStatement ps = connection
					.prepareStatement("insert into lich_su_nhap_hang(id,ngay_nhap,id_nbh,hd) values(?,?,?,?)");
			ps.setString(1, idls);
			ps.setDate(2, ngayNhap);
			ps.setString(3, idNbh);
			ps.setInt(4, 1);
			int affect = ps.executeUpdate();
			if (affect > 0) {
				boolean addChiTiet = addChiTiet(idls, "vl01", 0);
				NhaBanHang nbh = NhaCungCapDao.getInstance().getNBH(idNbh);
				if (nbh != null) {
					return addChiTiet && NhaCungCapDao.getInstance().editNCC(nbh.getId(), nbh.getTen(), nbh.getSdt(),
							nbh.getDiaChi(), nbh.getNoCCC(), nbh.getGhiChu());
				}
				return false;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public boolean deleteLichSu(String idLichSu) {
		try {
			LichSuMuaHang ls = getLichSu(idLichSu);
			connection = ConnectionDao.getConnection();
			PreparedStatement ps = connection.prepareStatement("update lich_su_nhap_hang ls set ls.hd=? where ls.id=?");
			ps.setInt(1, 0);
			ps.setString(2, idLichSu);
			int affect = ps.executeUpdate();
			if (affect > 0) {
				NhaBanHang nbh = ls.getCoSoNhap();
				return NhaCungCapDao.getInstance().editNCC(nbh.getId(), nbh.getTen(), nbh.getSdt(), nbh.getDiaChi(),
						nbh.getNoCCC() - ls.thanhTien(), nbh.getGhiChu());
			}
		} catch (SQLException e) {

			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public boolean deleteChiTiet(String idLs, String idVl) {
		try {
			LichSuMuaHang ls = getLichSu(idLs);
			connection = ConnectionDao.getConnection();
			PreparedStatement ps = connection
					.prepareStatement("delete from ct_lsnh ct where ct.id_ls=? and ct.id_vl=?");
			ps.setString(1, idLs);
			ps.setString(2, idVl);
			int affect = ps.executeUpdate();
			if (affect > 0) {
				ChiTietLichSuMuaHang ct = ls.getChiTietLichSu(idVl);
				NhaBanHang nbh = ls.getCoSoNhap();
				return NhaCungCapDao.getInstance().editNCC(nbh.getId(), nbh.getTen(), nbh.getSdt(), nbh.getDiaChi(),
						nbh.getNoCCC() - ls.thanhTien(), nbh.getGhiChu());
			}
		} catch (SQLException e) {

			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public int totalReceived(int month, int year) {
		try {
			connection = ConnectionDao.getConnection();
			PreparedStatement ps = connection.prepareStatement(
					"SELECT COUNT(*) as total from  lich_su_nhap_hang as d WHERE d.hd=1 AND YEAR(d.NGAY_NHAP) = ? AND MONTH(d.NGAY_NHAP) = ?");
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
}
