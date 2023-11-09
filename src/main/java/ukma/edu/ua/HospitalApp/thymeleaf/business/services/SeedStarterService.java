package ukma.edu.ua.HospitalApp.thymeleaf.business.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ukma.edu.ua.HospitalApp.thymeleaf.business.entities.SeedStarter;
import ukma.edu.ua.HospitalApp.thymeleaf.business.entities.repositories.SeedStarterRepository;

@Service
public class SeedStarterService {

    @Autowired
    private SeedStarterRepository seedstarterRepository;


    public SeedStarterService() {
        super();
    }



    public List<SeedStarter> findAll() {
        return this.seedstarterRepository.findAll();
    }

    public void add(final SeedStarter seedStarter) {
        this.seedstarterRepository.add(seedStarter);
    }

}