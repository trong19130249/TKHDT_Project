package model;

public class ChiTietDonHang {
	@Override
	public String toString() {
		return "ChiTietDonHang [sanPham=" + sanPham + ", soluong=" + soluong + "]";
	}

	private ASanPham sanPham;
	private double soluong;

	public ChiTietDonHang(ASanPham sanPham, double soluong) {
		this.sanPham = sanPham;
		this.soluong = soluong;
	}

	public ChiTietDonHang() {

	}

	public ASanPham getSanPham() {
		return sanPham;
	}

	public void setSanPham(ASanPham sanPham) {
		this.sanPham = sanPham;
	}

	public double getSoluong() {
		return soluong;
	}

	public void setSoluong(double soluong) {
		this.soluong = soluong;
	}

	public double thanhTien() {
		return sanPham.getDonGia() * soluong;
	}

}
