package ukma.edu.ua.GatewayService;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RouteConfig {
	@Bean
	RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
		return builder.routes()
				.route("auth-service", r -> r.path("/auth/**")
						.filters(f -> f
								.addRequestHeader("X-Request-Service", "Auth-Service")
								.rewritePath("/auth/(?<segment>.*)", "/${segment}"))
						.uri("http://localhost:8091"))
				.route("visit-service", r -> r.path("/visit/**")
						.filters(f -> f.addRequestHeader("X-Request-Service", "Visit-Service"))
						.uri("http://localhost:8093"))
				.route("prescription-service", r -> r.path("/prescription/**")
						.filters(f -> f.addRequestHeader("X-Request-Service", "Prescription-Service"))
						.uri("http://localhost:8095"))
				.build();
	}
}
