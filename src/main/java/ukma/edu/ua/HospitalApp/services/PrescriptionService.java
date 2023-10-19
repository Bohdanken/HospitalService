package ukma.edu.ua.HospitalApp.services;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ukma.edu.ua.HospitalApp.dto.PrescriptionDTO;
import ukma.edu.ua.HospitalApp.models.Prescription;
import ukma.edu.ua.HospitalApp.repositories.PrescriptionRepository;

import java.util.Objects;

@Service
public class PrescriptionService {
    ModelMapper modelMapper;
    private PrescriptionRepository prescriptionRepository;


    @Autowired
    public PrescriptionService(PrescriptionRepository prescriptionRepository, ModelMapper modelMapper) {
        this.prescriptionRepository = prescriptionRepository;
        this.modelMapper = modelMapper;
    }


    public Prescription createPrescription(Prescription prescription) {
        return prescriptionRepository.save(prescription);
    }

    // Not full update

    public Prescription updatePrescription(long id, Prescription prescription) {
        Prescription depDB = prescriptionRepository.findById(id).get();

        if (Objects.nonNull(prescription.getName()) && !"".equalsIgnoreCase(prescription.getName())) {
            depDB.setName(prescription.getName());
        }
        return prescriptionRepository.save(depDB);
    }

    Prescription convertDtoToEntity(PrescriptionDTO prescriptionDTO) {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        Prescription prescription = modelMapper.map(prescriptionDTO, Prescription.class);
        return prescription;
    }


    public void deletePrescription(Prescription prescription) {
        prescriptionRepository.delete(prescription);
    }
}
