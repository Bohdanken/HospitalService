package ukma.edu.ua.PrescriptionService.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.context.annotation.Configuration;

@Configuration
@io.swagger.v3.oas.annotations.security.SecurityScheme(type = SecuritySchemeType.HTTP, bearerFormat = "JWT", scheme = "bearer", name = "Bearer Auth", description = "All endpoints require JWT token to be a part of request")
@OpenAPIDefinition(info = @io.swagger.v3.oas.annotations.info.Info(title = "Prescription service API", description = "API documentation for Prescription Service", version = "1.0.0"), security = {
		@SecurityRequirement(name = "Bearer Auth") })
public class SwaggerConfig {
}
