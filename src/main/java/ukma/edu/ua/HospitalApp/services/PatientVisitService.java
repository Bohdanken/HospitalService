package ukma.edu.ua.HospitalApp.services;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import ukma.edu.ua.HospitalApp.api.visit.dto.UpdateVisitBody;
import ukma.edu.ua.HospitalApp.api.visit.dto.VisitBody;
import ukma.edu.ua.HospitalApp.dto.VisitDTO;
import ukma.edu.ua.HospitalApp.exceptions.errors.NotFoundException;
import ukma.edu.ua.HospitalApp.mappers.PatientVisitMapper;
import ukma.edu.ua.HospitalApp.models.DoctorDetails;
import ukma.edu.ua.HospitalApp.models.PatientDetails;
import ukma.edu.ua.HospitalApp.models.PatientVisit;
import ukma.edu.ua.HospitalApp.repositories.DoctorDetailsRepository;
import ukma.edu.ua.HospitalApp.repositories.PatientDetailsRepository;
import ukma.edu.ua.HospitalApp.repositories.PatientVisitRepository;

@Service
@RequiredArgsConstructor
public class PatientVisitService {

    private final PatientVisitRepository patientVisitRepository;
    private final PatientDetailsRepository patientDetailsRepository;
    private final DoctorDetailsRepository doctorDetailsRepository;
//    private final CacheManager cacheManager;

    public VisitDTO createPatientVisit(VisitBody data) {
//        var patientVisit = PatientVisit.builder()
//                .patientDetailsId(data.getPatientId())
//                .doctorDetailsId(data.getDoctorId())
//                .dateOfVisit(data.getDateOfVisit())
//                .build();

        PatientDetails patient = patientDetailsRepository.findById(data.getPatientId())
                .orElseThrow(() -> new RuntimeException("Patient not found"));
        DoctorDetails doctor = doctorDetailsRepository.findById(data.getDoctorId())
                .orElseThrow(() -> new RuntimeException("Doctor not found"));

        var patientVisit = PatientVisit.builder()
                .patientDetails(patient)
                .doctorDetails(doctor)
                .dateOfVisit(data.getDateOfVisit())
                .build();

        patientVisitRepository.save(patientVisit);

        return toVisitDTO(patientVisit);
    }

//    @Cacheable(value = "PatientVisitsByPatient", key = "#patientId")
    public VisitDTO[] getPatientVisitsByPatient(Long patientId) {
        var visits = patientVisitRepository.findByPatientDetailsId(patientId);
        return visits.stream().map(this::toVisitDTO).toArray(VisitDTO[]::new);
    }

//    @Cacheable(value = "PatientVisitsByDoctor", key = "#doctorId")
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

//        var cache_by_patient = cacheManager.getCache("PatientVisitsByPatient");
//        var cache_by_doctor = cacheManager.getCache("PatientVisitsByDoctor");
//
//        if (cache_by_patient != null)
//            cache_by_patient.evict(data.getPatientDetailsId());
//
//        if (cache_by_doctor != null)
//            cache_by_doctor.evict(data.getPatientDetailsId());

        patientVisitRepository.delete(data);
    }

    public VisitDTO toVisitDTO(PatientVisit patientVisit) {
        return PatientVisitMapper.INSTANCE.patientVisitToVisitDTO(patientVisit);
    }
}
