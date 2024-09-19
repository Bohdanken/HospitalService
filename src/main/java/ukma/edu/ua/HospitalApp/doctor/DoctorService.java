package ukma.edu.ua.HospitalApp.doctor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ukma.edu.ua.HospitalApp.doctor.internal.DoctorDetails;
import ukma.edu.ua.HospitalApp.doctor.internal.DoctorMapper;
import ukma.edu.ua.HospitalApp.doctor.internal.DoctorDetailsRepository;
import ukma.edu.ua.HospitalApp.visit.PatientVisitService;
import ukma.edu.ua.HospitalApp.visit.VisitDTO;
import ukma.edu.ua.HospitalApp.visit.internal.VisitBody;

@Service
public class DoctorService {

    private final DoctorDetailsRepository doctorDetailsRepository;
    private final DoctorMapper doctorMapper;
    private final PatientVisitService patientVisitService;

    @Autowired
    public DoctorService(DoctorDetailsRepository doctorDetailsRepository, DoctorMapper doctorMapper, PatientVisitService patientVisitService) {
        this.doctorDetailsRepository = doctorDetailsRepository;
        this.doctorMapper = doctorMapper;
        this.patientVisitService = patientVisitService;
    }

    public VisitDTO createAppointmentForDoctor(Long doctorId, VisitBody visitBody) {
        DoctorDetails doctorDetails = doctorDetailsRepository.findById(doctorId)
                .orElseThrow(() -> new IllegalArgumentException("Doctor not found"));
        DoctorDTO doctorDTO = doctorMapper.doctorToDoctorDTO(doctorDetails);
        boolean isAvailable = patientVisitService.isDoctorAvailable(doctorDTO, visitBody.getDateOfVisit(), visitBody.getDateOfVisit());
        if (!isAvailable) {
            throw new IllegalArgumentException("Doctor is not available for this time slot");
        }
        return patientVisitService.createPatientVisit(visitBody);
    }

    public DoctorDTO updateDoctor(Long doctorId, DoctorDTO doctorDTO) {
        DoctorDetails existingDoctor = doctorDetailsRepository.findById(doctorId)
                .orElseThrow(() -> new IllegalArgumentException("Doctor not found"));
        DoctorDetails updatedDoctor = doctorMapper.doctorDTOToDoctor(doctorDTO);
        updatedDoctor.setId(existingDoctor.getId());
        updatedDoctor.setUser(existingDoctor.getUser());
        DoctorDetails savedDoctor = doctorDetailsRepository.save(updatedDoctor);
        return doctorMapper.doctorToDoctorDTO(savedDoctor);
    }
}
