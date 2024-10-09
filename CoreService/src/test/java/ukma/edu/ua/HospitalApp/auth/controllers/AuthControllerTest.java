package ukma.edu.ua.HospitalApp.auth.controllers;

import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Date;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import ukma.edu.ua.HospitalApp.common.entities.Doctor.DoctorType;
import ukma.edu.ua.HospitalApp.common.security.JWTService.TokenResponse;
import ukma.edu.ua.HospitalApp.auth.dto.LoginBody;
import ukma.edu.ua.HospitalApp.auth.dto.RegisterDoctorBody;
import ukma.edu.ua.HospitalApp.auth.dto.RegisterPatientBody;
import ukma.edu.ua.HospitalApp.auth.services.AuthService;

@SpringBootTest
@AutoConfigureMockMvc
public class AuthControllerTest {
	@Autowired
	private MockMvc mvc;

	@Autowired
	private ObjectMapper objectMapper;

	@MockBean
	private AuthService authService;

	@Test()
	@DisplayName("should allow /auth/login endpoint and respond with empty body error")
	public void login() throws Exception {
		mvc.perform(post("/api/auth/login")).andDo(print()).andExpect(status().isBadRequest());
	}

	@Test()
	@DisplayName("should validate /auth/login body and respond with error")
	public void validateLogin() throws Exception {
		var body = LoginBody.builder().email("email").password("pass").build();
		mvc.perform(post("/api/auth/login")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(body)))
				.andDo(print())
				.andExpect(status().isBadRequest());
	}

	@Test()
	@DisplayName("should validate /auth/login body and respond with access token")
	public void validateLoginAndRespond() throws Exception {
		var expectedResponse = TokenResponse.builder().accessToken("access-token").build();
		when(authService.login(any())).thenReturn(expectedResponse);

		var body = LoginBody.builder().email("email@email.com").password("pass").build();
		mvc.perform(post("/api/auth/login")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(body)))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.accessToken", is(expectedResponse.accessToken())));
	}

	@Test()
	@DisplayName("should allow /auth/register/doctor endpoint and respond with empty body error")
	public void registerDoctor() throws Exception {
		mvc.perform(post("/api/auth/register/doctor")).andDo(print()).andExpect(status().isBadRequest());
	}

	@Test()
	@DisplayName("should validate /auth/register/doctor body and respond with error")
	public void validateRegisterDoctor() throws Exception {
		var body = RegisterDoctorBody.builder().email("email").password("pass").build();
		mvc.perform(post("/api/auth/register/doctor")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(body)))
				.andDo(print())
				.andExpect(status().isBadRequest());
	}

	@Test()
	@DisplayName("should validate /auth/register/doctor endpoint and respond with access token")
	public void validateRegisterDoctorAndRespond() throws Exception {
		var expectedResponse = TokenResponse.builder().accessToken("access-token").build();
		when(authService.registerDoctor(any())).thenReturn(expectedResponse);

		var body = RegisterDoctorBody.builder()
				.email("email@email.com")
				.password("pass")
				.birthDate(new Date())
				.doctorType(DoctorType.DENTIST)
				.hospitalId(Long.valueOf(1))
				.firstName("Name")
				.lastName("Name")
				.build();

		mvc.perform(post("/api/auth/register/doctor")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(body)))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.accessToken", is(expectedResponse.accessToken())));
	}

	@Test()
	@DisplayName("should allow /auth/register/patient endpoint and respond with empty body error")
	public void registerPatient() throws Exception {
		mvc.perform(post("/api/auth/register/patient")).andDo(print()).andExpect(status().isBadRequest());
	}

	@Test()
	@DisplayName("should validate /auth/register/patient body and respond with error")
	public void validateRegisterPatient() throws Exception {
		var body = RegisterPatientBody.builder().email("email").password("pass").build();
		mvc.perform(post("/api/auth/register/patient")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(body)))
				.andDo(print())
				.andExpect(status().isBadRequest());
	}

	@Test()
	@DisplayName("should validate /auth/register/patient endpoint and respond with access token")
	public void validateRegisterPatientAndRespond() throws Exception {
		var expectedResponse = TokenResponse.builder().accessToken("access-token").build();
		when(authService.registerPatient(any())).thenReturn(expectedResponse);

		var body = RegisterPatientBody.builder()
				.email("email@email.com")
				.password("pass")
				.birthDate(new Date())
				.address("some address")
				.firstName("Name")
				.lastName("Name")
				.passportNumber("FF111111")
				.build();

		mvc.perform(post("/api/auth/register/patient")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(body)))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.accessToken", is(expectedResponse.accessToken())));
	}
}
