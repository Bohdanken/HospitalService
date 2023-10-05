package ukma.edu.ua.HospitalApp.Database;

import org.springframework.data.jpa.repository.JpaRepository;
import ukma.edu.ua.HospitalApp.models.Admin;
import ukma.edu.ua.HospitalApp.models.BaseEntity;

public interface AdminRepository extends JpaRepository<Admin, Long> {
}