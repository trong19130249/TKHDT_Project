package model;

public class KhachHang {
private String maSo;
private String hoVaTen;
private String diachi;
private String email;
private double soTienNo;
private String ghiChu;
public KhachHang(String maSo, String hoVaTen, String diachi, String email,double soTienNo,String ghiChu) {
	this.maSo = maSo;
	this.hoVaTen = hoVaTen;
	this.diachi = diachi;
	this.email = email;
	this.soTienNo=soTienNo;
	this.ghiChu=ghiChu;
}
public KhachHang() {
	
}
public double getSoTienNo() {
	return soTienNo;
}
public void setSoTienNo(double soTienNo) {
	this.soTienNo = soTienNo;
}
public String getGhiChu() {
	return ghiChu;
}
public void setGhiChu(String ghiChu) {
	this.ghiChu = ghiChu;
}
public String getMaSo() {
	return maSo;
}
public void setMaSo(String maSo) {
	this.maSo = maSo;
}
public String getHoVaTen() {
	return hoVaTen;
}
public void setHoVaTen(String hoVaTen) {
	this.hoVaTen = hoVaTen;
}
public String getDiachi() {
	return diachi;
}
public void setDiachi(String diachi) {
	this.diachi = diachi;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public String stringShow() {
	return "Khách hàng"+this.hoVaTen+"đã đặt ở địa chỉ mặc định"+ this.getDiachi() ;
}
@Override
public String toString() {
	return hoVaTen;
}

}
