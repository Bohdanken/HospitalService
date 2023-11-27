package HospitalApp;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import ukma.edu.ua.HospitalApp.api.patient.PatientController;
import ukma.edu.ua.HospitalApp.repositories.PrescriptionRepository;
import ukma.edu.ua.HospitalApp.services.PrescriptionService;

import java.util.Arrays;

@SpringBootTest
@ContextConfiguration(classes = AppConfig.class)
public class MySpringBootTest {
    @Autowired
    private PatientController patientController;

    @Autowired
    private ApplicationContext applicationContext;

    @Test
    void contextLoads() {
        applicationContext.getBeanDefinitionNames();
        System.out.println(patientController.getPatientPrescriptions(0L));
    }
}
