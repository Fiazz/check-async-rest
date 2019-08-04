package com.example.check.async.rest.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EmployeeDetailsVO {

	private AddressVO address;
	private DepartmentVO department;
	private EmployeeVO employee;

}
