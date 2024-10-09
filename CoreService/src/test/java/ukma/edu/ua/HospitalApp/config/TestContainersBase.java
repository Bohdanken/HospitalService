package ukma.edu.ua.HospitalApp.config;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;

@ExtendWith({ MockitoExtension.class, TestContainersExtention.class })
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
public class TestContainersBase {
	@DynamicPropertySource
	static void configureProperties(DynamicPropertyRegistry registry) {
		registry.add("spring.datasource.url", TestContainersExtention.postgres::getJdbcUrl);
		registry.add("spring.datasource.username", TestContainersExtention.postgres::getUsername);
		registry.add("spring.datasource.password", TestContainersExtention.postgres::getPassword);
		registry.add("spring.sql.init.mode", () -> "always");
	}
}
