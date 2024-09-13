package ukma.edu.ua.HospitalApp.controllers;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Calendar;
import java.util.GregorianCalendar;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import ukma.edu.ua.HospitalApp.ControllerTest;
import ukma.edu.ua.HospitalApp.auth.AuthController;
import ukma.edu.ua.HospitalApp.auth.LoginBody;
import ukma.edu.ua.HospitalApp.auth.RegisterDoctorBody;
import ukma.edu.ua.HospitalApp.auth.RegisterPatientBody;
import ukma.edu.ua.HospitalApp.auth.JWTService.TokenResponse;
import ukma.edu.ua.HospitalApp.exceptions.errors.BadRequestException;
import ukma.edu.ua.HospitalApp.doctor.internal.DoctorDetails.DoctorType;
import ukma.edu.ua.HospitalApp.auth.AuthService;

@WebMvcTest(controllers = AuthController.class)
public class AuthControllerTest extends ControllerTest {
  @Autowired
  private AuthController authController;

  @MockBean
  private AuthService authService;

  private static String loginBody = null;

  private static String registerDoctorBody = null;

  private static String registerPatientBody = null;

  @BeforeAll
  public static void setup() throws JsonProcessingException {
    var loginData = new LoginBody();
    loginData.setEmail("some@email.com");
    loginData.setPassword("");
    loginBody = new ObjectMapper().writeValueAsString(loginData);

    var registerDoctorData = new RegisterDoctorBody();
    registerDoctorData.setEmail("some@email.com");
    registerDoctorData.setPassword("pass");
    registerDoctorData.setBirthDate(new GregorianCalendar(1990, Calendar.FEBRUARY, 11).getTime());
    registerDoctorData.setDoctorType(DoctorType.CARDIOLOGIST);
    registerDoctorData.setFirstName("Doc");
    registerDoctorData.setLastName("Doc2");
    registerDoctorData.setHospitalId(Long.valueOf(1));
    registerDoctorBody = new ObjectMapper().writeValueAsString(registerDoctorData);

    var registerPatientData = new RegisterPatientBody();
    registerPatientData.setEmail("some@email.com");
    registerPatientData.setPassword("pass");
    registerPatientData.setBirthDate(new GregorianCalendar(1990, Calendar.FEBRUARY, 11).getTime());
    registerPatientData.setPassportNumber("FF111111");
    registerPatientData.setAddress("Some addr");
    registerPatientData.setFirstName("Pat");
    registerPatientData.setLastName("Pat2");
    registerPatientBody = new ObjectMapper().writeValueAsString(registerPatientData);
  }

  @Test
  @DisplayName("Smoke test on auth controller")
  public void smoke() {
    assertNotNull(authController);
  }

  @Test
  @DisplayName("It should respond with token if login creds are correct")
  public void testLogin() throws Exception {
    when(authService.login(any())).thenReturn(new TokenResponse("TOKEN"));

    mvc
        .perform(post("/api/auth/login")
            .contentType(MediaType.APPLICATION_JSON)
            .content(loginBody))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.accessToken").value("TOKEN"));
  }

  @Test
  @DisplayName("It should respond with error 400 if login creds are incorrect")
  public void testLoginFail() throws Exception {
    when(authService.login(any())).thenThrow(BadRequestException.class);

    mvc
        .perform(post("/api/auth/login")
            .contentType(MediaType.APPLICATION_JSON)
            .content(loginBody))
        .andExpect(status().isBadRequest())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.status").exists())
        .andExpect(jsonPath("$.message").hasJsonPath())
        .andExpect(jsonPath("$.time").exists());
  }

  @Test
  @DisplayName("It should respond with error 400 if body data is not valid")
  public void testLoginFailWithNotValidData() throws Exception {
    mvc
        .perform(post("/api/auth/login")
            .contentType(MediaType.APPLICATION_JSON)
            .content(""))
        .andExpect(status().isBadRequest());
  }

  @Test
  @DisplayName("It should respond with token if the doctor has been registered")
  public void registerDoctor() throws Exception {
    when(authService.registerDoctor(any())).thenReturn(new TokenResponse("TOKEN"));

    mvc
        .perform(post("/api/auth/register/doctor")
            .contentType(MediaType.APPLICATION_JSON)
            .content(registerDoctorBody))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.accessToken").value("TOKEN"));
  }

  @Test
  @DisplayName("It should respond with token if the patient has been registered")
  public void registerPatient() throws Exception {
    when(authService.registerPatient(any())).thenReturn(new TokenResponse("TOKEN"));

    mvc
        .perform(post("/api/auth/register/patient")
            .contentType(MediaType.APPLICATION_JSON)
            .content(registerPatientBody))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.accessToken").value("TOKEN"));
  }
}
