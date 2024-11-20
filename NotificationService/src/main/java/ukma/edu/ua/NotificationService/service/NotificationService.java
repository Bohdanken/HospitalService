package ukma.edu.ua.NotificationService.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ukma.edu.ua.NotificationService.dto.UserDto;
import ukma.edu.ua.NotificationService.dto.VisitDto;

import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class NotificationService {

    private final ObjectMapper objectMapper;

    public void handlePrescriptionCreated(String prescription) {
        log.info("New prescription created: " + prescription);
    }

    public void handleUserCreated(String userJson) {
        try {
            var user = objectMapper.readValue(userJson, UserDto.class);
            log.info("New user created: " + user.getEmail() + " (" + user.getRole() + ")");
        } catch (Exception e) {
            log.error("Failed to process UserCreated event: " + e.getMessage(), e);
        }
    }

    public void handleVisitCreated(String visitJson) {
        try {
            var visit = objectMapper.readValue(visitJson, VisitDto.class);
            log.info("New visit created: Visit ID = " + visit.getId() +
                    ", Doctor ID = " + visit.getDoctorId() +
                    ", Patient ID = " + visit.getPatientId() +
                    ", Date = " + visit.getDateOfVisit().toString());
        } catch (Exception e) {
            log.error("Failed to process VisitCreated event: " + e.getMessage(), e);
        }
    }
}
