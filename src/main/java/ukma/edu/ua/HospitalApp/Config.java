package ukma.edu.ua.HospitalApp;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
@ComponentScan("ukma.edu.ua.HospitalApp.injection")
public class Config {

    @Bean
    public Map<String, String> envVariables() {
        var variables = new HashMap<String, String>();
        variables.put("PORT", "8081");
        variables.put("DATABASE_URL", "some-db-url");

        return variables;
    }
}
