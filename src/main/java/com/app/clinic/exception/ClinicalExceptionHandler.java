package com.app.clinic.exception;

import java.time.ZonedDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class ClinicalExceptionHandler {


	@ExceptionHandler(value = {ClinicalException.class})
	public ResponseEntity<Object> handleDroneException(ClinicalException e){
		
		HttpStatus httpStatus=HttpStatus.BAD_REQUEST;
		
		Exception exception = new Exception(
				e.getMessage(),
				httpStatus,
				ZonedDateTime.now()
				); 
		return new ResponseEntity<Object>(exception,httpStatus);
	}
}
