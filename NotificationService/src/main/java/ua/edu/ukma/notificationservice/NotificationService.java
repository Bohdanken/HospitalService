package ua.edu.ukma.notificationservice;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    private static final String CONTAINER_FACTORY = "jmsListenerContainerFactory";

    private static final String PRESCRIPTION_CREATED_TOPIC = "prescription_created";
    private static final String USER_CREATED_TOPIC = "user_created";
    private static final String VISIT_CREATED_TOPIC = "visit_created";

    @JmsListener(destination = PRESCRIPTION_CREATED_TOPIC, containerFactory = CONTAINER_FACTORY)
    public void handlePrescriptionCreated(String prescription) {
        System.out.println("New prescription created: " + prescription);
    }
}
