package ukma.edu.ua.HospitalApp.shared;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class JWTService {
    private int accessTokenLifetime;
    private int refreshTokenLifetime;


    @Autowired
    public void setTokens(Environment env) {
        accessTokenLifetime = Integer.parseInt(Objects.requireNonNull(env.getProperty("app.access-token-lifetime")));
        refreshTokenLifetime= Integer.parseInt(Objects.requireNonNull(env.getProperty("app.refresh-token-lifetime")));

        System.out.println(accessTokenLifetime);
        System.out.println(refreshTokenLifetime);
    }

    public String generateAccessToken() {
        System.out.println(accessTokenLifetime);
        return null;
    }

    public String generateRefreshToken() {
        System.out.println(refreshTokenLifetime);
        return null;
    }
}
