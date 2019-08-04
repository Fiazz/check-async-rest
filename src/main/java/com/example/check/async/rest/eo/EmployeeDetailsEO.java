package com.example.check.async.rest.eo;

import java.time.Instant;
import java.util.concurrent.CompletableFuture;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.check.async.rest.vo.AddressVO;
import com.example.check.async.rest.vo.DepartmentVO;
import com.example.check.async.rest.vo.EmployeeVO;

@Service
public class EmployeeDetailsEO {

	private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeDetailsEO.class);

	@Autowired
	private RestTemplate restTemplate;

	@Async("asyncExecutor")
	public CompletableFuture<AddressVO> getEmployeeAddress() throws InterruptedException {
		LOGGER.info("Inside getEmployeeAddress -------- {} " + Instant.now(),
				Thread.currentThread().getName() + Thread.currentThread().getId());
		Thread.sleep(5000L);
		AddressVO address = restTemplate.getForObject("http://localhost:11401/employee/address", AddressVO.class);
		LOGGER.info("{} is complete -------- " + Instant.now(),
				Thread.currentThread().getName() + Thread.currentThread().getId());
		return CompletableFuture.completedFuture(address);
	}

	@Async("asyncExecutor")
	public CompletableFuture<EmployeeVO> getEmployeeProfile() throws InterruptedException {
		LOGGER.info("Inside getEmployeeProfile -------- {} " + Instant.now(),
				Thread.currentThread().getName() + Thread.currentThread().getId());
		Thread.sleep(1000L);
		EmployeeVO employee = restTemplate.getForObject("http://localhost:11401/employee/profile", EmployeeVO.class);
		LOGGER.info("{} is complete --------" + Instant.now(),
				Thread.currentThread().getName() + Thread.currentThread().getId());
		return CompletableFuture.completedFuture(employee);
	}

	@Async("asyncExecutor")
	public CompletableFuture<DepartmentVO> getEmployeeDept() throws InterruptedException {
		LOGGER.info("Inside getEmployeeDept -------- {} " + Instant.now(),
				Thread.currentThread().getName() + Thread.currentThread().getId());
		Thread.sleep(3000L);
		DepartmentVO dept = restTemplate.getForObject("http://localhost:11401/employee/dept", DepartmentVO.class);
		LOGGER.info("{} is complete --------" + Instant.now(),
				Thread.currentThread().getName() + Thread.currentThread().getId());
		return CompletableFuture.completedFuture(dept);
	}
}