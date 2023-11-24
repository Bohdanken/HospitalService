package HospitalApp;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import ukma.edu.ua.HospitalApp.api.patient.PatientController;
import ukma.edu.ua.HospitalApp.repositories.PatientRepository;
import ukma.edu.ua.HospitalApp.services.PatientService;
import ukma.edu.ua.HospitalApp.services.PrescriptionService;

@ContextConfiguration(classes= {PatientController.class, PatientService.class} )
@AutoConfigureMockMvc(addFilters = false)
@Import({PatientService.class})
@WebMvcTest
public class PatientControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private PrescriptionService prescriptionService;
    @MockBean
    private PatientRepository patientRepository;
    @Autowired
    private PatientService patientService;
    @Test
    void testGetAddressSuggestions() throws Exception {
        String testAddress = "Dalveen street 2";
        String[] mockResponse = {", Test City, TC"};
        //given(patientService.addressOptions(testAddress)).willReturn(mockResponse);

        mockMvc.perform(get("/patient/address-suggestions")
                        .param("address", testAddress)).andDo(print())
                .andExpect(status().isOk());
    }
}
