package ukma.edu.ua.HospitalApp.prescription.services;

import lombok.RequiredArgsConstructor;
import ukma.edu.ua.HospitalApp.common.exceptions.BadRequestException;
import ukma.edu.ua.HospitalApp.common.security.AppUser;
import ukma.edu.ua.HospitalApp.medicine.MedicineService;
import ukma.edu.ua.HospitalApp.patient.PatientService;
import ukma.edu.ua.HospitalApp.prescription.dto.CreatePrescriptionBodyThirdParty;
import ukma.edu.ua.HospitalApp.prescription.dto.CreatePresriptionBody;
import ukma.edu.ua.HospitalApp.prescription.dto.PrescriptionResponse;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@RequiredArgsConstructor
public class PrescriptionServiceInternal {
  private final PatientService patientService;
  private final MedicineService medicineService;

  @Qualifier("prescriptionServiceClient")
  private final WebClient prescriptionServiceClient;

  public String getPrescriptionForCurrentPatient() {
    var authentication = SecurityContextHolder.getContext().getAuthentication();
    var appUser = (AppUser) authentication.getPrincipal();

    return prescriptionServiceClient
        .get()
        .uri("/api/prescriptions/patient/" + appUser.getUserId())
        .retrieve()
        .toEntity(String.class)
        .block()
        .getBody();
  }

  public String getPrescriptionById(long id) {
    return prescriptionServiceClient
        .get()
        .uri("/api/prescriptions/" + id)
        .retrieve()
        .toEntity(String.class)
        .block()
        .getBody();
  }

  public PrescriptionResponse createPresription(CreatePresriptionBody data) {
    var patient = patientService.getPatientData(data.getPatientId());

    var drugIds = data.getDrugs().stream().map(drug -> drug.getDrugId()).toList();
    var allDrugs = medicineService.getDrugsByIds(drugIds);
    var drugs = allDrugs
        .stream()
        .map(drug -> CreatePrescriptionBodyThirdParty.Drug
            .builder()
            .name(drug.getBrandName())
            .timesPerDay(
                data.getDrugs()
                    .stream()
                    .filter(e -> e.getDrugId() == drug.getId())
                    .findFirst()
                    .get()
                    .getTimesPerDay())
            .build())
        .toList();
    var reqBody = CreatePrescriptionBodyThirdParty.builder()
        .patientId(patient.getId())
        .patientFirstName(patient.getFirstName())
        .patientLastName(patient.getLastName())
        .drugs(drugs)
        .build();

    try {
      return prescriptionServiceClient
          .post()
          .uri("/api/prescriptions/create")
          .contentType(MediaType.APPLICATION_JSON)
          .bodyValue(reqBody)
          .retrieve()
          .toEntity(PrescriptionResponse.class)
          .block()
          .getBody();
    } catch (DataIntegrityViolationException e) {
      throw new BadRequestException("Error creating prescription");
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
