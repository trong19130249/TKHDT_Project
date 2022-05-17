package model;

import java.sql.Date;

public class ThuNo {
	private KhachHang khachHang;
	private Date ngayThu;
	private double soTienNo;

	public ThuNo(KhachHang khachHang, Date ngayThu, double soTienNo) {
		this.khachHang = khachHang;
		this.ngayThu = ngayThu;
		this.soTienNo = soTienNo;
	}

	public KhachHang getKhachHang() {
		return khachHang;
	}

	public void setKhachHang(KhachHang khachHang) {
		this.khachHang = khachHang;
	}

	public Date getNgayThu() {
		return ngayThu;
	}

	public void setNgayThu(Date ngayThu) {
		this.ngayThu = ngayThu;
	}

	public double getSoTienNo() {
		return soTienNo;
	}

	public void setSoTienNo(double soTienNo) {
		this.soTienNo = soTienNo;
	}

}
