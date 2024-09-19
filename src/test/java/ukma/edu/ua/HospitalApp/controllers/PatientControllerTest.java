package ukma.edu.ua.HospitalApp.controllers;
/*
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import ukma.edu.ua.HospitalApp.patient.PatientController;
import ukma.edu.ua.HospitalApp.doctor.internal.DoctorDetailsRepository;
import ukma.edu.ua.HospitalApp.patient.PatientService;
import ukma.edu.ua.HospitalApp.prescription.PrescriptionService;

@ContextConfiguration(classes = { PatientController.class, PatientService.class })
@AutoConfigureMockMvc(addFilters = false)
@Import({ PatientService.class })
@WebMvcTest
@Disabled
public class PatientControllerTest {
  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private PrescriptionService prescriptionService;

  @MockBean
  private DoctorDetailsRepository doctorRepository;

  @Autowired
  private PatientService patientService;

  @Test
  void testGetAddressSuggestions() throws Exception {
    String testAddress = "Dalveen street 2";
    String[] mockResponse = { ", Test City, TC" };
    // given(patientService.addressOptions(testAddress)).willReturn(mockResponse);

    mockMvc.perform(get("/patient/address-suggestions")
        .param("address", testAddress)).andDo(print())
        .andExpect(status().isOk());
  }
}

 */
