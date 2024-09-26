package ukma.edu.ua.HospitalApp.doctor;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ukma.edu.ua.HospitalApp.doctor.mappers.DoctorMapper;
import ukma.edu.ua.HospitalApp.doctor.repositories.DoctorDetailsRepository;
import ukma.edu.ua.HospitalApp.entities.DoctorDTO;
import ukma.edu.ua.HospitalApp.entities.internal.DoctorDetails;
import ukma.edu.ua.HospitalApp.visit.PatientVisitService;
import ukma.edu.ua.HospitalApp.visit.dto.VisitBody;
import ukma.edu.ua.HospitalApp.entities.VisitDTO;



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
