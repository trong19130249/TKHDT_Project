package Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Dao.KhachHangDao;
import Dao.VatLieuDao;
import View.form.FormSales;
import model.ASanPham;
import model.DonHangModelInterface;
import model.KhachHang;

public class FormSalesController {
	private DonHangModelInterface model;
	private FormSales view;
	private Map<String, Double> data = new HashMap<String, Double>();
	private KhachHang khachHang = null;

	public FormSalesController(DonHangModelInterface model) {
		super();
		this.model = model;
		this.view = new FormSales(model, this);
		updateListProduct();
		updateListCustomer();
	}

	public FormSales getView() {
		return view;
	}

	public void updateListProduct() {
		view.removeAllProductView();
		List<ASanPham> listProduct = VatLieuDao.getInstance().getAll();
		for (int i = 0; i < listProduct.size(); i++) {
			view.addProduct(listProduct.get(i));
		}
	}

	public void updateListCustomer() {
		view.removeAllCombobox();
		view.addCombobox(null);
		List<KhachHang> listCustomer = KhachHangDao.getInstance().getDanhSachKhachHang();
		for (int i = 0; i < listCustomer.size(); i++) {
			view.addCombobox(listCustomer.get(i));
		}
	}

	public KhachHang getKhachhang() {
		return khachHang;
	}

	public void setKhachhang(KhachHang khachHang) {
		this.khachHang = khachHang;
	}

	public void pushOrdersCache(ASanPham sanPham) {
		double soLuong = 10;
		String idSanPham = sanPham.getId();
		if (sanPham.getSoLuong() > 0) {
			if (data.containsKey(idSanPham)) {
				double soLuongSau = sanPham.getSoLuong() - data.get(idSanPham);
				if (soLuongSau > 0) {
					soLuong = (soLuongSau > 1 ? 1 : soLuongSau) + data.get(idSanPham);
					data.put(idSanPham, soLuong);
					view.addDetailsOrder(sanPham, soLuong);
				}

			} else {
				soLuong = (sanPham.getSoLuong() > 1 ? 1 : sanPham.getSoLuong());
				data.put(idSanPham, soLuong);
				view.addDetailsOrder(sanPham, soLuong);

			}
		}

	}

	public void setQuality(String id, double input) {
		double soLuong = VatLieuDao.getInstance().getVatLieu(id).getSoLuong();
		if (input > 0) {
			if (input < soLuong) {
				data.put(id, input);
			} else {
				data.put(id, soLuong);
			}
		} else {
			double s = (soLuong > 1 ? 1 : soLuong);
			data.put(id, s);
		}
	}

	public boolean addDonHang(double payMoney) {
		if (model.addDonHang(khachHang.getMaSo(), payMoney, new java.sql.Date(System.currentTimeMillis()), data)) {
			khachHang = null;
			data.clear();
			view.resetView();
			return true;
		}
		return false;
	}

	public double getQuality(String id) {
		return data.get(id);
	}

	public void removeProduct(String id) {
		data.remove(id);
		view.removeRowTable(id);
	};
}
