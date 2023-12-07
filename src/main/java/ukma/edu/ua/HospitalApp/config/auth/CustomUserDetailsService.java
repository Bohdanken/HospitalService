package ukma.edu.ua.HospitalApp.config.auth;

import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ukma.edu.ua.HospitalApp.models.Admin;
import ukma.edu.ua.HospitalApp.models.Doctor;
import ukma.edu.ua.HospitalApp.models.Patient;
import ukma.edu.ua.HospitalApp.models.User;
import ukma.edu.ua.HospitalApp.repositories.AdminRepository;
import ukma.edu.ua.HospitalApp.repositories.DoctorRepository;
import ukma.edu.ua.HospitalApp.repositories.PatientRepository;

@Service("userDetailsService")
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
  private final AdminRepository adminRepository;

  private final PatientRepository patientRepository;

  private final DoctorRepository doctorRepository;

  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    var searchAdmin = new Admin();
    searchAdmin.setEmail(email);
    var admin = adminRepository.findOne(Example.of(searchAdmin)).orElse(null);

    var searchPatient = new Patient();
    searchPatient.setEmail(email);
    var patient = patientRepository.findOne(Example.of(searchPatient)).orElse(null);

    var searchDoctor = new Doctor();
    searchDoctor.setEmail(email);
    var doctor = doctorRepository.findOne(Example.of(searchDoctor)).orElse(null);

    try {
      var user = Objects.requireNonNullElse(
          admin,
          Objects.requireNonNullElse(patient, doctor)
      );
      var role = admin != null
          ? User.Roles.ADMIN
          : patient != null
          ? User.Roles.PATIENT
          : User.Roles.DOCTOR;
      return org.springframework.security.core.userdetails.User
          .withUsername(user.getEmail())
          .password(user.getPassword())
          .authorities(role)
          .build();
    } catch (NullPointerException e) {
      throw new UsernameNotFoundException(email);
    }
  }
}

