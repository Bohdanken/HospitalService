package ukma.edu.ua.HospitalApp.visit.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import ukma.edu.ua.HospitalApp.doctor.DoctorService;
import ukma.edu.ua.HospitalApp.entities.DoctorDetails;
import ukma.edu.ua.HospitalApp.entities.PatientDetails;
import ukma.edu.ua.HospitalApp.entities.PatientVisit;
import ukma.edu.ua.HospitalApp.exceptions.NotFoundException;
import ukma.edu.ua.HospitalApp.patient.PatientService;
import ukma.edu.ua.HospitalApp.visit.dto.UpdateVisitBody;
import ukma.edu.ua.HospitalApp.visit.dto.VisitBody;
import ukma.edu.ua.HospitalApp.visit.dto.VisitDTO;
import ukma.edu.ua.HospitalApp.visit.mappers.PatientVisitMapper;
import ukma.edu.ua.HospitalApp.visit.repositories.PatientVisitRepository;

@Service
@RequiredArgsConstructor
public class PatientVisitService {

    private final PatientVisitRepository patientVisitRepository;
    private final PatientService patientService;
    private final DoctorService doctorService;

    public VisitDTO createPatientVisit(VisitBody data) {
        PatientDetails patient = patientService.getPatientData(data.getPatientId());
        if (patient == null) {
            throw new RuntimeException("Patient not found");
        }
        DoctorDetails doctor = this.doctorService.getDoctorById(data.getDoctorId());
        if (doctor == null) {
            throw new RuntimeException("Doctor not found");
        }

        var patientVisit = PatientVisit.builder()
                .patientDetails(patient)
                .doctorDetails(doctor)
                .dateOfVisit(data.getDateOfVisit())
                .build();

        patientVisitRepository.save(patientVisit);

        return toVisitDTO(patientVisit);
    }

    // @Cacheable(value = "PatientVisitsByPatient", key = "#patientId")
    public VisitDTO[] getPatientVisitsByPatient(Long patientId) {
        var visits = patientVisitRepository.findByPatientDetailsId(patientId);
        return visits.stream().map(this::toVisitDTO).toArray(VisitDTO[]::new);
    }

    // @Cacheable(value = "PatientVisitsByDoctor", key = "#doctorId")
    public VisitDTO[] getPatientVisitsByDoctor(Long doctorId) {
        var visits = patientVisitRepository.findByDoctorDetailsId(doctorId);
        return visits.stream().map(this::toVisitDTO).toArray(VisitDTO[]::new);
    }

    public VisitDTO[] getVisits() {
        var visits = patientVisitRepository.findAll();
        return visits.stream().map(this::toVisitDTO).toArray(VisitDTO[]::new);
    }

    public VisitDTO getVisit(Long visitId) {
        var visit = patientVisitRepository.findById(visitId)
                .orElseThrow(() -> new NotFoundException("Visit not found"));

        return toVisitDTO(visit);
    }

    public VisitDTO updateVisit(UpdateVisitBody data, Long id) {
        var visit = patientVisitRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Visit not found"));

        visit.setDateOfVisit(data.getDateOfVisit());

        patientVisitRepository.save(visit);

        return toVisitDTO(visit);
    }

    public void deletePatientVisit(long id) {
        var data = patientVisitRepository.findById(id).orElse(null);

        if (data == null)
            return;

        // var cache_by_patient = cacheManager.getCache("PatientVisitsByPatient");
        // var cache_by_doctor = cacheManager.getCache("PatientVisitsByDoctor");
        //
        // if (cache_by_patient != null)
        // cache_by_patient.evict(data.getPatientDetailsId());
        //
        // if (cache_by_doctor != null)
        // cache_by_doctor.evict(data.getPatientDetailsId());

        patientVisitRepository.delete(data);
    }

    public VisitDTO toVisitDTO(PatientVisit patientVisit) {
        return PatientVisitMapper.INSTANCE.patientVisitToVisitDTO(patientVisit);
    }
}
