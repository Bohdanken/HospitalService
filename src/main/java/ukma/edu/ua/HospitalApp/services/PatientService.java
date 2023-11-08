package ukma.edu.ua.HospitalApp.services;

import com.google.maps.*;
import com.google.maps.PlaceAutocompleteRequest.*;
import com.google.maps.errors.ApiException;
import com.google.maps.model.AutocompletePrediction;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.PlaceDetails;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ukma.edu.ua.HospitalApp.api.patient.dto.UpdatePatientBody;
import ukma.edu.ua.HospitalApp.dto.PatientDTO;
import ukma.edu.ua.HospitalApp.mappers.PatientMapper;
import ukma.edu.ua.HospitalApp.models.Patient;
import ukma.edu.ua.HospitalApp.repositories.PatientRepository;
import ukma.edu.ua.HospitalApp.shared.JWTService;
import ukma.edu.ua.HospitalApp.shared.JwtConfig;

import java.io.IOException;

@Service
public class PatientService {
  private final PatientRepository patientRepository;
  @Autowired
  public PatientService(PatientRepository patientRepository) {
    this.patientRepository = patientRepository;
  }

  public Patient createPatient(Patient patient) {
    return patientRepository.save(patient);
  }

  public String[] addressOptions(String address){
    String apiKey = JWTService.getApiKey("maps_api_key");
    GeoApiContext context = new GeoApiContext.Builder()
            .apiKey(apiKey)
            .build();
    try {
      GeocodingResult[] results = getGeocodingResults(context, address);
      String[] options=new String[results.length];
      for (int i=0;i< results.length;i++){
        System.out.println(results[i].formattedAddress);
        options[i]=results[i].formattedAddress;
      }
      return options;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return new String[]{"No suggestions"};

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
    Patient patient = patientRepository.findById(id).orElseThrow();
    patient.setFirstName(data.getFirstName());
    patient.setLastName(data.getLastName());
    patient.setAddress(data.getAddress());
    patient.setBirthDate(data.getBirthDate());
    patient.setPassportNumber(data.getPassportNumber());
    patient.setEmail(data.getEmail());
    patientRepository.save(patient);
    return toPatientDTO(patient);
  }

  public PatientDTO toPatientDTO(Patient patient) {
    return PatientMapper.INSTANCE.patientToPatientDTO(patient);
  }
}
