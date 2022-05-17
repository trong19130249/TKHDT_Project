package model;

public class NhanVien {
	private String id;
	private String ten;
	private int sdt;
	private int trangThai;
	private String ghiChu;
	private int gioiTinh;
	private String email;
	private String diaChi;
	private String chucVu;
	private double luong;

	public NhanVien() {

	}

	public NhanVien(String id, String ten, int sdt, int trangThai, String ghiChu, int gioiTinh, String email,
			String diaChi, String chucVu, double luong) {
		this.id = id;
		this.ten = ten;
		this.sdt = sdt;
		this.trangThai = trangThai;
		this.ghiChu = ghiChu;
		this.gioiTinh = gioiTinh;
		this.email = email;
		this.diaChi = diaChi;
		this.chucVu = chucVu;
		this.luong = luong;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTen() {
		return ten;
	}

	public void setTen(String ten) {
		this.ten = ten;
	}

	public int getSdt() {
		return sdt;
	}

	public void setSdt(int sdt) {
		this.sdt = sdt;
	}

	public int getTrangThai() {
		return trangThai;
	}

	public void setTrangThai(int trangThai) {
		this.trangThai = trangThai;
	}

	public String getGhiChu() {
		return ghiChu;
	}

	public void setGhiChu(String ghiChu) {
		this.ghiChu = ghiChu;
	}

	public int getGioiTinh() {
		return gioiTinh;
	}

	public void setGioiTinh(int gioiTinh) {
		this.gioiTinh = gioiTinh;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDiaChi() {
		return diaChi;
	}

	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}

	public String getChucVu() {
		return chucVu;
	}

	public void setChucVu(String chucVu) {
		this.chucVu = chucVu;
	}

	public double getLuong() {
		return luong;
	}

	public void setLuong(double luong) {
		this.luong = luong;
	}
}
