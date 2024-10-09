package ukma.edu.ua.HospitalApp.prescription;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import ukma.edu.ua.HospitalApp.prescription.dto.PrescriptionResponse;
import ukma.edu.ua.HospitalApp.prescription.services.PrescriptionServiceInternal;

@Service
@RequiredArgsConstructor
public class PrescriptionService {
	private final PrescriptionServiceInternal prescriptionServiceInternal;

	public Flux<ResponseEntity<List<PrescriptionResponse>>> getPatientPrescriptions(long patientId) {
		return prescriptionServiceInternal.getPatientPrescriptions(patientId);
	}
}
