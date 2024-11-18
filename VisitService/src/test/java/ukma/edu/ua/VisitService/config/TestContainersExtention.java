package ukma.edu.ua.HospitalApp.config;

import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.testcontainers.containers.PostgreSQLContainer;
import org.junit.jupiter.api.extension.AfterAllCallback;

public class TestContainersExtention implements BeforeAllCallback, AfterAllCallback {
	public static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:16-alpine");

	@Override
	public void beforeAll(ExtensionContext context) throws Exception {
		postgres.start();
	}

	@Override
	public void afterAll(ExtensionContext context) throws Exception {
		postgres.close();
	}
}
