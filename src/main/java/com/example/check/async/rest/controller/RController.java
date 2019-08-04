package com.example.check.async.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.check.async.rest.bo.EmployeeDetailsBO;
import com.example.check.async.rest.v2.bo.EmployeeDetailsBOv2;
import com.example.check.async.rest.vo.EmployeeDetailsVO;

@RestController
public class RController {
	
	@Autowired
	private EmployeeDetailsBO employeeDetailsBO;
	
	@Autowired
	private EmployeeDetailsBOv2 employeeDetailsBOv2;

	//fire and callback
	@RequestMapping(value = "/employee/details", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public EmployeeDetailsVO getEmployeeDetails(){
		return employeeDetailsBO.getEmployeDetails();
	}
	
	//fire and forget
	@RequestMapping(value = "/v2/employee/details", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> getEmployeeDetailsV2(){
		employeeDetailsBOv2.invokeEmployeeService();
		return new ResponseEntity<Void>(HttpStatus.ACCEPTED) {
		};
	}
}
