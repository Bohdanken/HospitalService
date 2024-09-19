package ukma.edu.ua.HospitalApp.patient;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import ukma.edu.ua.HospitalApp.entities.PatientDetails;
import ukma.edu.ua.HospitalApp.patient.repositories.PatientDetailsRepository;

@RequiredArgsConstructor
@Service
public class PatientService {
	private final PatientDetailsRepository patientDetailsRepository;

	public PatientDetails getPatientData(long id) {
		return this.patientDetailsRepository.findById(id).orElse(null);
	}

	public PatientDetails savePatientData(PatientDetails data) {
		return this.patientDetailsRepository.save(data);
	}
}
