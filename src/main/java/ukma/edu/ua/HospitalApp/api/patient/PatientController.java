package ukma.edu.ua.HospitalApp.api.patient;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ukma.edu.ua.HospitalApp.api.patient.dto.UpdatePatientBody;
import ukma.edu.ua.HospitalApp.dto.PatientDTO;
import ukma.edu.ua.HospitalApp.dto.PrescriptionDTO;
import ukma.edu.ua.HospitalApp.services.PatientService;
import ukma.edu.ua.HospitalApp.services.PrescriptionService;

@RestController
@RequestMapping("${app.prefix}/patient")
@RequiredArgsConstructor
@Tag(name = "Patient", description = "Patient endpoints")
public class PatientController {
  private final PrescriptionService prescriptionService;

  private final PatientService patientService;

  @GetMapping("/{id}/prescriptions")
  public PrescriptionDTO[] getPatientPrescriptions(@Valid @PathVariable("id") Long id) {
    return prescriptionService.getPatientPrescriptions(id);
  }

  @GetMapping("/address-suggestions")
  public ResponseEntity<String[]> getAddressSuggestions(@RequestParam("address") String address) {
    String[] addressOptions = patientService.addressOptions(address);
    return ResponseEntity.ok(addressOptions);
  }

  @PutMapping("/{id}")
  public PatientDTO updatePatient(
      @Valid @PathVariable("id") Long id,
      @Valid @RequestBody UpdatePatientBody body) {
    return patientService.updatePatient(body, id);
  }
}
