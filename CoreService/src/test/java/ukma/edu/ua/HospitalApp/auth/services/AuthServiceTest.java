package ukma.edu.ua.HospitalApp.auth.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Date;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;

import ukma.edu.ua.HospitalApp.auth.dto.LoginBody;
import ukma.edu.ua.HospitalApp.auth.dto.RegisterDoctorBody;
import ukma.edu.ua.HospitalApp.auth.dto.RegisterPatientBody;
import ukma.edu.ua.HospitalApp.doctor.DoctorService;
import ukma.edu.ua.HospitalApp.common.entities.User;
import ukma.edu.ua.HospitalApp.common.entities.Doctor.DoctorType;
import ukma.edu.ua.HospitalApp.common.entities.User.Role;
import ukma.edu.ua.HospitalApp.common.exceptions.BadRequestException;
import ukma.edu.ua.HospitalApp.common.security.JWTService;
import ukma.edu.ua.HospitalApp.common.security.JWTService.TokenResponse;
import ukma.edu.ua.HospitalApp.patient.PatientService;
import ukma.edu.ua.HospitalApp.user.UserService;

@ExtendWith({ MockitoExtension.class })
public class AuthServiceTest {
	@Autowired
	@InjectMocks
	private AuthService authService;

	@Mock
	private AuthenticationConfiguration auth;

	@Mock
	private JWTService jwtService;

	@Mock
	private UserDetailsService userDetailsService;

	@Mock
	private UserService userService;

	@Mock
	private PatientService patientService;

	@Mock
	private DoctorService doctorService;

	@Test
	@DisplayName("smoke")
	public void smoke() {
		assertNotNull(authService);
		assertNotNull(auth);
		assertNotNull(jwtService);
		assertNotNull(userDetailsService);
		assertNotNull(userService);
		assertNotNull(patientService);
		assertNotNull(doctorService);
	}

	@Test
	@DisplayName("should authenticate user")
	public void auth() throws Exception {
		AuthenticationManager authenticationManagerMock = mock(AuthenticationManager.class);
		when(auth.getAuthenticationManager()).thenReturn(authenticationManagerMock);
		when(userDetailsService.loadUserByUsername(anyString())).thenReturn(null);
		when(auth.getAuthenticationManager().authenticate(any())).thenReturn(null);

		assertDoesNotThrow(() -> authService.authenticate("", ""));
	}

	@Test
	@DisplayName("should not authenticate a user if userDetailsService throws an error")
	public void authDetailsError() throws Exception {
		when(userDetailsService.loadUserByUsername(anyString())).thenThrow(RuntimeException.class);

		assertThrows(BadRequestException.class, () -> authService.authenticate("", ""));
	}

	@Test
	@DisplayName("should not authenticate a user if user is not found")
	public void authError() throws Exception {
		AuthenticationManager authenticationManagerMock = mock(AuthenticationManager.class);
		when(auth.getAuthenticationManager()).thenReturn(authenticationManagerMock);
		when(userDetailsService.loadUserByUsername(anyString())).thenReturn(null);
		when(auth.getAuthenticationManager().authenticate(any())).thenThrow(RuntimeException.class);

		assertThrows(BadRequestException.class, () -> authService.authenticate("", ""));
	}

	@Test
	@DisplayName("should login user in")
	public void login() throws Exception {
		var res = TokenResponse.builder().accessToken("token").build();

		AuthenticationManager authenticationManagerMock = mock(AuthenticationManager.class);
		when(auth.getAuthenticationManager()).thenReturn(authenticationManagerMock);
		when(userDetailsService.loadUserByUsername(anyString())).thenReturn(null);
		when(auth.getAuthenticationManager().authenticate(any())).thenReturn(null);
		when(jwtService.generateToken(anyString())).thenReturn(res);

		var loginBody = LoginBody.builder().email("email").password("password").build();
		var result = authService.login(loginBody);
		assertEquals(res, result);
		verify(userDetailsService, times(1)).loadUserByUsername(loginBody.getEmail());
		verify(auth.getAuthenticationManager(), times(1)).authenticate(any());
	}

	@Test
	@DisplayName("should register a patient")
	public void registerPatient() throws Exception {
		var body = RegisterPatientBody.builder()
				.email("email")
				.password("password")
				.firstName("name")
				.lastName("name")
				.address("addr")
				.birthDate(new Date())
				.passportNumber("SS111111").build();

		var res = TokenResponse.builder().accessToken("token").build();
		when(jwtService.generateToken(anyString())).thenReturn(res);
		when(userService.registerNewUser(any(), any(), any()))
				.thenReturn(User
						.builder()
						.email(body.getEmail())
						.id(Long.valueOf(1))
						.build());

		var result = authService.registerPatient(body);
		assertEquals(res, result);
		verify(userService, times(1)).registerNewUser(body.getEmail(), body.getPassword(), Role.PATIENT);
		verify(patientService, times(1)).savePatientData(any());
		verify(jwtService, times(1)).generateToken(body.getEmail());
	}

	@Test
	@DisplayName("should register a doctor")
	public void registerDoctor() throws Exception {
		var body = RegisterDoctorBody.builder()
				.email("email")
				.password("password")
				.firstName("name")
				.lastName("name")
				.birthDate(new Date())
				.doctorType(DoctorType.DENTIST)
				.hospitalId(Long.valueOf(1))
				.build();

		var res = TokenResponse.builder().accessToken("token").build();
		when(jwtService.generateToken(anyString())).thenReturn(res);
		when(userService.registerNewUser(any(), any(), any()))
				.thenReturn(User
						.builder()
						.email(body.getEmail())
						.id(Long.valueOf(1))
						.build());

		var result = authService.registerDoctor(body);
		assertEquals(res, result);
		verify(userService, times(1)).registerNewUser(body.getEmail(), body.getPassword(), Role.DOCTOR);
		verify(doctorService, times(1)).saveDoctorData(any());
		verify(jwtService, times(1)).generateToken(body.getEmail());
	}
}
