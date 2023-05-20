package com.app.clinic.controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.clinic.Enum.CancellationReasonEnum;
import com.app.clinic.dto.AppiontementResponse;
import com.app.clinic.dto.CreateDoctorModel;
import com.app.clinic.dto.CreateEmployeeModel;
import com.app.clinic.dto.CreatePataintModel;
import com.app.clinic.dto.CreationResponse;
import com.app.clinic.dto.MedicalAppiontementRequest;
import com.app.clinic.model.Doctor;
import com.app.clinic.model.Employee;
import com.app.clinic.model.Patient;
import com.app.clinic.service.ClinicService;
import com.app.clinic.util.Constants;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/clinic")
public class MedicalClinicController {

	@Autowired
	ClinicService clinicService;

	@GetMapping("/all-doctors")
	@ApiOperation(value = "Get All Doctors details from the System")
	public ResponseEntity<List<Doctor>> getDoctors() {

		return ResponseEntity.status(HttpStatus.OK).body(clinicService.getDoctorsList());
	}

	@PostMapping("/getdoctor")
	@ApiOperation(value = "Get Doctor Details from the System")
	public ResponseEntity<List<Doctor>> getDoctor(@RequestBody CreateDoctorModel createDoctorModel) {

		return ResponseEntity.status(HttpStatus.OK).body(clinicService.getDoctor(createDoctorModel));
	}

	@PostMapping("/save-doctor")
	@ApiOperation(value = "Create New Doctor In the System")
	public ResponseEntity<CreationResponse> saveDoctor(@RequestBody CreateDoctorModel createDoctorModel) {
		CreationResponse doctor = clinicService.createDoctor(createDoctorModel);
		if (doctor.getStatus().equalsIgnoreCase(Constants.SUCCESS)) {
			return ResponseEntity.status(HttpStatus.OK).body(doctor);
		} else {
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(doctor);
		}
	}

	@GetMapping("/all-pataints")
	@ApiOperation(value = "et All Pataints details from the System ")
	public ResponseEntity<List<Patient>> getAllPataint() {
		List<Patient> patients = clinicService.getAllPataint();
		return ResponseEntity.status(HttpStatus.OK).body(patients);
	}

	@PostMapping("/save-pataint")
	@ApiOperation(value = "Create New Pataint In the System")
	public ResponseEntity<CreationResponse> savePataint(@RequestBody CreatePataintModel createPataintModel) {
		CreationResponse pataint = clinicService.createPaitant(createPataintModel);
		if (pataint.getStatus().equalsIgnoreCase(Constants.SUCCESS)) {
			return ResponseEntity.status(HttpStatus.OK).body(pataint);
		} else {
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(pataint);
		}
	}

	@PostMapping("/pataint")
	@ApiOperation(value = "Get Pataint Details from the System")
	public ResponseEntity<List<Patient>> getPataint(@RequestBody CreatePataintModel createPataintModel) {
		List<Patient> patients = clinicService.getPatient(createPataintModel);
		return ResponseEntity.status(HttpStatus.OK).body(patients);
	}

	@GetMapping("/all-employee")
	@ApiOperation(value = "Get All Employees details from the System")
	public ResponseEntity<List<Employee>> getAllEmployees() {
		List<Employee> employees = clinicService.getAllEmployee();
		return ResponseEntity.status(HttpStatus.OK).body(employees);
	}

	@PostMapping("/save-employee")
	@ApiOperation(value = "Create New Employee In the System")
	public ResponseEntity<CreationResponse> saveEmployee(@RequestBody CreateEmployeeModel createEmployeeModel) {
		CreationResponse employee = clinicService.createEmployee(createEmployeeModel);
		if (employee.getStatus().equalsIgnoreCase(Constants.SUCCESS)) {
			return ResponseEntity.status(HttpStatus.OK).body(employee);
		} else {
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(employee);
		}
	}

	@PostMapping("/employee")
	@ApiOperation(value = "Get Employee Details from the System")
	public ResponseEntity<List<Employee>> getEmployee(@RequestBody CreateEmployeeModel createEmployeeModel) {
		List<Employee> employees = clinicService.getEmployee(createEmployeeModel);
		return ResponseEntity.status(HttpStatus.OK).body(employees);
	}

	@GetMapping("/getappiontement-today")
	@ApiOperation(value = "lists all today's appointments")
	public ResponseEntity<List<AppiontementResponse>> getTodayAppiontement() {
		List<AppiontementResponse> appiontementResponses = clinicService.getAppiontementForToday();
		return ResponseEntity.status(HttpStatus.OK).body(appiontementResponses);
	}

	@PostMapping("/save-appiontement")
	@ApiOperation(value = "add new appointments.")
	public ResponseEntity<CreationResponse> saveAppionetment(
			@RequestBody MedicalAppiontementRequest medicalAppiontementModel) {
		CreationResponse appiontementModel = clinicService.createAppiontement(medicalAppiontementModel);
		if (appiontementModel.getStatus().equalsIgnoreCase(Constants.SUCCESS)) {
			return ResponseEntity.status(HttpStatus.OK).body(appiontementModel);
		} else {
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(appiontementModel);
		}
	}

	@PostMapping("/cancel-appiontement/{appiontementId}/cancelReason/{cancelReason}/employee/{employeeId}")
	@ApiOperation(value = "Admin can cancel and log the reason.")
	public ResponseEntity<CreationResponse> cancelAppiontementById(
			@RequestParam(required = true) BigDecimal appiontementId,
			@RequestParam(required = true) CancellationReasonEnum cancelReason,
			@RequestParam(required = true) BigDecimal employeeId) {
		CreationResponse appiontementModel = clinicService.cancelAppiontementById(appiontementId, cancelReason,
				employeeId);
		if (appiontementModel.getStatus().equalsIgnoreCase(Constants.CANCELLED_SUCCESSFULLY)) {
			return ResponseEntity.status(HttpStatus.OK).body(appiontementModel);
		} else {
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(appiontementModel);
		}
	}

	@GetMapping("/getappiontement/date/{strDate}")
	@ApiOperation(value = "filter appointments by patient name")
	public ResponseEntity<List<AppiontementResponse>> getAppiontementByDate(@RequestParam(required = true) String strDate) {
		List<AppiontementResponse> appiontementResponses = clinicService.getAppiontementByDate(strDate);
		return ResponseEntity.status(HttpStatus.OK).body(appiontementResponses);
	}

	@GetMapping("/getappiontement/pataint-name/{patainName}")
	@ApiOperation(value = "preview patient appointments history.")
	public ResponseEntity<List<AppiontementResponse>> getAppiontementByPatientName(
			@RequestParam(required = true) String patainName) {
		List<AppiontementResponse> appiontementResponses = clinicService.getAppiontementByPatientName(patainName);
		return ResponseEntity.status(HttpStatus.OK).body(appiontementResponses);
	}

	@GetMapping("/getappiontement/name-history/{patainName}")
	@ApiOperation(value = "Admin can filter appointments by date (future or history)")
	public ResponseEntity<List<AppiontementResponse>> getPatientAppiontementHistory(
			@RequestParam(required = true) String patainName) {
		List<AppiontementResponse> appiontementResponses = clinicService.getPatientAppiontementHistory(patainName);
		return ResponseEntity.status(HttpStatus.OK).body(appiontementResponses);
	}

}
