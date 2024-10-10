package ukma.edu.ua.HospitalApp.prescription.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import ukma.edu.ua.HospitalApp.common.Endpoints;
import ukma.edu.ua.HospitalApp.prescription.dto.CreatePresriptionBody;
import ukma.edu.ua.HospitalApp.prescription.dto.PrescriptionResponse;
import ukma.edu.ua.HospitalApp.prescription.services.PrescriptionServiceInternal;

@RestController
@RequestMapping("${app.prefix}" + Endpoints.PRESCRIPTION)
@RequiredArgsConstructor
@Tag(name = "Prescription", description = "Prescription endpoints")
public class PrescriptionController {
  private final PrescriptionServiceInternal prescriptionService;

  public static final String CREATE_PRESCRIPTION_PATH = "/issue";
  public static final String GET_PRESCRIPTION_PATH = "/{id}";
  public static final String DELETE_PRESCRIPTION_PATH = "/{id}";

  @PostMapping(CREATE_PRESCRIPTION_PATH)
  @ResponseStatus(code = HttpStatus.CREATED)
  @Operation(summary = "Issue a prescription for a patient")
  public PrescriptionResponse createPresription(@RequestBody @Valid CreatePresriptionBody body) {
    return prescriptionService.createPresription(body);
  }

  @GetMapping(GET_PRESCRIPTION_PATH)
  @Operation(summary = "Get a prescription by its id")
  public ResponseEntity<String> getPresription(@PathVariable Long id) {
    var res = prescriptionService.getPrescriptionById(id);
    return ResponseEntity.ok()
        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=prescription.txt")
        .contentType(MediaType.TEXT_PLAIN)
        .body(res);
  }

  // @GetMapping(GET_PRESCRIPTION_PATH)
  // @Operation(summary = "Get a patient's prescription")
  // public Mono<ResponseEntity<String>> getPresription(
  // @PathVariable Long id) {
  // return prescriptionService.getPrescriptionForCurrentPatient();
  // }

  // TODO
  // @DeleteMapping(DELETE_PRESCRIPTION_PATH)
  // @Operation(summary = "Delete patient's prescription")
  // public ResponseEntity<String> deletePrescription(@PathVariable("id") long id)
  // {
  // prescriptionService.deletePrescription(id);
  // return ResponseEntity.ok().build();
  // }
}
