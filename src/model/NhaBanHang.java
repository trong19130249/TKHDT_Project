package model;

public class NhaBanHang {
private String id;
private String ten;
private int sdt;
private String diaChi;
private double noCCC;
private String ghiChu;
public NhaBanHang(String id, String ten, int sdt, String diaChi,double noCCC,String ghiChu) {
	this.id = id;
	this.ten = ten;
	this.sdt = sdt;
	this.diaChi = diaChi;
	this.noCCC=noCCC;
	this.ghiChu=ghiChu;
}
public NhaBanHang() {
	
}
public double getNoCCC() {
	return noCCC;
}
public void setNoCCC(double noCCC) {
	this.noCCC = noCCC;
}
public String getGhiChu() {
	return ghiChu;
}
public void setGhiChu(String ghiChu) {
	this.ghiChu = ghiChu;
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
public String getDiaChi() {
	return diaChi;
}
public void setDiaChi(String diaChi) {
	this.diaChi = diaChi;
}


}
