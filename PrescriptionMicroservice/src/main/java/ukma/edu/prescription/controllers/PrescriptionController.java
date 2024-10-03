package ukma.edu.prescription.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ukma.edu.prescription.model.Prescription;
import ukma.edu.prescription.service.PrescriptionService;
import java.util.List;


@RestController
@RequestMapping("/api/prescriptions")
public class PrescriptionController {

  private final PrescriptionService prescriptionService;

  @Autowired
  public PrescriptionController(PrescriptionService prescriptionService) {
    this.prescriptionService = prescriptionService;
  }

  @GetMapping
  public List<Prescription> getAllPrescriptions() {
    return prescriptionService.getAllPrescriptions();
  }

  @GetMapping("/{id}")
  public ResponseEntity<Prescription> getPrescriptionById(@PathVariable Long id) {
    return prescriptionService.getPrescriptionById(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
  }

  @GetMapping("/patient/{patientId}")
  public List<Prescription> getPrescriptionsByPatientId(@PathVariable Long patientId) {
    return prescriptionService.getPrescriptionsByPatientId(patientId);
  }

  @PostMapping
  public Prescription createPrescription(@RequestBody Prescription prescription) {
    return prescriptionService.savePrescription(prescription);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deletePrescription(@PathVariable Long id) {
    prescriptionService.deletePrescription(id);
    return ResponseEntity.noContent().build();
  }
}
