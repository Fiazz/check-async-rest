package com.example.check.async.rest.v2.bo;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.check.async.rest.v2.eo.EmpAddressEO;
import com.example.check.async.rest.v2.eo.EmpDepartmentEO;
import com.example.check.async.rest.v2.eo.EmpProfileEO;
import com.example.check.async.rest.v2.eo.EmployeeDetailsEOv2;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class EmployeeDetailsBOv2 {

	private EmpAddressEO empAddressEO;
	private EmpDepartmentEO empDeptEO;
	private EmpProfileEO empProfileEO;

	private List<EmployeeDetailsEOv2> asyncTasks = Arrays.asList(empAddressEO, empDeptEO, empProfileEO);

	public void invokeEmployeeService() {
		asyncTasks.forEach(task -> task.invokeForEmployeeDetails());
	}
}
