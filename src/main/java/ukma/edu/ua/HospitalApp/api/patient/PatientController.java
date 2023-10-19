package ukma.edu.ua.HospitalApp.api.patient;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import ukma.edu.ua.HospitalApp.api.patient.dto.UpdatePatientBody;
import ukma.edu.ua.HospitalApp.dto.PatientDTO;
import ukma.edu.ua.HospitalApp.dto.PrescriptionDTO;
import ukma.edu.ua.HospitalApp.exceptionhandler.MyFirstException;
import ukma.edu.ua.HospitalApp.services.PatientService;
import ukma.edu.ua.HospitalApp.services.PrescriptionService;

@RestController
@RequestMapping("/patient")
@RequiredArgsConstructor
public class PatientController {
  private final PrescriptionService prescriptionService;

  private final PatientService patientService;

  @GetMapping("/{id}/prescriptions")
  public PrescriptionDTO[] getPatientPrescriptions(@Valid @PathVariable("id") Long id) {
    return prescriptionService.getPatientPrescriptions(id);
  }

  @PutMapping("/{id}")
  public PatientDTO updatePatient(@Valid @PathVariable("id") Long id,
                                  @Valid @RequestBody UpdatePatientBody body) {
    return patientService.updatePatient(body, id);
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public void handleException() {
    throw new MyFirstException("Exception");
  }
}
