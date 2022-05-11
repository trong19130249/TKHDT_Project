package model;

public class TaiKhoan {
private String userName;
private String pass;
private NhanVien nhanVien;
private int quyen;
public TaiKhoan(String userName, String pass, NhanVien nhanVien, int quyen) {
	this.userName = userName;
	this.pass = pass;
	this.nhanVien = nhanVien;
	this.quyen = quyen;
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
public int getQuyen() {
	return quyen;
}
public void setQuyen(int quyen) {
	this.quyen = quyen;
}

}
