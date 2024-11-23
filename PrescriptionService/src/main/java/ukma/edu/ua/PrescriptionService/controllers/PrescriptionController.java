package ukma.edu.ua.PrescriptionService.controllers;

import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import ukma.edu.ua.PrescriptionService.dto.CreatePrescriptionBody;
import ukma.edu.ua.PrescriptionService.dto.PrescriptionDTO;
import ukma.edu.ua.PrescriptionService.security.User.Roles;
import ukma.edu.ua.PrescriptionService.service.PrescriptionService;

@RestController
@RequestMapping("/prescription")
@Tag(name = "Prescriptions", description = "Prescription endopoints")
@RequiredArgsConstructor
public class PrescriptionController {
  private final PrescriptionService prescriptionService;

  @PostMapping("/create")
  @ResponseStatus(HttpStatus.CREATED)
  @Operation(summary = "Create a prescription for a patient")
  @Secured(Roles.DOCTOR)
  public ResponseEntity<String> createPrescription(@RequestBody @Valid CreatePrescriptionBody body) {
    var prescription = prescriptionService.createPrescription(body);
    return ResponseEntity.ok()
        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=prescription.txt")
        .contentType(MediaType.TEXT_PLAIN)
        .body(prescription);
  }

  @GetMapping("/all")
  @Operation(summary = "Get all prescription for a current patient")
  @Secured(Roles.PATIENT)
  public List<PrescriptionDTO> getAllPrecriptionsForPatient() {
    return prescriptionService.getAllPrecriptionsForPatient();
  }

  @GetMapping("/{id}")
  @Operation(summary = "Get one prescription by id for a current patient")
  @Secured(Roles.PATIENT)
  public ResponseEntity<String> getPatientPrescriptionById(@PathVariable Long id) {
    var prescriptionDetails = prescriptionService.getPrescriptionById(id);
    return ResponseEntity.ok()
        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=prescription.txt")
        .contentType(MediaType.TEXT_PLAIN)
        .body(prescriptionDetails);
  }

  @DeleteMapping("/{id}")
  @Operation(summary = "Delete patient's prescription")
  @Secured(Roles.DOCTOR)
  public ResponseEntity<Void> deletePrescription(@PathVariable Long id) {
    prescriptionService.deletePrescription(id);
    return ResponseEntity.noContent().build();
  }
}
