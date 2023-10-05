package ukma.edu.ua.HospitalApp.Database;

import org.springframework.data.jpa.repository.JpaRepository;
import ukma.edu.ua.HospitalApp.models.BaseEntity;
import ukma.edu.ua.HospitalApp.models.Patient;

public interface PatientRepository extends JpaRepository<Patient, Long> {
}