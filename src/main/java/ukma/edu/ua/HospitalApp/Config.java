package ukma.edu.ua.HospitalApp;

import org.modelmapper.ModelMapper;
import java.util.HashMap;
import java.util.Map;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    // @ConditionalOnSingleCandidate
    public Map<String, String> envVariables() {
        var variables = new HashMap<String, String>();
        variables.put("PORT", "8081");
        variables.put("DATABASE_URL", "some-db-url");
        return variables;
    }
}
