package ukma.edu.ua.AuthService.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ukma.edu.ua.AuthService.entities.Patient;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
}