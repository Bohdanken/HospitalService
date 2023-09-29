package ukma.edu.ua.HospitalApp.api.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ukma.edu.ua.HospitalApp.shared.JWTService;

import java.util.Map;

@Service
public class AuthService {
    @Autowired
    public JWTService jwtService;

    private String databaseURL;

    public TokenResponse login() {
        System.out.println("Making request to db by URL: " + databaseURL);
        return new TokenResponse(
            jwtService.generateAccessToken(),
            jwtService.generateRefreshToken()
        );
    }

    @Autowired
    public void setDatabaseURL(Map<String, String> envVars) {
        this.databaseURL = envVars.get("DATABASE_URL");
    }

    public record TokenResponse(String accessToken, String refreshToken) {
    }
}
