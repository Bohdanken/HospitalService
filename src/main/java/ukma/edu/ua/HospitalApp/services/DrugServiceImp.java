package ukma.edu.ua.HospitalApp.services;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ukma.edu.ua.HospitalApp.Database.DrugDTO;
import ukma.edu.ua.HospitalApp.Database.DrugRepository;
import ukma.edu.ua.HospitalApp.models.Drug;

import java.util.Objects;

@Service
public class DrugServiceImp implements DrugService {
    ModelMapper modelMapper;
    private DrugRepository drugRepository;

    @Autowired
    public DrugServiceImp(DrugRepository drugRepository, ModelMapper modelMapper) {
        this.drugRepository = drugRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Drug createDrug(Drug drug) {
        return drugRepository.save(drug);
    }
    //Not full update
    @Override
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

    @Override
    public void deleteDrug(Drug drug) {
        drugRepository.delete(drug);
    }

}
