package ukma.edu.ua.HospitalApp.common.thirdParty;

import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PrescriptionServiceClient {
	@Value("app.prescription-service.url")
	private String url;

	@Value("app.prescription-service.key")
	private String key;

	@Bean("prescriptionServiceClient")
	WebClient prescriptionServiceClient() {
		return WebClient.builder().baseUrl(url).defaultHeader("X-API-KEY", key).build();
	}
}
