package ukma.edu.ua.HospitalApp.prescription.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ukma.edu.ua.HospitalApp.common.entities.Patient;
import ukma.edu.ua.HospitalApp.common.entities.Prescription;

@Repository
public interface PrescriptionRepository extends JpaRepository<Prescription, Long> {
  List<Prescription> findByPatient(Patient patient);
}