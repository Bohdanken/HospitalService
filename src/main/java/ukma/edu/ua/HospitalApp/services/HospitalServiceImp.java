package ukma.edu.ua.HospitalApp.services;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ukma.edu.ua.HospitalApp.Database.HospitalDTO;
import ukma.edu.ua.HospitalApp.Database.HospitalRepository;
import ukma.edu.ua.HospitalApp.models.Hospital;

import java.util.Objects;

@Service
public class HospitalServiceImp implements HospitalService {
    ModelMapper modelMapper;
    private HospitalRepository hospitalRepository;

    @Autowired
    public HospitalServiceImp(HospitalRepository hospitalRepository, ModelMapper modelMapper) {
        this.hospitalRepository = hospitalRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Hospital createHospital(Hospital hospital) {
        return hospitalRepository.save(hospital);
    }

    //Not full update
    @Override
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

    @Override
    public void deleteHospital(Hospital hospital) {
        hospitalRepository.delete(hospital);
    }

}
