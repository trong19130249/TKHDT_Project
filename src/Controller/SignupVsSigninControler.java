package Controller;

import View.main.SigninView;
import View.main.SignupView;
import View.model.SignInSignUpData;
import model.DangNhapDangXuatModelInterface;
import model.TaiKhoan;

public class SignupVsSigninControler {
	DangNhapDangXuatModelInterface modelSuSi;
	SignupView viewSignup;
	SigninView signinView;

	public SignupVsSigninControler(DangNhapDangXuatModelInterface data) {
		this.modelSuSi = data;
		this.viewSignup = new SignupView(modelSuSi, this);
		this.signinView = new SigninView(modelSuSi, this);
	}

	public void onViewSignUp() {
		viewSignup.onView();
	}

	public void offViewSignUp() {
		viewSignup.offView();
	}

	public void onViewSignIn() {
		signinView.onView();
	}

	public void offViewSignIn() {
		signinView.offView();
	}

	public boolean signup(TaiKhoan cus) {
		if (modelSuSi.signup(cus)) {
			return true;
		}
		return false;
	}

	public boolean signin(String userName, String password) {
		if (modelSuSi.signin(userName, password)) {
			return true;
		}
		return false;
	}

	public boolean containUsername(String userName) {
		return modelSuSi.containUserName(userName);
	}

	public static void main(String[] args) {
		SignInSignUpData modelSu = new SignInSignUpData();
		SignupVsSigninControler c = new SignupVsSigninControler(modelSu);
		c.onViewSignIn();
	}
}
