package com.app.clinic.dto;

import java.math.BigDecimal;

public class MedicalAppiontementRequest {
	

	private BigDecimal patientId;
	private BigDecimal doctorId;
	private String appiontementDate;
	private BigDecimal employeeId;
	public BigDecimal getPatientId() {
		return patientId;
	}
	public void setPatientId(BigDecimal patientId) {
		this.patientId = patientId;
	}
	public BigDecimal getDoctorId() {
		return doctorId;
	}
	public void setDoctorId(BigDecimal doctorId) {
		this.doctorId = doctorId;
	}
	public String getAppiontementDate() {
		return appiontementDate;
	}
	public void setAppiontementDate(String appiontementDate) {
		this.appiontementDate = appiontementDate;
	}
	
	
	public BigDecimal getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(BigDecimal employeeId) {
		this.employeeId = employeeId;
	}
	public MedicalAppiontementRequest(BigDecimal patientId, BigDecimal doctorId, String appiontementDate,BigDecimal employeeId) {
		super();
		this.patientId = patientId;
		this.doctorId = doctorId;
		this.appiontementDate = appiontementDate;
		this.employeeId=employeeId;
	}
	public MedicalAppiontementRequest() {
		super();
	}
	

}
