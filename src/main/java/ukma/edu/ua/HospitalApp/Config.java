package ukma.edu.ua.HospitalApp;

import org.springframework.boot.autoconfigure.condition.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import ukma.edu.ua.HospitalApp.Conditions.DataBaseURLCondition;
import java.util.HashMap;
import java.util.Map;

@Configuration
@Conditional(DataBaseURLCondition.class)
@ComponentScan("ukma.edu.ua.HospitalApp.injection")
public class Config {

    @Bean
    @ConditionalOnSingleCandidate
    public Map<String, String> envVariables() {
        var variables = new HashMap<String, String>();
        variables.put("PORT", "8081");
        variables.put("DATABASE_URL", "some-db-url");
        return variables;
    }
}
