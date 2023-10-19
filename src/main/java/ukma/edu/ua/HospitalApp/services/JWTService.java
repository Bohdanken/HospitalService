package ukma.edu.ua.HospitalApp.services;

import org.springframework.beans.factory.annotation.Value;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import java.nio.file.Files;
import java.nio.file.Paths;

@Component
public class JWTService {
  @Value("${app.access-token-lifetime}")
  private int accessTokenLifetime;

  @Value("${app.refresh-token-lifetime}")
  private int refreshTokenLifetime;

  public String generateAccessToken() {
    System.out.println(accessTokenLifetime);
    return null;
  }

  public String generateRefreshToken() {
    System.out.println(refreshTokenLifetime);
    return null;
  }
  public static String getApiKey(String jsonKey){
    try {
      String content = new String(Files.readAllBytes(Paths.get("config.json")));
      JSONObject json = new JSONObject(content);
      String apiKey = json.getString(jsonKey);
      System.out.println("API Key: " + apiKey);
      return apiKey;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return "0";
  }
}
