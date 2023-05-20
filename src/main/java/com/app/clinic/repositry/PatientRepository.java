package com.app.clinic.repositry;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.app.clinic.model.Patient;

public interface PatientRepository extends CrudRepository<Patient, BigDecimal> {

	public List<Patient> findAll();

	List<Patient> findByPatientPhone(String patientPhone);

	List<Patient> findByPatientName(String patientName);

	List<Patient> findByPatientId(BigDecimal patientId);

	@Query(value = "SELECT * FROM MEDICAL_PATIENT WHERE LOWER(PATIENT_NAME)=?1", nativeQuery = true)
	public Patient getByPatiantName(String patientName);

}
