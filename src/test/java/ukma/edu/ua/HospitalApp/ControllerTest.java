package ukma.edu.ua.HospitalApp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import ukma.edu.ua.HospitalApp.auth.CustomUserDetailsService;
import ukma.edu.ua.HospitalApp.auth.JWTService;
import ukma.edu.ua.HospitalApp.auth.SpringSecurityConfiguration;
import ukma.edu.ua.HospitalApp.auth.UserRepository;

@SuppressWarnings("OneTopLevelClassCheck")
@TestConfiguration
class SecurityTestConfig {
  @MockBean
  UserRepository userRepository;

  @Bean
  JWTService jwtService() {
    return new JWTService();
  }

  @Bean
  UserDetailsService userDetailsService() {
    return new CustomUserDetailsService(userRepository);
  }
}

@TestPropertySource(properties = {
    "app.jwt.access-token-secret=ZsjE6p5Ir2UWbxLFgml2UhVL9WDEo1CN3NRzBD72WJw",
    "app.jwt.refresh-token-secret=2BRBFR+Nlz63Np0MOqAHOyVzM/xi9OYZFme6lI2n7wU=",
    "app.jwt.access-token-lifetime=10000",
    "app.jwt.refresh-token-lifetime=15000",
    "app.prefix=/api"
})
@ExtendWith(MockitoExtension.class)
@Import({ SecurityTestConfig.class, SpringSecurityConfiguration.class })
@WebAppConfiguration
public class ControllerTest {
  protected MockMvc mvc;

  @Autowired
  protected WebApplicationContext context;

  @Autowired
  protected UserRepository userRepository;

  @BeforeEach
  public void setupEach() {
    mvc = MockMvcBuilders
        .webAppContextSetup(context)
        .apply(SecurityMockMvcConfigurers.springSecurity())
        .build();
  }
}
