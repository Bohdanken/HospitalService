package ukma.edu.ua.HospitalApp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ukma.edu.ua.HospitalApp.models.Prescription;
@Repository
public interface PrescriptionRepository extends JpaRepository<Prescription, Long> {
}