package ukma.edu.ua.HospitalApp.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminDetailsRepository extends JpaRepository<AdminDetails, Long> {
}