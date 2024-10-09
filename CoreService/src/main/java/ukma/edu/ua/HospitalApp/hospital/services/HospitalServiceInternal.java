package ukma.edu.ua.HospitalApp.hospital.services;

import java.util.List;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import ukma.edu.ua.HospitalApp.common.dto.HospitalDTO;
import ukma.edu.ua.HospitalApp.common.entities.Hospital;
import ukma.edu.ua.HospitalApp.hospital.mappers.HospitalMapper;
import ukma.edu.ua.HospitalApp.hospital.repositories.HospitalRepository;

@Service
@RequiredArgsConstructor
public class HospitalServiceInternal {
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

  public HospitalDTO toHospitalDTO(Hospital hospital) {
    return HospitalMapper.INSTANCE.hospitalToHospitalDTO(hospital);
  }
}
