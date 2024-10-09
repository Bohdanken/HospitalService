package ukma.edu.ua.HospitalApp.auth.services;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import ukma.edu.ua.HospitalApp.auth.dto.LoginBody;
import ukma.edu.ua.HospitalApp.auth.dto.RegisterDoctorBody;
import ukma.edu.ua.HospitalApp.auth.dto.RegisterPatientBody;
import ukma.edu.ua.HospitalApp.doctor.DoctorService;
import ukma.edu.ua.HospitalApp.common.entities.Doctor;
import ukma.edu.ua.HospitalApp.common.entities.Hospital;
import ukma.edu.ua.HospitalApp.common.entities.Patient;
import ukma.edu.ua.HospitalApp.common.entities.User.Role;
import ukma.edu.ua.HospitalApp.common.exceptions.BadRequestException;
import ukma.edu.ua.HospitalApp.common.security.JWTService;
import ukma.edu.ua.HospitalApp.patient.PatientService;
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

    var patientDetails = Patient.builder()
        .id(user.getId())
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

    var doctorDetails = Doctor.builder()
        .id(user.getId())
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
      auth
          .getAuthenticationManager()
          .authenticate(new UsernamePasswordAuthenticationToken(email, password));
    } catch (Exception e) {
      throw new BadRequestException("Invalid credentials");
    }
  }
}