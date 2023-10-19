package ukma.edu.ua.HospitalApp.services;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ukma.edu.ua.HospitalApp.dto.HospitalDTO;
import ukma.edu.ua.HospitalApp.models.Hospital;
import ukma.edu.ua.HospitalApp.repositories.HospitalRepository;

import java.util.Objects;

@Service
public class HospitalService{
    ModelMapper modelMapper;
    private HospitalRepository hospitalRepository;

    @Autowired
    public HospitalService(HospitalRepository hospitalRepository, ModelMapper modelMapper) {
        this.hospitalRepository = hospitalRepository;
        this.modelMapper = modelMapper;
    }


    public Hospital createHospital(Hospital hospital) {
        return hospitalRepository.save(hospital);
    }

    //Not full update

    public Hospital updateHospital(long id, Hospital hospital) {
        Hospital depDB = hospitalRepository.findById(id).get();

        if (Objects.nonNull(hospital.getName()) && !"".equalsIgnoreCase(hospital.getName())) {
            depDB.setName(hospital.getName());
        }
        return hospitalRepository.save(depDB);
    }

    Hospital convertDtoToEntity(HospitalDTO hospitalDTO) {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        Hospital hospital = modelMapper.map(hospitalDTO, Hospital.class);
        return hospital;
    }


    public void deleteHospital(Hospital hospital) {
        hospitalRepository.delete(hospital);
    }

}
