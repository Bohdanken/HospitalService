package ukma.edu.ua.HospitalApp.services;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import ukma.edu.ua.HospitalApp.dto.PrescriptionDTO;
import ukma.edu.ua.HospitalApp.mappers.PrescriptionMapper;
import ukma.edu.ua.HospitalApp.models.Patient;
import ukma.edu.ua.HospitalApp.models.Prescription;
import ukma.edu.ua.HospitalApp.repositories.PrescriptionRepository;

@Service
public class PrescriptionService {

  private PrescriptionRepository prescriptionRepository;

  public PrescriptionDTO[] getPatientPrescriptions(long patientId) {
    Prescription searchPrescription = new Prescription();
    Patient searchPatient = new Patient();
    searchPatient.setId(patientId);
    searchPrescription.setPatient(searchPatient);

    var prescriptions = prescriptionRepository.findAll(Example.of(searchPrescription));
    return prescriptions.stream().map(this::toPrescriptionDTO).toArray(PrescriptionDTO[]::new);
  }
  @Autowired
  public PrescriptionService(PrescriptionRepository repository) {
    this.prescriptionRepository = repository;
  }

  public void deletePrescription(long id) {
    prescriptionRepository.deleteById(id);
  }

  public PrescriptionDTO toPrescriptionDTO(Prescription prescription) {
    return PrescriptionMapper.INSTANCE.prescriptionToPrescriptionDTO(prescription);
  }
}
