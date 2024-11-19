package ukma.edu.ua.MedicineSourceService.config;

import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;

@Configuration
@SecurityScheme(type = SecuritySchemeType.APIKEY, scheme = "apikey", in = SecuritySchemeIn.HEADER, name = "X-API-KEY", description = "All endpoints require X-API-KEY header to be a part of request")
@OpenAPIDefinition(info = @Info(title = "Medicine source service API", description = "API documentation for Medicine source service", version = "1.0.0"), security = {
		@SecurityRequirement(name = "Bearer Auth") })
public class OpenApiConfig {
}
