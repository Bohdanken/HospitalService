package ukma.edu.ua.HospitalApp.patient.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ukma.edu.ua.HospitalApp.entities.src.main.java.internal.PatientDetails;

@Repository
public interface PatientDetailsRepository extends JpaRepository<PatientDetails, Long> {
}