package model;

import java.sql.Date;

public class TraNo {
private NhaBanHang Nbh;
private Date ngayTra;
private double soTienNo;
public TraNo( NhaBanHang nbh, Date ngayTra, double soTienNo) {
	Nbh = nbh;
	this.ngayTra = ngayTra;
	this.soTienNo = soTienNo;
}

public NhaBanHang getNbh() {
	return Nbh;
}
public void setNbh(NhaBanHang nbh) {
	Nbh = nbh;
}
public Date getNgayTra() {
	return ngayTra;
}
public void setNgayTra(Date ngayTra) {
	this.ngayTra = ngayTra;
}
public double getSoTienNo() {
	return soTienNo;
}
public void setSoTienNo(double soTienNo) {
	this.soTienNo = soTienNo;
}

}
