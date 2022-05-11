package model;

public class TaiKhoan {
private String userName;
private String pass;
private NhanVien nhanVien;
private boolean quyenQuanTri;
public TaiKhoan(String userName, String pass, NhanVien nhanVien, boolean quyenQuanTri) {
	this.userName = userName;
	this.pass = pass;
	this.nhanVien = nhanVien;
	this.quyenQuanTri = quyenQuanTri;
}
public String getUserName() {
	return userName;
}
public void setUserName(String userName) {
	this.userName = userName;
}
public String getPass() {
	return pass;
}
public void setPass(String pass) {
	this.pass = pass;
}
public NhanVien getNhanVien() {
	return nhanVien;
}
public void setNhanVien(NhanVien nhanVien) {
	this.nhanVien = nhanVien;
}
public boolean isQuyenQuanTri() {
	return quyenQuanTri;
}
public void setQuyenQuanTri(boolean quyenQuanTri) {
	this.quyenQuanTri = quyenQuanTri;
}

}
