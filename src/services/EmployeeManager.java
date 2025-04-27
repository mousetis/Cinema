package services;

import java.util.ArrayList;

import model.Employee;

public class EmployeeManager {
	dao.EmployeeManager dao;
	
	public EmployeeManager(dao.EmployeeManager dao) {
		this.dao = new dao.EmployeeManager();
	}

	// lấy danh sách nhân viên
	public ArrayList<Employee> getList() {
		ArrayList<Employee> list = new ArrayList<Employee>();
		list = dao.getListEmployee();
		return list;
	}
	
	//tìm nhân viên
	public ArrayList<Employee> findEmployees(String keyID, String keyName){
		ArrayList<Employee> list = new  ArrayList<Employee>();
		list = dao.findEmployee(keyID, keyName);
		return list;
	}
	
	//xoá nhân viên
	public boolean deleteEmployee(String keyID) {
		return dao.deleteEmployee(keyID);
	}
	
	//cập nhật nhân viên
	public boolean updateEmployee(String keyID, Employee nv) {
		return dao.updateEmployee(keyID, nv);
	}
	
	//thêm nhân viên
	public boolean addEmployee(Employee nv, String email, String password) {
		return dao.addEmployee(nv, email, password);
	}
	
	//lấy data của nhân viên
	public Employee getEmployee(String keyID) {
		Employee nv = new Employee();
		nv = dao.getEmployee(keyID);
		return nv;
	}
}
