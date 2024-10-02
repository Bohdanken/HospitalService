package ukma.edu.ua.HospitalApp.user.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ukma.edu.ua.HospitalApp.common.entities.Admin;

@Repository
public interface AdminDetailsRepository extends JpaRepository<Admin, Long> {
}