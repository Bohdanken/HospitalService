package ukma.edu.prescription.controllers;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import ukma.edu.prescription.dto.CreatePrescriptionBody;
import ukma.edu.prescription.dto.PrescriptionDTO;
import ukma.edu.prescription.service.PrescriptionService;

@RestController
@RequestMapping("/api/prescriptions")
@RequiredArgsConstructor
public class PrescriptionController {
  private final PrescriptionService prescriptionService;

  @PostMapping("/create")
  @ResponseStatus(HttpStatus.CREATED)
  public PrescriptionDTO createPrescription(@RequestBody @Valid CreatePrescriptionBody body) {
    return prescriptionService.createPrescription(body);
  }

  @GetMapping("/patient/{patientId}")
  public ResponseEntity<String> getLatestPrescriptionByPatientId(@PathVariable Long patientId) {
    var prescriptionDetails = prescriptionService.getLatestPrescriptionByPatientId(patientId);
    return ResponseEntity.ok()
        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=prescription.txt")
        .contentType(MediaType.TEXT_PLAIN)
        .body(prescriptionDetails);
  }

  @GetMapping("/{id}")
  public ResponseEntity<String> getPresctiptionById(@PathVariable Long id) {
    var prescriptionDetails = prescriptionService.getPrescriptionById(id);
    return ResponseEntity.ok()
        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=prescription.txt")
        .contentType(MediaType.TEXT_PLAIN)
        .body(prescriptionDetails);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deletePrescription(@PathVariable Long id) {
    prescriptionService.deletePrescription(id);
    return ResponseEntity.noContent().build();
  }
}
