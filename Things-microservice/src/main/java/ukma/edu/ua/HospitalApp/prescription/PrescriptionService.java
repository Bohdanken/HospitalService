package ukma.edu.ua.HospitalApp.prescription;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import ukma.edu.ua.HospitalApp.entities.src.main.java.dto.PrescriptionDTO;
import ukma.edu.ua.HospitalApp.prescription.services.PrescriptionServiceInternal;

@Service
@RequiredArgsConstructor
public class PrescriptionService {
	private final PrescriptionServiceInternal prescriptionServiceInternal;

	public PrescriptionDTO[] getPatientPrescriptions(long patientId) {
		return prescriptionServiceInternal.getPatientPrescriptions(patientId);
	}
}
