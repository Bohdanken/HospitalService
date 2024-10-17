package ukma.edu.prescription.service;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;
import ukma.edu.prescription.dto.CreatePrescriptionBody;
import ukma.edu.prescription.dto.PrescriptionDTO;
import ukma.edu.prescription.exceptions.BadRequestException;
import ukma.edu.prescription.mappers.PrescriptionMapper;
import ukma.edu.prescription.model.Prescription;
import ukma.edu.prescription.repositories.PrescriptionRepository;

import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class PrescriptionService {
    private final PrescriptionRepository prescriptionRepository;
    private final ObjectMapper mapper;

    @JmsListener(destination = "prescription-queue")
    private void createPrescriptionListener(String msg) throws JsonMappingException, JsonProcessingException {
        var data = mapper.readValue(msg, CreatePrescriptionBody.class);
        createPrescription(data);
    }

    public PrescriptionDTO createPrescription(CreatePrescriptionBody body) {
        var patientInfo = "Patient: " + body.getPatientFirstName() + " " + body.getPatientLastName() + "\n";

        var format = new SimpleDateFormat("dd MMM yyyy");
        var dateOfIssue = new Date();
        var dateInfo = "Date: " + format.format(dateOfIssue) + "\n";

        var drugs = Stream.of(body.getDrugs())
                .map(drug -> "\t" + drug.getName() + " - " + drug.getTimesPerDay() + " times per day")
                .toList();
        var consume = "Consume:\n" + String.join("\n", drugs);

        var details = patientInfo + dateInfo + consume;
        var prescription = Prescription.builder()
                .dateOfIssue(dateOfIssue)
                .patientId(body.getPatientId())
                .prescriptionDetails(details.getBytes(StandardCharsets.UTF_8))
                .build();

        var data = prescriptionRepository.save(prescription);
        return PrescriptionMapper.INSTANCE.prescriptionToPrescriptionDto(data);
    }

    public String getLatestPrescriptionByPatientId(Long patientId) {
        var data = prescriptionRepository
                .findByPatientIdOrderByIdDesc(patientId)
                .stream()
                .findFirst()
                .map(Prescription::getPrescriptionDetails)
                .orElseThrow(() -> new BadRequestException("No prescriptions found"));

        return PrescriptionMapper.INSTANCE.bytesToString(data);
    }

    public String getPrescriptionById(Long id) {
        var data = prescriptionRepository
                .findById(id)
                .stream()
                .findFirst()
                .map(Prescription::getPrescriptionDetails)
                .orElseThrow(() -> new BadRequestException("No prescriptions found"));

        return PrescriptionMapper.INSTANCE.bytesToString(data);
    }

    public void deletePrescription(Long id) {
        prescriptionRepository.deleteById(id);
    }
}
