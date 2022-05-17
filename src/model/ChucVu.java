package model;

public class ChucVu {
	private String id;
	private String ten;
	private double luong;

	public ChucVu(String id, String ten, double luong) {
		this.id = id;
		this.ten = ten;
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

	public double getLuong() {
		return luong;
	}

	public void setLuong(double luong) {
		this.luong = luong;
	}

}
