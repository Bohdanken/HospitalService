package ua.edu.ukma.notificationservice.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ua.edu.ukma.notificationservice.dto.UserDto;
import ua.edu.ukma.notificationservice.dto.VisitDto;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final ObjectMapper objectMapper;

    public void handlePrescriptionCreated(String prescription) {
        System.out.println("New prescription created: " + prescription);
    }

    public void handleUserCreated(String userJson) {
        try {
            var user = objectMapper.readValue(userJson, UserDto.class);
            System.out.println("New user created: " + user.getEmail() + " (" + user.getRole() + ")");
        } catch (Exception e) {
            System.err.println("Failed to process UserCreated event: " + e.getMessage());
        }
    }

    public void handleVisitCreated(String visitJson) {
        try {
            var visit = objectMapper.readValue(visitJson, VisitDto.class);
            System.out.println("New visit created: Visit ID = " + visit.getId() +
                    ", Doctor ID = " + visit.getDoctorId() +
                    ", Patient ID = " + visit.getPatientId() +
                    ", Date = " + visit.getDateOfVisit().toString());
        } catch (Exception e) {
            System.err.println("Failed to process VisitCreated event: " + e.getMessage());
        }
    }
}
