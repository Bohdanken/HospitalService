package ukma.edu.ua.HospitalApp.doctor;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ukma.edu.ua.HospitalApp.doctor.mappers.DoctorMapper;
import ukma.edu.ua.HospitalApp.doctor.repositories.DoctorDetailsRepository;
import ukma.edu.ua.HospitalApp.entities.src.main.java.dto.DoctorDTO;
import ukma.edu.ua.HospitalApp.entities.src.main.java.internal.DoctorDetails;

@Service
@RequiredArgsConstructor
public class DoctorService {
    private final DoctorDetailsRepository doctorDetailsRepository;
    private final DoctorMapper doctorMapper;

    public DoctorDTO updateDoctor(Long doctorId, DoctorDTO doctorDTO) {
        DoctorDetails existingDoctor = doctorDetailsRepository.findById(doctorId)
                .orElseThrow(() -> new IllegalArgumentException("Doctor not found"));
        DoctorDetails updatedDoctor = doctorMapper.doctorDTOToDoctor(doctorDTO);
        updatedDoctor.setId(existingDoctor.getId());
        updatedDoctor.setUser(existingDoctor.getUser());
        DoctorDetails savedDoctor = doctorDetailsRepository.save(updatedDoctor);
        return doctorMapper.doctorToDoctorDTO(savedDoctor);
    }

    public DoctorDetails getDoctorById(long id) {
        return doctorDetailsRepository.findById(id).orElse(null);
    }

    public DoctorDetails saveDoctorData(DoctorDetails data) {
        return doctorDetailsRepository.save(data);
    }
}
