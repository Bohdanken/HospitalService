package ukma.edu.prescription.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ukma.edu.prescription.service.PrescriptionService;
import java.util.Optional;

@RestController
@RequestMapping("/api/prescriptions")
public class PrescriptionController {

  private final PrescriptionService prescriptionService;

  @Autowired
  public PrescriptionController(PrescriptionService prescriptionService) {
    this.prescriptionService = prescriptionService;
  }


  @PostMapping("/create")
  public ResponseEntity<Void> createPrescription(@RequestParam Long patientId, @RequestParam String prescriptionDetails) {
    prescriptionService.createPrescription(patientId, prescriptionDetails);
    return ResponseEntity.status(HttpStatus.CREATED).build();
  }


  @GetMapping("/patient/{patientId}")
  public ResponseEntity<String> getPrescriptionDetailsByPatientId(@PathVariable Long patientId) {
    Optional<String> prescriptionDetails = prescriptionService.getPrescriptionDetailsByPatientId(patientId);
    return prescriptionDetails.map(string -> ResponseEntity.ok()
            .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=prescription.txt")
            .contentType(MediaType.TEXT_PLAIN)
            .body(string)).orElseGet(() -> ResponseEntity.notFound().build());
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deletePrescription(@PathVariable Long id) {
    prescriptionService.deletePrescription(id);
    return ResponseEntity.noContent().build();
  }
}
