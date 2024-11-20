package ukma.edu.ua.PrescriptionService.service;

import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import org.springframework.http.HttpStatus;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import lombok.RequiredArgsConstructor;
import ukma.edu.ua.PrescriptionService.dto.CreatePrescriptionBody;
import ukma.edu.ua.PrescriptionService.dto.PrescriptionDTO;
import ukma.edu.ua.PrescriptionService.exceptions.BadRequestException;
import ukma.edu.ua.PrescriptionService.mappers.PrescriptionMapper;
import ukma.edu.ua.PrescriptionService.medicine.MedicineService;
import ukma.edu.ua.PrescriptionService.model.Prescription;
import ukma.edu.ua.PrescriptionService.repositories.PrescriptionRepository;
import ukma.edu.ua.PrescriptionService.security.User;

@Service
@RequiredArgsConstructor
public class PrescriptionService {
	private final PrescriptionRepository prescriptionRepository;
	private final UserService userService;
	private final JmsTemplate jmsTemplate;
	private final MedicineService medicineService;

	public String createPrescription(CreatePrescriptionBody body) {
		// Retrieve patient's data
		var patient = userService.getUserData(body.getPatientId());
		if (!patient.hasPatientDetails()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Patient not found");
		}

		// Retrieve drugs data
		var drugIds = body.getDrugs().stream().map(CreatePrescriptionBody.Drug::getId).toList();
		var drugs = medicineService.getDrugsByIds(drugIds);

		// Patient info
		var patientInfo = "Patient: " + patient.getPatientDetails().getFirstName() + " "
				+ patient.getPatientDetails().getLastName() + "\n";

		// Date info
		var format = new SimpleDateFormat("dd MMM yyyy");
		var dateOfIssue = new Date();
		var dateInfo = "Date: " + format.format(dateOfIssue) + "\n";

		// Drugs info
		var drugsToConsume = drugs
				.stream()
				.map(drug -> {
					var drugTime = body
							.getDrugs()
							.stream()
							.filter(d -> Objects.equals(d.getId(), drug.getId()))
							.map(CreatePrescriptionBody.Drug::getTimesPerDay)
							.findFirst().orElse(0);

					return "\t" + drug.getBrandName() + " - Consume " + drugTime + "times per day";
				})
				.toArray(String[]::new);
		var consume = "Consume:\n" + String.join("\n", drugsToConsume);

		var details = patientInfo + dateInfo + consume;
		var prescription = Prescription.builder()
				.dateOfIssue(dateOfIssue)
				.patientId(body.getPatientId())
				.prescriptionDetails(details.getBytes(StandardCharsets.UTF_8))
				.build();

		var data = prescriptionRepository.save(prescription);
		var prescriptionDetails = PrescriptionMapper.INSTANCE.bytesToString(data.getPrescriptionDetails());

		jmsTemplate.convertAndSend("prescription_created", prescriptionDetails);
		return prescriptionDetails;
	}

	public List<PrescriptionDTO> getAllPrecriptionsForPatient() {
		var user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return prescriptionRepository
				.findAllByPatientId(user.getId())
				.stream()
				.map(PrescriptionMapper.INSTANCE::prescriptionToPrescriptionDto)
				.toList();
	}

	public String getPrescriptionById(Long id) {
		var user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		var data = prescriptionRepository
				.findByIdAndPatientId(id, user.getId())
				.orElseThrow(() -> new BadRequestException("Prescriptions not found"));

		return PrescriptionMapper.INSTANCE.bytesToString(data.getPrescriptionDetails());
	}

	public void deletePrescription(Long id) {
		prescriptionRepository.deleteById(id);
	}
}
