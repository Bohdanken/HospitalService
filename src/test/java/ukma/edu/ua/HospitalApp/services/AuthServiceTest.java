package ukma.edu.ua.HospitalApp.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.AuthenticationException;
import ukma.edu.ua.HospitalApp.api.auth.dto.LoginBody;
import ukma.edu.ua.HospitalApp.api.auth.dto.RegisterDoctorBody;
import ukma.edu.ua.HospitalApp.api.auth.dto.RegisterPatientBody;
import ukma.edu.ua.HospitalApp.config.auth.JWTService;
import ukma.edu.ua.HospitalApp.config.auth.JWTService.TokenResponse;
import ukma.edu.ua.HospitalApp.exceptions.errors.BadRequestException;
import ukma.edu.ua.HospitalApp.exceptions.errors.UniqueConstraintException;
import ukma.edu.ua.HospitalApp.models.User;
import ukma.edu.ua.HospitalApp.repositories.DoctorDetailsRepository;
import ukma.edu.ua.HospitalApp.repositories.PatientDetailsRepository;

@DisplayName("AuthService unit test")
@ExtendWith(MockitoExtension.class)
public class AuthServiceTest {
  @InjectMocks
  private AuthService authService;

  @Mock
  private AuthenticationConfiguration auth;

  @Mock
  private AuthenticationManager authManager;

  @Mock
  private JWTService jwtService;

  @Mock
  private UserService userService;

  @Mock
  private PatientDetailsRepository patientDetailsRepository;

  @Mock
  private DoctorDetailsRepository doctorDetailsRepository;

  private static LoginBody loginBody = new LoginBody();

  private static TokenResponse tokenResponse = new TokenResponse("TOKEN");

  @BeforeAll
  public static void setup() {
    loginBody.setEmail("some@email.com");
    loginBody.setPassword("1234");
  }

  @Test
  @Order(1)
  @DisplayName("It should return error if user credentials are not correct")
  public void shouldReturnError() throws Exception {
    when(auth.getAuthenticationManager()).thenReturn(authManager);
    when(authManager.authenticate(any())).thenThrow(new AuthenticationException("") {
    });

    assertThrows(BadRequestException.class, () -> authService.login(loginBody));
  }

  @Test
  @Order(2)
  @DisplayName("It should return token response when user is logged in")
  public void shouldReturnTokenOnLogin() throws Exception {
    when(auth.getAuthenticationManager()).thenReturn(authManager);
    when(authManager.authenticate(any())).thenReturn(null);
    when(jwtService.generateToken(anyString())).thenReturn(tokenResponse);

    var data = authService.login(loginBody);
    assertEquals(data, tokenResponse);
  }

  @Test
  @Order(3)
  @DisplayName("It should return token response when patient is being registered")
  public void shouldRegisterPatient() {
    var email = anyString();
    var password = anyString();
    var role = eq(User.Role.PATIENT);

    var body = new RegisterPatientBody();
    body.setEmail(email);
    body.setPassword(password);

    var user = User.builder()
        .email(email)
        .password(password)
        .role(role)
        .id(Long.valueOf(1))
        .build();

    when(userService.registerNewUser(email, password, role)).thenReturn(user);
    when(patientDetailsRepository.save(any())).thenReturn(null);
    when(jwtService.generateToken(anyString())).thenReturn(tokenResponse);

    var data = authService.registerPatient(body);
    assertEquals(data, tokenResponse);
  }

  @Test
  @Order(4)
  @DisplayName("It should throw an error if patient is already registered")
  public void patientUniqueError() {
    var body = new RegisterPatientBody();
    body.setEmail(anyString());
    body.setPassword(anyString());

    when(userService
        .registerNewUser(body.getEmail(), body.getPassword(), eq(User.Role.PATIENT)))
        .thenThrow(UniqueConstraintException.class);

    assertThrows(UniqueConstraintException.class,
        () -> authService.registerPatient(body));
  }

  @Test
  @Order(5)
  @DisplayName("It should return token response when doctor is registered")
  public void shouldRegisterDoctor() {
    var email = anyString();
    var password = anyString();
    var role = eq(User.Role.DOCTOR);

    var user = User.builder().email(email).password(password).role(role).build();
    var body = new RegisterDoctorBody();
    body.setEmail(email);
    body.setPassword(password);

    when(userService.registerNewUser(email, password, role))
        .thenReturn(user);
    when(doctorDetailsRepository.save(any())).thenReturn(null);
    when(jwtService.generateToken(anyString())).thenReturn(tokenResponse);

    var data = authService.registerDoctor(body);
    assertEquals(data, tokenResponse);
  }

  @Test
  @Order(6)
  @DisplayName("It should throw an error if doctor is already registered")
  public void doctorUniqueError() {
    var body = new RegisterDoctorBody();
    body.setEmail(anyString());
    body.setPassword(anyString());

    when(userService
        .registerNewUser(body.getEmail(), body.getPassword(), eq(User.Role.DOCTOR)))
        .thenThrow(UniqueConstraintException.class);

    assertThrows(UniqueConstraintException.class,
        () -> authService.registerDoctor(body));
  }
}
