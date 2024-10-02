package ukma.edu;

import jakarta.annotation.PostConstruct;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.server.ResponseStatusException;
import ukma.edu.prescription.dto.CreatePrescriptionBody;
import ukma.edu.prescription.dto.PrescriptionDTO;
import ukma.edu.prescription.service.PrescriptionService;


import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
class PrescriptionControllerTest {

  @Autowired
  private WebApplicationContext webApplicationContext;

  private MockMvc mockMvc;

  @MockBean
  private PrescriptionService prescriptionService;

  @PostConstruct
  void setup() {
    mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
  }

  @Test
  void createPrescription_ShouldReturnPrescriptionDTO_WhenRequestIsValid() throws Exception {
    CreatePrescriptionBody body = CreatePrescriptionBody.builder()
            .patientId(1L)
            .patientFirstName("John")
            .patientLastName("Doe")
            .drugs(new CreatePrescriptionBody.Drug[]{
                    CreatePrescriptionBody.Drug.builder()
                            .name("Aspirin")
                            .timesPerDay(2)
                            .build()
            })
            .build();
    PrescriptionDTO expectedPrescription = new PrescriptionDTO();
    when(prescriptionService.createPrescription(any(CreatePrescriptionBody.class))).thenReturn(expectedPrescription);
    mockMvc.perform(post("/api/prescriptions/create")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content("{\"patientId\":1,\"patientFirstName\":\"John\",\"patientLastName\":\"Doe\",\"drugs\":[{\"name\":\"Aspirin\",\"timesPerDay\":2}]}"))
            .andExpect(status().isCreated())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON));

    verify(prescriptionService, times(1)).createPrescription(any(CreatePrescriptionBody.class));
  }

  @Test
  void getLatestPrescriptionByPatientId_ShouldReturnPrescriptionDetails_WhenPatientExists() throws Exception {
    Long patientId = 1L;
    String prescriptionDetails = "Prescription details for patient";
    when(prescriptionService.getLatestPrescriptionByPatientId(patientId)).thenReturn(prescriptionDetails);
    mockMvc.perform(get("/api/prescriptions/patient/{patientId}", patientId))
            .andExpect(status().isOk())
            .andExpect(header().string(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=prescription.txt"))
            .andExpect(content().contentType(MediaType.TEXT_PLAIN))
            .andExpect(content().string(prescriptionDetails));

    verify(prescriptionService, times(1)).getLatestPrescriptionByPatientId(patientId);
  }

  @Test
  void getPrescriptionById_ShouldReturnPrescriptionDetails_WhenIdIsValid() throws Exception {
    Long id = 1L;
    String prescriptionDetails = "Prescription details";
    when(prescriptionService.getPrescriptionById(id)).thenReturn(prescriptionDetails);
    mockMvc.perform(get("/api/prescriptions/{id}", id))
            .andExpect(status().isOk())
            .andExpect(header().string(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=prescription.txt"))
            .andExpect(content().contentType(MediaType.TEXT_PLAIN))
            .andExpect(content().string(prescriptionDetails));

    verify(prescriptionService, times(1)).getPrescriptionById(id);
  }

  @Test
  void deletePrescription_ShouldReturnNoContent_WhenPrescriptionExists() throws Exception {
    Long id = 1L;
    doNothing().when(prescriptionService).deletePrescription(id);
    mockMvc.perform(delete("/api/prescriptions/{id}", id))
            .andExpect(status().isNoContent());

    verify(prescriptionService, times(1)).deletePrescription(id);
  }

  @Test
  void deletePrescription_ShouldReturnNotFound_WhenPrescriptionDoesNotExist() throws Exception {
    Long id = 1L;
    doThrow(new ResponseStatusException(HttpStatus.NOT_FOUND, "Prescription not found"))
            .when(prescriptionService).deletePrescription(id);
    mockMvc.perform(delete("/api/prescriptions/{id}", id))
            .andExpect(status().isNotFound());
    verify(prescriptionService, times(1)).deletePrescription(id);
  }
}