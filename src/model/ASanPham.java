package model;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import View.model.ModelAction;

public class ASanPham implements SanPham{
@Override
	public String toString() {
	String list="";
	for(String s:listUrl) {
		list+=s;
	}
		String r= "ASanPham [id=" + id + ", tenSanPham=" + tenSanPham + ", mota=" + mota + ", donViTinh=" + donViTinh
				+ ", donGia=" + donGia + ", soLuong=" + soLuong + ", type=" + type + ", listUrl={" + list + "}, giaNhap= "+giaNhap+"]";

		return r;
}

private String id;
private String tenSanPham;
private String mota;
private String donViTinh;
private double donGia;
private double soLuong;
private String type;
private double giaNhap;
private List<String> listUrl;
// constructor rỗng
public ASanPham() {
	
}
// constructor đầy đủ bao gồm các thuộc tính
public ASanPham(String id, String tenSanPham, String mota, String donViTinh, double donGia, double soLuong, String type,
		List<String> listUrl,double giaNhap) {
	this.id = id;
	this.tenSanPham = tenSanPham;
	this.mota = mota;
	this.donViTinh = donViTinh;
	this.donGia = donGia;
	this.soLuong = soLuong;
	this.type = type;
	this.listUrl = listUrl;
	this.giaNhap=giaNhap;
}
public List<String> getListUrl() {
	return listUrl;
}
public void setListUrl(List<String> listUrl) {
	this.listUrl = listUrl;
}
public String getTenSanPham() {
	return tenSanPham;
}
public void setType(String type) {
	this.type = type;
}
public double getGiaNhap() {
	return giaNhap;
}
public void setGiaNhap(double giaNhap) {
	this.giaNhap = giaNhap;
}
public String getType() {
	return type;
}
public String jcbName() {
	return tenSanPham;
}
public void setTenSanPham(String tenSanPham) {
	this.tenSanPham = tenSanPham;
}
public String getDonViTinh() {
	return donViTinh;
}
public void setDonViTinh(String donViTinh) {
	this.donViTinh = donViTinh;
}
public double getDonGia() {
	return donGia;
}
public void setDonGia(double donGia) {
	this.donGia = donGia;
}
public double getSoLuong() {
	return soLuong;
}
public void setSoLuong(double soLuong) {
	this.soLuong = soLuong;
}
public String getId() {
	return id;
}
public void setId(String id) {
	this.id = id;
}
public String getMota() {
	return mota;
}
public void setMota(String mota) {
	this.mota = mota;
}
public void addImage(String url) {
	this.listUrl.add(url);
}
public String formatCurrencyVN(double amt){
    Locale vn = new Locale("vi", "VN");
//    Currency vnd = Currency.getInstance(vn);
    NumberFormat formatter = NumberFormat.getCurrencyInstance(vn);
    return formatter.format(amt);
}
public Object[] toRowTable(View.event.EventAction event,double quantity) {
    return new Object[]{new ModelAction(this, event), id,tenSanPham , donViTinh,quantity,formatCurrencyVN(donGia) };
}
}
