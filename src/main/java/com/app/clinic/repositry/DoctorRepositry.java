package com.app.clinic.repositry;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.app.clinic.model.Doctor;

public interface DoctorRepositry extends CrudRepository<Doctor, BigDecimal>{
	
	List<Doctor> findAll();
	
	List<Doctor> findByDoctorName(String doctorName);
	
	List<Doctor> findByDoctorPhone(String doctorPhone);
	
	List<Doctor> findByDoctorId(BigDecimal doctorId);
	

}
