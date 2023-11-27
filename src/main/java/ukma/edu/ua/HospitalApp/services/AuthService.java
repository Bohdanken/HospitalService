package ukma.edu.ua.HospitalApp.services;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import ukma.edu.ua.HospitalApp.api.auth.dto.LoginBody;
import ukma.edu.ua.HospitalApp.dto.DoctorDTO;
import ukma.edu.ua.HospitalApp.dto.PatientDTO;
import ukma.edu.ua.HospitalApp.mappers.DoctorMapper;
import ukma.edu.ua.HospitalApp.mappers.PatientMapper;
import ukma.edu.ua.HospitalApp.models.Doctor;
import ukma.edu.ua.HospitalApp.models.Patient;
import ukma.edu.ua.HospitalApp.repositories.DoctorRepository;
import ukma.edu.ua.HospitalApp.repositories.PatientRepository;

@RequiredArgsConstructor
@Service
public class AuthService {
  private final PatientRepository patientRepository;

  private final DoctorRepository doctorRepository;

  public PatientDTO loginPatient(LoginBody data) {
    Patient patientSearch = new Patient();
    patientSearch.setEmail(data.getEmail());

    var patient = patientRepository.findOne(Example.of(patientSearch));
    if (patient.isEmpty()) {
      throw new ResponseStatusException(HttpStatusCode.valueOf(401),
          "Given credentials are not correct");
    }

    return toPatientDTO(patient.get());
  }

  public DoctorDTO loginDoctor(LoginBody data) {
    Doctor doctorSearch = new Doctor();
    doctorSearch.setEmail(data.getEmail());

    var doctor = doctorRepository.findOne(Example.of(doctorSearch));

    if (doctor.isEmpty()) {
      throw new ResponseStatusException(HttpStatusCode.valueOf(401),
          "Given credentials are not correct");
    }

    return toDoctorDTO(doctor.get());
  }

  public PatientDTO toPatientDTO(Patient patient) {
    return PatientMapper.INSTANCE.patientToPatientDTO(patient);
  }

  public DoctorDTO toDoctorDTO(Doctor doctor) {
    return DoctorMapper.INSTANCE.doctorToDoctorDTO(doctor);
  }
}