package com.app.clinic.dto;

import java.math.BigDecimal;
import java.util.Date;

public class AppiontementResponse {
	

	private BigDecimal appiontementId;
	private String doctorName;
	private String patientName;
	private Date appiontementDate;
	private String employeeName;
	
	public BigDecimal getAppiontementId() {
		return appiontementId;
	}
	public void setAppiontementId(BigDecimal appiontementId) {
		this.appiontementId = appiontementId;
	}
	public String getDoctorName() {
		return doctorName;
	}
	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}
	public String getPatientName() {
		return patientName;
	}
	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}
	public Date getAppiontementDate() {
		return appiontementDate;
	}
	public void setAppiontementDate(Date appiontementDate) {
		this.appiontementDate = appiontementDate;
	}
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	public AppiontementResponse(String doctorName, String patientName, Date appiontementDate ,String employeeName) {
		super();
		this.doctorName = doctorName;
		this.patientName = patientName;
		this.appiontementDate = appiontementDate;
		this.employeeName=employeeName;
	}
	public AppiontementResponse() {
		super();
	}
	
}
