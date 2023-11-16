package HospitalApp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import ukma.edu.ua.HospitalApp.api.patient.PatientController;
import ukma.edu.ua.HospitalApp.services.PatientService;
import ukma.edu.ua.HospitalApp.services.PrescriptionService;

@Configuration
@ComponentScan({"ukma.edu.ua.HospitalApp.services"})
public class AppConfig {

//    @Autowired
//    private PrescriptionService prescriptionService;
//
//    @Autowired
//    private PatientService patientService;

    @Bean
    public PatientController patientController(PrescriptionService prescriptionService, PatientService patientService) {
        return new PatientController(prescriptionService, patientService);
    }
}
