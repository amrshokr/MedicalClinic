package com.app.clinic.repositry;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.app.clinic.model.Employee;

public interface EmployeeRepositry extends CrudRepository<Employee, BigDecimal> {
	
	List<Employee> findAll();
	
	List<Employee> findByEmployeeId(BigDecimal employeeId);
	
	List<Employee> findByEmployeePhone(String employeePhone);
	
	List<Employee> findByEmployeeName(String employeeName);

}
