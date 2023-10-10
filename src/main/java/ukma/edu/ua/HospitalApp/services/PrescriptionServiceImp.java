package ukma.edu.ua.HospitalApp.services;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ukma.edu.ua.HospitalApp.Database.PrescriptionDTO;
import ukma.edu.ua.HospitalApp.Database.PrescriptionRepository;
import ukma.edu.ua.HospitalApp.models.Prescription;

import java.util.Objects;

@Service
public class PrescriptionServiceImp implements PrescriptionService {
    ModelMapper modelMapper;
    private PrescriptionRepository prescriptionRepository;

    @Autowired
    public PrescriptionServiceImp(PrescriptionRepository prescriptionRepository, ModelMapper modelMapper) {
        this.prescriptionRepository = prescriptionRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Prescription createPrescription(Prescription prescription) {
        return prescriptionRepository.save(prescription);
    }

    // Not full update
    @Override
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

    @Override
    public void deletePrescription(Prescription prescription) {
        prescriptionRepository.delete(prescription);
    }
}
