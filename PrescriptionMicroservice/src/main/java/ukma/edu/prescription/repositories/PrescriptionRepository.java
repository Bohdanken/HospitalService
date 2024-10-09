package ukma.edu.prescription.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ukma.edu.prescription.model.Prescription;

import java.util.List;

@Repository
public interface PrescriptionRepository extends JpaRepository<Prescription, Long> {

  List<Prescription> findByPatientId(Long patientId);
}
