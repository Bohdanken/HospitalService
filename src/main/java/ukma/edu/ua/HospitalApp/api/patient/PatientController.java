package ukma.edu.ua.HospitalApp.api.patient;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ukma.edu.ua.HospitalApp.api.patient.dto.UpdatePatientBody;
import ukma.edu.ua.HospitalApp.dto.PatientDTO;
import ukma.edu.ua.HospitalApp.dto.PrescriptionDTO;
import ukma.edu.ua.HospitalApp.exceptionhandler.IncorrectBodyException;
import ukma.edu.ua.HospitalApp.exceptionhandler.IncorrectIDException;
import ukma.edu.ua.HospitalApp.services.PatientService;
import ukma.edu.ua.HospitalApp.services.PrescriptionService;

@Controller
@RequestMapping("/patient")
@RequiredArgsConstructor
@Tag(name = "Patient", description = "Patient endpoints")
public class PatientController {
  private final PrescriptionService prescriptionService;

  private final PatientService patientService;

//  @GetMapping("/{id}/prescriptions")
//  public PrescriptionDTO[] getPatientPrescriptions(@Valid @PathVariable("id") Long id) {
//    return prescriptionService.getPatientPrescriptions(id);
//  }

  @GetMapping("/{id}/prescriptions")
  public String getPatientPrescriptions(@Valid @PathVariable("id") Long id) {
    prescriptionService.getPatientPrescriptions(id);
    return "patient/index";
  }

//  @GetMapping("/{id}/prescriptions")
//  public String getPatientPrescriptions(@Valid @PathVariable("id") Long id, Model model) {
//    model.addAttribute("message", "Привіт, це повідомлення з сервера!");
//    return "patient/index";
//  }

  @PutMapping("/{id}")
  public PatientDTO updatePatient(@Valid @PathVariable("id") Long id,
                                  @Valid @RequestBody UpdatePatientBody body) {
    return patientService.updatePatient(body, id);
  }

  @ExceptionHandler
  public void handleException(MethodArgumentNotValidException ex) {
    if (ex.getParameter().getParameterIndex() == 0) {
      throw new IncorrectIDException("Incorrect id", ex);
    } else {
      List<String> errors = ex.getBindingResult()
          .getFieldErrors()
          .stream()
          .map(err -> "Поле '" + err.getField() + "' " + err.getDefaultMessage())
          .toList();

      throw new IncorrectBodyException("Incorrect patient data", ex, errors);
    }
  }
}
