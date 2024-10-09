package ukma.edu.ua.HospitalApp.patient.controllers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Date;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import ukma.edu.ua.HospitalApp.patient.dto.UpdatePatientBody;
import ukma.edu.ua.HospitalApp.patient.services.PatientServiceInternal;
import ukma.edu.ua.HospitalApp.prescription.PrescriptionService;

@SpringBootTest
@AutoConfigureMockMvc
public class PatientControllerTest {
	@Autowired
	protected MockMvc mvc;

	@Autowired
	protected ObjectMapper objectMapper;

	@MockBean
	private PrescriptionService prescriptionService;

	@MockBean
	private PatientServiceInternal patientServiceInternal;

	@Test
	@DisplayName("Should update patient")
	@WithMockUser(authorities = { "PATIENT" })
	public void updatePatient() throws Exception {
		var body = UpdatePatientBody
				.builder()
				.address("addr")
				.birthDate(new Date())
				.firstName("name")
				.lastName("name")
				.passportNumber("SS111111")
				.build();

		mvc.perform(put("/api/patient/12345")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(body)))
				.andDo(print())
				.andExpect(status().isOk());

		verify(patientServiceInternal, times(1)).updatePatient(any(), eq(Long.valueOf(12345)));
	}

	@Test
	@DisplayName("Should return 401 on PUT /{id} when no user is found")
	public void updatePatientUnauthorized() throws Exception {
		mvc.perform(put("/api/patient/1"))
				.andDo(print())
				.andExpect(status().isUnauthorized());
	}

	@Test
	@DisplayName("Should return 403 on PUT /{id} when user doesn't have Patient role")
	@WithMockUser
	public void updatePatientForbidden() throws Exception {
		mvc.perform(put("/api/patient/1"))
				.andDo(print())
				.andExpect(status().isForbidden());
	}

	@Test
	@DisplayName("Should get prescriptions")
	@WithMockUser(authorities = { "PATIENT" })
	public void getPrescriptions() throws Exception {
		mvc.perform(get("/api/patient/12345/prescriptions")
				.contentType(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isOk());

		verify(prescriptionService, times(1)).getPatientPrescriptions(12345);
	}

	@Test
	@DisplayName("Should return 401 on GET /{id}/prescriptions when no user is found")
	public void getPrescriptionsUnauthorized() throws Exception {
		mvc.perform(get("/api/patient/1/prescriptions"))
				.andDo(print())
				.andExpect(status().isUnauthorized());
	}

	@Test
	@DisplayName("Should return 403 on GET /{id}/prescriptions when user doesn't have Patient role")
	@WithMockUser
	public void getPrescriptionsForbidden() throws Exception {
		mvc.perform(get("/api/patient/1/prescriptions"))
				.andDo(print())
				.andExpect(status().isForbidden());
	}
}
