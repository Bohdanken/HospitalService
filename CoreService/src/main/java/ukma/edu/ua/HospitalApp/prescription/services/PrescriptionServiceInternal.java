package ukma.edu.ua.HospitalApp.prescription.services;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import ukma.edu.ua.HospitalApp.common.dto.DrugDTO;
import ukma.edu.ua.HospitalApp.common.dto.PrescriptionDTO;
import ukma.edu.ua.HospitalApp.common.entities.Patient;
import ukma.edu.ua.HospitalApp.prescription.dto.CreatePresriptionBody;
import ukma.edu.ua.HospitalApp.prescription.dto.PrescriptionResponse;

import org.hibernate.mapping.Collection;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@RequiredArgsConstructor
public class PrescriptionServiceInternal {
  @Qualifier("prescriptionServiceClient")
  private WebClient prescriptionServiceClient;

  @Qualifier("medicineServiceClient")
  private WebClient medicineServiceClient;

  public Flux<ResponseEntity<List<PrescriptionResponse>>> getPatientPrescriptions(long patientId) {
    return prescriptionServiceClient
        .get()
        .uri("/api/prescriptions/patient/" + patientId)
        .retrieve()
        .toEntityList(PrescriptionResponse.class)
        .flux();
  }

  public PrescriptionDTO createPresription(CreatePresriptionBody data) {
    var drugs = data
        .getDrugs()
        .stream()
        .map(id -> DrugDTO.builder().id(id).build())
        .collect(Collectors.toList())
        .toArray(new DrugDTO[] {});

    var presription = PrescriptionDTO
        .builder()
        .dateOfIssue(Date.from(Instant.now()))
        .patient(Patient.builder().id(data.getPatientId()).build())
        .drugs(drugs)
        .build();

    try {
      var res = prescriptionRepository.save(presription);
      return toPrescriptionDTO(res);
    } catch (DataIntegrityViolationException e) {
      throw new BadRequestException("Provided data is not valid or does not exist");
    }
  }

  // TODO
  // public void deletePrescription(long id) {
  // var prescription = prescriptionRepository.findById(id).orElse(null);
  // if (prescription == null) {
  // throw new NotFoundException("Prescription not found");
  // }
  // prescriptionRepository.deleteById(id);
  // }
}
