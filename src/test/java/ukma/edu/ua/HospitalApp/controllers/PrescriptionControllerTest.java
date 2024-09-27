package ukma.edu.ua.HospitalApp.controllers;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import ukma.edu.ua.HospitalApp.ControllerTest;
import ukma.edu.ua.HospitalApp.prescription.PrescriptionService;
import ukma.edu.ua.HospitalApp.prescription.controllers.PrescriptionController;
import ukma.edu.ua.HospitalApp.prescription.dto.CreatePresriptionBody;

@WebMvcTest(controllers = PrescriptionController.class)
public class PrescriptionControllerTest extends ControllerTest {
  @MockBean
  private PrescriptionService prescriptionService;

  @Autowired
  private PrescriptionController prescriptionController;

  @Test
  @DisplayName("Smoke test on prescriptions controller")
  public void smoke() {
    assertNotNull(prescriptionController);
  }

  @Test
  @DisplayName("It should respond with 201 when creating prescription")
  @WithMockUser(authorities = "DOCTOR")
  public void shouldCreatePrescription() throws Exception {
    var body = new CreatePresriptionBody();
    body.setPatientId(Long.valueOf(1));
    body.setDrugs(List.of(Long.valueOf(1), Long.valueOf(2)));

    //when(prescriptionService.createPresription(any())).thenReturn(new PrescriptionDTO());

    mvc.perform(
        post("/api/prescription/issue")
            .contentType(MediaType.APPLICATION_JSON)
            .content(new ObjectMapper().writeValueAsString(body)))
        .andExpect(status().isCreated())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.id").hasJsonPath())
        .andExpect(jsonPath("$.patientId").hasJsonPath())
        .andExpect(jsonPath("$.dateOfIssue").hasJsonPath())
        .andExpect(jsonPath("$.drugs").hasJsonPath());
  }

  @Test
  @DisplayName("It should fail with 403 when no auth is provided when creating prescription")
  public void shouldFailOnCreatePrescription() throws Exception {
    mvc
        .perform(post("/api/prescription/issue"))
        .andExpect(status().isForbidden());
  }

  @Test
  @DisplayName("It should respond with 200 when deleting prescription")
  @WithMockUser(authorities = "DOCTOR")
  public void shouldDeletePrescription() throws Exception {
    mvc.perform(delete("/api/prescription/1")).andExpect(status().isOk());
  }

  @Test
  @DisplayName("It should fail with 403 when deleting prescription without auth")
  public void shouldFailOnDeletePrescription() throws Exception {
    mvc.perform(delete("/api/prescription/1")).andExpect(status().isForbidden());
  }
}
