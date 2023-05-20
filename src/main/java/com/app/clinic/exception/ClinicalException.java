package com.app.clinic.exception;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class ClinicalException extends RuntimeException{
	

	private Map<String, Object> extensions=new HashMap<>();
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String message;
	
	
	
	public ClinicalException(String message,BigDecimal id) {
		super(message);
		this.message = message;
		extensions.put("invalidId", id);
		return;
	}
	
	public ClinicalException(String message) {
		super(message);
		this.message = message;
		extensions.put("invalidId",null);
		return;
	}
	
	public ClinicalException(String message,Throwable cause) {
		super(message,cause);
		this.message = message;
		extensions.put("invalidId",null);
		return;
	}


}
