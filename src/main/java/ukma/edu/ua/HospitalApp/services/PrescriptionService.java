package ukma.edu.ua.HospitalApp.services;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import ukma.edu.ua.HospitalApp.dto.PrescriptionDTO;
import ukma.edu.ua.HospitalApp.mappers.PrescriptionMapper;
import ukma.edu.ua.HospitalApp.models.Prescription;
import ukma.edu.ua.HospitalApp.repositories.PrescriptionRepository;

@Service
@RequiredArgsConstructor
public class PrescriptionService {
  private final PrescriptionRepository prescriptionRepository;

  private final CacheManager cacheManager;

  @Cacheable(value = "PatientPrescriptions", key = "#patientId")
  public PrescriptionDTO[] getPatientPrescriptions(long patientId) {
    var prescriptions = prescriptionRepository.findByPatientDetailsId(patientId);
    return prescriptions.stream().map(this::toPrescriptionDTO).toArray(PrescriptionDTO[]::new);
  }

  public void deletePrescription(long id) {
    var data = prescriptionRepository.findById(id).orElse(null);
    if (data == null) {
      return;
    }

    var cache = cacheManager.getCache("PatientPrescriptions");
    if (cache != null) {
      cache.evict(data.getPatientDetailsId());
    }
    prescriptionRepository.delete(data);
  }

  public PrescriptionDTO toPrescriptionDTO(Prescription prescription) {
    return PrescriptionMapper.INSTANCE.prescriptionToPrescriptionDTO(prescription);
  }
}
