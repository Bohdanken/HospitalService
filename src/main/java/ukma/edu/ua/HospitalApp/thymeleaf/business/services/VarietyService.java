package ukma.edu.ua.HospitalApp.thymeleaf.business.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ukma.edu.ua.HospitalApp.thymeleaf.business.entities.Variety;
import ukma.edu.ua.HospitalApp.thymeleaf.business.entities.repositories.VarietyRepository;

@Service
public class VarietyService {

    @Autowired
    private VarietyRepository varietyRepository;


    public VarietyService() {
        super();
    }



    public List<Variety> findAll() {
        return this.varietyRepository.findAll();
    }

    public Variety findById(final Integer id) {
        return this.varietyRepository.findById(id);
    }

}