package ukma.edu.ua.HospitalApp.api.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ukma.edu.ua.HospitalApp.api.auth.dto.LoginBody;
import ukma.edu.ua.HospitalApp.services.AuthService;

import java.util.ArrayList;

@Controller
@RequiredArgsConstructor
public class ThymeleafController {

    @GetMapping("/login")
    public String login(Model model,RedirectAttributes redirectAttributes) {
        if (AuthService.isAuthenticated()) {
            redirectAttributes.addFlashAttribute("infoMessage", "You are already logged in.");
            return "Need some redirection";
        }
        ArrayList<String> staff = new ArrayList<>();
        staff.add("patient");
        staff.add("doctor");

        model.addAttribute("staff", staff);

        if (!model.containsAttribute("mLoginBody"))
            model.addAttribute("mLoginBody", new LoginBody());

        return "login/login";
    }
}