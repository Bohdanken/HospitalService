package ukma.edu.ua.HospitalApp;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import ukma.edu.ua.HospitalApp.api.auth.AuthService;

@SpringBootApplication
@ComponentScan(basePackages ={"ukma.edu.ua.HospitalApp.Database", "ukma.edu.ua.HospitalApp.models"})
@EnableConfigurationProperties
public class Application {

    public static void main(String[] args) {


        SpringApplication.run(Application.class, args);
    }
}