package ukma.edu.ua.HospitalApp.patient.services;

import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.errors.ApiException;
import com.google.maps.model.GeocodingResult;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import ukma.edu.ua.HospitalApp.entities.PatientDetails;
import ukma.edu.ua.HospitalApp.exceptions.NotFoundException;
import ukma.edu.ua.HospitalApp.patient.dto.PatientDTO;
import ukma.edu.ua.HospitalApp.patient.dto.UpdatePatientBody;
import ukma.edu.ua.HospitalApp.patient.mappers.PatientMapper;
import ukma.edu.ua.HospitalApp.patient.repositories.PatientDetailsRepository;

@Service
@RequiredArgsConstructor
public class PatientServiceInternal {
  private final PatientDetailsRepository patientDetailsRepository;

  public String[] addressOptions(String address) {
    String apiKey = null;
    GeoApiContext context = new GeoApiContext.Builder()
        .apiKey(apiKey)
        .build();
    try {
      GeocodingResult[] results = getGeocodingResults(context, address);
      String[] options = new String[results.length];
      for (int i = 0; i < results.length; i++) {
        System.out.println(results[i].formattedAddress);
        options[i] = results[i].formattedAddress;
      }
      return options;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return new String[] { "No suggestions" };

  }

  private GeocodingResult[] getGeocodingResults(GeoApiContext context, String address) {
    try {
      GeocodingResult[] results = GeocodingApi.geocode(context, address).await();
      return results;
    } catch (ApiException | InterruptedException | IOException e) {
      e.printStackTrace();
      return new GeocodingResult[0];
    }
  }

  public PatientDTO updatePatient(UpdatePatientBody data, long id) {
    var patient = patientDetailsRepository.findById(id)
        .orElseThrow(() -> new NotFoundException("Patient not found"));

    patient.setFirstName(data.getFirstName());
    patient.setLastName(data.getLastName());
    patient.setPassportNumber(data.getPassportNumber());
    patient.setBirthDate(data.getBirthDate());
    patient.setAddress(data.getAddress());

    patientDetailsRepository.save(patient);

    return toPatientPropertiesDTO(patient);
  }

  public PatientDTO toPatientPropertiesDTO(PatientDetails patient) {
    return PatientMapper.INSTANCE.patientToPatientDTO(patient);
  }
}
