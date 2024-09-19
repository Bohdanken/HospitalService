package ukma.edu.ua.HospitalApp;

import static org.junit.jupiter.api.Assertions.assertFalse;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import ukma.edu.ua.HospitalApp.patient.repositories.PatientDetailsRepository;
import ukma.edu.ua.HospitalApp.patient.services.PatientServiceInternal;

@Disabled
class ApiTest {
  @Mock
  private PatientDetailsRepository doctorRepository;

  private final PatientServiceInternal patientService = new PatientServiceInternal(doctorRepository);

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
