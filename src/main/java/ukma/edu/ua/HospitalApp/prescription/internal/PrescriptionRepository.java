package ukma.edu.ua.HospitalApp.prescription.internal;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrescriptionRepository extends JpaRepository<Prescription, Long> {
  List<Prescription> findByPatientDetailsId(Long patientDetailsId);
}