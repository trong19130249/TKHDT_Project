package model;

public interface DangNhapDangXuatModelInterface {
	public void registerObserver(Observer o);
	public void removeObserver(Observer o);
	public void notifyObservers();
	public boolean signup(TaiKhoan cus);
	public boolean signin(String userName, String password);
	public boolean containUserName(String userName) ;
}
