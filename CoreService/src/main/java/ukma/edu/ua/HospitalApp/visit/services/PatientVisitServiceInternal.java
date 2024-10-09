package ukma.edu.ua.HospitalApp.visit.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import ukma.edu.ua.HospitalApp.doctor.DoctorService;
import ukma.edu.ua.HospitalApp.common.dto.VisitDTO;
import ukma.edu.ua.HospitalApp.common.entities.Doctor;
import ukma.edu.ua.HospitalApp.common.entities.Patient;
import ukma.edu.ua.HospitalApp.common.entities.PatientVisit;
import ukma.edu.ua.HospitalApp.common.exceptions.NotFoundException;
import ukma.edu.ua.HospitalApp.patient.PatientService;
import ukma.edu.ua.HospitalApp.visit.dto.UpdateVisitBody;
import ukma.edu.ua.HospitalApp.visit.dto.VisitBody;
import ukma.edu.ua.HospitalApp.visit.mappers.PatientVisitMapper;
import ukma.edu.ua.HospitalApp.visit.repositories.PatientVisitRepository;

import java.sql.Timestamp;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PatientVisitServiceInternal {
    private final PatientVisitRepository patientVisitRepository;
    private final PatientService patientService;
    private final DoctorService doctorService;

    public boolean isDoctorAvailable(Long doctorId, Timestamp startTime, Timestamp endTime) {
        List<PatientVisit> appointments = patientVisitRepository
                .findAppointmentsForDoctorInRange(doctorId, startTime, endTime);
        return appointments.isEmpty();
    }

    public VisitDTO createPatientVisit(VisitBody data) {
        Patient patient = patientService.getPatientData(data.getPatientId());
        Doctor doctor = doctorService.getDoctorById(data.getDoctorId());

        var patientVisit = PatientVisit.builder()
                .patientDetails(patient)
                .doctorDetails(doctor)
                .dateOfVisit(data.getDateOfVisit())
                .build();

        patientVisitRepository.save(patientVisit);

        return toVisitDTO(patientVisit);
    }

    public VisitDTO createAppointmentForDoctor(Long doctorId, VisitBody visitBody) {
        boolean isAvailable = isDoctorAvailable(doctorId, visitBody.getDateOfVisit(), visitBody.getDateOfVisit());
        if (!isAvailable) {
            throw new IllegalArgumentException("Doctor is not available for this time slot");
        }

        return createPatientVisit(visitBody);
    }

    public VisitDTO[] getPatientVisitsByPatient(Long patientId) {
        var visits = patientVisitRepository.findByPatientDetailsId(patientId);
        return visits.stream().map(this::toVisitDTO).toArray(VisitDTO[]::new);
    }

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

        patientVisitRepository.delete(data);
    }

    public VisitDTO toVisitDTO(PatientVisit patientVisit) {
        return PatientVisitMapper.INSTANCE.patientVisitToVisitDTO(patientVisit);
    }
}
