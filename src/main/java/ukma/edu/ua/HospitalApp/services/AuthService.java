package ukma.edu.ua.HospitalApp.services;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.stereotype.Service;
import ukma.edu.ua.HospitalApp.api.auth.dto.LoginBody;
import ukma.edu.ua.HospitalApp.api.auth.dto.RegisterDoctorBody;
import ukma.edu.ua.HospitalApp.api.auth.dto.RegisterPatientBody;
import ukma.edu.ua.HospitalApp.config.auth.JWTService;
import ukma.edu.ua.HospitalApp.exceptions.errors.BadRequestException;
import ukma.edu.ua.HospitalApp.models.DoctorDetails;
import ukma.edu.ua.HospitalApp.models.Hospital;
import ukma.edu.ua.HospitalApp.models.PatientDetails;
import ukma.edu.ua.HospitalApp.models.User;
import ukma.edu.ua.HospitalApp.models.User.Role;
import ukma.edu.ua.HospitalApp.repositories.DoctorDetailsRepository;
import ukma.edu.ua.HospitalApp.repositories.PatientDetailsRepository;

@RequiredArgsConstructor
@Service
public class AuthService {
  private final AuthenticationConfiguration auth;

  private final JWTService jwtService;

  private final UserService userService;

  private final PatientDetailsRepository patientDetailsRepository;

  private final DoctorDetailsRepository doctorDetailsRepository;

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

    patientDetailsRepository.save(patientDetails);

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

    doctorDetailsRepository.save(doctorDetails);

    return jwtService.generateToken(user.getEmail());
  }

  private void authenticate(String email, String password) {
    try {
      auth
          .getAuthenticationManager()
          .authenticate(new UsernamePasswordAuthenticationToken(email, password));
    } catch (Exception e) {
      throw new BadRequestException("Incorrect credentials");
    }
  }
}