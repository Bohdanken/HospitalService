package ukma.edu.ua.HospitalApp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ukma.edu.ua.HospitalApp.models.Doctor;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {
}