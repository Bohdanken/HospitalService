package HospitalApp;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.google.maps.PlaceAutocompleteRequest;
import com.google.maps.PlacesApi;
import com.google.maps.errors.ApiException;
import com.google.maps.model.AutocompletePrediction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ukma.edu.ua.HospitalApp.repositories.PatientRepository;
import ukma.edu.ua.HospitalApp.services.PatientService;

import java.io.IOException;

class ApiTest {
    @Mock
    private PatientRepository patientRepository;
    private final PatientService patientService = new PatientService(patientRepository);

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
