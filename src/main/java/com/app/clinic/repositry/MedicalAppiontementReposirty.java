package com.app.clinic.repositry;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.app.clinic.model.MedicalAppiontement;
import com.app.clinic.model.Patient;

public interface MedicalAppiontementReposirty extends CrudRepository<MedicalAppiontement, BigDecimal> {

	@Query(value = "SELECT * FROM MEDICAL_APPIONTEMENT  WHERE ISACTIVE='Y' and Date(APPIONTEMENT_DATE) = Date(SYSDATE())", nativeQuery = true)
	public List<MedicalAppiontement> getAppiontementForToday();

	public List<MedicalAppiontement> findByPatient(Patient patient);

	@Query(value = "SELECT * FROM MEDICAL_APPIONTEMENT  WHERE  ISACTIVE='Y' and PATIENT_ID=?1 and Date(APPIONTEMENT_DATE) < Date(SYSDATE())", nativeQuery = true)
	public List<MedicalAppiontement> findByPatiantForHistory(BigDecimal patientId);

	@Query(value = "SELECT * FROM MEDICAL_APPIONTEMENT  WHERE ISACTIVE='Y' and Date(APPIONTEMENT_DATE) =Date(?1)", nativeQuery = true)
	public List<MedicalAppiontement> getAppiontementByDate(Date date);

	public MedicalAppiontement findByAppiontementId(BigDecimal appiontementId);

	@Query(value = "SELECT * FROM MEDICAL_APPIONTEMENT  WHERE  ISACTIVE='Y' and PATIENT_ID=?1", nativeQuery = true)
	public List<MedicalAppiontement> findByPatiant(BigDecimal patientId);

}
