package ukma.edu.ua.HospitalApp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ukma.edu.ua.HospitalApp.models.Prescription;

public interface PrescriptionRepository extends JpaRepository<Prescription, Long> {
}