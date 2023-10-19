package ukma.edu.ua.HospitalApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ukma.edu.ua.HospitalApp.services.PatientService;

import java.util.Arrays;

@SpringBootApplication
public class Application {
  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  //  System.out.println(Arrays.toString(PatientService.addressOptions("Glasgow, Dalveen street, Uk")));
  }
}