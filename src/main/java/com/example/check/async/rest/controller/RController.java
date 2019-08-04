package com.example.check.async.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.check.async.rest.bo.EmployeeDetailsBO;
import com.example.check.async.rest.vo.EmployeeDetailsVO;

@RestController
public class RController {
	
	@Autowired
	private EmployeeDetailsBO employeeDetailsBO;

	@RequestMapping(value = "/employee/details", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public EmployeeDetailsVO getEmployeeDetailsVO(){
		return employeeDetailsBO.getEmployeDetails();
	}
}
