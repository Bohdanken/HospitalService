package ukma.edu.ua.HospitalApp.prescription.services;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import ukma.edu.ua.HospitalApp.entities.Drug;
import ukma.edu.ua.HospitalApp.entities.PatientDetails;
import ukma.edu.ua.HospitalApp.entities.Prescription;
import ukma.edu.ua.HospitalApp.exceptions.BadRequestException;
import ukma.edu.ua.HospitalApp.exceptions.NotFoundException;
import ukma.edu.ua.HospitalApp.prescription.PrescriptionDTO;
import ukma.edu.ua.HospitalApp.prescription.dto.CreatePresriptionBody;
import ukma.edu.ua.HospitalApp.prescription.mappers.PrescriptionMapper;
import ukma.edu.ua.HospitalApp.prescription.repositories.*;

@Service
@RequiredArgsConstructor
public class PrescriptionServiceInternal {
  private final PrescriptionRepository prescriptionRepository;

  public PrescriptionDTO[] getPatientPrescriptions(long patientId) {
    var prescriptions = prescriptionRepository.findByPatientDetailsId(patientId);
    return prescriptions.stream().map(this::toPrescriptionDTO).toArray(PrescriptionDTO[]::new);
  }

  public PrescriptionDTO createPresription(CreatePresriptionBody data) {
    List<Drug> drugs = data
        .getDrugs()
        .stream()
        .map(id -> Drug.builder().id(id).build())
        .collect(Collectors.toList());

    var presription = Prescription
        .builder()
        .dateOfIssue(Date.from(Instant.now()))
        .patientDetails(PatientDetails.builder().id(data.getPatientId()).build())
        .drugs(drugs)
        .build();
    try {
      var result = prescriptionRepository.save(presription);
      return toPrescriptionDTO(result);
    } catch (DataIntegrityViolationException e) {
      throw new BadRequestException("Provided data is not valid or does not exist");
    }
  }

  public void deletePrescription(long id) {
    var prescription = prescriptionRepository.findById(id).orElse(null);
    if (prescription == null) {
      throw new NotFoundException("Prescription not found");
    }
    prescriptionRepository.deleteById(id);
  }

  public PrescriptionDTO toPrescriptionDTO(Prescription prescription) {
    return PrescriptionMapper.INSTANCE.prescriptionToPrescriptionDTO(prescription);
  }
}
