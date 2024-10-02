package ukma.edu.ua.HospitalApp.patient.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import ukma.edu.ua.HospitalApp.common.dto.PatientDTO;
import ukma.edu.ua.HospitalApp.common.entities.Patient;
import ukma.edu.ua.HospitalApp.common.exceptions.NotFoundException;
import ukma.edu.ua.HospitalApp.patient.dto.UpdatePatientBody;
import ukma.edu.ua.HospitalApp.patient.mappers.PatientMapper;
import ukma.edu.ua.HospitalApp.patient.repositories.PatientDetailsRepository;

@Service
@RequiredArgsConstructor
public class PatientServiceInternal {
  private final PatientDetailsRepository patientDetailsRepository;

  public Patient getPatientData(long id) {
    return patientDetailsRepository.findById(id).orElse(null);
  }

  public Patient savePatientData(Patient data) {
    return patientDetailsRepository.save(data);
  }

  public PatientDTO updatePatient(UpdatePatientBody data, long id) {
    var patient = patientDetailsRepository.findById(id)
        .orElseThrow(() -> new NotFoundException("Patient not found"));

    patient.setFirstName(data.getFirstName());
    patient.setLastName(data.getLastName());
    patient.setPassportNumber(data.getPassportNumber());
    patient.setBirthDate(data.getBirthDate());
    patient.setAddress(data.getAddress());

    patientDetailsRepository.save(patient);

    return toPatientPropertiesDTO(patient);
  }

  public PatientDTO toPatientPropertiesDTO(Patient patient) {
    return PatientMapper.INSTANCE.patientToPatientDTO(patient);
  }
}
