package HospitalApp;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import ukma.edu.ua.HospitalApp.api.patient.PatientController;
import ukma.edu.ua.HospitalApp.services.PatientService;
import ukma.edu.ua.HospitalApp.services.PrescriptionService;

@Configuration
public class AppConfig {

  @Bean(name = "someUniqueName")
  @Primary
  public PatientController patientControl(PrescriptionService prescriptionService,
                                          PatientService patientService) {
    return new PatientController(prescriptionService, patientService);
  }
}
