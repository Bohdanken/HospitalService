package ukma.edu.ua.HospitalApp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ukma.edu.ua.HospitalApp.models.Patient;

public interface PatientRepository extends JpaRepository<Patient, Long> {
}