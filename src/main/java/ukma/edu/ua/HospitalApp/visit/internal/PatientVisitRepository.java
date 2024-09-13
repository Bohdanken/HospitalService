package ukma.edu.ua.HospitalApp.visit.internal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PatientVisitRepository extends JpaRepository<PatientVisit, Long> {
    List<PatientVisit> findByPatientDetailsId(Long patientDetailsId);
    List<PatientVisit> findByDoctorDetailsId(Long doctorDetailsId);
}
