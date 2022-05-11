package model;

import java.util.List;

public class MaterialFactory {
private MaterialFactory() {
	
}
public static MaterialFactory getInstance() {
return MaterialHelperFactory.INSTANCE;
}
private static class MaterialHelperFactory{
	private static final MaterialFactory INSTANCE=new MaterialFactory(); 
}
public ASanPham createMaterial(String type,String id, String tenSanPham, String mota, String donViTinh, double donGia, double soLuong,List<String> listUrl,double giaNhap) {
	return new ASanPham(id, tenSanPham, mota, donViTinh, donGia, soLuong, type, listUrl, giaNhap);
}
}
