package ukma.edu.ua.HospitalApp.api.prescription;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import ukma.edu.ua.HospitalApp.exceptionhandler.IncorrectIDException;
import ukma.edu.ua.HospitalApp.services.PrescriptionService;

@RestController
@RequestMapping("/prescription")
@RequiredArgsConstructor
@Tag(name = "Prescription", description = "Prescription endpoints")
public class PrescriptionController {
  private final PrescriptionService prescriptionService;

  @GetMapping("/limit")
  public String limit() {
    return "1";
  }

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
