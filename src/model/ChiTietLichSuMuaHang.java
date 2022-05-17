package model;

public class ChiTietLichSuMuaHang {
	private ASanPham vatLieu;
	private double soLuong;

	public ChiTietLichSuMuaHang(ASanPham vatLieu, double soLuong) {
		this.vatLieu = vatLieu;
		this.soLuong = soLuong;
	}

	public ChiTietLichSuMuaHang() {

	}

	public ASanPham getVatLieu() {
		return vatLieu;
	}

	public void setVatLieu(ASanPham vatLieu) {
		this.vatLieu = vatLieu;
	}

	public double getSoLuong() {
		return soLuong;
	}

	public void setSoLuong(double soLuong) {
		this.soLuong = soLuong;
	}

	public double thanhTien() {
		return vatLieu.getGiaNhap() * soLuong;
	}
}
