package ukma.edu.ua.HospitalApp.patient.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ukma.edu.ua.HospitalApp.common.Endpoints;
import ukma.edu.ua.HospitalApp.common.dto.PatientDTO;
import ukma.edu.ua.HospitalApp.patient.dto.UpdatePatientBody;
import ukma.edu.ua.HospitalApp.patient.services.PatientServiceInternal;
import ukma.edu.ua.HospitalApp.prescription.PrescriptionService;

@RestController
@RequestMapping("${app.prefix}" + Endpoints.PATIENT)
@RequiredArgsConstructor
@Tag(name = "Patient", description = "Patient endpoints")
public class PatientController {
  private final PrescriptionService prescriptionService;
  private final PatientServiceInternal patientService;

  private static final String GET_PATIENT_PRESCRIPTIONS_PATH = "/prescriptions";
  private static final String UPDATE_PATIENT_PATH = "/{id}";

  @GetMapping(GET_PATIENT_PRESCRIPTIONS_PATH)
  @Operation(summary = "Get patient's prescriptions")
  public String getPatientPrescription() {
    return prescriptionService.getPrescriptionForCurrentPatient();
  }

  @PutMapping(UPDATE_PATIENT_PATH)
  @Operation(summary = "Update patient's info")
  public PatientDTO updatePatient(
      @Valid @PathVariable Long id,
      @Valid @RequestBody UpdatePatientBody body) {
    return patientService.updatePatient(body, id);
  }
}
