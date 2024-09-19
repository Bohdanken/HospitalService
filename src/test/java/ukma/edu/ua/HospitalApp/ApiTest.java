package ukma.edu.ua.HospitalApp;

import static org.junit.jupiter.api.Assertions.assertFalse;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import ukma.edu.ua.HospitalApp.patient.internal.PatientDetailsRepository;
import ukma.edu.ua.HospitalApp.patient.PatientService;

@Disabled
class ApiTest {
  @Mock
  private PatientDetailsRepository doctorRepository;

  private final PatientService patientService = new PatientService(doctorRepository);

  @Test
  void testAddressOptionsForDalveenStreet() throws Exception {
    String[] answer = patientService.addressOptions("Flat 2/1, Dalveen street 2, Glasgow");
    assertFalse(answer[0].equalsIgnoreCase("No suggestions"));
  }

  @Test
  void testAddressOptionsForRomanowska() throws Exception {
    String[] answer = patientService.addressOptions("Romanowska street, Lodz");
    assertFalse(answer[0].equalsIgnoreCase("No suggestions"));
  }

  @Test
  void testAddressOptionsForRokytne() throws Exception {
    String[] answer = patientService.addressOptions("Rokytne, Kyiv region, Ukraine");
    assertFalse(answer[0].equalsIgnoreCase("No suggestions"));
  }
}
