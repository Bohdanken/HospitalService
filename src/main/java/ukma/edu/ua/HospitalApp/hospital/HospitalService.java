package ukma.edu.ua.HospitalApp.services;

import java.util.List;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ukma.edu.ua.HospitalApp.models.Hospital;
import ukma.edu.ua.HospitalApp.repositories.HospitalRepository;

// TODO: add proper types
@Service
@RequiredArgsConstructor
public class HospitalService {
  private final HospitalRepository hospitalRepository;

  public Hospital createHospital(Hospital hospital) {
    return hospitalRepository.save(hospital);
  }
  public Hospital updateHospital(long id, Hospital hospital) {
    Hospital depDB = hospitalRepository.findById(id).get();

    if (Objects.nonNull(hospital.getName()) && !"".equalsIgnoreCase(hospital.getName())) {
      depDB.setName(hospital.getName());
    }
    return hospitalRepository.save(depDB);
  }
  public List<Hospital> getAllHospitals() {
    return hospitalRepository.findAll();
  }
  public void deleteHospital(Hospital hospital) {
    hospitalRepository.delete(hospital);
  }
}
