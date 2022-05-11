package View.model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import Dao.DonHangDao;
import Dao.NhapHangDao;
import model.DonHang;
import model.DonHangModelInterface;
import model.KhachHang;
import model.Observer;

public class OrdersData implements DonHangModelInterface{
	private String[] columnNames = { "", "Chi tiết", "Mã đơn hàng","Mã Khách Hàng", "Tên khách hàng", "Email", "Địa chỉ GH", "Ngày lập",
			"Trạng Thái", "Tổng tiền" };
	private Object[][] rowData;
	Calendar cal  ;
	private int month ;
	private int year ;
	private int month2;
	private int year2;
	public OrdersData() {
		cal = Calendar.getInstance();
		java.util.Date date= new java.util.Date();
		this.cal.setTime(date);
		month= cal.get(Calendar.MONTH);
		year = cal.get(Calendar.YEAR);
		month2 = (month==1?12:month-1);
		year2 = (month==1?year-1:year);
	}

	public String[] getColumnNames() {
		return columnNames;
	}
	public Object[][] getRowData() {
		return rowData;
	}
	ArrayList<Observer> observers=new ArrayList<Observer>();
	public void setData() {
		List<DonHang> list=getAll();
		rowData=new Object[list.size()][columnNames.length];
		for(int i=0;i<rowData.length;i++) {
			DonHang dh=list.get(i);
		
			KhachHang kh=dh.getKhachHang();
			double tongTien=dh.thanhTien();
			Object[] element= {false,"Xem",dh.getId(),kh.getMaSo(),kh.getHoVaTen(),kh.getEmail(),kh.getDiachi(),dh.getNgayLap().toString(),dh.getTrangThai(),tongTien};
			rowData[i]=element;
		}
	}
	public Object[][] getDataTable(){
		List<DonHang> list=getAllOrder();
		Object[][] data=new Object[list.size()][];
		for(int i=0;i<data.length;i++) {
			System.out.println(list.get(i).getNgayLap());
			DonHang dh=list.get(i);
			KhachHang kh=dh.getKhachHang();
			double tongTien=dh.thanhTien();
			int statusVal =dh.getTrangThai();
			StatusType statusType;
//			System.out.println(kh.getHoVaTen());
			// 0 là chờ xác nhận, 1 đang vận chuyển, 2 đã giao, 3 đã huỷ
			switch (statusVal) {
			case 0:
				statusType = StatusType.ACCEPT;
				break;
			case 1:
				statusType = StatusType.SHIP;
				break;
			case 2:
				statusType = StatusType.RECEIVE;
				break;
			case 3:
				statusType = StatusType.CANCELLED;
				break;
			default:
				statusType = StatusType.CANCELLED;
				break;
			}
		
			data[i] = new Object[]{kh.getHoVaTen(),	tongTien, kh.getDiachi(), dh.getNgayLap(), statusType};
		}
		return data;
		
	}
	@Override
	public void registerObserver(Observer o) {
		// TODO Auto-generated method stub
		observers.add(o);
	}

	@Override
	public void removeObserver(Observer o) {
		// TODO Auto-generated method stub
		int index=observers.indexOf(o);
		if(index>=0) observers.remove(index);
		
	}

	@Override
	public void notifyObservers() {
		// TODO Auto-generated method stub
		for(Observer view:observers) {
			view.update();
		}
	}
	@Override
	public List<DonHang> getAll() {
		// TODO Auto-generated method stub
		return DonHangDao.getInstance().getAll();
	}

	@Override
	public List<DonHang> getAll(String idKhachHang) {
		// TODO Auto-generated method stub
		return DonHangDao.getInstance().getAll(idKhachHang);
	}

	@Override
	public DonHang getDonHang(String idDonHang) {
		// TODO Auto-generated method stub
		return DonHangDao.getInstance().getDonHang(idDonHang);
	}

	@Override
	public boolean delete(String id) {
		// TODO Auto-generated method stub
		boolean r= DonHangDao.getInstance().delete(id);
		if(r) notifyObservers();
		return r;
	}

	@Override
	public boolean setDonHang(String idKh, Date ngayLap,String id,String oldKh) {
		// TODO Auto-generated method stub
		boolean r=DonHangDao.getInstance().setDonHang(idKh, ngayLap,id,oldKh);
		System.out.println(r);
		if(r)notifyObservers();
		return r;
	}

	@Override
	public boolean addDonHang(String idDh, String idKh, Date ngayLap) {
		// TODO Auto-generated method stub
		boolean r=DonHangDao.getInstance().addDonHang(idDh, idKh, ngayLap);
		if(r)notifyObservers();
		return r;
	}

	@Override
	public List<DonHang> getAllOrder() {
		// TODO Auto-generated method stub
		return DonHangDao.getInstance().getAllOrder();
	}
	
	@Override
	public double getRatioTotalOrderMonth(int service) {
		double result =0;
		switch (service) {
		case 0: {
			result = ((double)DonHangDao.getInstance().totalOrderByMonth(month+1, year))/DonHangDao.getInstance().totalOrderByMonth(month2+1, year2);
			break;
		}
		case 1: {
			result = ((double)DonHangDao.getInstance().totalSales(month+1, year))/DonHangDao.getInstance().totalSales(month2+1, year2);
			break;
		}
		case 2:{
			result = ((double)NhapHangDao.getInstance().totalReceived(month+1, year))/NhapHangDao.getInstance().totalReceived(month2+1, year2);
			break;
		}
		default:
			break;
		}
//		System.out.println(result);
//		System.out.println(DonHangDao.getInstance().totalDonHangByMonth(month+1, year));
//		System.out.println(DonHangDao.getInstance().totalDonHangByMonth(month2+1, year2))
		return result;
	}
	@Override
	protected Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return super.clone();
	}

	@Override
	public int countOrderMonth() {
		// TODO Auto-generated method stub
		return DonHangDao.getInstance().totalOrderByMonth(month+1, year);
	}

	@Override
	public double totalProfitMonth() {
		// TODO Auto-generated method stub
		return DonHangDao.getInstance().totalSales(month+1, year);
	}

	@Override
	public int totalReceivedMonth() {
		// TODO Auto-generated method stub
		return NhapHangDao.getInstance().totalReceived(month+1, year);
	}

	@Override
	public boolean addDonHang(String idKh, double payMoneyDate, Date ngayLap, Map<String, Double> data) {
		// TODO Auto-generated method stub
		boolean r=DonHangDao.getInstance().addDonHang(idKh, payMoneyDate, ngayLap, data);
		if(r)notifyObservers();
		return r;
	}



	

}
