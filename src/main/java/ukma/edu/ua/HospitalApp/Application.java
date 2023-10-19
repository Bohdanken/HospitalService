package ukma.edu.ua.HospitalApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import ukma.edu.ua.HospitalApp.api.auth.AuthService;

@SpringBootApplication
@ComponentScan(basePackages ={"ukma.edu.ua.HospitalApp.services", "ukma.edu.ua.HospitalApp.models",
        "ukma.edu.ua.HospitalApp.dto","ukma.edu.ua.HospitalApp.repositories"})
@EnableConfigurationProperties
public class Application {
  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }
}