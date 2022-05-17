package model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Iterator;

public class LichSuMuaHang {
	private String id;
	private ArrayList<ChiTietLichSuMuaHang> lichSu;
	private Date ngayNhap;
	private NhaBanHang coSoNhap;

	public LichSuMuaHang(String id, ArrayList<ChiTietLichSuMuaHang> lichSu, Date ngayNhap, NhaBanHang coSoNhap) {
		this.id = id;
		this.lichSu = lichSu;
		this.ngayNhap = ngayNhap;
		this.coSoNhap = coSoNhap;
	}

	public LichSuMuaHang() {

	}

	public void addChiTiet(ChiTietLichSuMuaHang ct) {
		this.lichSu.add(ct);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public ArrayList<ChiTietLichSuMuaHang> getLichSu() {
		return lichSu;
	}

	public double thanhTien() {
		double result = 0;
		for (ChiTietLichSuMuaHang ct : lichSu) {
			result += ct.thanhTien();
		}
		return result;
	}

	public void setLichSu(ArrayList<ChiTietLichSuMuaHang> lichSu) {
		this.lichSu = lichSu;
	}

	public Date getNgayNhap() {
		return ngayNhap;
	}

	public void setNgayNhap(Date ngayNhap) {
		this.ngayNhap = ngayNhap;
	}

	public NhaBanHang getCoSoNhap() {
		return coSoNhap;
	}

	public void setCoSoNhap(NhaBanHang coSoNhap) {
		this.coSoNhap = coSoNhap;
	}

	@Override
	public String toString() {
		return "LichSuMuaHang [id=" + id + ", lichSu=" + lichSu + ", ngayNhap=" + ngayNhap + ", coSoNhap=" + coSoNhap
				+ "]";
	}

	public Iterator createrIterator() {
		return lichSu.iterator();
	}

	public ChiTietLichSuMuaHang getChiTietLichSu(String idSanPham) {
		Iterator list = createrIterator();
		while (list.hasNext()) {
			ChiTietLichSuMuaHang ct = (ChiTietLichSuMuaHang) list.next();
			if (ct.getVatLieu().getId().equalsIgnoreCase(idSanPham))
				return ct;
		}
		return null;
	}
}
