package ukma.edu.ua.NotificationService.service;

import lombok.RequiredArgsConstructor;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationListenerService {
    private static final String PRESCRIPTION_CREATED_TOPIC = "prescription_created";
    private static final String USER_CREATED_TOPIC = "user_created";
    private static final String VISIT_CREATED_TOPIC = "visit_created";

    private final NotificationService notificationService;

    @JmsListener(destination = PRESCRIPTION_CREATED_TOPIC)
    public void handlePrescriptionCreated(String prescription) {
        notificationService.handlePrescriptionCreated(prescription);
    }

    @JmsListener(destination = USER_CREATED_TOPIC)
    public void handleUserCreated(String userJson) {
        notificationService.handleUserCreated(userJson);
    }

    @JmsListener(destination = VISIT_CREATED_TOPIC)
    public void handleVisitCreated(String visitJson) {
        notificationService.handleVisitCreated(visitJson);
    }
}
