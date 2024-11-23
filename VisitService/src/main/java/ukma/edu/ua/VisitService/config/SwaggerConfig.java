package ukma.edu.ua.VisitService.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.context.annotation.Configuration;

@Configuration
@SecurityScheme(type = SecuritySchemeType.HTTP, bearerFormat = "JWT", scheme = "bearer", name = "Bearer Auth", description = "All endpoints require JWT token to be a part of request")
@OpenAPIDefinition(info = @Info(title = "Visit service API", description = "API documentation for Visit Service", version = "1.0.0"), security = {
    @SecurityRequirement(name = "Bearer Auth") })
public class SwaggerConfig {
}
