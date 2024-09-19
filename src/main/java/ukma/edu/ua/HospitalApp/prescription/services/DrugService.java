package ukma.edu.ua.HospitalApp.prescription.services;

import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import ukma.edu.ua.HospitalApp.entities.Drug;
import ukma.edu.ua.HospitalApp.prescription.repositories.DrugRepository;

// TODO: add proper types
@Service
@RequiredArgsConstructor
public class DrugService {
  private final DrugRepository drugRepository;

  public Drug createDrug(Drug drug) {
    return drugRepository.save(drug);
  }

  public Drug updateDrug(long id, Drug drug) {
    Drug depDB = drugRepository.findById(id).get();

    if (Objects.nonNull(drug.getName()) && !"".equalsIgnoreCase(drug.getName())) {
      depDB.setName(drug.getName());
    }

    return drugRepository.save(depDB);
  }

  public void deleteDrug(Drug drug) {
    drugRepository.delete(drug);
  }
}
