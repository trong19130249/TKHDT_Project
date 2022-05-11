package Controller;

import Dao.VatLieuDao;
import View.model.ProductsData;
import model.VatLieuModelInterface;

public class VatLieuController {
	VatLieuModelInterface model;
	// controller nhận vào model tương ứng
	public VatLieuController(VatLieuModelInterface model) {
		this.model=model;
	}
	// sau đó ủy quyền cho model thực hiện các tác vụ thêm,xóa,sửa
	public boolean setProperty(String id,String ten,String donViTinh,double donGia,double soLuong,String loai,double giaNhap,String idnew) {
		return model.setProperty(id, ten, donViTinh, donGia, soLuong, loai, giaNhap, idnew);
	}
	
	public boolean addVatLieu(String id, String ten, String donViTinh, double donGia, double soLuong, String loai,
			double giaNhap) {
	return model.addVatLieu(id, ten, donViTinh, donGia, soLuong, loai, giaNhap);
	}
	public boolean delete (String id) {
		return model.delete(id);
	}
	
}
