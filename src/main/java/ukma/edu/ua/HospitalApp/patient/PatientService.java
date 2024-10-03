package ukma.edu.ua.HospitalApp.patient;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import ukma.edu.ua.HospitalApp.common.entities.Patient;
import ukma.edu.ua.HospitalApp.patient.services.PatientServiceInternal;

@RequiredArgsConstructor
@Service
public class PatientService {
	private final PatientServiceInternal patientServiceInternal;

	public Patient getPatientData(long id) {
		return this.patientServiceInternal.getPatientData(id);
	}

	public Patient savePatientData(Patient data) {
		return this.patientServiceInternal.savePatientData(data);
	}
}
