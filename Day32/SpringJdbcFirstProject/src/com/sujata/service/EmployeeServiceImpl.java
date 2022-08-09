package com.sujata.service;

import java.util.List;

import com.sujata.bean.Employee;
import com.sujata.bean.EmployeePayslip;
import com.sujata.persistence.EmployeeDao;
import com.sujata.persistence.EmployeeDaoImpl;

public class EmployeeServiceImpl implements EmployeeService {

	private EmployeeDao employeeDao;
	

	public void setEmployeeDao(EmployeeDao employeeDao) {
		this.employeeDao = employeeDao;
	}

	@Override
	public List<Employee> getAllEmployees() {
		return employeeDao.getAllRecords();
	}

	@Override
	public Employee searchEmployeeById(int employeeId) {
		return employeeDao.searchRecord(employeeId);
	}

	/*
	 * HR= 15% of Basic
	 * DA= 18% of Basic
	 * PF= 12% of Basic
	 */
	@Override
	public EmployeePayslip generatePayslip(int employeeId) {
		Employee employee=employeeDao.searchRecord(employeeId);
		EmployeePayslip employeePayslip=null;
		if(employee!=null) {
		double hr=.15*employee.getEmpSalary();
		double da=.18*employee.getEmpSalary();
		double pf=.12*employee.getEmpSalary();
		double total=employee.getEmpSalary()+hr+da-pf;
		employeePayslip=new EmployeePayslip(employee, hr, pf, da, total);
		}
		return employeePayslip;
	}

	@Override
	public boolean addEmployee(Employee employee) {
		if(employeeDao.addRecord(employee)>0)
			return true;
		return false;
	}

}
