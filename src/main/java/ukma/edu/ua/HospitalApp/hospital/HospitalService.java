package ukma.edu.ua.HospitalApp.hospital;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import ukma.edu.ua.HospitalApp.entities.Hospital;
import ukma.edu.ua.HospitalApp.hospital.repositories.HospitalRepository;

@Service
@RequiredArgsConstructor
public class HospitalService {
	private final HospitalRepository hospitalRepository;

	public List<Hospital> getAllHospitals() {
		return hospitalRepository.findAll();
	}
}
