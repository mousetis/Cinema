package services;

import model.Employee;

public class Login {

	dao.Login dao;
	
	public Login(dao.Login dao) {
		this.dao = new dao.Login();
	}
	
	//kiểm tra đăng nhập
	public Employee checkLogin(String user, String password) {
		Employee nv = dao.checkAccount(user, password);
		return nv;
	}
	
}
