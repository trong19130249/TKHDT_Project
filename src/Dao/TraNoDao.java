package Dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.NhaBanHang;
import model.TraNo;

public class TraNoDao {
	Connection connection;
	private static TraNoDao instance;

	private TraNoDao() {

	}

	public static TraNoDao getInstance() {
		if (instance == null)
			instance = new TraNoDao();
		return instance;
	}

	public List<TraNo> getAllDebt() {
		List<TraNo> result = new ArrayList<TraNo>();
		try {
			connection = ConnectionDao.getConnection();
			PreparedStatement ps = connection.prepareStatement(
					"select * from nha_ban_hang nbh inner join ls_tra_no ls on ls.id_nbh=nbh.id",
					ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				String id = rs.getString("id");
				String ten = rs.getString("ten");
				int sdt = rs.getInt("so_dien_thoai");
				String diaChi = rs.getString("dia_chi");
				double noCCC = rs.getDouble("no_ccc");
				String ghiChu = rs.getString("ghi_chu");
				Date ngayTra = rs.getDate("ngay_tra");
				double soTienNoLs = rs.getDouble("ls.so_tien_no");
				NhaBanHang nbh = new NhaBanHang(id, ten, sdt, diaChi, noCCC, ghiChu);
				TraNo traNo = new TraNo(nbh, ngayTra, soTienNoLs);
				result.add(traNo);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;

	}

	public TraNo getDebt(String idNbh) {
		try {
			connection = ConnectionDao.getConnection();
			PreparedStatement ps = connection.prepareStatement(
					"select * from nha_ban_hang nbh inner join ls_tra_no ls on ls.id_nbh=nbh.id where ls.id_nbh=?",
					ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			ps.setString(1, idNbh);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				String id = rs.getString("id");
				String ten = rs.getString("ten");
				int sdt = rs.getInt("so_dien_thoai");
				String diaChi = rs.getString("dia_chi");
				double noCCC = rs.getDouble("no_ccc");
				String ghiChu = rs.getString("ghi_chu");
				Date ngayTra = rs.getDate("ngay_tra");
				double soTienNoLs = rs.getDouble("ls.so_tien_no");
				NhaBanHang nbh = new NhaBanHang(id, ten, sdt, diaChi, noCCC, ghiChu);
				TraNo traNo = new TraNo(nbh, ngayTra, soTienNoLs);
				return traNo;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

	public NhaBanHang getNBH(String id) {
		try {
			connection = ConnectionDao.getConnection();
			PreparedStatement ps = connection.prepareStatement("select * from nha_ban_hang");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				String ten = rs.getString("ten");
				int sdt = rs.getInt("so_dien_thoai");
				String diaChi = rs.getString("dia_chi");
				double noCCC = rs.getDouble("no_ccc");
				String ghiChu = rs.getString("ghi_chu");
				NhaBanHang nbh = new NhaBanHang(id, ten, sdt, diaChi, noCCC, ghiChu);
				return nbh;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public boolean setDebt(String idNbh, Date ngayTra, double soTienNo, String idOld) {
		try {
			connection = ConnectionDao.getConnection();
			PreparedStatement ps = connection.prepareStatement(
					"update ls_tra_no ls set ls.id_nbh=?,ls.so_tien_no=?,ls.ngay_tra=? where ls.id_nbh=?");
			ps.setString(1, idNbh);
			ps.setDate(3, ngayTra);
			ps.setDouble(2, soTienNo);
			ps.setString(4, idOld);
			int affect = ps.executeUpdate();
			if (affect > 0)
				return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public boolean addDebt(String id, Date ngayTra, double soTienNo) {
		try {
			connection = ConnectionDao.getConnection();
			PreparedStatement ps = connection
					.prepareStatement("insert into ls_tra_no(id_nbh,ngay_tra,so_tien_no) values(?,?,?)");
			ps.setString(1, id);
			ps.setDate(2, ngayTra);
			ps.setDouble(3, soTienNo);
			int affect = ps.executeUpdate();
			if (affect > 0)
				return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	// tra no cho nha cc
	public boolean deleteDebt(String idNBH, Date ngayTra) {
		try {
			connection = ConnectionDao.getConnection();
			PreparedStatement ps = connection
					.prepareStatement("DELETE from ls_tra_no where ls_tra_no.ID_nbh=? and ls_tra_no.ngay_tra=?");
			ps.setString(1, idNBH);
			ps.setDate(2, ngayTra);
			int affect = ps.executeUpdate();
			if (affect > 0)
				return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}
}
