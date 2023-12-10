package ukma.edu.ua.HospitalApp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ukma.edu.ua.HospitalApp.models.AdminDetails;

@Repository
public interface AdminDetailsRepository extends JpaRepository<AdminDetails, Long> {
}