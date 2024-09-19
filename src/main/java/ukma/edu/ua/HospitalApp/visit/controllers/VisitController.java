package ukma.edu.ua.HospitalApp.visit.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ukma.edu.ua.HospitalApp.visit.dto.UpdateVisitBody;
import ukma.edu.ua.HospitalApp.visit.dto.VisitBody;
import ukma.edu.ua.HospitalApp.visit.dto.VisitDTO;
import ukma.edu.ua.HospitalApp.visit.services.PatientVisitService;

@RestController
@RequestMapping("${app.prefix}/visit")
@RequiredArgsConstructor
@Tag(name = "Visit", description = "Visit endpoints")
public class VisitController {

    private final PatientVisitService patientVisitService;

    @PostMapping("/create")
    @Operation(summary = "Create a visit for patient")
    public VisitDTO createVisit(@Valid @RequestBody VisitBody body) {
        return patientVisitService.createPatientVisit(body);
    }

    @GetMapping("/patient/{patientId}")
    @Operation(summary = "Get patient visits for patient")
    public VisitDTO[] getVisitByPatient(@Valid @PathVariable("patientId") Long id) {
        return patientVisitService.getPatientVisitsByPatient(id);
    }

    @GetMapping("/doctor/{doctorId}")
    @Operation(summary = "Get patient visits for doctor")
    public VisitDTO[] getVisitByDoctor(@Valid @PathVariable("doctorId") Long id) {
        return patientVisitService.getPatientVisitsByDoctor(id);
    }

    @GetMapping("/")
    @Operation(summary = "Get all visits")
    public VisitDTO[] getVisits() {
        return patientVisitService.getVisits();
    }

    @GetMapping("/{visitId}")
    @Operation(summary = "Get specific visit information")
    public VisitDTO getVisit(@Valid @PathVariable("visitId") Long id) {
        return patientVisitService.getVisit(id);
    }

    @PutMapping("/{visitId}")
    @Operation(summary = "Update visit information")
    public VisitDTO updateVisit(
            @Valid @PathVariable("visitId") Long id,
            @Valid @RequestBody UpdateVisitBody body) {
        return patientVisitService.updateVisit(body, id);
    }

    @DeleteMapping("/{visitId}")
    @Operation(summary = "Delete visit")
    public ResponseEntity<String> deleteVisit(@PathVariable("visitId") long id) {
        patientVisitService.deletePatientVisit(id);
        return ResponseEntity.ok().build();
    }
}
