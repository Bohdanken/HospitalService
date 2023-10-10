package ukma.edu.ua.HospitalApp.services;

import org.modelmapper.convention.MatchingStrategies;
import ukma.edu.ua.HospitalApp.Database.DrugDTO;
import ukma.edu.ua.HospitalApp.models.Drug;

public interface DrugService {
    Drug createDrug(Drug drug);

    Drug updateDrug(long id, Drug drug);

    void deleteDrug(Drug drug);
}
