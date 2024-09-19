package ukma.edu.ua.HospitalApp.auth.services;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import ukma.edu.ua.HospitalApp.auth.dto.LoginBody;
import ukma.edu.ua.HospitalApp.auth.dto.RegisterDoctorBody;
import ukma.edu.ua.HospitalApp.auth.dto.RegisterPatientBody;
import ukma.edu.ua.HospitalApp.doctor.DoctorService;
import ukma.edu.ua.HospitalApp.entities.DoctorDetails;
import ukma.edu.ua.HospitalApp.entities.Hospital;
import ukma.edu.ua.HospitalApp.entities.PatientDetails;
import ukma.edu.ua.HospitalApp.entities.User;
import ukma.edu.ua.HospitalApp.entities.User.Role;
import ukma.edu.ua.HospitalApp.exceptions.BadRequestException;
import ukma.edu.ua.HospitalApp.patient.PatientService;
import ukma.edu.ua.HospitalApp.security.JWTService;
import ukma.edu.ua.HospitalApp.user.UserService;

@RequiredArgsConstructor
@Service
public class AuthService {
  private final AuthenticationConfiguration auth;
  private final JWTService jwtService;
  private final UserDetailsService userDetailsService;

  private final UserService userService;
  private final PatientService patientService;
  private final DoctorService doctorService;

  public JWTService.TokenResponse login(LoginBody data) {
    authenticate(data.getEmail(), data.getPassword());
    return jwtService.generateToken(data.getEmail());
  }

  public JWTService.TokenResponse registerPatient(RegisterPatientBody data) {
    var user = userService.registerNewUser(data.getEmail(), data.getPassword(), Role.PATIENT);

    var patientDetails = PatientDetails.builder()
        .user(User.builder().id(user.getId()).build())
        .firstName(data.getFirstName())
        .lastName(data.getLastName())
        .address(data.getAddress())
        .birthDate(data.getBirthDate())
        .passportNumber(data.getPassportNumber())
        .build();

    patientService.savePatientData(patientDetails);

    return jwtService.generateToken(user.getEmail());
  }

  public JWTService.TokenResponse registerDoctor(RegisterDoctorBody data) {
    var user = userService.registerNewUser(data.getEmail(), data.getPassword(), Role.DOCTOR);

    var doctorDetails = DoctorDetails.builder()
        .user(User.builder().id(user.getId()).build())
        .firstName(data.getFirstName())
        .lastName(data.getLastName())
        .birthDate(data.getBirthDate())
        .hospital(Hospital.builder().id(data.getHospitalId()).build())
        .doctorType(data.getDoctorType())
        .build();

    doctorService.saveDoctorData(doctorDetails);

    return jwtService.generateToken(user.getEmail());
  }

  public void authenticate(String email, String password) {
    try {
      userDetailsService.loadUserByUsername(email);
    } catch (Exception ex) {
      throw new BadRequestException("Incorrect email.");
    }
    try {
      auth
          .getAuthenticationManager()
          .authenticate(new UsernamePasswordAuthenticationToken(email, password));
    } catch (Exception e) {
      throw new BadRequestException("Incorrect password.");
    }
  }

  public static boolean isAuthenticated() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    return authentication != null && !(authentication instanceof AnonymousAuthenticationToken)
        && authentication.isAuthenticated();
  }
}