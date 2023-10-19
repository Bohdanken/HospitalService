package ukma.edu.ua.HospitalApp.services;

import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import ukma.edu.ua.HospitalApp.models.Hospital;
import ukma.edu.ua.HospitalApp.repositories.HospitalRepository;
import java.util.Objects;

// TODO: add proper types
@Service
@RequiredArgsConstructor
public class HospitalService {
    private final HospitalRepository hospitalRepository;

    public Hospital createHospital(Hospital hospital) {
        return hospitalRepository.save(hospital);
    }

    // Not full update

    public Hospital updateHospital(long id, Hospital hospital) {
        Hospital depDB = hospitalRepository.findById(id).get();

        if (Objects.nonNull(hospital.getName()) && !"".equalsIgnoreCase(hospital.getName())) {
            depDB.setName(hospital.getName());
        }
        return hospitalRepository.save(depDB);
    }

    public void deleteHospital(Hospital hospital) {
        hospitalRepository.delete(hospital);
    }
}
