package ukma.edu.ua.HospitalApp.Database;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ukma.edu.ua.HospitalApp.models.BaseEntity;
import ukma.edu.ua.HospitalApp.models.Hospital;

@Repository
public interface HospitalRepository extends JpaRepository<Hospital, Long> {
}