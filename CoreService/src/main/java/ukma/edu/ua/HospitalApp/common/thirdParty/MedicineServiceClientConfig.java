package ukma.edu.ua.HospitalApp.common.thirdParty;

import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MedicineServiceClientConfig {
	@Value("${app.medicine-source-service.url}")
	private String url;

	@Value("${app.medicine-source-service.key}")
	private String key;

	@Bean
	WebClient medicineServiceClient() {
		return WebClient.builder().baseUrl(url).defaultHeader("X-API-KEY", key).build();
	}
}
