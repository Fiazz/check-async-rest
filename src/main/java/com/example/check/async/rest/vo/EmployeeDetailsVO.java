package com.example.check.async.rest.vo;

import lombok.Data;

@Data
public class EmployeeDetailsVO {

	private AddressVO address;
	private DepartmentVO department;
	private EmployeeVO employee;

	public EmployeeDetailsVO(AddressVO addressVO, DepartmentVO departmentVO, EmployeeVO employeeVO) {
		this.address = addressVO;
		this.department = departmentVO;
		this.employee = employeeVO;
	}
}
