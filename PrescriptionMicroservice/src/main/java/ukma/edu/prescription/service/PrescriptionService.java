package ukma.edu.prescription.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ukma.edu.prescription.model.Prescription;
import ukma.edu.prescription.repositories.PrescriptionRepository;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PrescriptionService {

    private final PrescriptionRepository prescriptionRepository;

    @Autowired
    public PrescriptionService(PrescriptionRepository prescriptionRepository) {
        this.prescriptionRepository = prescriptionRepository;
    }

    public Prescription createPrescription(Long patientId, String prescriptionDetails) {
        // Create and save the Prescription entity
        Prescription prescription = Prescription.builder()
                .dateOfIssue(new Date())
                .patientId(patientId)
                .prescriptionDetails(prescriptionDetails)  // Store prescription details as a string
                .build();

        return prescriptionRepository.save(prescription);
    }


    public Optional<String> getPrescriptionDetailsByPatientId(Long patientId) {
        return prescriptionRepository.findByPatientId(patientId)
                .stream()
                .findFirst()
                .map(Prescription::getPrescriptionDetails);
    }


    public void deletePrescription(Long id) {
        prescriptionRepository.deleteById(id);
    }
}
