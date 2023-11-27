package ukma.edu.ua.HospitalApp.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
    info = @Info(
        title = "Hospital Service API",
        description = "API documentation for Hospital Service",
        version = "1.0.0"
    )
)
public class Config {
}
