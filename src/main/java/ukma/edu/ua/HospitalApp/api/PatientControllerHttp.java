package ukma.edu.ua.HospitalApp.api;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ukma.edu.ua.HospitalApp.api.visit.dto.VisitBody;
import ukma.edu.ua.HospitalApp.dto.VisitDTO;
import ukma.edu.ua.HospitalApp.models.DoctorDetails;
import ukma.edu.ua.HospitalApp.models.User;
import ukma.edu.ua.HospitalApp.repositories.UserRepository;
import ukma.edu.ua.HospitalApp.services.HospitalService;
import ukma.edu.ua.HospitalApp.services.PatientVisitService;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
@Tag(name = "Patient Visit Management", description = "Manage patient visits")
public class PatientControllerHttp {
    private final PatientVisitService patientVisitService;
    private final HospitalService hospitalService;

   /* @GetMapping("/visit-form")
    public String getVisitForm(Model model, @RequestParam String patientEmail, @RequestParam String patientName) {
        model.addAttribute("patientEmail", patientEmail);
        model.addAttribute("patientName", patientName);
        model.addAttribute("visit", new VisitBody());
        return "visitForm";
    }*/

    @GetMapping("/visit-form")
    public String getVisitForm(Model model){
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
