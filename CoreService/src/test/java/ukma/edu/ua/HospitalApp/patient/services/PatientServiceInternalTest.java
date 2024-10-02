package ukma.edu.ua.HospitalApp.patient.services;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import ukma.edu.ua.HospitalApp.config.TestContainersBase;
import ukma.edu.ua.HospitalApp.patient.repositories.PatientDetailsRepository;

public class PatientServiceInternalTest extends TestContainersBase {
	@Autowired
	private PatientDetailsRepository patientDetailsRepository;

	private PatientServiceInternal patientServiceInternal;

	@BeforeEach
	public void beforeEach() {
		patientServiceInternal = new PatientServiceInternal(patientDetailsRepository);
	}

	@Test
	@DisplayName("smoke")
	public void smoke() {
		assertNotNull(patientDetailsRepository);
		assertNotNull(patientServiceInternal);
	}

	@Test
	@DisplayName("should get patient's data")
	public void getPatientData() {
		var res = patientServiceInternal.getPatientData(11);
		assertNotNull(res);
	}

	@Test
	@DisplayName("should return null if no patient's data found")
	public void getPatientDataNull() {
		var res = patientServiceInternal.getPatientData(11111);
		assertNull(res);
	}
}
