package ukma.edu.ua.HospitalApp.services;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ukma.edu.ua.HospitalApp.dto.DrugDTO;
import ukma.edu.ua.HospitalApp.models.Drug;
import ukma.edu.ua.HospitalApp.repositories.DrugRepository;

import java.util.Objects;

@Service
public class DrugService {
    ModelMapper modelMapper;
    private DrugRepository drugRepository;

    @Autowired
    public DrugService(DrugRepository drugRepository, ModelMapper modelMapper) {
        this.drugRepository = drugRepository;
        this.modelMapper = modelMapper;
    }


    public Drug createDrug(Drug drug) {
        return drugRepository.save(drug);
    }
    //Not full update

    public Drug updateDrug(long id, Drug drug) {
        Drug depDB = drugRepository.findById(id).get();

        if (Objects.nonNull(drug.getName()) && !"".equalsIgnoreCase(drug.getName())) {
            depDB.setName(drug.getName());
        }

        return drugRepository.save(depDB);
    }
     Drug convertDtoToEntity(DrugDTO drugDTO) {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        Drug drug = modelMapper.map(drugDTO, Drug.class);
        return drug;
    }

    public void deleteDrug(Drug drug) {
        drugRepository.delete(drug);
    }

}
