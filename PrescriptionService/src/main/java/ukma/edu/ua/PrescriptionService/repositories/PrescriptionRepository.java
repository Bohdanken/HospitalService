package ukma.edu.ua.PrescriptionService.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ukma.edu.ua.PrescriptionService.model.Prescription;

import java.util.List;
import java.util.Optional;

@Repository
public interface PrescriptionRepository extends JpaRepository<Prescription, Long> {
  Optional<Prescription> findByIdAndPatientId(Long id, Long patientId);

  void deleteByIdAndPatientId(Long id, Long patientId);

  List<Prescription> findByPatientIdOrderByIdDesc(Long patientId);

  List<Prescription> findAllByPatientId(Long patientId);
}
