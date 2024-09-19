package ukma.edu.ua.HospitalApp.prescription.controllers;

import io.swagger.v3.oas.annotations.Operation;
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

import ukma.edu.ua.HospitalApp.common.Endpoints;
import ukma.edu.ua.HospitalApp.prescription.PrescriptionDTO;
import ukma.edu.ua.HospitalApp.prescription.dto.CreatePresriptionBody;
import ukma.edu.ua.HospitalApp.prescription.services.PrescriptionServiceInternal;

@RestController
@RequestMapping("${app.prefix}" + Endpoints.PRESCRIPTION)
@RequiredArgsConstructor
@Tag(name = "Prescription", description = "Prescription endpoints")
public class PrescriptionController {
  private final PrescriptionServiceInternal prescriptionService;

  public static final String CREATE_PRESCRIPTION_PATH = "/issue";

  public static final String DELETE_PRESCRIPTION_PATH = "/{id}";

  @PostMapping(CREATE_PRESCRIPTION_PATH)
  @ResponseStatus(code = HttpStatus.CREATED)
  @Operation(summary = "Issue a prescription for a patient")
  public PrescriptionDTO createPresription(@Valid @RequestBody CreatePresriptionBody body) {
    return prescriptionService.createPresription(body);
  }

  @DeleteMapping(DELETE_PRESCRIPTION_PATH)
  @Operation(summary = "Delete patient's prescription")
  public ResponseEntity<String> deletePrescription(@PathVariable("id") long id) {
    prescriptionService.deletePrescription(id);
    return ResponseEntity.ok().build();
  }
}
