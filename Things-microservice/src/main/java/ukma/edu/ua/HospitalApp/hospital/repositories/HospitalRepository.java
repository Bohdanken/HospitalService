package ukma.edu.ua.HospitalApp.hospital.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ukma.edu.ua.HospitalApp.entities.src.main.java.internal.Hospital;

@Repository
public interface HospitalRepository extends JpaRepository<Hospital, Long> {
}