package ukma.edu.ua.HospitalApp.visit.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import ukma.edu.ua.HospitalApp.entities.src.main.java.internal.DoctorDetails;
import ukma.edu.ua.HospitalApp.hospital.HospitalService;
import ukma.edu.ua.HospitalApp.visit.dto.VisitBody;

@Controller
@RequiredArgsConstructor
@Tag(name = "Patient Visit Management", description = "Manage patient visits")
public class PatientVisitControllerHttp {
    private final HospitalService hospitalService;

    @GetMapping("/visit-form")
    public String getVisitForm(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && !(authentication instanceof AnonymousAuthenticationToken)) {
            String userEmail = authentication.getName();
            model.addAttribute("patientEmail", userEmail);
            model.addAttribute("patientName", "Unknown");
            model.addAttribute("hospitals", hospitalService.getAllHospitals());
            model.addAttribute("doctorTypes", DoctorDetails.DoctorType.values());
            model.addAttribute("appointment", new VisitBody());
        }
        return "login/patientPage";
    }
}
