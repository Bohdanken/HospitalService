package ukma.edu.ua.HospitalApp.api.patient;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import ukma.edu.ua.HospitalApp.api.patient.dto.UpdatePatientBody;
import ukma.edu.ua.HospitalApp.dto.PatientDTO;
import ukma.edu.ua.HospitalApp.dto.PrescriptionDTO;
import ukma.edu.ua.HospitalApp.exceptionhandler.IncorrectIDException;
import ukma.edu.ua.HospitalApp.exceptionhandler.IncorrectBodyException;
import ukma.edu.ua.HospitalApp.services.PatientService;
import ukma.edu.ua.HospitalApp.services.PrescriptionService;

import java.util.List;
import java.util.stream.Collectors;

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

  @ExceptionHandler
  public void handleException(MethodArgumentNotValidException ex) {
    if (ex.getParameter().getParameterIndex() == 0)
      throw new IncorrectIDException("Incorrect id", ex);
    else {
      List<String> errors = ex.getBindingResult()
              .getFieldErrors()
              .stream()
              .map(err -> "Поле '" + err.getField() + "' " + err.getDefaultMessage())
              .toList();

      throw new IncorrectBodyException("Incorrect patient data", ex, errors);
    }
  }
}
