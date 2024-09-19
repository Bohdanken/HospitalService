package ukma.edu.ua.HospitalApp;
/*
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Collections;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest
@AutoConfigureMockMvc
@Disabled
public class SecurityRolesTest {

  @Autowired
  private MockMvc mockMvc;

  private void setSecurityContextWithRole(String role) {
    UserDetails userDetails = new User("user", "password",
        Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + role)));
    SecurityContextHolder.getContext().setAuthentication(
        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities())
    );
  }

  @BeforeEach
  public void setUp() {
    SecurityContextHolder.clearContext();
  }

  // Test access for ADMIN role
  @Test
  public void adminAccessTest() throws Exception {
    setSecurityContextWithRole("ADMIN");
    mockMvc.perform(get("/admin"))
        .andExpect(status().isOk());
  }

  @Test
  public void doctorAccessTest() throws Exception {
    setSecurityContextWithRole("DOCTOR");
    mockMvc.perform(get("/doctor"))
        .andExpect(status().isOk());
  }

  @Test
  public void patientAccessTest() throws Exception {
    setSecurityContextWithRole("PATIENT");
    mockMvc.perform(get("/patient"))
        .andExpect(status().isOk());
  }

  @Test
  public void unauthorizedAccessTest() throws Exception {
    setSecurityContextWithRole("PATIENT");
    mockMvc.perform(get("/admin"))
        .andExpect(status().isForbidden());
  }
}

 */
