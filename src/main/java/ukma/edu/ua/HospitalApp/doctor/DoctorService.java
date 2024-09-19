package ukma.edu.ua.HospitalApp.doctor;

import lombok.RequiredArgsConstructor;
import ukma.edu.ua.HospitalApp.doctor.repositories.DoctorDetailsRepository;
import ukma.edu.ua.HospitalApp.entities.DoctorDetails;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DoctorService {
	private final DoctorDetailsRepository doctorDetailsRepository;

	public DoctorDetails getDoctorById(long id) {
		return doctorDetailsRepository.findById(id).orElse(null);
	}

	public DoctorDetails saveDoctorData(DoctorDetails data) {
		return doctorDetailsRepository.save(data);
	}
}
