package HospitalApp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ukma.edu.ua.HospitalApp.api.patient.PatientController;
import ukma.edu.ua.HospitalApp.services.PatientService;
import ukma.edu.ua.HospitalApp.services.PrescriptionService;

@SpringBootTest(classes = AppConfig.class)
public class ManualBeanConfigurationTest {

  @Autowired
  private PatientController patientController;

  @MockBean
  private PatientService patientService;

  @MockBean
  private PrescriptionService prescriptionService;

//    @Test
//    void testUpdatePatient() {
//        UpdatePatientBody requestBody = new UpdatePatientBody();
//        Long patientId = 1L; // Припустимий ID пацієнта
//        PatientDTO expectedPatientDTO = new PatientDTO();
//        when(patientService.updatePatient(any(UpdatePatientBody.class), eq(patientId)))
//                .thenReturn(expectedPatientDTO);
//
//        PatientDTO result = patientController.updatePatient(patientId, requestBody);
//
//        assertEquals(expectedPatientDTO, result);
//        verify(patientService).updatePatient(requestBody, patientId);
//    }

}
