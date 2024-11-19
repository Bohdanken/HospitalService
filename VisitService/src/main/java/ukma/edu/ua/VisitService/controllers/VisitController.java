package ukma.edu.ua.VisitService.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;

import ukma.edu.ua.VisitService.entities.PatientVisit;
import ukma.edu.ua.VisitService.services.PatientVisitService;
import ukma.edu.ua.VisitService.dto.UpdateVisitBody;
import ukma.edu.ua.VisitService.dto.VisitBody;

@RestController
@RequestMapping("/visit")
@RequiredArgsConstructor
@Tag(name = "Visit", description = "Visit endpoints")
public class VisitController {
    private final PatientVisitService patientVisitService;

    @PostMapping("/create")
    @Operation(summary = "Allows patient to create a visit")
    public PatientVisit createVisit(@Valid @RequestBody VisitBody body) {
        return patientVisitService.createPatientVisit(body);
    }

    @GetMapping("/all")
    @Operation(summary = "Return all visits registered for a patient")
    public PatientVisit[] getVisitForPatient() {
        return patientVisitService.getAllPatientVisits();
    }

    @GetMapping("/{visitId}")
    @Operation(summary = "Return a patient's visit by its id")
    public PatientVisit getVisit(@Valid @PathVariable("visitId") Long id) {
        return patientVisitService.getPatientVisit(id);
    }

    @PutMapping("/{visitId}")
    @Operation(summary = "Allows patient to update an existing visit")
    public PatientVisit updateVisit(
            @Valid @PathVariable("visitId") Long id,
            @Valid @RequestBody UpdateVisitBody body) {
        return patientVisitService.updateVisit(id, body);
    }

    @DeleteMapping("/{visitId}")
    @Operation(summary = "Allows patient to delete a visit")
    public ResponseEntity<String> deleteVisit(@PathVariable("visitId") long id) {
        patientVisitService.deletePatientVisit(id);
        return ResponseEntity.ok().build();
    }
}
