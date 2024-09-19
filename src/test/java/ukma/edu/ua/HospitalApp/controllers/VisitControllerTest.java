package ukma.edu.ua.HospitalApp.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import ukma.edu.ua.HospitalApp.security.JWTService;
import ukma.edu.ua.HospitalApp.user.CustomUserDetailsService;
import ukma.edu.ua.HospitalApp.visit.controllers.VisitController;
import ukma.edu.ua.HospitalApp.visit.dto.UpdateVisitBody;
import ukma.edu.ua.HospitalApp.visit.dto.VisitBody;
import ukma.edu.ua.HospitalApp.visit.dto.VisitDTO;
import ukma.edu.ua.HospitalApp.visit.services.PatientVisitService;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebAppConfiguration
@WebMvcTest(controllers = VisitController.class)
public class VisitControllerTest {
        @Autowired
        private MockMvc mvc;

        @Autowired
        private WebApplicationContext context;

        @MockBean
        private PatientVisitService patientVisitService;

        @MockBean
        private CustomUserDetailsService customUserDetailsService;

        @MockBean
        private JWTService jwtService;

        private static String updateVisitBody = null;
        private static String visitBody = null;

        @BeforeAll
        public static void setup() throws JsonProcessingException {
                var visitData = new VisitBody();
                visitData.setPatientId(2L);
                visitData.setDoctorId(1L);
                visitData.setDateOfVisit(
                                Timestamp.valueOf(LocalDateTime.of(2024, 2, 21, 16, 30)));
                visitBody = new ObjectMapper().writeValueAsString(visitData);

                var updateVisitData = new UpdateVisitBody();
                updateVisitData.setDateOfVisit(
                                Timestamp.valueOf(LocalDateTime.of(2024, 2, 22, 16, 30)));
                updateVisitBody = new ObjectMapper().writeValueAsString(updateVisitData);
        }

        @BeforeEach
        public void setupEach() {
                mvc = MockMvcBuilders
                                .webAppContextSetup(context)
                                .build();
        }

        @Test
        @Order(1)
        @DisplayName("Returns a new visit if it is successfully created")
        public void testCreateVisit() throws Exception {
                when(patientVisitService.createPatientVisit(any())).thenReturn(
                                new VisitDTO(1L, 2L, 1L,
                                                Timestamp.valueOf(LocalDateTime.of(2024, 2, 21, 16, 30))));

                mvc
                                .perform(post("/api/visit/create")
                                                .contentType(MediaType.APPLICATION_JSON)
                                                .content(visitBody))
                                .andExpect(status().isOk())
                                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                                .andExpect(jsonPath("$.id").value(1L))
                                .andExpect(jsonPath("$.patientId").value(2L))
                                .andExpect(jsonPath("$.doctorId").value(1L))
                                .andExpect(jsonPath("$.dateOfVisit").value("2024-02-21T14:30:00.000+00:00"));
        }

        @Test
        @Order(2)
        @DisplayName("Returns an array of visits")
        public void testGetVisitByPatient() throws Exception {
                VisitDTO[] visitDTOS = new VisitDTO[] {
                                new VisitDTO(1L, 2L, 1L,
                                                Timestamp.valueOf(LocalDateTime.of(2024, 2, 21, 16, 30))),
                                new VisitDTO(2L, 2L, 2L,
                                                Timestamp.valueOf(LocalDateTime.of(2024, 2, 21, 16, 30)))
                };

                when(patientVisitService.getPatientVisitsByPatient(any())).thenReturn(visitDTOS);

                mvc
                                .perform(get("/api/visit/patient/2")
                                                .contentType(MediaType.APPLICATION_JSON))
                                .andExpect(status().isOk())
                                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                                .andExpect(jsonPath("$", hasSize(2)))
                                .andExpect(jsonPath("$[0].patientId", is(2)))
                                .andExpect(jsonPath("$[1].patientId", is(2)));
        }

        @Test
        @Order(3)
        @DisplayName("Returns an array of visits")
        public void testGetVisitByDoctor() throws Exception {
                VisitDTO[] visitDTOS = new VisitDTO[] {
                                new VisitDTO(1L, 1L, 1L,
                                                Timestamp.valueOf(LocalDateTime.of(2024, 2, 21, 16, 30))),
                                new VisitDTO(2L, 2L, 1L,
                                                Timestamp.valueOf(LocalDateTime.of(2024, 2, 21, 16, 30)))
                };

                when(patientVisitService.getPatientVisitsByDoctor(any())).thenReturn(visitDTOS);

                mvc
                                .perform(get("/api/visit/doctor/1")
                                                .contentType(MediaType.APPLICATION_JSON))
                                .andExpect(status().isOk())
                                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                                .andExpect(jsonPath("$", hasSize(2)))
                                .andExpect(jsonPath("$[0].doctorId", is(1)))
                                .andExpect(jsonPath("$[1].doctorId", is(1)));
        }

        @Test
        @Order(4)
        @DisplayName("Returns a modified visit")
        public void testUpdateVisit() throws Exception {
                when(patientVisitService.updateVisit(any(), anyLong())).thenReturn(
                                new VisitDTO(1L, 2L, 1L,
                                                Timestamp.valueOf(LocalDateTime.of(2024, 2, 22, 16, 30))));

                mvc
                                .perform(put("/api/visit/1")
                                                .contentType(MediaType.APPLICATION_JSON)
                                                .content(updateVisitBody))
                                .andExpect(status().isOk())
                                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                                .andExpect(jsonPath("$.id").value(1L))
                                .andExpect(jsonPath("$.patientId").value(2L))
                                .andExpect(jsonPath("$.doctorId").value(1L))
                                .andExpect(jsonPath("$.dateOfVisit").value("2024-02-22T14:30:00.000+00:00"));
        }

        @Test
        @Order(5)
        @DisplayName("Delete a visit")
        public void testDeleteVisit() throws Exception {

                doNothing().when(patientVisitService).deletePatientVisit(anyLong());

                mvc.perform(delete("/api/visit/1"))
                                .andExpect(status().isOk());

                verify(patientVisitService).deletePatientVisit(1L);
        }
}
