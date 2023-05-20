package com.app.clinic.dto;

import java.math.BigDecimal;

public class CreatePataintModel {

	String pataintName;
	String pataintPhone;
	BigDecimal pataintId;
	public String getPataintName() {
		return pataintName;
	}
	public void setPataintName(String pataintName) {
		this.pataintName = pataintName;
	}
	public String getPataintPhone() {
		return pataintPhone;
	}
	public void setPataintPhone(String pataintPhone) {
		this.pataintPhone = pataintPhone;
	}
	public BigDecimal getPataintId() {
		return pataintId;
	}
	public void setPataintId(BigDecimal pataintId) {
		this.pataintId = pataintId;
	}
	
}
