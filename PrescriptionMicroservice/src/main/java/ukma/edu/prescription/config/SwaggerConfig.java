package ukma.edu.prescription.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("Prescription API")
                        .version("v1.0")
                        .description("API documentation for the Prescription service"))
                // Add API Key as a security scheme
                .addSecurityItem(new SecurityRequirement().addList("apiKey"))
                .components(new io.swagger.v3.oas.models.Components()
                        .addSecuritySchemes("apiKey", new SecurityScheme()
                                .name("X-API-KEY")
                                .type(SecurityScheme.Type.APIKEY)
                                .in(SecurityScheme.In.HEADER)
                                .description("API Key for authentication")));
    }
}
