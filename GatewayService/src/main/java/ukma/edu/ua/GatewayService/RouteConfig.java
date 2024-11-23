package ukma.edu.ua.GatewayService;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;

@Configuration
public class RouteConfig {
    @Bean
    RouteLocator customRouteLocator(RouteLocatorBuilder builder){
        return builder.routes()
                // AuthService Route
                .route("auth-service", r -> r.path("/auth/**", "/auth/swagger/**", "/auth/swagger-ui/**", "/v3/api-docs/**")
                        .filters(f -> f
                                .addRequestHeader("X-Request-Service", "Auth-Service")
                                .rewritePath("/auth/(?<segment>.*)", "/${segment}"))
                        .uri("lb://AuthService"))

                // VisitService Route
                .route("visit-service", r -> r.path("/visit/**", "/visit/swagger/**", "/visit/swagger-ui/**", "/swagger-json/**" ,"/v3/api-docs/**")
                        .filters(f -> f
                                .addRequestHeader("X-Request-Service", "Visit-Service")
                                .rewritePath("/visit/(?<segment>.*)", "/${segment}"))
                        .uri("lb://VisitService"))

                // PrescriptionMicroservice Route
                .route("prescription-service", r -> r.path("/prescription/**", "/prescription/swagger/**", "/prescription/swagger-ui/**", "/swagger-json/**","/v3/api-docs/**")
                        .filters(f -> f
                                .addRequestHeader("X-Request-Service", "Prescription-Service")
                                .rewritePath("/prescription/(?<segment>.*)", "/${segment}"))
                        .uri("lb://PrescriptionMicroservice"))
                .build();
    }
}
