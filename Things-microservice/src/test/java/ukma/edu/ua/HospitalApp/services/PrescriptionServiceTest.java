package ukma.edu.ua.HospitalApp.services;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataIntegrityViolationException;

import ukma.edu.ua.HospitalApp.prescription.dto.CreatePresriptionBody;
import ukma.edu.ua.HospitalApp.prescription.repositories.PrescriptionRepository;
import ukma.edu.ua.HospitalApp.entities.src.main.java.internal.Drug;
import ukma.edu.ua.HospitalApp.entities.src.main.java.internal.PatientDetails;
import ukma.edu.ua.HospitalApp.entities.src.main.java.internal.Prescription;
import ukma.edu.ua.HospitalApp.exceptions.BadRequestException;
import ukma.edu.ua.HospitalApp.exceptions.NotFoundException;
import ukma.edu.ua.HospitalApp.prescription.services.PrescriptionServiceInternal;

@DisplayName("PrescriptionService unit test")
@ExtendWith(MockitoExtension.class)
public class PrescriptionServiceTest {
  @InjectMocks
  private PrescriptionServiceInternal prescriptionService;

  @Mock
  private PrescriptionRepository prescriptionRepository;

  @Test
  @Order(1)
  @DisplayName("It should always return an array of a patient's prescriptions")
  public void shouldGetListOfPatientsPrescriptions() {
    when(prescriptionRepository.findByPatientDetailsId(anyLong()))
        .thenReturn(List.of(
            Prescription.builder().build(),
            Prescription.builder().build(),
            Prescription.builder().build()));

    var result = prescriptionService.getPatientPrescriptions(1);
    assertEquals(result.length, 3);
  }

  @Test
  @Order(2)
  @DisplayName("It should create a prescription")
  public void shouldCreatePrescription() {
    var body = new CreatePresriptionBody();
    body.setPatientId(Long.valueOf(1));
    body.setDrugs(List.of(Long.valueOf(1), Long.valueOf(2)));

    when(prescriptionRepository.save(any()))
        .thenReturn(Prescription.builder()
            .id(Long.valueOf(1))
            .dateOfIssue(new Date())
            .patientDetails(PatientDetails
                .builder()
                .id(Long.valueOf(1))
                .build())
            .drugs(List.of(
                Drug.builder().id(Long.valueOf(1)).build(),
                Drug.builder().id(Long.valueOf(2)).build()))
            .build());

    var result = prescriptionService.createPresription(body);
    assertEquals(result.getDrugs().length, 2, "Drug list is invalid");
    assertEquals(result.getPatientId(), 1, "Patient id is invalid");
    assertEquals(result.getId(), 1, "Id is invalid");
    assertNotNull(result.getDateOfIssue(), "Date of issue is null");
  }

  @Test
  @Order(3)
  @DisplayName("It should return 400 if prescription cannot be created")
  public void shouldNotCreatePrescription() {
    var body = new CreatePresriptionBody();
    body.setPatientId(Long.valueOf(1));
    body.setDrugs(List.of(Long.valueOf(1), Long.valueOf(2)));

    when(prescriptionRepository.save(any())).thenThrow(DataIntegrityViolationException.class);

    assertThrows(BadRequestException.class, () -> prescriptionService.createPresription(body));
  }

  @Test
  @Order(4)
  @DisplayName("It should delete prescription")
  public void shouldDeletePrescription() {
    when(prescriptionRepository.findById(anyLong()))
        .thenReturn(Optional.of(Prescription.builder().build()));

    assertDoesNotThrow(() -> prescriptionService.deletePrescription(1));
  }

  @Test
  @Order(5)
  @DisplayName("It should return 404 if cannot delete prescription")
  public void shouldNotDeletePrescription() {
    when(prescriptionRepository.findById(anyLong())).thenReturn(Optional.ofNullable(null));

    assertThrows(NotFoundException.class, () -> prescriptionService.deletePrescription(1));
  }
}
