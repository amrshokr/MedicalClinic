package com.app.clinic.service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.clinic.Enum.CancellationReasonEnum;
import com.app.clinic.dto.AppiontementResponse;
import com.app.clinic.dto.CreateDoctorModel;
import com.app.clinic.dto.CreateEmployeeModel;
import com.app.clinic.dto.CreatePataintModel;
import com.app.clinic.dto.CreationResponse;
import com.app.clinic.dto.MedicalAppiontementRequest;
import com.app.clinic.exception.ClinicalException;
import com.app.clinic.model.Doctor;
import com.app.clinic.model.Employee;
import com.app.clinic.model.MedicalAppiontement;
import com.app.clinic.model.Patient;
import com.app.clinic.repositry.DoctorRepositry;
import com.app.clinic.repositry.EmployeeRepositry;
import com.app.clinic.repositry.MedicalAppiontementReposirty;
import com.app.clinic.repositry.PatientRepository;
import com.app.clinic.util.Constants;

@Service
public class ClinicService {

	private static final Logger log = LoggerFactory.getLogger(ClinicService.class);

	@Autowired
	DoctorRepositry doctorRepositry;

	@Autowired
	EmployeeRepositry employeeRepositry;

	@Autowired
	MedicalAppiontementReposirty appiontementReposirty;

	@Autowired
	PatientRepository patientRepository;

	public List<Doctor> getDoctorsList() {
		return doctorRepositry.findAll();
	}

	public CreationResponse createDoctor(CreateDoctorModel createDoctorModel) {
		CreationResponse appiontementModel = new CreationResponse();
		try {
			createDoctorValidation(createDoctorModel);
			Doctor doctor = new Doctor();
			doctor.setDoctorName(createDoctorModel.getDoctorName());
			doctor.setDoctorPhone(createDoctorModel.getDoctorPhone());
			doctorRepositry.save(doctor);
			appiontementModel.setId(doctor.getDoctorId());
			appiontementModel.setStatus(Constants.SUCCESS);
			log.info("Created Doctor name: " + createDoctorModel.getDoctorName() + " Id :" + doctor.getDoctorId());
		} catch (Exception e) {
			log.info("Creation Doctor name: " + createDoctorModel.getDoctorName() + "Reason: "
					+ e.getLocalizedMessage());
			appiontementModel.setError(e.getLocalizedMessage());
			appiontementModel.setStatus(Constants.FAIL);
		}
		return appiontementModel;
	}

	public void createDoctorValidation(CreateDoctorModel createDoctorModel) {
		if (createDoctorModel != null && createDoctorModel.getDoctorName() != null
				&& createDoctorModel.getDoctorPhone() != null) {
			List<Doctor> doctors = doctorRepositry.findByDoctorName(createDoctorModel.getDoctorName());
			if (doctors != null && !doctors.isEmpty()) {
				throw new ClinicalException("Doctor Name Already exist with Id" + doctors.get(0).getDoctorId());
			}
			doctors = doctorRepositry.findByDoctorPhone(createDoctorModel.getDoctorPhone());
			if (doctors != null && !doctors.isEmpty()) {
				throw new ClinicalException("Doctor Phone Already exist with Id" + doctors.get(0).getDoctorId());
			}

		} else {
			throw new ClinicalException("Please Enter the Required Data!");
		}
	}

	public List<Doctor> getDoctor(CreateDoctorModel createDoctorModel) {
		if (createDoctorModel != null) {
			if (createDoctorModel.getDoctorName() != null || createDoctorModel.getDoctorPhone() != null
					|| createDoctorModel.getDoctorId() != null) {
				List<Doctor> doctor = new ArrayList<>();
				if (createDoctorModel.getDoctorName() != null) {
					doctor = doctorRepositry.findByDoctorName(createDoctorModel.getDoctorName());
				} else if (createDoctorModel.getDoctorPhone() != null) {
					doctor = doctorRepositry.findByDoctorPhone(createDoctorModel.getDoctorPhone());
				} else if (createDoctorModel.getDoctorId() != null) {
					doctor = doctorRepositry.findByDoctorId(createDoctorModel.getDoctorId());
				}

				return doctor;
			} else {
				throw new ClinicalException("Please Enter any Field to get Doctor!");
			}
		} else {
			throw new ClinicalException("Please Enter the Required Data!");
		}
	}

	public List<Patient> getAllPataint() {
		List<Patient> patients = patientRepository.findAll();
		return patients;
	}

	public CreationResponse createPaitant(CreatePataintModel createPataintModel) {
		CreationResponse appiontementModel = new CreationResponse();
		try {
			createPataintValidation(createPataintModel);
			Patient patient = new Patient();
			patient.setPatientName(createPataintModel.getPataintName());
			patient.setPatientPhone(createPataintModel.getPataintPhone());
			patient.setCreatedDate(new Date());
			patientRepository.save(patient);
			appiontementModel.setId(patient.getPatientId());
			appiontementModel.setStatus(Constants.SUCCESS);
			log.info("Created Patient name: " + patient.getPatientName() + " Id :" + patient.getPatientId());
		} catch (Exception e) {
			appiontementModel.setError(e.getLocalizedMessage());
			appiontementModel.setStatus(Constants.FAIL);
		}
		return appiontementModel;

	}

	public void createPataintValidation(CreatePataintModel createPataintModel) {
		if (createPataintModel != null && createPataintModel.getPataintName() != null
				&& createPataintModel.getPataintPhone() != null) {
			List<Patient> patient = patientRepository.findByPatientName(createPataintModel.getPataintName());
			if (patient != null && !patient.isEmpty()) {
				throw new ClinicalException("Patient Name Already exist with Id" + patient.get(0).getPatientId());
			}
			patient = patientRepository.findByPatientPhone(createPataintModel.getPataintPhone());
			if (patient != null && !patient.isEmpty()) {
				throw new ClinicalException("Doctor Phone Already exist with Id" + patient.get(0).getPatientId());
			}

		} else {
			throw new ClinicalException("Please Enter the Required Data!");
		}
	}

	public List<Patient> getPatient(CreatePataintModel createPataintModel) {
		if (createPataintModel != null) {
			if (createPataintModel.getPataintName() != null || createPataintModel.getPataintPhone() != null
					|| createPataintModel.getPataintId() != null) {
				List<Patient> patient = new ArrayList<>();
				if (createPataintModel.getPataintName() != null) {
					patient = patientRepository.findByPatientName(createPataintModel.getPataintName());
				} else if (createPataintModel.getPataintPhone() != null) {
					patient = patientRepository.findByPatientPhone(createPataintModel.getPataintPhone());
				} else if (createPataintModel.getPataintId() != null) {
					patient = patientRepository.findByPatientId(createPataintModel.getPataintId());
				}
				if (patient != null && !patient.isEmpty()) {
					return patient;
				} else {
					throw new ClinicalException("No Record Found!");
				}
			} else {
				throw new ClinicalException("Please Enter any Field to get Pataint!");
			}

		} else {
			throw new ClinicalException("Please Enter the Required Data!");
		}
	}

	public List<Employee> getAllEmployee() {
		List<Employee> employees = employeeRepositry.findAll();
		return employees;
	}

	public CreationResponse createEmployee(CreateEmployeeModel createEmployeeModel) {
		CreationResponse appiontementModel = new CreationResponse();
		try {
			createEmployeeValidation(createEmployeeModel);
			Employee employee = new Employee();
			employee.setEmployeeName(createEmployeeModel.getEmployeeName());
			employee.setEmployeePhone(createEmployeeModel.getEmployeePhone());
			employee.setRoleId(createEmployeeModel.getRoleId());
			if (createEmployeeModel.getRoleId().equals(BigDecimal.ONE)) {
				employee.setRoleName(Constants.ADMIN);
			} else {
				employee.setRoleName(Constants.OTHER);
			}
			employeeRepositry.save(employee);
			appiontementModel.setId(employee.getEmployeeId());
			appiontementModel.setStatus(Constants.SUCCESS);
			log.info("Created Employee name: " + employee.getEmployeeName() + " Id :" + employee.getEmployeeId());
		} catch (Exception e) {
			appiontementModel.setError(e.getLocalizedMessage());
			appiontementModel.setStatus(Constants.FAIL);
		}
		return appiontementModel;

	}

	public void createEmployeeValidation(CreateEmployeeModel createEmployeeModel) {
		if (createEmployeeModel != null && createEmployeeModel.getEmployeeName() != null
				&& createEmployeeModel.getEmployeePhone() != null && createEmployeeModel.getRoleId() != null) {
			List<Employee> employees = employeeRepositry.findByEmployeeName(createEmployeeModel.getEmployeeName());
			if (employees != null && !employees.isEmpty()) {
				throw new ClinicalException("Employee Name Already exist with Id" + employees.get(0).getEmployeeId());
			}
			employees = employeeRepositry.findByEmployeePhone(createEmployeeModel.getEmployeePhone());
			if (employees != null && !employees.isEmpty()) {
				throw new ClinicalException("Employee Phone Already exist with Id" + employees.get(0).getEmployeeId());
			}

		} else {
			throw new ClinicalException("Please Enter the Required Data!");
		}
	}

	public List<Employee> getEmployee(CreateEmployeeModel employeeModel) {
		if (employeeModel != null) {
			if (employeeModel.getEmployeeId() != null || employeeModel.getEmployeeName() != null
					|| employeeModel.getEmployeePhone() != null) {
				List<Employee> employees = new ArrayList<>();
				if (employeeModel.getEmployeeName() != null) {
					employees = employeeRepositry.findByEmployeeName(employeeModel.getEmployeeName());
				} else if (employeeModel.getEmployeePhone() != null) {
					employees = employeeRepositry.findByEmployeeName(employeeModel.getEmployeePhone());
				} else if (employeeModel.getEmployeeId() != null) {
					employees = employeeRepositry.findByEmployeeId(employeeModel.getEmployeeId());
				}
				if (employees != null && !employees.isEmpty()) {
					return employees;
				} else {
					throw new ClinicalException("No Record Found!");
				}
			} else {
				throw new ClinicalException("Please Enter any Field to get Employee!");
			}

		} else {
			throw new ClinicalException("Please Enter the Required Data!");
		}
	}

	public List<AppiontementResponse> getAppiontementForToday() {
		List<AppiontementResponse> appiontementResponsList = new ArrayList<>();
		List<MedicalAppiontement> appiontements = appiontementReposirty.getAppiontementForToday();
		if (appiontements != null & !appiontements.isEmpty()) {
			appiontements.forEach(k -> {
				AppiontementResponse appiontementResponse = new AppiontementResponse();
				appiontementResponse.setAppiontementId(k.getAppiontementId());
				appiontementResponse.setAppiontementDate(k.getAppiontementDate());
				appiontementResponse.setDoctorName(k.getDoctor().getDoctorName());
				appiontementResponse.setPatientName(k.getPatient().getPatientName());
				appiontementResponse.setEmployeeName(k.getEmployee().getEmployeeName());
				appiontementResponsList.add(appiontementResponse);
			});
		} else {
			throw new ClinicalException("No Record Found!");
		}
		return appiontementResponsList;
	}

	public CreationResponse createAppiontement(MedicalAppiontementRequest appiontementRequest) {
		CreationResponse appiontementModel = new CreationResponse();
		try {
			validateCreateAppiontement(appiontementRequest);
			MedicalAppiontement appiontement = new MedicalAppiontement();
			Patient patient = new Patient();
			patient.setPatientId(appiontementRequest.getPatientId());
			appiontement.setPatient(patient);
			Doctor doctor = new Doctor();
			doctor.setDoctorId(appiontementRequest.getDoctorId());
			appiontement.setDoctor(doctor);
			Employee employee = new Employee();
			employee.setEmployeeId(appiontementRequest.getEmployeeId());
			appiontement.setEmployee(employee);
			appiontement.setIsActive(Constants.YES);
			appiontement.setCreatedDate(new Date());
			if (appiontementRequest.getAppiontementDate() != null) {
				Date date = new SimpleDateFormat("dd/MM/yyyy").parse(appiontementRequest.getAppiontementDate());
				Date todayDate = new SimpleDateFormat("dd/MM/yyyy").parse(appiontementRequest.getAppiontementDate());
				if (date.before(todayDate)) {
					throw new ClinicalException("Pervious Date Not Allowed!");
				}
				appiontement.setAppiontementDate(date);
			}
			appiontementReposirty.save(appiontement);
			appiontementModel.setId(appiontement.getAppiontementId());
			appiontementModel.setStatus(Constants.SUCCESS);
			log.info("Created MedicalAppiontement  Id :" + appiontementModel.getId());
		} catch (Exception e) {
			appiontementModel.setError(e.getLocalizedMessage());
			appiontementModel.setStatus(Constants.FAIL);
		}
		return appiontementModel;
	}

	public void validateCreateAppiontement(MedicalAppiontementRequest appiontementRequest) {
		if (appiontementRequest != null && appiontementRequest.getDoctorId() != null
				&& appiontementRequest.getAppiontementDate() != null && appiontementRequest.getEmployeeId() != null
				&& appiontementRequest.getPatientId() != null) {
			List<Employee> employees = employeeRepositry.findByEmployeeId(appiontementRequest.getEmployeeId());
			if (employees != null && !employees.isEmpty()) {
				if (!employees.get(0).getRoleName().equalsIgnoreCase(Constants.ADMIN)) {
					throw new ClinicalException("User Not Allowed to Create Appiontement!");
				}
			} else {
				throw new ClinicalException("User Not Found!");
			}

		} else {
			throw new ClinicalException("Please Enter All Required Fields!");
		}

	}
	
	public CreationResponse cancelAppiontementById(BigDecimal appiontementId, CancellationReasonEnum cancelReason,BigDecimal employeeId) {
		CreationResponse appiontementModel = new CreationResponse();
		try {
		MedicalAppiontement medicalAppiontement = appiontementReposirty.findByAppiontementId(appiontementId);
		if (medicalAppiontement != null) {
			medicalAppiontement.setReason(cancelReason.name());
			medicalAppiontement.setIsActive(Constants.NO);
			medicalAppiontement.setUpdatedBy(employeeId);
			medicalAppiontement.setUpdatedDate(new Date());
			appiontementReposirty.save(medicalAppiontement);
			appiontementModel.setId(medicalAppiontement.getAppiontementId());
			appiontementModel.setStatus(Constants.CANCELLED_SUCCESSFULLY);
			log.info("Cancel MedicalAppiontement  Id :"+appiontementModel.getId());
		} else {
			throw new ClinicalException("No Record Found!");
		}
		} catch (Exception e) {
			appiontementModel.setError(e.getLocalizedMessage());
			appiontementModel.setStatus(Constants.FAIL);
		}
		return appiontementModel;
	}
	
	public List<AppiontementResponse> getAppiontementByDate(String strDate) {
		List<AppiontementResponse> appiontementResponses = new ArrayList<>();
		try {
		
		List<MedicalAppiontement> medicalAppiontementReposirties = new ArrayList<>();
		Date date = new SimpleDateFormat("dd/MM/yyyy").parse(strDate);
		medicalAppiontementReposirties = appiontementReposirty.getAppiontementByDate(date);

		if (medicalAppiontementReposirties != null && !medicalAppiontementReposirties.isEmpty()) {
			medicalAppiontementReposirties.forEach(k -> {
				AppiontementResponse appiontementResponse = new AppiontementResponse();
				appiontementResponse.setAppiontementId(k.getAppiontementId());
				appiontementResponse.setAppiontementDate(k.getAppiontementDate());
				appiontementResponse.setDoctorName(k.getDoctor().getDoctorName());
				appiontementResponse.setPatientName(k.getPatient().getPatientName());
				appiontementResponse.setEmployeeName(k.getEmployee().getEmployeeName());
				appiontementResponses.add(appiontementResponse);
			});

		} else {
			throw new ClinicalException("No Appiontement Found!");
		}
	} catch (Exception e) {
		log.info("Error getAppiontementByDate "+e.getLocalizedMessage());
		throw new ClinicalException("Error getAppiontementByDate "+e.getLocalizedMessage());
	}
		return appiontementResponses;

	}
	
	public List<AppiontementResponse> getAppiontementByPatientName(String patainName) {
		List<AppiontementResponse> appiontementResponses = new ArrayList<>();
		Patient patient = patientRepository.getByPatiantName(patainName.toLowerCase().trim());
		if (patient != null) {
			List<MedicalAppiontement> medicalAppiontementReposirties = new ArrayList<>();
			
				medicalAppiontementReposirties = appiontementReposirty.findByPatiant(patient.getPatientId());

			if (medicalAppiontementReposirties != null && !medicalAppiontementReposirties.isEmpty()) {
				medicalAppiontementReposirties.forEach(k -> {
					AppiontementResponse appiontementResponse = new AppiontementResponse();
					appiontementResponse.setAppiontementId(k.getAppiontementId());
					appiontementResponse.setAppiontementDate(k.getAppiontementDate());
					appiontementResponse.setDoctorName(k.getDoctor().getDoctorName());
					appiontementResponse.setPatientName(k.getPatient().getPatientName());
					appiontementResponse.setEmployeeName(k.getEmployee().getEmployeeName());
					appiontementResponses.add(appiontementResponse);
				});
			} else {
				throw new ClinicalException("No Patiant Found");
			}
		} else {
			throw new ClinicalException("No Appiontement Found!");
		}
		return appiontementResponses;

	}
	
	public List<AppiontementResponse> getPatientAppiontementHistory(String patainName) {
		List<AppiontementResponse> appiontementResponses = new ArrayList<>();
		Patient patient = patientRepository.getByPatiantName(patainName.toLowerCase().trim());
		if (patient != null) {
			List<MedicalAppiontement> medicalAppiontementReposirties = new ArrayList<>();
			
				medicalAppiontementReposirties = appiontementReposirty.findByPatiantForHistory(patient.getPatientId());

			if (medicalAppiontementReposirties != null && !medicalAppiontementReposirties.isEmpty()) {
				medicalAppiontementReposirties.forEach(k -> {
					AppiontementResponse appiontementResponse = new AppiontementResponse();
					appiontementResponse.setAppiontementId(k.getAppiontementId());
					appiontementResponse.setAppiontementDate(k.getAppiontementDate());
					appiontementResponse.setDoctorName(k.getDoctor().getDoctorName());
					appiontementResponse.setPatientName(k.getPatient().getPatientName());
					appiontementResponse.setEmployeeName(k.getEmployee().getEmployeeName());
					appiontementResponses.add(appiontementResponse);
				});
			} else {
				throw new ClinicalException("No Patiant Found");
			}
		} else {
			throw new ClinicalException("No Appiontement Found!");
		}
		return appiontementResponses;

	}

}
