package ukma.edu.ua.HospitalApp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ukma.edu.ua.HospitalApp.models.Admin;

public interface AdminRepository extends JpaRepository<Admin, Long> {
}