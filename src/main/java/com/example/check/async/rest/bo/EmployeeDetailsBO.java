package com.example.check.async.rest.bo;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;

import com.example.check.async.rest.eo.EmployeeDetailsEO;
import com.example.check.async.rest.vo.AddressVO;
import com.example.check.async.rest.vo.DepartmentVO;
import com.example.check.async.rest.vo.EmployeeDetailsVO;
import com.example.check.async.rest.vo.EmployeeVO;

@Component
public class EmployeeDetailsBO {
	private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeDetailsBO.class);

	@Autowired
	EmployeeDetailsEO employeeDetailsEO;

	public EmployeeDetailsVO getEmployeDetails() {

		EmployeeDetailsVO employeeDetails = null;

		CompletableFuture<AddressVO> employeeAddress = null;
		CompletableFuture<DepartmentVO> employeeDept = null;
		CompletableFuture<EmployeeVO> employeeProfile = null;
		try {
			employeeAddress = employeeDetailsEO.getEmployeeAddress();
			employeeDept = employeeDetailsEO.getEmployeeDept();
			employeeProfile = employeeDetailsEO.getEmployeeProfile();

			LOGGER.info("Employee Address : {}", employeeAddress.get());
			LOGGER.info("Employee Department: {}", employeeDept.get());
			LOGGER.info("Employee Profile: {} ", employeeProfile.get());
			employeeDetails = new EmployeeDetailsVO(employeeAddress.get(), employeeDept.get(), employeeProfile.get());
		} catch (InterruptedException ie) {
			LOGGER.error("Received Exception : {}", ie);

		} catch (RestClientException re) {
			LOGGER.error("Received Exception : {}", re);

		} catch (ExecutionException ee) {
			LOGGER.error("Received Exception : {}", ee);
		}

		// Wait until all threads are complete
		CompletableFuture.allOf(employeeAddress, employeeDept, employeeProfile).join();
		return employeeDetails;

	}

}
