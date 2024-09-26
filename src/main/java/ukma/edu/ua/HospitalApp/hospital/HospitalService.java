package ukma.edu.ua.HospitalApp.hospital;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ukma.edu.ua.HospitalApp.entities.HospitalDTO;
import ukma.edu.ua.HospitalApp.entities.internal.Hospital;
import ukma.edu.ua.HospitalApp.hospital.mappers.HospitalMapper;
import ukma.edu.ua.HospitalApp.hospital.repositories.HospitalRepository;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HospitalService {

  private final HospitalRepository hospitalRepository;
  private final HospitalMapper hospitalMapper;


  public HospitalDTO createHospital(HospitalDTO hospitalDTO) {
    Hospital hospital = hospitalMapper.hospitalDTOToHospital(hospitalDTO);
    Hospital savedHospital = hospitalRepository.save(hospital);
    return hospitalMapper.hospitalToHospitalDTO(savedHospital);
  }

  public HospitalDTO updateHospital(long id, HospitalDTO hospitalDTO) {
    Hospital existingHospital = hospitalRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Hospital not found"));

    if (Objects.nonNull(hospitalDTO.getName()) && !"".equalsIgnoreCase(hospitalDTO.getName())) {
      existingHospital.setName(hospitalDTO.getName());
    }
    if (Objects.nonNull(hospitalDTO.getAddress()) && !"".equalsIgnoreCase(hospitalDTO.getAddress())) {
      existingHospital.setAddress(hospitalDTO.getAddress());
    }

    Hospital updatedHospital = hospitalRepository.save(existingHospital);
    return hospitalMapper.hospitalToHospitalDTO(updatedHospital);
  }


  public List<HospitalDTO> getAllHospitals() {
    List<Hospital> hospitals = hospitalRepository.findAll();
    // Convert list of entities to list of DTOs
    return hospitals.stream()
            .map(hospitalMapper::hospitalToHospitalDTO)
            .collect(Collectors.toList());
  }


  public void deleteHospital(HospitalDTO hospitalDTO) {
    Hospital hospital = hospitalMapper.hospitalDTOToHospital(hospitalDTO); // Convert DTO to entity
    hospitalRepository.delete(hospital);
  }
}
