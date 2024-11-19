package ukma.edu.ua.AuthService.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ukma.edu.ua.AuthService.entities.Doctor;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {
}