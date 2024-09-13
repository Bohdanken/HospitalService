package ukma.edu.ua.HospitalApp.visit;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ukma.edu.ua.HospitalApp.visit.dto.UpdateVisitBody;
import ukma.edu.ua.HospitalApp.visit.dto.VisitBody;

@RestController
@RequestMapping("${app.prefix}/visit")
@RequiredArgsConstructor
@Tag(name = "Visit", description = "Visit endpoints")
public class VisitController {

    private final PatientVisitService patientVisitService;

    @PostMapping("/create")
    public VisitDTO createVisit(@Valid @RequestBody VisitBody body) {
        return patientVisitService.createPatientVisit(body);
    }

    @GetMapping("/patient/{patientId}")
    public VisitDTO[] getVisitByPatient(@Valid @PathVariable("patientId") Long id) {
        return patientVisitService.getPatientVisitsByPatient(id);
    }

    @GetMapping("/doctor/{doctorId}")
    public VisitDTO[] getVisitByDoctor(@Valid @PathVariable("doctorId") Long id) {
        return patientVisitService.getPatientVisitsByDoctor(id);
    }

    @GetMapping("/")
    public VisitDTO[] getVisits() {
        return patientVisitService.getVisits();
    }

    @GetMapping("/{visitId}")
    public VisitDTO getVisit(@Valid @PathVariable("visitId") Long id) {
        return patientVisitService.getVisit(id);
    }

    @PutMapping("/{visitId}")
    public VisitDTO updateVisit(
            @Valid @PathVariable("visitId") Long id,
            @Valid @RequestBody UpdateVisitBody body) {
        return patientVisitService.updateVisit(body, id);
    }

    @DeleteMapping("/{visitId}")
    public ResponseEntity<String> deleteVisit(@PathVariable("visitId") long id) {
        patientVisitService.deletePatientVisit(id);
        return ResponseEntity.ok().build();
    }
}
