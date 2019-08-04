package com.example.check.async.rest.v2.eo;

import java.time.Instant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.check.async.rest.vo.EmployeeVO;

@Service
public class EmpProfileEO implements EmployeeDetailsEOv2{

	private static final Logger LOGGER = LoggerFactory.getLogger(EmpProfileEO.class);

	@Autowired
	private RestTemplate restTemplate;
	
	@Override
	@Async("simpleAsyncExecutor")
	public void invokeForEmployeeDetails() {
		LOGGER.info("Inside getEmployeeProfile -------- {} " + Instant.now(),
				Thread.currentThread().getName() + Thread.currentThread().getId());
		try {
			Thread.sleep(3000L);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		restTemplate.getForObject("http://localhost:11401/employee/profile", EmployeeVO.class);
		LOGGER.info("{} is complete -------- " + Instant.now(),
				Thread.currentThread().getName() + Thread.currentThread().getId());
		
	}

}
