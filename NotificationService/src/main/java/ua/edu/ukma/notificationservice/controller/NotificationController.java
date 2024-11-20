package ua.edu.ukma.notificationservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Controller;
import ua.edu.ukma.notificationservice.service.NotificationService;

@Controller
@RequiredArgsConstructor
public class NotificationController {
    private static final String CONTAINER_FACTORY = "jmsListenerContainerFactory";

    private static final String PRESCRIPTION_CREATED_TOPIC = "prescription_created";
    private static final String USER_CREATED_TOPIC = "user_created";
    private static final String VISIT_CREATED_TOPIC = "visit_created";

    private final NotificationService notificationService;

    @JmsListener(destination = PRESCRIPTION_CREATED_TOPIC, containerFactory = CONTAINER_FACTORY)
    public void handlePrescriptionCreated(String prescription) {
        notificationService.handlePrescriptionCreated(prescription);
    }

    @JmsListener(destination = USER_CREATED_TOPIC, containerFactory = CONTAINER_FACTORY)
    public void handleUserCreated(String userJson) {
        notificationService.handleUserCreated(userJson);
    }

    @JmsListener(destination = VISIT_CREATED_TOPIC, containerFactory = CONTAINER_FACTORY)
    public void handleVisitCreated(String visitJson) {
        notificationService.handleVisitCreated(visitJson);
    }
}
