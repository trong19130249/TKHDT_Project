package model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DonHang {
	private String id;
	private Date ngayLap;
	private KhachHang khachHang;
	private int trangThai;
	private ArrayList<ChiTietDonHang> danhSachThanhPhan = new ArrayList<ChiTietDonHang>();

	public DonHang(String id, Date ngayLap, KhachHang khachHang, int trangThai,
			ArrayList<ChiTietDonHang> danhSachThanhPhan) {
		this.id = id;
		this.ngayLap = ngayLap;
		this.khachHang = khachHang;
		this.trangThai = trangThai;
		this.danhSachThanhPhan = danhSachThanhPhan;
	}

	public DonHang() {

	}

	public Date getNgayLap() {
		return ngayLap;
	}

	public void setNgayLap(Date ngayLap) {
		this.ngayLap = ngayLap;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setKhachHang(KhachHang khachHang) {
		this.khachHang = khachHang;
	}

	public void setDanhSachThanhPhan(ArrayList<ChiTietDonHang> danhSachThanhPhan) {
		this.danhSachThanhPhan = danhSachThanhPhan;
	}

	public void add(ChiTietDonHang tp) {
		danhSachThanhPhan.add(tp);
	}

	public List<ChiTietDonHang> getDanhSachThanhPhan() {
		return this.danhSachThanhPhan;
	}

	@Override
	public String toString() {
		String result = "DonHang= {\n+";
		result += "id=" + this.id + "\n";
		result += this.khachHang.toString() + ",\n";
		result += this.trangThai + ",\nDanhSachThanhPhan=[";
		for (ChiTietDonHang ct : danhSachThanhPhan) {
			result += ct.toString() + ",";
		}
		result += "]}";
		return result;

	}

	public void remove(ChiTietDonHang tp) {
		int i = danhSachThanhPhan.indexOf(tp);
		if (i >= 0)
			danhSachThanhPhan.remove(i);
	}

	public KhachHang getKhachHang() {
		return khachHang;
	}

	public int getTrangThai() {
		return trangThai;
	}

	public void setTrangThai(int trangThai) {
		this.trangThai = trangThai;
	}

	public void setIdKhachHang(KhachHang khachHang) {
		this.khachHang = khachHang;
	}

	public Iterator createrIterator() {
		return danhSachThanhPhan.iterator();
	}

	public double thanhTien() {
		double price = 0;
		Iterator list = createrIterator();
		while (list.hasNext()) {
			ChiTietDonHang ct = (ChiTietDonHang) list.next();
			price += ct.thanhTien();
		}
		return price;
	}

	public ChiTietDonHang getChiTietHoaDon(String idSanPham) {
		Iterator list = createrIterator();
		while (list.hasNext()) {
			ChiTietDonHang ct = (ChiTietDonHang) list.next();
			if (ct.getSanPham().getId().equalsIgnoreCase(idSanPham))
				return ct;
		}
		return null;
	}
}
