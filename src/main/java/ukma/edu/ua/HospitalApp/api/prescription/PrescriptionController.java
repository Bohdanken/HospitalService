package ukma.edu.ua.HospitalApp.api.prescription;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ukma.edu.ua.HospitalApp.exceptionhandler.IncorrectIDException;
import ukma.edu.ua.HospitalApp.services.PrescriptionService;

@RestController
@RequestMapping("${app.prefix}/prescription")
@RequiredArgsConstructor
@Tag(name = "Prescription", description = "Prescription endpoints")
public class PrescriptionController {
  private final PrescriptionService prescriptionService;

  @DeleteMapping("/{id}")
  public ResponseEntity<String> deletePrescription(@PathVariable("id") long id) {
    prescriptionService.deletePrescription(id);
    return ResponseEntity.ok().build();
  }

  @ExceptionHandler
  public void handleException(MethodArgumentNotValidException ex) {
    throw new IncorrectIDException(ex);
  }
}
