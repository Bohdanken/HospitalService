package ukma.edu.ua.HospitalApp.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ukma.edu.ua.HospitalApp.api.patient.dto.UpdatePatientBody;
import ukma.edu.ua.HospitalApp.dto.PatientDTO;
import ukma.edu.ua.HospitalApp.mappers.PatientMapper;
import ukma.edu.ua.HospitalApp.models.Patient;
import ukma.edu.ua.HospitalApp.repositories.PatientRepository;

@Service
@RequiredArgsConstructor
public class PatientService {
  private final PatientRepository patientRepository;

  public PatientDTO updatePatient(UpdatePatientBody data, long id) {
    Patient patient = patientRepository.findById(id).orElseThrow();
    patient.setFirstName(data.getFirstName());
    patient.setLastName(data.getLastName());
    patient.setAddress(data.getAddress());
    patient.setBirthDate(data.getBirthDate());
    patient.setPassportNumber(data.getPassportNumber());
    patient.setEmail(data.getEmail());
    patientRepository.save(patient);
    return toPatientDTO(patient);
  }

  public PatientDTO toPatientDTO(Patient patient) {
    return PatientMapper.INSTANCE.patientToPatientDTO(patient);
  }
}
