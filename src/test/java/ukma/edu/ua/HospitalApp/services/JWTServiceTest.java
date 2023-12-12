package ukma.edu.ua.HospitalApp.services;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;
import ukma.edu.ua.HospitalApp.config.auth.JWTService;

@DisplayName("JWTService unit test")
public class JWTServiceTest {
  private static JWTService jwtService;

  @BeforeAll
  public static void setup() {
    jwtService = new JWTService();
    ReflectionTestUtils.setField(
        jwtService,
        "accessTokenSecret",
        "ZsjE6p5Ir2UWbxLFgml2UhVL9WDEo1CN3NRzBD72WJw=");
    ReflectionTestUtils.setField(
        jwtService,
        "refreshTokenSecret",
        "2BRBFR+Nlz63Np0MOqAHOyVzM/xi9OYZFme6lI2n7wU=");
    ReflectionTestUtils.setField(jwtService, "accessTokenLifetime", 10000);
    ReflectionTestUtils.setField(jwtService, "refreshTokenLifetime", 15000);
  }

  @Test
  @Order(1)
  @DisplayName("It should return secret without errors")
  public void getSecretKey() {
    assertDoesNotThrow(
        () -> ReflectionTestUtils.invokeMethod(jwtService, "getSecret"),
        "An error occured while retrieving secret");

    var res = ReflectionTestUtils.invokeMethod(jwtService, "getSecret");
    assertNotNull(res, "Secret key is null");
  }

  @Test
  @Order(2)
  @DisplayName("It should generate a JWT token without exceptions")
  public void tokenIsGeneratedWithoutExceptions() {
    assertDoesNotThrow(() -> jwtService.generateToken("some@email.com"));
    assertDoesNotThrow(() -> jwtService.generateToken("asdasdasd"));
    assertDoesNotThrow(() -> jwtService.generateToken(""));
    assertDoesNotThrow(() -> jwtService.generateToken(null));
  }

  @Test
  @Order(3)
  @DisplayName("It should generate a JWT token and return a valid token response")
  public void tokenIsGenerated() {
    var result = jwtService.generateToken("some@email.com");
    assertNotNull(result, "Result is null");

    var token = result.accessToken();
    assertNotNull(token, "Token is null");
    assertNotEquals(token, "", "Token is an empty string");

    var tokenData = token.split("\\.");
    assertEquals(tokenData.length, 3, "Token does not consist of 3 parts");
    assertNotEquals(tokenData[0], "", "Token does not contain header");
    assertNotEquals(tokenData[1], "", "Token does not contain body");
    assertNotEquals(tokenData[2], "", "Token does not contain secret");
  }

  @Test
  @Order(4)
  @DisplayName("It should return valid email from a valid token")
  public void getEmailFromToken() {
    var token = jwtService.generateToken("some@email.com");
    String email = jwtService.getUserEmailFromToken(token.accessToken());
    assertEquals(email, "some@email.com", "Email output is incorrect");
  }

  @Test
  @Order(5)
  @DisplayName("It should return null and not throw an error if a token is invalid")
  public void getNullIfTokenIsInvalid() {
    String email = assertDoesNotThrow(() -> jwtService.getUserEmailFromToken(""));
    assertNull(email, "Null is not returned on incorrect token");
  }
}
