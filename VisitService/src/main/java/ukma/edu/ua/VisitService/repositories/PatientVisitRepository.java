package ukma.edu.ua.VisitService.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ukma.edu.ua.VisitService.entities.PatientVisit;

import java.util.List;
import java.util.Optional;

@Repository
public interface PatientVisitRepository extends JpaRepository<PatientVisit, Long> {
    List<PatientVisit> findByPatientId(Long patientId);

    Optional<PatientVisit> findByIdAndPatientId(Long id, Long patientId);
}
