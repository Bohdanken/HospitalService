package ukma.edu.ua.HospitalApp.config;

import java.text.SimpleDateFormat;
import java.util.Date;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import ukma.edu.ua.HospitalApp.repositories.DrugRepository;

@Configuration
@EnableScheduling
@RequiredArgsConstructor
public class HealthCheckConfig {
  private final Logger logger = LogManager.getLogger();

  private final DrugRepository drugRepository;

  @Scheduled(fixedDelay = 3000)
  public void logHeathCheck() {
    var dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    var dateStr = dateFormat.format(new Date());
    logger.info("[" + dateStr + "]" + " Up and running");
  }

  @Scheduled(cron = "0 */1 * * * *")
  public void log() {
    var totalDrugs = drugRepository.count();
    var dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    var dateStr = dateFormat.format(new Date());
    logger.info("[" + dateStr + "]" + " Total drugs registered in system: " + totalDrugs);
  }
}
