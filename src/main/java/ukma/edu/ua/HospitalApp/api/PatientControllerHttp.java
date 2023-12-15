package ukma.edu.ua.HospitalApp.api;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
@Tag(name = "Auth", description = "Authentication routes for patients, doctors and admins")
public class PatientControllerHttp {
    private final AppointmentService appointmentService;

    // Конструктор з автоматичним введенням залежностей для сервісу записів
    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    // Метод для отримання форми запису
    @GetMapping
    public String getAppointmentForm(Model model, @RequestParam String patientEmail, @RequestParam String patientName) {
        // Припускаємо, що сервіс повертає списки лікарень та типів лікарів
        model.addAttribute("hospitals", appointmentService.getHospitals());
        model.addAttribute("doctorTypes", appointmentService.getDoctorTypes());
        model.addAttribute("patientEmail", patientEmail);
        model.addAttribute("patientName", patientName);
        model.addAttribute("appointment", new Appointment());

        return "appointment"; // Назва шаблону Thymeleaf
    }

    // Метод для обробки відправленої форми
    @PostMapping
    public String submitAppointmentForm(@ModelAttribute Appointment appointment) {
        // Обробка данних форми
        appointmentService.createAppointment(appointment);

        return "redirect:/appointment-success"; // Перенаправлення на сторінку успішного запису
    }
}
