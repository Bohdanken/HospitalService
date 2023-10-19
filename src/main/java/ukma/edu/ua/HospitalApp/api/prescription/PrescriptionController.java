package ukma.edu.ua.HospitalApp.api.prescription;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import ukma.edu.ua.HospitalApp.exceptionhandler.MyFirstException;
import ukma.edu.ua.HospitalApp.exceptionhandler.MySecondException;
import ukma.edu.ua.HospitalApp.services.PrescriptionService;

@RestController
@RequestMapping("/prescription")
@RequiredArgsConstructor
public class PrescriptionController {
  private final PrescriptionService prescriptionService;

  @DeleteMapping("/{id}")
  public ResponseEntity<String> deletePrescription(@Valid @PathVariable("id") long id) {
    prescriptionService.deletePrescription(id);
    return ResponseEntity.ok().build();
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public void handleException() {
    throw new MySecondException("Exception");
  }
}
