package ukma.edu.ua.HospitalApp.api.prescription;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ukma.edu.ua.HospitalApp.api.prescription.dto.CreatePresriptionBody;
import ukma.edu.ua.HospitalApp.config.Endpoints;
import ukma.edu.ua.HospitalApp.dto.PrescriptionDTO;
import ukma.edu.ua.HospitalApp.services.PrescriptionService;

@RestController
@RequestMapping("${app.prefix}" + Endpoints.PRESCRIPTION)
@RequiredArgsConstructor
@Tag(name = "Prescription", description = "Prescription endpoints")
public class PrescriptionController {
  private final PrescriptionService prescriptionService;

  public static final String CREATE_PRESCRIPTION_PATH = "/issue";

  public static final String DELETE_PRESCRIPTION_PATH = "/{id}";

  @PostMapping(CREATE_PRESCRIPTION_PATH)
  @ResponseStatus(code = HttpStatus.CREATED)
  public PrescriptionDTO createPresription(@Valid @RequestBody CreatePresriptionBody body) {
    return prescriptionService.createPresription(body);
  }

  @DeleteMapping(DELETE_PRESCRIPTION_PATH)
  public ResponseEntity<String> deletePrescription(@PathVariable("id") long id) {
    prescriptionService.deletePrescription(id);
    return ResponseEntity.ok().build();
  }
}
