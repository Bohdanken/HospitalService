package ukma.edu.ua.HospitalApp.notifications;

import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import ukma.edu.ua.HospitalApp.doctor.DoctorService;
import ukma.edu.ua.HospitalApp.entities.src.main.java.dto.EmailMessageDTO;
import ukma.edu.ua.HospitalApp.entities.src.main.java.dto.PrescriptionDTO;
import ukma.edu.ua.HospitalApp.entities.src.main.java.dto.UserDTO;
import ukma.edu.ua.HospitalApp.entities.src.main.java.dto.VisitDTO;
import ukma.edu.ua.HospitalApp.patient.PatientService;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender mailSender;
    private final PatientService patientService;
    private final DoctorService doctorService;

    @EventListener
    public void handleEmailRequestPublished(EmailMessageDTO emailMessageDTO) {
        sendEmail(emailMessageDTO.to(), emailMessageDTO.subject(), emailMessageDTO.body());
    }

    @EventListener
    void handleAppointmentCreated(VisitDTO visitDTO) {
        var patient = patientService.getPatientData(visitDTO.getPatientId());
        var doctor = doctorService.getDoctorById(visitDTO.getDoctorId());

        sendEmail(
                patient.getUser().getEmail(),
                "Approintment created",
                "Your appointment has been scheduled with doctor " + doctor.getFirstName() + " "
                        + doctor.getLastName());

        sendEmail(
                doctor.getUser().getEmail(),
                "Approintment created",
                patient.getFirstName() + " " + patient.getLastName() + " has scheduled an appointment with you");
    }

    @EventListener
    void handlePrescriptionCreated(PrescriptionDTO prescriptionDTO) {
        var patient = patientService.getPatientData(prescriptionDTO.getPatientId());

        sendEmail(
                patient.getUser().getEmail(),
                "Prescription created",
                "There's a new prescription available for you");
    }

    @EventListener
    void handleUserCreated(UserDTO userDTO) {
        sendEmail(
                userDTO.getEmail(),
                "Welcome!",
                "Welcome to the hospital app, %s %s!".formatted(userDTO.getFirstName(), userDTO.getLastName()));
    }

    public void sendEmail(String to, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);

        mailSender.send(message);
    }
}