package ukma.edu.ua.HospitalApp.config.cache;

import com.github.benmanes.caffeine.cache.Caffeine;
import java.time.Duration;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableCaching
public class CacheConfig {
  @Bean
  CacheManager cacheManager() {
    CaffeineCacheManager cacheManager = new CaffeineCacheManager("PatientPrescriptions");
    cacheManager.setCaffeine(Caffeine
        .newBuilder()
        .expireAfterWrite(Duration.ofSeconds(15))
        .initialCapacity(10)
        .maximumSize(20)
        .recordStats());
    return cacheManager;
  }
}
