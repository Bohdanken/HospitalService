package ukma.edu.ua.HospitalApp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ukma.edu.ua.HospitalApp.models.Hospital;

public interface HospitalRepository extends JpaRepository<Hospital, Long> {
}